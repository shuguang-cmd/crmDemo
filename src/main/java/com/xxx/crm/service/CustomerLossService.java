package com.xxx.crm.service;

import com.xxx.base.BaseMapper;
import com.xxx.base.BaseService;
import com.xxx.crm.dao.CustomerLossMapper;
import com.xxx.crm.dao.CustomerMapper;
import com.xxx.crm.dao.CustomerOrderMapper;
import com.xxx.crm.utils.AssertUtil;
import com.xxx.crm.vo.Customer;
import com.xxx.crm.vo.CustomerLoss;
import com.xxx.crm.vo.CustomerOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerLossService extends BaseService<CustomerLoss, Integer> {

    @Resource
    private CustomerLossMapper customerLossMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    @Override
    public BaseMapper<CustomerLoss, Integer> getMapper() {
        return customerLossMapper;
    }

    /**
     * 更新客户流失状态 (自动化检测)
     * 逻辑：
     * 1. 查询所有符合流失条件的客户 (6个月未下单)
     * 2. 将这些客户批量插入到流失客户表 t_customer_loss 中
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerLossState() {
        // 1. 查询待流失客户
        List<Customer> lossCustomers = customerMapper.queryLossCustomers();
        
        // 2. 判断并处理
        if (null != lossCustomers && lossCustomers.size() > 0) {
            List<CustomerLoss> customerLossList = new ArrayList<>();
            
            for (Customer customer : lossCustomers) {
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusNo(customer.getKhno());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setIsValid(1);
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
                customerLoss.setState(0); // 默认为 0=暂缓流失
                
                // 查询最后一次下单时间
                CustomerOrder lastOrder = customerOrderMapper.queryLastOrderTimeByCusId(customer.getId());
                if (null != lastOrder) {
                    customerLoss.setLastOrderTime(lastOrder.getOrderDate());
                } else {
                    // 如果没订单，则最后下单时间按创建时间算 (或者留空)
                    customerLoss.setLastOrderTime(customer.getCreateDate());
                }
                
                customerLossList.add(customerLoss);
            }
            
            // 3. 批量插入流失记录
            if (customerLossList.size() > 0) {
                AssertUtil.isTrue(customerLossMapper.insertBatch(customerLossList) != customerLossList.size(), "客户流失数据转移失败");
            }
        }
    }

    /**
     * 确认流失
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void confirmLoss(Integer id, String lossReason) {
        // 1. 参数校验
        AssertUtil.isTrue(null == id, "待确认记录不存在");
        CustomerLoss temp = customerLossMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == temp, "待确认记录不存在");
        AssertUtil.isTrue(null == lossReason || lossReason.isEmpty(), "流失原因不能为空");
        
        // 2. 设置状态
        temp.setState(1); // 确认流失
        temp.setLossReason(lossReason);
        temp.setConfirmLossTime(new Date());
        temp.setUpdateDate(new Date());
        
        // 3. 执行更新
        AssertUtil.isTrue(customerLossMapper.updateByPrimaryKeySelective(temp) < 1, "确认流失失败");
    }
}

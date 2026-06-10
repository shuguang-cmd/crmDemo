-- 客户订单表
CREATE TABLE IF NOT EXISTS `t_customer_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cus_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单号',
  `order_date` datetime DEFAULT NULL COMMENT '下单日期',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `state` int(11) DEFAULT '0' COMMENT '状态 (0=未回款, 1=已回款)',
  `is_valid` int(11) DEFAULT '1' COMMENT '有效状态 (0=无效, 1=有效)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 客户流失表
CREATE TABLE IF NOT EXISTS `t_customer_loss` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cus_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `cus_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `cus_manager` varchar(100) DEFAULT NULL COMMENT '客户经理',
  `last_order_time` datetime DEFAULT NULL COMMENT '最后下单时间',
  `confirm_loss_time` datetime DEFAULT NULL COMMENT '确认流失时间',
  `state` int(11) DEFAULT '0' COMMENT '流失状态 (0=暂缓流失, 1=确认流失)',
  `loss_reason` varchar(1000) DEFAULT NULL COMMENT '流失原因',
  `is_valid` int(11) DEFAULT '1' COMMENT '有效状态 (0=无效, 1=有效)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 暂缓措施表
CREATE TABLE IF NOT EXISTS `t_customer_reprieve` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `loss_id` int(11) DEFAULT NULL COMMENT '流失ID',
  `measure` varchar(500) DEFAULT NULL COMMENT '暂缓措施',
  `is_valid` int(11) DEFAULT '1' COMMENT '有效状态 (0=无效, 1=有效)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

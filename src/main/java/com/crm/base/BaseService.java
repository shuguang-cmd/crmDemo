package com.crm.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DataAccessException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public abstract class BaseService<T, ID> {

    // 【关键改动】：定义抽象方法，子类必须实现它返回具体的 Mapper
    public abstract BaseMapper<T, ID> getMapper();

    public Integer insertSelective(T entity) throws DataAccessException {
        return getMapper().insertSelective(entity);
    }

    public ID insertHasKey(T entity) throws DataAccessException {
        getMapper().insertHasKey(entity);
        try {
            return (ID) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer insertBatch(List<T> entities) throws DataAccessException {
        return getMapper().insertBatch(entities);
    }

    public T selectByPrimaryKey(ID id) throws DataAccessException {
        return getMapper().selectByPrimaryKey(id);
    }

    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException {
        return getMapper().selectByParams(baseQuery);
    }

    public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException {
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    public Integer updateBatch(List<T> entities) throws DataAccessException {
        return getMapper().updateBatch(entities);
    }

    public Integer deleteByPrimaryKey(ID id) throws DataAccessException {
        return getMapper().deleteByPrimaryKey(id);
    }

    public Integer deleteBatch(ID[] ids) throws DataAccessException {
        return getMapper().deleteBatch(ids);
    }

    public Map<String, Object> queryByParamsForTable(BaseQuery baseQuery) {
        Map<String, Object> result = new HashMap<String, Object>();
        PageHelper.startPage(baseQuery.getPage(), baseQuery.getLimit());
        // 调用子类实现的 getMapper()
        PageInfo<T> pageInfo = new PageInfo<T>(getMapper().selectByParams(baseQuery));
        result.put("count", pageInfo.getTotal());
        result.put("data", pageInfo.getList());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }
}
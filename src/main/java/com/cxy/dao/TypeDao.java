package com.cxy.dao;

import com.cxy.bean.Type;
import com.cxy.bean.TypeDetail;

import java.util.List;

public interface TypeDao {
    Type getType(Integer childId);

    boolean updateType(Type type);

    boolean deleteType(Integer childId);

    List<TypeDetail> getTypes(Integer parentId);

    boolean updateSort(List<TypeDetail> types);
}

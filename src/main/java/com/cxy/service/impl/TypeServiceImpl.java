package com.cxy.service.impl;

import com.cxy.bean.Type;
import com.cxy.bean.TypeDetail;
import com.cxy.dao.TypeDao;
import com.cxy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Override
    public boolean sortData(Integer childId, List<TypeDetail> typeData) {
        Type type = typeDao.getType(childId);
        Integer parentId = type.getParentId();
        Integer nparentId = typeData.get(0).getParentId();
        for (int i = 0; i < typeData.size(); i++) {
            typeData.get(i).setSort(i + 1);
            if (typeData.get(i).getId().equals(childId)) {
                typeData.get(i).setParentId(nparentId);
            }
        }
        boolean b1 = typeDao.updateSort(typeData);
        if (!parentId.equals(nparentId)) {
            List<TypeDetail> types = typeDao.getTypes(parentId);
            for (int i = 0; i < types.size(); i++) {
                types.get(i).setSort(i + 1);
            }
            return typeDao.updateSort(types);
        }
        return b1;
    }
}

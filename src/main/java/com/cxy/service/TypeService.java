package com.cxy.service;


import com.cxy.bean.TypeDetail;

import java.util.List;

public interface TypeService {

    boolean sortData(Integer childId, List<TypeDetail> typeData);
}

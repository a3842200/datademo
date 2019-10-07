package com.cxy.dao;

import com.cxy.bean.Data;
import com.cxy.bean.DataDetail;

import java.util.List;

public interface DataDao {
    List<DataDetail> getData(String key);

    boolean addData(DataDetail dataDetail);

    Data getParentKey(Integer parentId);

    boolean deleteData(String key);

    List<String> getChild(String key);
}

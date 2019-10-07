package com.cxy.service;

import com.cxy.bean.DataDetail;
import com.cxy.common.ApiResponse;

import java.util.Map;

public interface DataService {
     Map<String, String> getData(String key);

     boolean addData(DataDetail dataDetail);

     ApiResponse deleteData(DataDetail dataDetail);

     boolean updateData(DataDetail dataDetail);
}

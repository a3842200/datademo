package com.cxy.web;

import com.cxy.bean.DataDetail;
import com.cxy.bean.TypeDetail;
import com.cxy.common.ApiResponse;
import com.cxy.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {
    @Autowired
    private TypeServiceImpl typeService;

    @RequestMapping(value = "sortData",method = RequestMethod.POST)
    public ApiResponse sortData(Integer childId,@RequestBody List<TypeDetail> typeData) {
       boolean isSuccess = typeService.sortData(childId,typeData);
        if (isSuccess) {
            return new ApiResponse(true, "", "", null);
        } else {
            return new ApiResponse(false, "", "", null);
        }
    }
}

package com.cxy.web;

import com.alibaba.fastjson.JSONObject;
import com.cxy.bean.System;
import com.cxy.common.ApiResponse;
import com.cxy.service.impl.SystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system")
public class SystemController extends BaseController {
    @Autowired
    private SystemServiceImpl systemService;

    @RequestMapping(value = "createSystem", method = RequestMethod.POST)
    public ApiResponse createSystem(@RequestBody System system) {
        boolean isSuccess = systemService.createSystem(system);
        if (isSuccess) {
            return new ApiResponse(true, "", "", null);
        } else {
            return new ApiResponse(false, "", "", null);
        }
    }

    @RequestMapping(value = "querySystems", method = RequestMethod.POST)
    public ApiResponse querySystems() {
        System system = systemService.querySystems();
        return new ApiResponse(true, "", "", JSONObject.toJSONString(system));
    }

    @RequestMapping(value = "deleteById",method = RequestMethod.POST)
    public ApiResponse deleteById(@RequestParam("systemId") String systemId) {
        boolean isSuccess = systemService.deleteById(systemId);
        if (isSuccess) {
            return new ApiResponse(true, "", "", null);
        } else {
            return new ApiResponse(false, "", "", null);
        }
    }

    @RequestMapping(value = "updateSystem",method = RequestMethod.POST)
    public ApiResponse updateSystem(@RequestBody System system) {
        boolean isSuccess = systemService.updateSystem(system);
        if (isSuccess) {
            return new ApiResponse(true, "", "", null);
        } else {
            return new ApiResponse(false, "", "", null);
        }
    }
}

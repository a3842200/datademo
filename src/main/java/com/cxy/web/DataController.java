package com.cxy.web;

import com.alibaba.fastjson.JSONObject;
import com.cxy.bean.DataDetail;
import com.cxy.common.ApiResponse;
import com.cxy.service.impl.DataServiceImpl;
import com.cxy.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("data")
public class DataController {

    @Autowired
    private DataServiceImpl dataService;

    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> map = new HashMap<>();
        jedis.hdel("demo1", "key2");
        System.out.println(jedis.hget("demo1","key4"));
        jedis.close();
    }

    @RequestMapping("initialization")
    public void initialization() {

    }

    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public ApiResponse getData(String key) {
        Jedis jedis = JedisUtil.getJedis();
        Set<String> keys = jedis.keys("*" + key + "*");
        if (keys.size() > 0) {
            Map<String, Map> map1 = new HashMap<>();
            for (String keyD : keys) {
                Map<String, String> key1 = jedis.hgetAll(keyD);
                if (key1 == null || key1.size() == 0) {
                    Map<String, String> map = dataService.getData(key);
                    if (map != null && map.size() != 0) {
                        jedis.hmset(key, map);
                    }
                    map1.put(keyD, map);
                } else {
                    map1.put(keyD, key1);
                }
            }
            return new ApiResponse(true,"","", JSONObject.toJSONString(map1));
        }
        jedis.close();
        return new ApiResponse(true, "", "", null);
    }

    @RequestMapping(value = "addData", method = RequestMethod.POST)
    public ApiResponse addData(@RequestBody DataDetail dataDetail) {
        boolean isSuccess = dataService.addData(dataDetail);
        if (isSuccess) {
            return new ApiResponse(true, "", "", null);
        } else {
            return new ApiResponse(false, "", "", null);
        }
    }

    @RequestMapping(value = "deleteData", method = RequestMethod.POST)
    public ApiResponse deleteData(@RequestBody DataDetail dataDetail) {
        return dataService.deleteData(dataDetail);
    }

    @RequestMapping(value = "updateData",method = RequestMethod.POST)
    public ApiResponse updateData(@RequestBody DataDetail dataDetail) {
        boolean isSuccess = dataService.updateData(dataDetail);
        if (isSuccess) {
            return new ApiResponse(true, "", "", null);
        } else {
            return new ApiResponse(false, "", "", null);
        }
    }

}

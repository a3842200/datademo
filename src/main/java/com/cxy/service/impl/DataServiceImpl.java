package com.cxy.service.impl;

import com.cxy.bean.Data;
import com.cxy.bean.DataDetail;
import com.cxy.common.ApiResponse;
import com.cxy.dao.DataDao;
import com.cxy.service.DataService;
import com.cxy.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService{
    @Autowired
    private DataDao dataDao;

    @Override
    public Map<String, String> getData(String key) {
        List<DataDetail> data = dataDao.getData(key);
        Map<String, String> map = new HashMap<>();
        if (data.size() > 0) {
            for (DataDetail datum : data) {
                map.put(datum.getKey(),datum.getValue());
            }
        }
        return map;
    }

    @Override
    public boolean addData(DataDetail dataDetail) {
        boolean b = dataDao.addData(dataDetail);
        if (b) {
            StringBuffer sb = new StringBuffer();
            Data parent = dataDao.getParentKey(dataDetail.getParentId());
            sb.append(parent.getKey());
            while (true) {
                parent = dataDao.getParentKey(parent.getParentId());

                if (parent.getParentId() == null) {
                    break;
                }
            }
            Jedis jedis = JedisUtil.getJedis();
            jedis.hset(sb.reverse().toString(), dataDetail.getKey(), dataDetail.getValue());
            jedis.hmset(sb.append("-").append(dataDetail.getKey()).reverse().toString(), new HashMap<>());
            jedis.close();
        }
        return b;
    }

    @Override
    public ApiResponse deleteData(DataDetail dataDetail) {
        List<String> childs = dataDao.getChild(dataDetail.getKey());
        if (childs.size() == 0) {
            boolean b = dataDao.deleteData(dataDetail.getKey());
            if (b) {
                StringBuffer sb = new StringBuffer();
                Data parent = dataDao.getParentKey(dataDetail.getParentId());
                sb.append(parent.getKey());
                while (true) {
                    parent = dataDao.getParentKey(parent.getParentId());
                    sb.append("-").append(parent.getKey());
                    if (parent.getParentId() == null) {
                        break;
                    }
                }
                Jedis jedis = JedisUtil.getJedis();
                jedis.hdel(sb.reverse().toString(), dataDetail.getKey(), dataDetail.getValue());
                jedis.del(sb.append("-").append(dataDetail.getKey()).reverse().toString());
                jedis.close();
                return new ApiResponse(true, "", "", null);
            }
            return new ApiResponse(false, "", "失败", null);
        } else {
            return new ApiResponse(false, "", "有子词条，无法删除", null);
        }
    }

    @Override
    public boolean updateData(DataDetail dataDetail) {
        boolean b = dataDao.addData(dataDetail);
        if (b) {
            StringBuffer sb = new StringBuffer();
            Data parent = dataDao.getParentKey(dataDetail.getParentId());
            sb.append(parent.getKey());
            while (true) {
                parent = dataDao.getParentKey(parent.getParentId());

                if (parent.getParentId() == null) {
                    break;
                }
            }
            Jedis jedis = JedisUtil.getJedis();
            jedis.hset(sb.reverse().toString(), dataDetail.getKey(), dataDetail.getValue());
            jedis.close();
        }
        return b;
    }
}

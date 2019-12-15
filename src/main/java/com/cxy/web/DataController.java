package com.cxy.web;

import com.alibaba.fastjson.JSONObject;
import com.cxy.bean.DataDetail;
import com.cxy.common.ApiResponse;
import com.cxy.service.impl.DataServiceImpl;
import com.cxy.utils.Demo;
import com.cxy.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("data")
@Slf4j
public class DataController {

    @Autowired
    private DataServiceImpl dataService;

    @Autowired
    private HttpServletRequest request;

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


    @RequestMapping(value = "test", method = RequestMethod.POST)
    public ApiResponse test(@RequestParam("file") MultipartFile file) {
        String realPath = "\\ppt_to_pdf";
        //将文件缓冲到本地
        boolean localFile = createLocalFile(realPath, file);
        Demo.ppttopdf(realPath+"/"+file.getOriginalFilename());
        return null;
    }


    public boolean createLocalFile(String filePath,MultipartFile file) {
        File localFile = new File(filePath);
        //先创建目录
        localFile.mkdirs();

        String originalFilename = file.getOriginalFilename();
        String path = filePath+"/"+originalFilename;


        localFile = new File(path);
        FileOutputStream fos = null;
        InputStream in = null;
        try {

            if(localFile.exists()){
                //如果文件存在删除文件
                boolean delete = localFile.delete();
                if (delete == false){
                    log.error("Delete exist file \"{}\" failed!!!",path,new Exception("Delete exist file \""+path+"\" failed!!!"));
                }
            }
            //创建文件
            if(!localFile.exists()){
                //如果文件不存在，则创建新的文件
                localFile.createNewFile();
                log.info("Create file successfully,the file is {}",path);
            }

            //创建文件成功后，写入内容到文件里
            fos = new FileOutputStream(localFile);
            in = file.getInputStream();
            byte[] bytes = new byte[1024];

            int len = -1;

            while((len = in.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }

            fos.flush();
            log.info("Reading uploaded file and buffering to local successfully!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(fos != null) {
                    fos.close();
                }
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                return false;
            }
        }

        return true;
    }
}


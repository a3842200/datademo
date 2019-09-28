package com.cxy.service.impl;

import com.cxy.bean.System;
import com.cxy.dao.SystemDao;
import com.cxy.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService{
    @Autowired
    private SystemDao systemDao;

    @Override
    public boolean createSystem(System system) {
        return systemDao.createSystem(system);
    }

    @Override
    public System querySystems() {
        return systemDao.querySystems();
    }

    @Override
    public boolean deleteById(String systemId) {
        return systemDao.deleteById(systemId);
    }

    @Override
    public boolean updateSystem(System system) {
        return systemDao.updateSystem(system);
    }

}

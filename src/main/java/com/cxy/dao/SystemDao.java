package com.cxy.dao;


import com.cxy.bean.System;

public interface SystemDao {
    boolean createSystem(System system);

    System querySystems();

    boolean deleteById(String systemId);

    boolean updateSystem(System system);
}

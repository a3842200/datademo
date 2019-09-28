package com.cxy.service;

import com.cxy.bean.System;

public interface SystemService {

    boolean createSystem(System system);

    System querySystems();

    boolean deleteById(String systemId);

    boolean updateSystem(System system);
}

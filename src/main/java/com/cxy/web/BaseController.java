package com.cxy.web;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {
    private HttpServletRequest request;
    private HttpServletResponse response;
}

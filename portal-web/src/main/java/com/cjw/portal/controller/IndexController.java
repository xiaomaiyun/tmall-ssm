package com.cjw.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author codeAC
 * @Date: 2018/7/9
 * @Time: 9:24
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }
}

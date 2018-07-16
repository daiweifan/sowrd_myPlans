package com.daiwei.project.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daiwei.utils.Result;

/**
 * 登录
 * @author david
 *
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController {

    /**
     * 登录
     */
    public Result login(String mobile, String password){


        return Result.ok();
    }

}

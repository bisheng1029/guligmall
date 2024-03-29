package com.atguigu.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
public class OrderController {

    @Reference
    private UserInfoService userInfoService;

    @RequestMapping("trade")
    public List<UserAddress> findAddress(String id){
        List<UserAddress> list = userInfoService.findUserAddressByUserId(id);

        return list;
    }

}

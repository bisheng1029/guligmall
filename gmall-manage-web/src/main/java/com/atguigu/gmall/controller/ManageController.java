package com.atguigu.gmall.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class ManageController {

    @Reference
    private ManageService manageService;


    @RequestMapping("getCatalog1")
    //@ResponseBody
    public List<BaseCatalog1> getCatalog1(){
        return manageService.getCatalog1();
    }

    @RequestMapping("getCatalog2")
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);
    }

    @RequestMapping("getCatalog3")
    public List<BaseCatalog3> getCatalog3(String catalog2Id){
        return manageService.getCatalog3(catalog2Id);
    }

    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){
        return manageService.getAttrList(catalog3Id);
    }

    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
        return "OK";
    }

    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue>  getAttrValueList(String attrId){
        // attrId = baseAttrInfo.id 平台属性Id
        // 根据平台属性Id 查询是否有平台属性对象
        BaseAttrInfo baseAttrInfo =  manageService.getBaseAttrInfo(attrId);
        // 返回该对象下的平台属性值集合
        return baseAttrInfo.getAttrValueList();
    }



}

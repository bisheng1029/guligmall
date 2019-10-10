package com.atguigu.gmall.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {


    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    BaseCatalog3Mapper baseCatalog3Mapper;

    @Override
    public List<BaseCatalog1> getCatalog1() {
        List<BaseCatalog1> baseCatalog1List = baseCatalog1Mapper.selectAll();
        return baseCatalog1List;
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2=new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);

        List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2List;
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3=new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);

        List<BaseCatalog3> baseCatalog3List = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3List;
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3_id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3_id);

        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoMapper.select(baseAttrInfo);
        return baseAttrInfoList;

    }

    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        if (baseAttrInfo.getId()!=null &&baseAttrInfo.getId().length()>0){
            // baseAttrInfo
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else {
            // baseAttrInfo
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }
        // 先删除
        // baseAttrValue 【如果是修改的话，可以将baseAttrValue 中的数据先删除在新增】
        // delete from baseAttrValue where attrId = baseAttrInfo.id
        BaseAttrValue baseAttrValueDel = new BaseAttrValue();
        baseAttrValueDel.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValueDel);
        // 再新增
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList!=null && attrValueList.size()>0){
            // 循环遍历
            for (BaseAttrValue baseAttrValue : attrValueList) {
                // baseAttrValue.attrId = baseAttrInfo.getId();
                // attrId 赋值
                baseAttrValue.setAttrId(baseAttrInfo.getId()); // baseAttrInfo.getId() 要想获取到Id ，则在baseAttrInfo 实体类中必须添加获取主键自增的注解
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }

    }

    @Override
    public BaseAttrInfo getBaseAttrInfo(String attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        List<BaseAttrValue> list = baseAttrValueMapper.select(baseAttrValue);
        baseAttrInfo.setAttrValueList(list);
        return baseAttrInfo;
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return null;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {

    }


}

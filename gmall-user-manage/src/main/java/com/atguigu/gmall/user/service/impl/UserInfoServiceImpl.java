package com.atguigu.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.UserInfoService;
import com.atguigu.gmall.user.mapper.UserAddressMapper;
import com.atguigu.gmall.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        return userInfos;
    }

    @Override
    public List<UserInfo> findByUserInfo(UserInfo userInfo) {
        return null;
    }

    @Override
    public List<UserInfo> findByLoginName(String loginName) {
        return null;
    }

    @Override
    public void addUser(UserInfo userInfo) {

    }

    @Override
    public void updUser(UserInfo userInfo) {

    }

    @Override
    public void delUser(UserInfo userInfo) {

    }

    @Override
    public List<UserAddress> findUserAddressByUserId(String userId) {
        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("userId",userId);
        List<UserAddress> list = userAddressMapper.selectByExample(example);
        return list;
    }
}

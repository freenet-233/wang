package com.wang.mybatisplus.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.mybatisplus.generator.domain.UserInfo;
import com.wang.mybatisplus.generator.service.UserInfoService;
import com.wang.mybatisplus.generator.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author wangguangpeng
* @description 针对表【user_info】的数据库操作Service实现
* @createDate 2022-05-20 23:05:37
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService{

}





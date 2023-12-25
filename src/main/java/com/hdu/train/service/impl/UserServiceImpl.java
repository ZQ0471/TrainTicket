package com.hdu.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hdu.train.DTO.UserInfoDTO;
import com.hdu.train.entity.Train;
import com.hdu.train.entity.User;
import com.hdu.train.mapper.UserMapper;
import com.hdu.train.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
@Service("iUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword());
        if(this.baseMapper.selectList(wrapper).size()==1){
            return true;
        }
        return false;
    }

    @Override
    public User getUserInfo(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public boolean register(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        if(this.baseMapper.selectList(wrapper).isEmpty()){
            this.baseMapper.insert(user);
            return true;
        }
        return false;
    }


}

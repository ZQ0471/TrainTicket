package com.hdu.train.service;

import com.hdu.train.DTO.UserInfoDTO;
import com.hdu.train.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
public interface IUserService extends IService<User> {

    User getUserInfo(String username);

    boolean register(User user);

    boolean login(User user);
}

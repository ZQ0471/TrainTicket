package com.hdu.train.controller;

import com.hdu.train.DTO.UserInfoDTO;
import com.hdu.train.entity.User;
import com.hdu.train.result.Result;
import com.hdu.train.service.IUserService;
import com.hdu.train.util.JwtToken;
import com.hdu.train.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    RedisUtils redisUtils = new RedisUtils();
    @PostMapping("/login")
    public Result Login(@RequestBody User user){
        if(iUserService.login(user)){
            String token = JwtToken.generateToken(user.getUsername());
            redisUtils.set(token,user.getUsername());
            return Result.ok().data("token",token).message("登陆成功");
        }
        return Result.error().message("账号或密码错误");
    }
    @PostMapping("/register")
    public Result Register(@RequestBody User user){
        if(iUserService.register(user)){
            String token = JwtToken.generateToken(user.getUsername());
//            redisUtils.set(token,user.getUsername());
            return Result.ok().data("token",token).message("注册成功");
        }
        return Result.error().message("注册失败");
    }
    @GetMapping("/info")
    public Result Info(@RequestParam String token){
        String username = JwtToken.getClaimsByToken(token).getSubject();
        if(username!=null){
            User user  = iUserService.getUserInfo(username);
            UserInfoDTO userInfo = new UserInfoDTO(user.getUsername(),user.getAvatar());
            System.out.println(userInfo);
            return Result.ok().data("data",userInfo);
        }
        return Result.error().message("获取失败");
    }
    @PostMapping("/logout")
    public Result Logout(){
        return Result.ok().message("登出成功");
    }

}

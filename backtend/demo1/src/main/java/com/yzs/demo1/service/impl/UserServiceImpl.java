package com.yzs.demo1.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzs.demo1.common.Constants;
import com.yzs.demo1.controller.dto.UserDTO;
import com.yzs.demo1.entity.User;
import com.yzs.demo1.exception.ServiceException;
import com.yzs.demo1.mapper.UserMapper;
import com.yzs.demo1.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzs
 * @since 2022-05-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log log = Log.get();
    @Override
    public UserDTO login(User userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
           one = getOne(queryWrapper);
           if(one != null){
               BeanUtil.copyProperties(one,userDTO,true);
               UserDTO userDTO1 = new UserDTO();
               userDTO1.setNickname(userDTO.getNickname()) ;
               userDTO1.setNickname(userDTO.getNickname()) ;
               return userDTO1;
           }else {
               throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
           }
        }catch(Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }

    }
}

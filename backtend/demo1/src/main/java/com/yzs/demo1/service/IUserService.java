package com.yzs.demo1.service;

import com.yzs.demo1.controller.dto.UserDTO;
import com.yzs.demo1.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzs
 * @since 2022-05-24
 */
public interface IUserService extends IService<User> {

    UserDTO login(User userDTO);
}

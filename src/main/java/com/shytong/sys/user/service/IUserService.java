package com.shytong.sys.user.service;

import com.shytong.sys.user.UserDto;

/**
 * @author sytong
 * @Package com.shytong.sys.user.service
 * @Description:
 * @date 2018-01-1617:05
 */
public interface IUserService<T extends UserDto> {

    void register(T userDto);


}

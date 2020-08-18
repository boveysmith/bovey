package com.ynrd.mapper;

import com.ynrd.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper
{
//    public User getUserById(Integer id);

    public void insertUser(User user);

    public User queryUserByUsername(String username);
}

package com.ynrd.mapper;

import com.ynrd.YnrdApplication;
import com.ynrd.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YnrdApplication.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void queryUserByUsername() throws Exception {

        User user = userMapper.queryUserByUsername("zhangsan");
        System.out.println(user);
    }
}
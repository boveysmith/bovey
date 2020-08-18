package com.ynrd.mapper;

import com.ynrd.YnrdApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YnrdApplication.class)
public class PermissionMapperTest {

    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void queryUserByUsername() throws Exception {

        Set<String> roleNames = permissionMapper.queryPermissionsByUsername("lisi");
        Iterator<String> iterator = roleNames.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
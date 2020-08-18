package com.ynrd.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface PermissionMapper
{
    public Set<String> queryPermissionsByUsername(String username);
}

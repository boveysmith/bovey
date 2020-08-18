package com.ynrd.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface RoleMapper
{
    public Set<String> queryRoleNamesByUsername(String username);
}

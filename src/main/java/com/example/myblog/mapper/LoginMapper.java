package com.example.myblog.mapper;


import com.example.myblog.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from admin where user_id=#{userId}")
    public Admin getUser(Integer userId);

    @Select("select * from admin where user_count=#{userCount} and password=#{password}")
    public Admin gerUserByIdAndPassword(String userCount, String password);
}

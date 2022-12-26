package com.example.cah.mapper;


import com.example.cah.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM User")
    List<User> getUserList();

}

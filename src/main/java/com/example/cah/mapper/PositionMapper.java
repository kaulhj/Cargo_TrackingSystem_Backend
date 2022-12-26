package com.example.cah.mapper;

import com.example.cah.controller.dto.ResponseDto;
import com.example.cah.controller.dto.Result;
import com.example.cah.model.Position;
import com.example.cah.model.getPositionAndName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface PositionMapper {


    @Select("SELECT * FROM Position p inner join User u on p.userId = u.userId WHERE p.userId=#{id}")
    List<getPositionAndName> getPosition(@PathVariable("id") int id);

    @Select("select endTag from Position order by positionIdx desc limit 1")
    int getLastEndTag();

    @Update("update Position set etc = '도착' where positionIdx =(select positionIdx from(select positionIdx from Position order by positionIdx desc limit 1) as c)")
    void informEnd();


}

package com.example.myblog.mapper;

import com.example.myblog.entity.Sort;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GetSortList {
    @Select("select * from sort")
    public List<Sort> getSortList();

    @Insert("INSERT INTO sort(sort) VALUES (#{sort})")
    @Options(useGeneratedKeys=true, keyProperty="sortId",keyColumn="sort_id")
    public Integer addSort(Sort sort);

    @Update("UPDATE  sort SET sort = #{sort}  WHERE  sort_id = #{sortId}")
    public Integer updateSort(Sort sort);

    @Delete("delete from sort where sort_id = #{sortId};")
    public Integer deleteSort(Sort sort);

    @Select("select count(1) from sort")
    public Integer getSortNumber();
}


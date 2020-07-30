package com.example.myblog.mapper;

import com.example.myblog.entity.Operation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperationMapper {

    @Select("select * from operation")
    public List<Operation> getOperationList();

    @Insert("INSERT INTO operation(operator,operation_content,operation_time,operation_type) VALUES(#{operator},#{operationContent},#{operationTime},#{operationType})")
    @Options(useGeneratedKeys=true, keyProperty="operationId")
    public Integer addOperationList(Operation operation);
}

package com.example.myblog.controller;

import com.example.myblog.entity.Operation;
import com.example.myblog.mapper.OperationMapper;
import com.example.myblog.response.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperationController {
    @Autowired
    OperationMapper operationMapper;

    @RequestMapping("/getOperationList")
    public ResponseFormat getOperationList(){
        ResponseFormat responseFormat =  new ResponseFormat();
        List<Operation> resList = operationMapper.getOperationList();
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(resList);
        responseFormat.setMsg("");
        return responseFormat;
    }
}

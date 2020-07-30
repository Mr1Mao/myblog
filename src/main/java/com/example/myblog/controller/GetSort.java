package com.example.myblog.controller;

import com.example.myblog.entity.Admin;
import com.example.myblog.entity.Operation;
import com.example.myblog.entity.Sort;
import com.example.myblog.mapper.GetArticleList;
import com.example.myblog.mapper.GetSortList;
import com.example.myblog.mapper.OperationMapper;
import com.example.myblog.response.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetSort {

    @Autowired
    GetSortList getSortList;
    @Autowired
    OperationMapper operationMapper;
    @Autowired
    GetArticleList getArticleList;

    Operation operation ;

    ResponseFormat responseFormat;

    @RequestMapping("/getSortList")
    public ResponseFormat getSortList(){
        responseFormat =  new ResponseFormat();
        List<Sort> resList =  getSortList.getSortList();
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(resList);
        responseFormat.setMsg("");
        return responseFormat;
    }

    @RequestMapping("/addSort")
    public ResponseFormat addSort(HttpSession session,@RequestBody Sort sort){
         responseFormat =  new ResponseFormat();
        Integer res = getSortList.addSort(sort);
        if(res == 1 ){

            //添加操作记录
            Admin admin = (Admin) session.getAttribute("admin");
            Integer id =  sort.getSortId();
            operation = new Operation();
            operation.setOperationTime(new Date());
            operation.setOperationType("添加类别");
            operation.setOperationContent("添加了一篇类别(ID:"+ id +")");
            operation.setOperator(admin.getUserName());
            operationMapper.addOperationList(operation);

            responseFormat.setCode(0);
            responseFormat.setSuccess(true);
            responseFormat.setData("");
            responseFormat.setMsg("");
        }else{
            responseFormat.setCode(1);
            responseFormat.setSuccess(false);
            responseFormat.setData("");
            responseFormat.setMsg("添加失败");
        }
        return responseFormat;
    }

    @RequestMapping("/updateSort")
    public ResponseFormat updateSort(HttpSession session,@RequestBody Sort sort){
        responseFormat =  new ResponseFormat();
        Integer res = getSortList.updateSort(sort);
        if(res == 1 ){

            //添加操作记录
            Admin admin = (Admin) session.getAttribute("admin");
            Integer id =  sort.getSortId();
            operation = new Operation();
            operation.setOperationTime(new Date());
            operation.setOperationType("修改类别");
            operation.setOperationContent("修改了一篇类别(ID:"+ id +")");
            operation.setOperator(admin.getUserName());
            operationMapper.addOperationList(operation);

            responseFormat.setCode(0);
            responseFormat.setSuccess(true);
            responseFormat.setData("");
            responseFormat.setMsg("");
        }else{
            responseFormat.setCode(1);
            responseFormat.setSuccess(false);
            responseFormat.setData("");
            responseFormat.setMsg("更新失败");
        }
        return responseFormat;
    }

    @RequestMapping("/deleteSort")
    public ResponseFormat deleteSort(HttpSession session,@RequestBody Sort sort){
        responseFormat =  new ResponseFormat();
        Integer res = getSortList.deleteSort(sort);
//        System.out.println(res);
        if(res == 1 ){

            //添加操作记录
            Admin admin = (Admin) session.getAttribute("admin");
            Integer id =  sort.getSortId();
            operation = new Operation();
            operation.setOperationTime(new Date());
            operation.setOperationType("删除类别");
            operation.setOperationContent("删除了一篇类别(ID:"+ id +")");
            operation.setOperator(admin.getUserName());
            operationMapper.addOperationList(operation);

            responseFormat.setCode(0);
            responseFormat.setSuccess(true);
            responseFormat.setData("");
            responseFormat.setMsg("");
        }else{
            responseFormat.setCode(1);
            responseFormat.setSuccess(false);
            responseFormat.setData("");
            responseFormat.setMsg("删除失败");
        }
        return responseFormat;
    }

    //文章数量
    @RequestMapping("/sortNumberAndArticle")
    public ResponseFormat sortNumberAndArticle(){
        responseFormat =  new ResponseFormat();
        Integer sortNumber = getSortList.getSortNumber();
        Integer articleNumber = getArticleList.getArticleNumber();
        Map res = new HashMap();
        res.put("sortNumber",sortNumber);
        res.put("articleNumber",articleNumber);
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(res);
        responseFormat.setMsg("");

        return responseFormat;
    }
}

package com.example.myblog.controller;


import com.example.myblog.entity.Admin;
import com.example.myblog.entity.Article;
import com.example.myblog.entity.Operation;
import com.example.myblog.mapper.GetArticleList;
import com.example.myblog.mapper.OperationMapper;
import com.example.myblog.response.ResponseFormat;
import com.example.myblog.tool.TimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.*;

@RestController
public class GetArticle {
    @Autowired
    GetArticleList getArticleList;
    @Autowired
    OperationMapper operationMapper;

    Operation operation ;
    //获取文章列表所有内容
    @RequestMapping("/getArticleList")
    public ResponseFormat getArticleList() {
        ResponseFormat responseFormat =  new ResponseFormat();
        List<Article> resList =  getArticleList.getArticleList();
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(resList);
        responseFormat.setMsg("");
        return responseFormat;
    }
    //通过获取文章信息列表（后台管理）
    @RequestMapping("/getArticleInfoList")
    public ResponseFormat getArticleInfoList() {
        ResponseFormat responseFormat =  new ResponseFormat();
        List<Article> resList =  getArticleList.getArticleInfoList();
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(resList);
        responseFormat.setMsg("");
        return responseFormat;
    }
    //通过ID获取文章列表
    @RequestMapping("/getArticleListBySortId")
    public ResponseFormat getArticleInfoList(@RequestBody Map<String,Object> requestBody) {
        ResponseFormat responseFormat =  new ResponseFormat();
        String sort = (String) requestBody.get("sort");
//        System.out.print(requestBody);
        List<Article> resList =  getArticleList.getArticleListBySortId(sort);
//        if()
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(resList);
        responseFormat.setMsg("");
        return responseFormat;
    }
    //获取文章详情信息
    @RequestMapping("/getDetail")
    public ResponseFormat getArticleById(@RequestBody Map<String,Object> requestBody) {
        ResponseFormat responseFormat =  new ResponseFormat();
        String id = (String) requestBody.get("id");
//        System.out.print(id);
        Article resList =  getArticleList.getArticleById(id);
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(resList);
        responseFormat.setMsg("");
        return responseFormat;
    }
//    更新文章
    @RequestMapping("/upDateArticle")
    public ResponseFormat upDateArticle(HttpSession session,@RequestBody Article article) {
        ResponseFormat responseFormat =  new ResponseFormat();
        Integer res  =  getArticleList.upDateArticle(article);
        if(res == 1 ){

            //添加操作记录
            Admin admin = (Admin) session.getAttribute("admin");
            Integer id = article.getArticleId();
            operation = new Operation();
            operation.setOperationTime(new Date());
            operation.setOperationType("修改文章");
            operation.setOperationContent("修改了一篇文章(ID:"+ id +")");
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
            responseFormat.setMsg("修改失败");
        }
        return responseFormat;
    }
    //删除文章
    @RequestMapping("/delectArticle")
    public ResponseFormat delectArticle(HttpSession session,@RequestBody Map<String,Object> requestBody){
        ResponseFormat responseFormat =  new ResponseFormat();
        Integer id = (Integer) requestBody.get("articleID");
        Integer res  =  getArticleList.delectArticle(id);
//        System.out.println("删除的文章ID："+id);
        if(res == 1 ){

            //添加操作记录
            Admin admin = (Admin) session.getAttribute("admin");
            operation = new Operation();
            operation.setOperationTime(new Date());
            operation.setOperationType("删除文章");
            operation.setOperationContent("删除了一篇文章(ID:"+ id +")");
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

    //发布文章
    @RequestMapping("/addArticle")
    public ResponseFormat addArticle(HttpSession session,@RequestBody Article article){
        ResponseFormat responseFormat =  new ResponseFormat();
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null){
            responseFormat.setCode(3);
            responseFormat.setSuccess(false);
            responseFormat.setData("");
            responseFormat.setMsg("未登入");
            return responseFormat;
        }else if("".equals(article.getArticleBrief()) || "".equals(article.getArticleTitle())
                || "".equals(article.getSort()) || "".equals(article.getArticleContent())){
//            System.out.println(article.getArticleBrief() + "进入了");
            responseFormat.setCode(2);
            responseFormat.setSuccess(false);
            responseFormat.setData("");
            responseFormat.setMsg("内容信息不完整，发布失败");
            return responseFormat;
        }

        article.setTime(new Date());
        article.setViewNumber(0);
        article.setArticleAuthor(admin.getUserName());
        Integer res  =  getArticleList.addArticle(article);

        if(res == 1 ){
            //添加操作记录
            Integer articleId =  article.getArticleId();
            operation = new Operation();
            operation.setOperationTime(new Date());
            operation.setOperationType("发表文章");
            operation.setOperationContent("发表了一篇文章(ID:"+ articleId +")");
            operation.setOperator(admin.getUserName());
            operationMapper.addOperationList(operation);

            responseFormat.setCode(0);
            responseFormat.setSuccess(true);
            responseFormat.setData("");
            responseFormat.setMsg("发布成功");
        }else{
            responseFormat.setCode(1);
            responseFormat.setSuccess(false);
            responseFormat.setData("");
            responseFormat.setMsg("发布失败");
        }
        return responseFormat;
    }

    //获取文章数量接口
    @RequestMapping("/getArticleNumber")
    public ResponseFormat getArticleNumber(){
        ResponseFormat responseFormat =  new ResponseFormat();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());//将date - 7
        Integer nowWeek= TimeFormat.dateToWeek(format.format(new Date()));
        c.add(Calendar.DATE, - nowWeek);
        Date d = c.getTime();
        String day = format.format(d);

        Calendar l = Calendar.getInstance();
        l.setTime(new Date());//将date - 7
        l.add(Calendar.DATE, - (nowWeek+7));
        String LastWeek = format.format(l.getTime());

        Integer articleTotalNumber = getArticleList.getArticleNumber();//总篇数
        Integer articleNumberThisWeek = getArticleList.getArticleNumberDay(format.format(new Date()),day);//本周
        Integer articleNumberLastWeek = getArticleList.getArticleNumberDay(day,LastWeek);//上周
        System.out.println("本周开始时间"+day);
        System.out.println("上周开始时间"+LastWeek);
        System.out.println("本周："+ articleNumberThisWeek);
        System.out.println("上周："+ articleNumberLastWeek);

        Map res = new HashMap();
        res.put("articleNumberThisWeek",articleNumberThisWeek);
        res.put("articleTotalNumber",articleTotalNumber);
        res.put("increaing",articleNumberThisWeek - articleNumberLastWeek);
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(res);
        responseFormat.setMsg("");
        return responseFormat;
    }

    //放回文章所有数量
    @RequestMapping("/getSumViewNumber")
    public ResponseFormat getSumViewNumber(){
        ResponseFormat responseFormat =  new ResponseFormat();
        Integer sumViewNumber = getArticleList.getSumViewNumber();
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(sumViewNumber);
        responseFormat.setMsg("");
        return responseFormat;
    }

    //放回给定时间文章阅读量
    @RequestMapping("/getSumViewNumberDay")
    public ResponseFormat getSumViewNumberDay(){
        ResponseFormat responseFormat =  new ResponseFormat();

        Format f = new SimpleDateFormat("yyyy-MM-dd");
        String now = f.format(new Date());
        String yesterday = f.format(new Date(new Date().getTime() - 86400000L));
        String beforeYesterday = f.format(new Date(new Date().getTime() - 172800000L));
//        Float sumViewNumber = Float.valueOf(getArticleList.getSumViewNumberDay(now,yesterday)); //今日的访问人数
//        if(sumViewNumber == null){
//            sumViewNumber = 0.0;
//        }

        Integer sumViewNumber = 13;
//        Float yestdaySumViewNumber = Float.valueOf(getArticleList.getSumViewNumberDay(yesterday,beforeYesterday));//昨日的访问人数
        Integer yestdaySumViewNumber = 10;
        float rateIncreaing = (sumViewNumber / yestdaySumViewNumber)*100;
        Map res = new HashMap();
        res.put("sumViewNumberDay",sumViewNumber);
        res.put("rateIncreaing",String.format("%.2f", rateIncreaing));
        responseFormat.setCode(0);
        responseFormat.setSuccess(true);
        responseFormat.setData(res);
        responseFormat.setMsg("");
        return responseFormat;
    }


    //增加文章阅读量
    @RequestMapping("/addViewNumber")
    public ResponseFormat addViewNumber(@RequestBody Article article){
        ResponseFormat responseFormat =  new ResponseFormat();
        Integer res = getArticleList.addViewNumber(article);
        if(res == 1){
            responseFormat.setCode(0);
            responseFormat.setSuccess(true);
            responseFormat.setMsg("");
        }else{
            responseFormat.setCode(1);
            responseFormat.setSuccess(false);
            responseFormat.setMsg("参数错误");
        }
        return responseFormat;
    }

    @RequestMapping("/nextChapter")
    public  ResponseFormat nextChapter (@RequestBody Map<String,Object> request){
        ResponseFormat responseFormat =  new ResponseFormat();
        Integer id = (Integer) request.get("articleId");
        Integer flag = (Integer) request.get("flag");
        Article res;
        if(flag == 1){
             res = getArticleList.nextChapter(id);
        }else {
            res = getArticleList.previousChapter(id);
        }

        if(res != null){
            responseFormat.setCode(0);
            responseFormat.setData(res);
            responseFormat.setSuccess(true);
            responseFormat.setMsg("下一页");
        }else{
            responseFormat.setCode(1);
            responseFormat.setData("");
            responseFormat.setSuccess(false);
            responseFormat.setMsg("无法下一页");
        }

        return responseFormat;
    }
}

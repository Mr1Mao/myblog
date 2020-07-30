package com.example.myblog.mapper;


import com.example.myblog.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GetArticleList {

    //放回所有文章列表（前台）
    @Select("select article_id,article_title,article_content,article_author,time,article_brief,view_number,sort from article left join  sort  on  article.sort_id = sort.sort_id;")
    public List<Article> getArticleList();

    //放回所有文章信息列表（后台管理）
    @Select("select article_id,article_title,article_author,time,view_number,sort from article left join  sort  on  article.sort_id = sort.sort_id;")
    public List<Article> getArticleInfoList();

//    通过文章类型查询文章接口
//    此处的ById改为Name
    @Select("select article_id,article_title,article_author,time,article_brief,view_number,sort,sort.sort_id from article left join  sort  on  article.sort_id = sort.sort_id where sort = #{sort};")
    public List<Article> getArticleListBySortId(String sort);

    //通过ID放回文章列表接口
    @Select("select article_id,article_title,article_author,article_brief,article_content,time,view_number,sort,sort.sort_id from article left join  sort  on  article.sort_id = sort.sort_id where article_id = #{Id};")
    public Article getArticleById(String Id);

    //修改文章接口
    @Update("UPDATE  article SET article_title = #{articleTitle},article_author =  #{articleAuthor} ,article_brief = #{articleBrief} ,sort_id = #{sort}  WHERE  article_id = #{articleId}")
    public Integer upDateArticle(Article article);

    //通过ID删除文章接口
    @Delete("delete from article where article_id = #{id}")
    public Integer delectArticle(Integer id);

    //发布文章接口
    @Insert("INSERT INTO article(article_title,article_content,article_author,time,article_brief,sort_id,view_number) VALUES (#{articleTitle},#{articleContent},#{articleAuthor},#{time},#{articleBrief},#{sort},#{viewNumber})")
    @Options(useGeneratedKeys=true, keyProperty="articleId" , keyColumn="article_id")
    public Integer addArticle(Article article);

    //返回文章总数
    @Select("select count(1) from article")
    public Integer getArticleNumber();

    //放回给定时间的文章数量
    @Select("select count(1) from article where time > #{yesterday} and time < #{now} ")
    public Integer getArticleNumberDay(String now,String yesterday);

    //返回所有文章访问量
    @Select("select SUM(view_number) from article")
    public Integer getSumViewNumber();

    //放回给定时间的文章访问量
    @Select("select SUM(view_number) from article where time > #{yesterday} and time < #{now} ")
    public Integer getSumViewNumberDay(String now,String yesterday);

    //添加文章阅读量
    @Update("UPDATE article SET view_number = view_number +1 WHERE article_id = #{articleId}")
    public Integer addViewNumber(Article article);

    //下一页
    @Select("select article_id,article_title,article_author,article_brief,article_content,time,view_number,sort,sort.sort_id  from article left join  sort  on  article.sort_id = sort.sort_id where article_id > #{id} LIMIT 1")
    public Article nextChapter(Integer id);

    //上一页
    @Select("select article_id,article_title,article_author,article_brief,article_content,time,view_number,sort,sort.sort_id  from article left join  sort  on  article.sort_id = sort.sort_id where article_id < #{id} LIMIT 1")
    public Article previousChapter (Integer id);

}

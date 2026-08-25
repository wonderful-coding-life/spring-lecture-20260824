package com.example.mybatis.mapper;

import com.example.mybatis.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("""
        INSERT INTO article(title, description, created, updated, member_id)
            VALUES(#{article.title}, #{article.description}, #{article.created}, #{article.updated}, #{article.memberId})
    """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(@Param("article") Article article);

    @Select("SELECT * FROM article")
    List<Article> selectAll();

    @Select("SELECT * FROM article WHERE id=#{id}")
    List<Article> selectById(@Param("id") Long id);
}

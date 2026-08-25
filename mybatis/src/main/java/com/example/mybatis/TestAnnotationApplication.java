package com.example.mybatis;

import com.example.mybatis.mapper.ArticleMapper;
import com.example.mybatis.model.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestAnnotationApplication implements ApplicationRunner {
    private final ArticleMapper articleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Article article = Article.builder()
                .title("방학 첫날이다")
                .description("신난다")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .memberId(1L).build();
        articleMapper.insert(article);

        Article article2 = Article.builder()
                .title("방학 둘날이다")
                .description("신난다. 맑음.")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .memberId(2L).build();
        articleMapper.insert(article2);

        List<Article> articles = articleMapper.selectAll();
        for (Article article1 : articles) {
            log.info("게시글 {}", article1);
        }
    }
}

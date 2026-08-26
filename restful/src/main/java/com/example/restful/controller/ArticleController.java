package com.example.restful.controller;

import com.example.restful.dto.ArticleRequest;
import com.example.restful.dto.ArticleResponse;
import com.example.restful.entity.Article;
import com.example.restful.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/articles")
    public ArticleResponse postArticles(@RequestParam("memberId") Long memberId, @RequestBody ArticleRequest articleRequest) {
        return articleService.create(memberId, articleRequest);
    }

    @GetMapping("/articles/{id}")
    public ArticleResponse getArticles(@PathVariable("id") Long id) {
        return articleService.findById(id);

    }

    @GetMapping("/articles")
    public Page<ArticleResponse> getArticles(@PageableDefault(sort="created", direction = Sort.Direction.DESC, size=10, page=0) Pageable pageable) {
        return articleService.findAll(pageable);
    }
}

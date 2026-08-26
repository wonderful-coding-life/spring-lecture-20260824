package com.example.restful.service;

import com.example.restful.dto.ArticleRequest;
import com.example.restful.dto.ArticleResponse;
import com.example.restful.entity.Article;
import com.example.restful.entity.Member;
import com.example.restful.repository.ArticleRepository;
import com.example.restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public ArticleResponse create(Long memberId, ArticleRequest articleRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Article article = Article.builder()
                .title(articleRequest.getTitle())
                .description(articleRequest.getDescription())
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .member(member).build();
        articleRepository.save(article);

        return mapToArticleResponse(article);
    }

    private ArticleResponse mapToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .created(article.getCreated())
                .updated(article.getUpdated())
                .memberId(article.getMember().getId())
                .name(article.getMember().getName())
                .email(article.getMember().getEmail())
                .build();
    }

    public ArticleResponse findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return mapToArticleResponse(article);
    }

    public List<ArticleResponse> findAll() {
        return articleRepository.findAll().stream().map(this::mapToArticleResponse).toList();
    }

    public Page<ArticleResponse> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(this::mapToArticleResponse);
    }
}

package com.example.bbs.service;

import com.example.bbs.entity.Article;
import com.example.bbs.entity.Member;
import com.example.bbs.repository.ArticleRepository;
import com.example.bbs.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<Article> findPage(Pageable pageable) {
        return articleRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Transactional(readOnly = true)
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("게시글을 찾을 수 없습니다."));
    }

    @Transactional
    public Long create(String title, String content, String username) {
        Member author = findMember(username);
        return articleRepository.save(Article.create(title, content, author)).getId();
    }

    @Transactional
    public void update(Long articleId, String title, String content, String username) {
        Article article = findById(articleId);
        validateAuthor(article, username);
        article.update(title, content);
    }

    @Transactional
    public void delete(Long articleId, String username) {
        Article article = findById(articleId);
        validateAuthor(article, username);
        articleRepository.delete(article);
    }

    @Transactional(readOnly = true)
    public void checkAuthor(Article article, String username) {
        validateAuthor(article, username);
    }

    private Member findMember(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));
    }

    private void validateAuthor(Article article, String username) {
        if (!article.getAuthor().getUsername().equals(username)) {
            throw new ArticleAccessDeniedException("게시글 작성자만 수정하거나 삭제할 수 있습니다.");
        }
    }
}

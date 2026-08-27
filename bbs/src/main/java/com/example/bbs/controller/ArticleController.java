package com.example.bbs.controller;

import com.example.bbs.entity.Article;
import com.example.bbs.form.ArticleForm;
import com.example.bbs.service.ArticleService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String list(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        Page<Article> articles = articleService.findPage(pageable);
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/{articleId}")
    public String detail(@PathVariable Long articleId, Principal principal, Model model) {
        model.addAttribute("article", articleService.findById(articleId));
        model.addAttribute("currentUsername", principal == null ? null : principal.getName());
        return "articles/detail";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("articleForm") ArticleForm articleForm) {
        return "articles/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("articleForm") ArticleForm articleForm,
                         BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "articles/form";
        }
        articleService.create(articleForm.getTitle(), articleForm.getContent(), principal.getName());
        return "redirect:/articles";
    }

    @GetMapping("/{articleId}/edit")
    public String editForm(@PathVariable Long articleId, Principal principal, Model model) {
        Article article = articleService.findById(articleId);
        articleService.checkAuthor(article, principal.getName());
        ArticleForm articleForm = new ArticleForm();
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        model.addAttribute("articleForm", articleForm);
        model.addAttribute("articleId", articleId);
        return "articles/form";
    }

    @PostMapping("/{articleId}/edit")
    public String edit(@PathVariable Long articleId,
                       @Valid @ModelAttribute("articleForm") ArticleForm articleForm,
                       BindingResult bindingResult, Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("articleId", articleId);
            return "articles/form";
        }
        articleService.update(articleId, articleForm.getTitle(), articleForm.getContent(), principal.getName());
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String delete(@PathVariable Long articleId, Principal principal) {
        articleService.delete(articleId, principal.getName());
        return "redirect:/articles";
    }
}

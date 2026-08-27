package com.example.bbs.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ArticleAccessDeniedException extends RuntimeException {
    public ArticleAccessDeniedException(String message) {
        super(message);
    }
}

package com.example.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    private Long id;
    private Long memberId;
    private String name;
    private String email;
    private String title;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
}

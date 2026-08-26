package com.example.restful.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description", length = 4096)
    private String description;
    @Column(name="created")
    private LocalDateTime created;
    @Column(name="updated")
    private LocalDateTime updated;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
}

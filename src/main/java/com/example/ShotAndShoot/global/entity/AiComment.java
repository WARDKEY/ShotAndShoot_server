package com.example.ShotAndShoot.global.entity;

import com.example.ShotAndShoot.global.util.common.CreateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ai_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiComment extends CreateTime {

    @Id
    @Column(name = "ai_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aiCommentId;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Builder
    public AiComment(String content, Question question) {
        this.content = content;
        this.question = question;
    }
}

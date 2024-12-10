package com.example.ShotAndShoot.global.entity;

import com.example.ShotAndShoot.global.util.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTime {

    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 댓글과 게시글 관계
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY) // 댓글과 회원 관계
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Comment(String content, Question question, Member member) {
        this.content = content;
        this.question = question;
        this.member = member;
    }
}

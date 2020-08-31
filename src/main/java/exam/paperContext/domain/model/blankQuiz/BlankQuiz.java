package exam.paperContext.domain.model.blankQuiz;

import exam.paperContext.shared.Entity;

import java.time.LocalDateTime;

public class BlankQuiz implements Entity<BlankQuiz> {
    private BlankQuizId blankQuizId;
    private String teacherId;
    private String content;
    private String referenceAnswer;
    private int score;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean isDeleted;

    private BlankQuiz(BlankQuizId blankQuizId, String teacherId, String content, String referenceAnswer, int score) {
        this.blankQuizId = blankQuizId;
        this.teacherId = teacherId;
        this.content = content;
        this.referenceAnswer = referenceAnswer;
        this.score = score;
        isDeleted = false;
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    public static BlankQuiz create(BlankQuizId blankQuizId, String teacherId, String content,
                                   String referenceAnswer, int score) {
        return new BlankQuiz(blankQuizId, teacherId, content, referenceAnswer, score);
    }

    public BlankQuizId getBlankQuizId() {
        return blankQuizId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getContent() {
        return content;
    }

    public String getReferenceAnswer() {
        return referenceAnswer;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void revise(String content, String referenceAnswer, int score) {
        this.content = content;
        this.referenceAnswer = referenceAnswer;
        this.score = score;
    }

    public void delete() {
        this.isDeleted = true;
    }

    @Override
    public boolean sameIdentityAs(BlankQuiz other) {
        return false;
    }
}

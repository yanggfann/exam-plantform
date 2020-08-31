package exam.examinationContext.domain.model.examination;

import exam.examinationContext.shared.Entity;
import exam.examinationContext.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(of = {"examinationId"})
public class Examination implements Entity<Examination> {
    private ExaminationId examinationId;
    private Paper paper;

    private Examination(ExaminationId examinationId, Paper paper) {
        this.examinationId = examinationId;
        this.paper = paper;
    }

    public static Examination create(ExaminationId examinationId, Paper paper) {
        validateQuizzes(paper.getBlankQuizzes());
        return new Examination(examinationId, paper);
    }

    private static void validateQuizzes(List<Paper.BlankQuiz> blankQuizzes) {
        if (blankQuizzes.size() > 20 || blankQuizzes.size() < 5) {
            throw new IllegalQuizzesCountException(blankQuizzes.size());
        }

        int totalScore = blankQuizzes.stream().mapToInt(Paper.BlankQuiz::getScore).sum();
        if (totalScore != 100) {
            throw new IllegalScoreException(totalScore);
        }
    }

    public ExaminationId getExaminationId() {
        return examinationId;
    }

    public Paper getPaper() {
        return paper;
    }

    @Override
    public boolean sameIdentityAs(Examination other) {
        return false;
    }

    @Getter
    @AllArgsConstructor
    public static class Paper implements ValueObject<Paper> {
        private String name;
        private List<BlankQuiz> blankQuizzes;

        @Override
        public boolean sameValueAs(Paper other) {
            return false;
        }

        @Getter
        public static class BlankQuiz implements ValueObject<BlankQuiz> {
            private String id;
            private String number;
            private int score;
            private String teacherId;
            private String content;
            private String referenceAnswer;
            private LocalDateTime createTime;
            private LocalDateTime updateTime;

            private static String generateId() {
                return UUID.randomUUID().toString();
            }

            public BlankQuiz(String number, int score, String teacherId, String content,
                             String referenceAnswer, LocalDateTime createTime, LocalDateTime updateTime) {
                this.id = generateId();
                this.number = number;
                this.score = score;
                this.teacherId = teacherId;
                this.content = content;
                this.referenceAnswer = referenceAnswer;
                this.createTime = createTime;
                this.updateTime = updateTime;
            }

            @Override
            public boolean sameValueAs(BlankQuiz other) {
                return false;
            }
        }
    }
}

package exam.examinationContext.domain.service;

import exam.examinationContext.domain.model.examination.Examination;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaperDto {
    private String name;
    private List<BlankQuiz> blankQuizzes;

    public static Examination.Paper toPaper(PaperDto paperDto) {
        // TODO map PaperDto to Paper
        return null;
    }

    @AllArgsConstructor
    public static class BlankQuiz {
        private String number;
        private int score;
        private String teacherId;
        private String content;
        private String referenceAnswer;
    }
}

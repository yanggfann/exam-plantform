package exam.examinationContext.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateExaminationCommand {
    private Paper paper;

    @Value
    public static class Paper {
        private String name;
        private List<BlankQuiz> blankQuizzes;

        @Value
        public static class BlankQuiz {
            private String number;
            private int score;
            private String teacherId;
            private String content;
            private String referenceAnswer;
        }
    }
}
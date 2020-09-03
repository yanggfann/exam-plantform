package exam.examinationContext.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExaminationCommand {
    private Paper paper;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Paper {
        public String name;
        public List<BlankQuiz> blankQuizzes;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public static class BlankQuiz {
            public String number;
            public int score;
            public String teacherId;
            public String content;
            public String referenceAnswer;
        }
    }
}
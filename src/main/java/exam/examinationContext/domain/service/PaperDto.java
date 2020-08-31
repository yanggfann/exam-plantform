package exam.examinationContext.domain.service;

import exam.examinationContext.domain.model.examination.Examination;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PaperDto {
    private String name;
    private List<BlankQuiz> blankQuizzes;

    public static Examination.Paper toPaper(PaperDto paperDto) {
        List<Examination.Paper.BlankQuiz> blankQuizs = paperDto.getBlankQuizzes()
                .stream()
                .map(quiz -> new Examination.Paper.BlankQuiz(quiz.number, quiz.score, quiz.teacherId,
                        quiz.content, quiz.referenceAnswer, LocalDateTime.now(), LocalDateTime.now()))
                .collect(Collectors.toList());
        return new Examination.Paper(paperDto.getName(), blankQuizs);
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

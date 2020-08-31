package exam.examinationContext.domain.model.examination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class ExaminationTest {
    @Test
    public void should_create_examination_with_new() {
        //given
        final ExaminationId examinationId = new ExaminationId("examinationId-000");
        final String examinationName = "java";
        final List<Examination.Paper.BlankQuiz> blankQuizzes = buildBlankQuiz();
        final Examination.Paper paper = new Examination.Paper(examinationName, blankQuizzes);

        //when
        Examination examination = Examination.create(examinationId, paper);

        //then
        assertThat(examination, is(notNullValue()));
        assertThat(examination.getExaminationId().getId(), is(examinationId.getId()));
        assertThat(examination.getPaper().getName(), is(examinationName));
        assertThat(examination.getPaper().getBlankQuizzes().size(), is(5));
    }

    private List<Examination.Paper.BlankQuiz> buildBlankQuiz() {
        final String firstQuizId = "quiz-01";
        final String secondQuizId = "quiz-02";
        final String thirdQuizId = "quiz-03";
        final String fourthQuizId = "quiz-04";
        final String fifthQuizId = "quiz-05";
        List<Examination.Paper.BlankQuiz> blankQuizzes = Arrays.asList(
                new Examination.Paper.BlankQuiz(firstQuizId, "1", 20, "teacher", "a",
                        "A", LocalDateTime.now(), LocalDateTime.now()),
                new Examination.Paper.BlankQuiz(secondQuizId, "2", 20, "teacher", "b",
                        "B", LocalDateTime.now(), LocalDateTime.now()),
                new Examination.Paper.BlankQuiz(thirdQuizId, "3", 20, "teacher", "c",
                        "C", LocalDateTime.now(), LocalDateTime.now()),
                new Examination.Paper.BlankQuiz(fourthQuizId, "4", 20, "teacher", "d",
                        "D", LocalDateTime.now(), LocalDateTime.now()),
                new Examination.Paper.BlankQuiz(fifthQuizId, "5", 20, "teacher", "e",
                        "E", LocalDateTime.now(), LocalDateTime.now())
        );
        return blankQuizzes;
    }

    @Test
    public void should_quizzes_quantity_less_than_20() {
        Assertions.assertThrows(IllegalQuizzesCountException.class, () -> {
            final ExaminationId examinationId = new ExaminationId("examinationId-000");
            final String examinationName = "java";
            final List<Examination.Paper.BlankQuiz> blankQuizzes = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                blankQuizzes.add(new Examination.Paper.BlankQuiz("quiz-" + i, "1", 4, "teacher", "a",
                        "A", LocalDateTime.now(), LocalDateTime.now()));
            }

            final Examination.Paper paper = new Examination.Paper(examinationName, blankQuizzes);

            //when
            Examination examination = Examination.create(examinationId, paper);
        });
    }

    @Test
    public void should_quizzes_quantity_more_than_5() {
        Assertions.assertThrows(IllegalQuizzesCountException.class, () -> {
            final ExaminationId examinationId = new ExaminationId("examinationId-000");
            final String examinationName = "java";
            final List<Examination.Paper.BlankQuiz> blankQuizzes = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                blankQuizzes.add(new Examination.Paper.BlankQuiz("quiz-" + i, "1", 25, "teacher", "a",
                        "A", LocalDateTime.now(), LocalDateTime.now()));
            }

            final Examination.Paper paper = new Examination.Paper(examinationName, blankQuizzes);

            //when
            Examination examination = Examination.create(examinationId, paper);
        });
    }

    @Test
    public void should_paper_total_score_equals_100() {
        Assertions.assertThrows(IllegalScoreException.class, () -> {
            final ExaminationId examinationId = new ExaminationId("examinationId-000");
            final String examinationName = "java";
            final List<Examination.Paper.BlankQuiz> blankQuizzes = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                blankQuizzes.add(new Examination.Paper.BlankQuiz("quiz-" + i, "1", 20, "teacher", "a",
                        "A", LocalDateTime.now(), LocalDateTime.now()));
            }

            final Examination.Paper paper = new Examination.Paper(examinationName, blankQuizzes);

            //when
            Examination examination = Examination.create(examinationId, paper);
        });
    }
}
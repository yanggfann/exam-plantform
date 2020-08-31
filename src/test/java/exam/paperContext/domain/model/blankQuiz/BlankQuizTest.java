package exam.paperContext.domain.model.blankQuiz;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class BlankQuizTest {
    @Test
    public void should_create_blank_quiz_with_new() {
        //given
        final BlankQuizId blankQuizId = new BlankQuizId("blankquiz-000");
        final String teacherId = "teacher-000";
        final String content = "content";
        final String referenceAnswer = "ddd";
        final int score = 10;

        //when
        BlankQuiz blankQuiz = BlankQuiz.create(blankQuizId, teacherId, content, referenceAnswer, score);

        //then
        assertThat(blankQuiz, is(notNullValue()));
        assertThat(blankQuiz.getBlankQuizId().getId(), is("blankquiz-000"));
        assertThat(blankQuiz.getContent(), is(content));
        assertThat(blankQuiz.getTeacherId(), is(teacherId));
        assertThat(blankQuiz.getReferenceAnswer(), is(referenceAnswer));
    }
}
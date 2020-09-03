package exam.examinationContext.userInterface;

import com.alibaba.fastjson.JSONObject;
import exam.examinationContext.application.CreateExaminationCommand;
import exam.examinationContext.application.CreateExaminationCommand.Paper;
import exam.examinationContext.application.CreateExaminationCommand.Paper.BlankQuiz;
import exam.examinationContext.application.ExaminationApplicationService;
import exam.examinationContext.domain.model.examination.ExaminationId;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ExaminationController.class)
class ExaminationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExaminationApplicationService examinationApplicationService;


    @Test
    public void should_create_examination_success() throws Exception {
        //given
        List<BlankQuiz> blankQuizzes = buildBlankQuiz();
        Paper paper = new Paper("java", blankQuizzes);
        CreateExaminationCommand createExaminationCommand = new CreateExaminationCommand(paper);
        ExaminationId examinationId = new ExaminationId("000");
        String requestJson = JSONObject.toJSONString(createExaminationCommand);

        //when
        when(examinationApplicationService.createExamination(createExaminationCommand)).thenReturn(examinationId);

        //then
        MvcResult mvcResult = mockMvc
                .perform(post("/examinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(201)).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        verify(examinationApplicationService, times(1)).createExamination(any());
    }

    private List<Paper.BlankQuiz> buildBlankQuiz() {
        return Arrays.asList(
                new BlankQuiz("1", 20, "teacher0", "nn", "mm"),
                new BlankQuiz("2", 20, "teacher0", "nn", "mm"),
                new BlankQuiz("3", 20, "teacher0", "nn", "mm"),
                new BlankQuiz("4", 20, "teacher0", "nn", "mm"),
                new BlankQuiz("5", 20, "teacher0", "nn", "mm")
        );
    }
}
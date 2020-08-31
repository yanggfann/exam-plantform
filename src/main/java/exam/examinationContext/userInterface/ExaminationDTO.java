package exam.examinationContext.userInterface;

import exam.examinationContext.domain.model.examination.ExaminationId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExaminationDTO {
    private String uri;

    public static ExaminationDTO from(ExaminationId examinationId) {
        return new ExaminationDTO("examinations/" + examinationId);
    };
}

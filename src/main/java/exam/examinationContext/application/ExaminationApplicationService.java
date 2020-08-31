package exam.examinationContext.application;

import exam.examinationContext.domain.model.examination.Examination;
import exam.examinationContext.domain.model.examination.ExaminationId;
import exam.examinationContext.domain.model.examination.ExaminationRepository;
import exam.examinationContext.domain.service.PaperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ExaminationApplicationService {
    private ExaminationRepository examinationRepository;

    @Autowired
    public ExaminationApplicationService(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    public ExaminationId createExamination(CreateExaminationCommand command) {
        final ExaminationId examinationId = ExaminationId.nextId();
        PaperDto paperDto = paperFrom(command);
        final Examination examination = Examination.create(examinationId, PaperDto.toPaper(paperDto));

        examinationRepository.save(examination);
        return examinationId;
    }

    private PaperDto paperFrom(CreateExaminationCommand command) {
        List<PaperDto.BlankQuiz> blankQuizzes = command.getPaper().getBlankQuizzes()
                .stream()
                .map(quiz -> new PaperDto.BlankQuiz(quiz.getNumber(), quiz.getScore(), quiz.getTeacherId(), quiz.getContent(), quiz.getReferenceAnswer()))
                .collect(toList());
        return new PaperDto(command.getPaper().getName(), blankQuizzes);
    }
}

package exam.examinationContext.infrastructure;

import exam.examinationContext.domain.model.examination.Examination;
import exam.examinationContext.domain.model.examination.ExaminationRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ExaminationRepositoryImpl implements ExaminationRepository {
    private Set<Examination> examinations = new HashSet<>();


    @Override
    public void save(Examination examination) {
        examinations.add(examination);
    }
}

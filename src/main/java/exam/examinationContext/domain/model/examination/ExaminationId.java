package exam.examinationContext.domain.model.examination;

import exam.paperContext.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ExaminationId implements ValueObject<ExaminationId> {
    private String id;

    public static ExaminationId nextId() {
        return new ExaminationId(UUID.randomUUID().toString());
    }

    @Override
    public boolean sameValueAs(ExaminationId other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExaminationId paperId = (ExaminationId) o;
        return Objects.equals(id, paperId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

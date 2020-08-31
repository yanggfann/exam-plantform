package exam.examinationContext.domain.model.examination;

public class IllegalScoreException extends IllegalArgumentException {
    public IllegalScoreException(int score) {
        super("IllegalScoreException: exception score is not 100, actual:" + score);
    }
}

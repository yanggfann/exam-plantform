package exam.examinationContext.domain.model.examination;

class IllegalQuizzesCountException extends IllegalArgumentException {

    public IllegalQuizzesCountException(int actual) {
        super("TooManyQuizzesException: exception:5~20, actual:" + actual);
    }
}

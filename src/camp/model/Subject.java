package camp.model;

public class Subject {
    // 필드
    private String subjectId;    // 과목 고유 ID
    private String subjectName;  // 과목 이름
    private String subjectType;  // 과목 유형 (필수, 선택)

    // 생성자
    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    // toString() 메서드
    @Override
    public String toString() {
        return String.format("%s (%s)", subjectName, subjectType);
    }
}

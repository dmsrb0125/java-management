package camp;


import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    public static List<Subject> initializeSubjects(IdSequenceGenerator idGenerator) {
        return List.of(
                new Subject(
                        idGenerator.generate("SU"),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        idGenerator.generate("SU"),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
    }

    public static List<Student> initializeStudents() {
        return new ArrayList<>();
    }

    public static List<Score> initializeScores() {
        return new ArrayList<>();
    }
}

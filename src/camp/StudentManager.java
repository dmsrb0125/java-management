package camp;

import camp.model.Student;
import camp.model.Subject;
import camp.view.ConsoleIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentManager {
    private final List<Student> studentStore;
    private final List<Subject> subjectStore;
    private final ConsoleIO consoleIO;
    private final IdSequenceGenerator idGenerator;

    // 과목 타입
    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    public StudentManager(List<Student> studentStore, List<Subject> subjectStore,
                          ConsoleIO consoleIO, IdSequenceGenerator idGenerator) {
        this.studentStore = studentStore;
        this.subjectStore = subjectStore;
        this.consoleIO = consoleIO;
        this.idGenerator = idGenerator;
    }

    public void createStudent() {
        consoleIO.print("\n수강생을 등록합니다...");
        String studentName = consoleIO.getStringInput("수강생 이름 입력: ");

        List<String> mandatorySubjectIds = selectMandatorySubjects();
        List<String> choiceSubjectIds = selectChoiceSubjects();

        List<String> allSubjectIds = new ArrayList<>(mandatorySubjectIds);
        allSubjectIds.addAll(choiceSubjectIds);

        Student student = new Student(idGenerator.generate("ST"), studentName, allSubjectIds);
        studentStore.add(student);

        consoleIO.print("수강생 등록 성공!\n");
    }

    public void inquireStudents() {
        consoleIO.print("\n수강생 목록을 조회합니다...");
        consoleIO.print("1. 전체 수강생 목록 조회");
        consoleIO.print("2. 상태별 수강생 목록 조회");
        consoleIO.print("3. 돌아가기");

        int input = consoleIO.getIntInput("조회 항목을 선택하세요...");

        switch (input) {
            case 1 -> {
                consoleIO.print("\n전체 수강생 목록을 조회합니다...");
                for (Student student : studentStore) {
                    consoleIO.print(String.format("ID: %s | Name: %s",
                            student.getStudentId(), student.getStudentName()));
                }
                consoleIO.print("\n전체 수강생 목록 조회 성공!");
            }
            case 2 -> {
                consoleIO.print("\n상태별 수강생 목록을 조회합니다...");
                consoleIO.print("1. Green");
                consoleIO.print("2. Red");
                consoleIO.print("3. Yellow");
                int statusChoice = consoleIO.getIntInput("상태를 선택하세요: ");

                String status = switch (statusChoice) {
                    case 1 -> "Green";
                    case 2 -> "Red";
                    case 3 -> "Yellow";
                    default -> {
                        consoleIO.print("잘못된 입력입니다.");
                        yield "";
                    }
                };

                if (!status.isEmpty()) {
                    List<Student> filteredStudents = studentStore.stream()
                            .filter(student -> status.equalsIgnoreCase(student.getStudentStatus()))
                            .toList();

                    if (filteredStudents.isEmpty()) {
                        consoleIO.print(String.format("\n%s 상태의 수강생이 없습니다.", status));
                    } else {
                        for (Student student : filteredStudents) {
                            consoleIO.print(String.format("ID: %s | Name: %s",
                                    student.getStudentId(), student.getStudentName()));
                        }
                        consoleIO.print(String.format("\n%s 상태의 수강생 목록 조회 성공!", status));
                    }
                }
            }
            case 3 -> consoleIO.print("돌아갑니다...");
            default -> consoleIO.print("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    public void inquireStudentById(String studentId) {
        Optional<Student> optionalStudent = studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst();

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            printStudentInfo(student);
            // Display further options for the student
            displayStudentOptions(student);
        } else {
            consoleIO.print("해당 ID의 수강생을 찾을 수 없습니다.");
        }
    }

    public void updateStudent(String studentId) {
        Optional<Student> optionalStudent = studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst();

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            boolean flag = true;
            while (flag) {
                consoleIO.print("1. 이름 수정");
                consoleIO.print("2. 상태 수정");
                consoleIO.print("3. 돌아가기");
                int input = consoleIO.getIntInput("수정 항목을 선택하세요...");

                switch (input) {
                    case 1 -> {
                        String newName = consoleIO.getStringInput("새로운 이름 입력: ");
                        student.setStudentName(newName);
                        consoleIO.print("이름 수정 성공!\n");
                        printStudentInfo(student);  // 다시 해당 수강생 정보를 표시
                        flag = false;
                        displayStudentOptions(student);  // 돌아가기
                    }
                    case 2 -> {
                        String newStatus = consoleIO.getStringInput("새로운 상태 입력 (Green / Red / Yellow): ");
                        student.setStudentStatus(newStatus);
                        consoleIO.print("상태 수정 성공!\n");
                        printStudentInfo(student);  // 다시 해당 수강생 정보를 표시
                        flag = false;
                        displayStudentOptions(student);  // 돌아가기
                    }
                    case 3 -> flag = false;
                    default -> consoleIO.print("잘못된 입력입니다. 다시 시도하세요.");
                }
            }
        } else {
            consoleIO.print("해당 ID의 수강생을 찾을 수 없습니다.");
        }
    }

    public void deleteStudent(String studentId) {
        Optional<Student> optionalStudent = studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst();

        if (optionalStudent.isPresent()) {
            studentStore.remove(optionalStudent.get());
            consoleIO.print("수강생 정보 삭제 성공!\n");
        } else {
            consoleIO.print("해당 ID의 수강생을 찾을 수 없습니다.");
        }
    }

    private void displayStudentOptions(Student student) {
        boolean flag = true;
        while (flag) {
            consoleIO.print("1. 수강생 정보 수정");
            consoleIO.print("2. 수강생 정보 삭제");
            consoleIO.print("3. 돌아가기");
            int input = consoleIO.getIntInput("관리 항목을 선택하세요...");

            switch (input) {
                case 1 -> updateStudent(student.getStudentId());
                case 2 -> {
                    deleteStudent(student.getStudentId());
                    flag = false;
                }
                case 3 -> flag = false;
                default -> consoleIO.print("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private void printStudentInfo(Student student) {
        List<String> subjectNames = student.getSubjectIds().stream()
                .map(this::getSubjectName)
                .collect(Collectors.toList());

        consoleIO.print(String.format("ID: %s | Name: %s | Status: %s | Subjects: %s",
                student.getStudentId(), student.getStudentName(), student.getStudentStatus(),
                String.join(", ", subjectNames)));
    }

    private List<String> selectMandatorySubjects() {
        return selectSubjects(SUBJECT_TYPE_MANDATORY, 3);
    }

    private List<String> selectChoiceSubjects() {
        return selectSubjects(SUBJECT_TYPE_CHOICE, 2);
    }

    private List<String> selectSubjects(String subjectType, int minSubjects) {
        List<String> selectedSubjects = new ArrayList<>();
        List<Subject> availableSubjects = subjectStore.stream()
                .filter(subject -> subject.getSubjectType().equals(subjectType))
                .toList();

        String subjectTypeName = subjectType.equals(SUBJECT_TYPE_MANDATORY) ? "필수" : "선택";
        consoleIO.print(String.format("%s 과목 목록:", subjectTypeName));
        for (int i = 0; i < availableSubjects.size(); i++) {
            Subject subject = availableSubjects.get(i);
            consoleIO.print(String.format("%d. %s", i + 1, subject.getSubjectName()));
        }

        while (true) {
            int subjectNumber = consoleIO.getIntInput(
                    String.format("최소 %d개 이상의 %s 과목을 선택하세요 (0 입력 시 선택 종료): ", minSubjects, subjectTypeName)
            );

            if (subjectNumber == 0) {
                if (selectedSubjects.size() < minSubjects) {
                    consoleIO.print(String.format("%s 과목은 최소 %d개를 선택해야 합니다.", subjectTypeName, minSubjects));
                } else {
                    break;
                }
            } else if (subjectNumber < 1 || subjectNumber > availableSubjects.size()) {
                consoleIO.print("잘못된 번호입니다. 다시 시도하세요.");
            } else {
                Subject selectedSubject = availableSubjects.get(subjectNumber - 1);
                if (!selectedSubjects.contains(selectedSubject.getSubjectId())) {
                    selectedSubjects.add(selectedSubject.getSubjectId());
                    consoleIO.print(String.format("과목 %s이(가) 선택되었습니다.", selectedSubject.getSubjectName()));
                } else {
                    consoleIO.print("이미 선택한 과목입니다.");
                }
            }
        }

        return selectedSubjects;
    }

    private String getSubjectName(String subjectId) {
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .map(Subject::getSubjectName)
                .findFirst()
                .orElse("Unknown");
    }
}



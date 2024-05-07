package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.view.ConsoleIO;

import java.util.List;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;
    private static StudentManager studentManager;
    private static ConsoleIO consoleIO = new ConsoleIO();
    private static IdSequenceGenerator idGenerator = new IdSequenceGenerator();

    public static void main(String[] args) {
        initializeData();
        displayMainView();
        System.out.println("프로그램을 종료합니다.");
    }

    private static void initializeData() {
        studentStore = DataInitializer.initializeStudents();
        subjectStore = DataInitializer.initializeSubjects(idGenerator);
        scoreStore = DataInitializer.initializeScores();
        studentManager = new StudentManager(studentStore, subjectStore, consoleIO, idGenerator);
    }

    private static void displayMainView() {
        boolean flag = true;
        while (flag) {
            consoleIO.displayMainMenu();
            int input = consoleIO.getIntInput("관리 항목을 선택하세요...");

            switch (input) {
                case 1 -> displayStudentView();
                case 2 -> displayScoreView();
                case 3 -> flag = false;
                default -> {
                    consoleIO.print("잘못된 입력입니다.\n되돌아갑니다!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        consoleIO.print("프로그램 오류 발생!");
                        flag = false;
                    }
                }
            }
        }
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            consoleIO.displayStudentMenu();
            int input = consoleIO.getIntInput("관리 항목을 선택하세요...");

            switch (input) {
                case 1 -> studentManager.createStudent();
                case 2 -> studentManager.inquireStudents();
                case 3 -> {
                    String studentId = consoleIO.getStringInput("조회할 수강생의 ID를 입력하세요: ");
                    studentManager.inquireStudentById(studentId);
                }
                case 4 -> flag = false;
                default -> {
                    consoleIO.print("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            consoleIO.displayScoreMenu();
            int input = consoleIO.getIntInput("관리 항목을 선택하세요...");

            switch (input) {
                case 1 -> createScore();
                case 2 -> updateRoundScoreBySubject();
                case 3 -> inquireRoundGradeBySubject();
                case 4 -> flag = false;
                default -> {
                    consoleIO.print("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void createScore() {
        consoleIO.print("시험 점수를 등록합니다...");
        consoleIO.print("\n점수 등록 성공!");
    }

    private static void updateRoundScoreBySubject() {
        consoleIO.print("시험 점수를 수정합니다...");
        consoleIO.print("\n점수 수정 성공!");
    }

    private static void inquireRoundGradeBySubject() {
        consoleIO.print("회차별 등급을 조회합니다...");
        consoleIO.print("\n등급 조회 성공!");
    }
}

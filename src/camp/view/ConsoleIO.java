package camp.view;

import java.util.Scanner;

public class ConsoleIO {
    private static final Scanner sc = new Scanner(System.in);

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        return sc.nextInt();
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        sc.nextLine(); // Clear newline
        return sc.nextLine();
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void displayMainMenu() {
        print("\n==================================");
        print("내일배움캠프 수강생 관리 프로그램 실행 중...");
        print("1. 수강생 관리");
        print("2. 점수 관리");
        print("3. 프로그램 종료");
    }

    public void displayStudentMenu() {
        print("==================================");
        print("수강생 관리 실행 중...");
        print("1. 수강생 등록");
        print("2. 수강생 목록 조회");
        print("3. 수강생 검색");
        print("4. 메인 화면 이동");
    }

    public void displayScoreMenu() {
        print("==================================");
        print("점수 관리 실행 중...");
        print("1. 수강생의 과목별 시험 회차 및 점수 등록");
        print("2. 수강생의 과목별 회차 점수 수정");
        print("3. 수강생의 특정 과목 회차별 등급 조회");
        print("4. 메인 화면 이동");
    }
}

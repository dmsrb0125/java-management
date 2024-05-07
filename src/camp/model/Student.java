package camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private String studentStatus;
    private List<String> subjectIds;

    public Student(String studentId, String studentName, List<String> subjectIds) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentStatus = "Unknown";  // 기본 상태
        this.subjectIds = subjectIds;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public List<String> getSubjectIds() {
        return subjectIds;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentStatus='" + studentStatus + '\'' +
                ", subjectIds=" + subjectIds +
                '}';
    }
}

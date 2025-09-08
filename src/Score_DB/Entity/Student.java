package Score_DB.Entity;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;                // 학생 이름
    List<Integer> recode = new ArrayList<>();               // 점수 배열
    private int total;                  // 총합 점수
    private float average;              // 점수 평균
    private String grade;              // 학생 학점

    public Student() {
        this.name = "^^";
    }

    @Override
    public String toString() {
        return name + "(총점 =" + total + ", " + "평균 =" + average + ", " + "학점 = " + grade + ")";
    }

    // 총합 구하기
    public void cal_total() {
        for (int score : recode) {
            this.total += score;
        }
    }

    // 평균 구하기
    public void cal_average() {
        this.average = (float) this.total / recode.size();
    }

    //학점 구하기
    public void cal_grade() {
        int score = (int) this.average;
        if (score >= 90) {
            this.grade = "A";
        }else if (score >= 80) {
            this.grade = "B";
        }else if (score >= 70) {
            this.grade = "C";
        }else if (score >= 60) {
            this.grade = "D";
        }else{
            this.grade = "F";
        }
    }
}

package DAO;

import Beans.Classroom;
import Beans.Exam;
import Beans.Section;
import Beans.TimeSlot;

public class test {
    public static void main(String[]ars){
        TakesDAO takesDAO = new TakesDAO();
        Section section = new Section("C002","K002");
        takesDAO.insertSectionToTakes("S001",section);
//        TeachesDAO teachesDAO = new TeachesDAO();
//        teachesDAO.insert("T001","C002","K002");

//        ExamDAO examDAO = new ExamDAO();
//        Exam exam = new Exam("C001","K001");
//        exam.setBuilding("Z");
//        exam.setRoomnumber("Z2201");
//        exam.setExamday(1);
//        exam.setExamstarttime(1);
//        exam.setExamendtime(3);
//        examDAO.insertExam(exam);
    }
}

package Service;

import Beans.*;
import DAO.*;
import util.Encode;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

public class InsertSectionService {
    public static String insertSection(HttpServletRequest request) throws Exception{
        SectionDAO sectionDAO = new SectionDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        CourseDAO courseDAO = new CourseDAO();
        TimeSlotDAO timeSlotDAO = new TimeSlotDAO();
        TeachesDAO teachesDAO = new TeachesDAO();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        ExamDAO examDAO = new ExamDAO();

        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String sectionName = Encode.parseToUTF8(request.getParameter("coursename"));
        String teacherid = request.getParameter("teacherid");
        String building = Encode.parseToUTF8(request.getParameter("building"));
        String roomnumber = request.getParameter("classroom");
        String examtype = Encode.parseToUTF8(request.getParameter("examtype"));
        String exambuilding = Encode.parseToUTF8(request.getParameter("exambuilding"));
        String examroomnumber = request.getParameter("examroomnumber");
        String examday = request.getParameter("examday");
        String examstart = request.getParameter("examstart");
        String examend = request.getParameter("examend");
        String studentnum = request.getParameter("studentnumberlimit");
        String day1 = request.getParameter("day1");
        String day2 = request.getParameter("day2");
        String day3 = request.getParameter("day3");
        String start1 = request.getParameter("start1");
        String start2 = request.getParameter("start2");
        String start3 = request.getParameter("start3");
        String end1 = request.getParameter("end1");
        String end2 = request.getParameter("end2");
        String end3 = request.getParameter("end3");
        Classroom classroom1 = new Classroom(building,roomnumber);
        Classroom classroom2 = new Classroom(exambuilding,examroomnumber);

        String[] s = id.split("\\.");
        String courseid = s[0];
        String sectionid = s[1];
        Section section = new Section(courseid, sectionid);

        Exam exam = new Exam(courseid,sectionid);
        exam.setBuilding(building);
        exam.setRoomnumber(roomnumber);
        exam.setExamday(Integer.parseInt(examday));
        exam.setExamstarttime(Integer.parseInt(examstart));
        exam.setExamendtime(Integer.parseInt(examend));

//        System.out.println("a"+ courseDAO.getCourseNameByID(courseid));
//        System.out.println(sectionName);
//        System.out.println("c"+courseid);

        if (sectionDAO.isInCourse(section)&&courseDAO.getCourseNameByID(courseid).equals(sectionName)) {
            User teacher = new User(teacherid);

            if (teacherDAO.hasTeacher(teacher)){//判断老师存在

                if(classroomDAO.haveClassroom(classroom1)&&classroomDAO.haveClassroom(classroom2)){                 //判断教室存在

                section.setSectionName(sectionName);
                section.setBuilding(building);
                section.setRoomNumber(roomnumber);
                section.setStudentNumberLimit(Integer.parseInt(studentnum));
                section.setExamType(examtype);

//                String t ="";
                String t = day1 +start1 + end1 + day2 +start2 + end2 + day3 +start3 + end3;

                TimeSlot timeSlot = new TimeSlot(Integer.parseInt(t));
                if (!day1.equals("") && !start1.equals("") && !end1.equals("")) {
                    timeSlot.setDay(Integer.parseInt(day1));
                    timeSlot.setStartTime(Integer.parseInt(start1));
                    timeSlot.setEndTime(Integer.parseInt(end1));
                    if(!timeSlotDAO.haveTimeSlot(timeSlot))
                    timeSlotDAO.insertTimeSlot(timeSlot);
                }
                if (!day2.equals("") && !start2.equals("") && !end2.equals("")) {
                    timeSlot.setDay(Integer.parseInt(day2));
                    timeSlot.setStartTime(Integer.parseInt(start2));
                    timeSlot.setEndTime(Integer.parseInt(end2));
                    if(!timeSlotDAO.haveTimeSlot(timeSlot))
                    timeSlotDAO.insertTimeSlot(timeSlot);
                }
                if (!day3.equals("")&& !start3.equals("") && !end3.equals("")) {
                    timeSlot.setDay(Integer.parseInt(day3));
                    timeSlot.setStartTime(Integer.parseInt(start3));
                    timeSlot.setEndTime(Integer.parseInt(end3));
                    if(!timeSlotDAO.haveTimeSlot(timeSlot))
                    timeSlotDAO.insertTimeSlot(timeSlot);
                }

                section.setTimeSlotId(timeSlot.getTimeSlotId());
                if(sectionDAO.haveSection(section)){                 //................
                    return "课程已存在";
                }
                sectionDAO.insertSetion(section);
                teachesDAO.insert(teacherid, courseid, sectionid);
                examDAO.insertExam(exam);

            }else {
                    return "教室不存在";
                }
        }else {
                return "教师不存在";
            }
        }else {
            return "课程不存在";
        }
    return null;
    }

}

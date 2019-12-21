package Service;

import Beans.Classroom;
import Beans.Section;
import DAO.ClassroomDAO;
import DAO.SectionDAO;
import util.Encode;

import javax.servlet.http.HttpServletRequest;

public class UpdateSectionService {
    public static String updateSection(HttpServletRequest request)throws Exception{

        ClassroomDAO classroomDAO = new ClassroomDAO();
        SectionDAO sectionDAO = new SectionDAO();

        String id = request.getParameter("id");
        String numberlimit = request.getParameter("numberlimit");
        String building = Encode.parseToUTF8(request.getParameter("building"));
        String roomnumber =  Encode.parseToUTF8(request.getParameter("roomnumber"));

        String courseid ="";
        String sectionid = "";
        if(id.contains(".")){
            String[] s = id.split("\\.");
            courseid = s[0];
            sectionid = s[1];
        }

//        Section section = new Section(courseid,sectionid);
        String re = "";

        if(!building.equals("")&&!roomnumber.equals("")){
            Classroom classroom = new Classroom(building,roomnumber);
            if(classroomDAO.haveClassroom(classroom)){
                Section newsection = sectionDAO.getSectionByCourseAndSectionid(courseid,sectionid);
                re = sectionDAO.updateSectionBuilding(newsection,building,roomnumber);
                if(!re.equals("修改成功")){
                    return re;
                }
            }
        }
        if(!numberlimit.equals("")){
            int limit = Integer.parseInt(numberlimit);
            Section newsection = sectionDAO.getSectionByCourseAndSectionid(courseid,sectionid);
            re = sectionDAO.updateSectionLimit(newsection,limit);
            if(!re.equals("修改成功")){
                return re;
            }
        }
        return re;
    }
}

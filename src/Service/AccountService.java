package Service;

import Beans.User;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import util.PermissionUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountService {
    private String ROOTPW="root";
    TeacherDAO teacherDAO=new TeacherDAO();
    StudentDAO studentDAO=new StudentDAO();
    public boolean Login(String id, String passwd, HttpServletRequest request){
        User user=null;
        if(id.startsWith(User.ROOT_START)){
            user=new User("root","admin",ROOTPW);
        }else if(id.startsWith(User.TEACHER_START)){
            user=teacherDAO.getUserByTeacherID(id);
        }else if(id.startsWith(User.STUDENT_START)){
            user=studentDAO.getUserByStudenetID(id);
        }else {
            return false;
        }
        if (user==null||!user.passwordCorrect(passwd)) {
            return false;
        }else{
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            return true;
        }
    }
    public void RedirectToCorrectPage(HttpServletRequest request, HttpServletResponse response){
        User user=(User)request.getSession().getAttribute("user");
        String des="/xk/";
        if(user==null){

        }else if(user.isRoot()){
            des+="addSection.jsp";
        }else if(user.isTeacher()){
            des+="teacherSchedule.jsp";
        }else if(user.isStudent()){
            des+="studentIndex.jsp";
        }
        PermissionUtil.setRedirectTo(des,response);
        return;
    }
    public void Logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
    }
}

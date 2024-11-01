package ec_takara;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String sname=request.getParameter("sname");
		String mname=request.getParameter("mname");
		String password=request.getParameter("password");
		String user_id=request.getParameter("user_id");
		String address=request.getParameter("address");
		String sex=request.getParameter("sex");
		String mm=request.getParameter("mm");
		String dd=request.getParameter("dd");
		String yyyy=request.getParameter("yyyy");
		String date=yyyy+"-"+mm+"-"+dd;
		Date b_day=Date.valueOf(date);
		String user_name=mname+sname;
		String url=null; 
		if(user_id=="" || sname=="" || password=="" || mname=="" || address=="" || sex=="" || mm=="" || dd=="" || yyyy=="" ) {
			url="/jsp/member.jsp";
			request.setAttribute("emptyerror",true);
		}else {
		boolean memberbean=MemberBean.member(user_id);//ユーザー周りのBean useridの重複を確かめるmethod
			if(memberbean==true) {
				url="/jsp/member.jsp";
				request.setAttribute("misserror",true);
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("member",true);
				MemberBean user=new MemberBean();
				url="/jsp/member_check.jsp";
				user.setUser_name(user_name);
				user.setPassword(password);
				user.setUser_id(user_id);
				user.setSex(sex);
				user.setAddress(address);
				user.setB_day(b_day);
				session.setAttribute("user",user);
			}
		}
			ServletContext context=getServletContext();
			RequestDispatcher rd=
					context.getRequestDispatcher(url);
			rd.forward(request, response);
		
	}

}

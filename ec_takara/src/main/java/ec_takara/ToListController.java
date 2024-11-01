package ec_takara;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ToListController
 */
@WebServlet("/ToListController")
public class ToListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		session.setAttribute("login",true);
	try {
		int pos=Integer.parseInt(request.getParameter("pos"));
		ArrayList<GoodBean> goodbeanlist=GoodBean.selectAllList(pos);
		session.setAttribute("goodbeanlist", goodbeanlist);
		PageBean pb=new PageBean();
		if(goodbeanlist.size()<20) {
			pb.setNext(pos);
		}else {
			pb.setNext(pos+20);
		}
		if((pos-20)<=0) {
			pb.setPrevious(pos);
		}else {
			pb.setPrevious(pos-20);
		}
		session.setAttribute("pb", pb);
	}catch(Exception e) {
		e.printStackTrace();
	}
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/shohin.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		MemberBean user=(MemberBean)session.getAttribute("user");
		String user_id=user.getUser_id();
		session.setAttribute("user_id", user_id);
		MemberBean.insertData(user);//userにはuser情報 user情報をdatabaseに格納するmethod
		session.setAttribute("login",true);
	try {
		int pos=Integer.parseInt(request.getParameter("pos"));
		ArrayList<GoodBean> goodbeanlist=GoodBean.selectAllList(pos);
		session.setAttribute("goodbeanlist", goodbeanlist);
		PageBean pb=new PageBean();
		if(goodbeanlist.size()<20) {
			pb.setNext(pos);
		}else {
			pb.setNext(pos+20);
		}
		if((pos-20)<=0) {
			pb.setPrevious(pos);
		}else {
			pb.setPrevious(pos-20);
		}
		session.setAttribute("pb", pb);
	}catch(Exception e) {
		e.printStackTrace();
	}
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/shohin.jsp");
		rd.forward(request, response);
	}

}

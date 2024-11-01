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
 * Servlet implementation class OperationPageController
 */
@WebServlet("/OperationPageController")
public class OperationPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		int pos = Integer.parseInt(request.getParameter("pos"));
		String relativePath = "/jsp/outputAll.csv";
		String absolutePath = getServletContext().getRealPath(relativePath);
		ArrayList<TakaraBean> tblist=TakaraBean.s_info(pos,absolutePath);
		session.setAttribute("tblist", tblist);
		PageBean pb2=new PageBean();
		if(tblist.size()<20) {
			pb2.setNext(pos);
		}else {
			pb2.setNext(pos+20);
		}
		if((pos-20)<=0) {
			pb2.setPrevious(pos);
		}else {
			pb2.setPrevious(pos-20);
		}
		session.setAttribute("pb2", pb2);
		
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/operation.jsp");
		rd.forward(request, response);
	}

}

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
 * Servlet implementation class ToConfirmController
 */
@WebServlet("/ToConfirmController")
public class ToConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToConfirmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		ArrayList<CartBean> cartlist= CartBean.UClist(user_id);//カートの中　データベースから
		session.setAttribute("cartlist", cartlist);
		ArrayList<CartBean> cart=(ArrayList<CartBean>)session.getAttribute("cartlist");
		if(cart==null || cart.isEmpty()) {
			request.setAttribute("cartemptyerror",true);
			ServletContext context=getServletContext();
			RequestDispatcher rd=
					context.getRequestDispatcher("/jsp/cart.jsp");
			rd.forward(request, response);
		}
		else {
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/buy.jsp");
		rd.forward(request, response);
		}
	}

}



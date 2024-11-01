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
 * Servlet implementation class ToBuyController
 */
@WebServlet("/ToBuyController")
public class ToBuyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToBuyController() {
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
		ArrayList<CartBean> cartlist=(ArrayList<CartBean>)session.getAttribute("cartlist");
		String user_id=(String)session.getAttribute("user_id");
		
		BoughtBean.insertData(cartlist);
		//boughtデータベースに入れる
		CartBean.dlAData(user_id);
		//カートデータベースから消す
		session.setAttribute("cartcom", cartlist);//カートの中身
		
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/complete.jsp");
		rd.forward(request, response);
	}

}

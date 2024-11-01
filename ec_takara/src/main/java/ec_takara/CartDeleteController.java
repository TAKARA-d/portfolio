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
 * Servlet implementation class CartDeleteController
 */
@WebServlet("/CartDeleteController")
public class CartDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDeleteController() {
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
		int goods_id=Integer.parseInt(request.getParameter("goods_id"));
		
			CartBean cart=new CartBean();
			cart.setUser_id(user_id);
			cart.setGoods_id(goods_id);
			String goods_name=CartBean.chData(cart);//カートの中の商品をデータベースからdeleteするメソッド
			request.setAttribute("goods_name", goods_name); //データベースから削除した商品の名前
			request.setAttribute("delete", true);	
			ArrayList<CartBean> cartlist= CartBean.UClist(user_id);//カートの中　データベースから
			session.setAttribute("cartlist", cartlist);
			
			ServletContext context=getServletContext();
			RequestDispatcher rd=
					context.getRequestDispatcher("/jsp/cart.jsp");
			rd.forward(request, response);
	}

}

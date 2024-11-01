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
 * Servlet implementation class ShousaiToCart
 */
@WebServlet("/ShousaiToCart")
public class ShousaiToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShousaiToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try {//cuantity->個数　name->品名
			HttpSession session=request.getSession();
			ServletContext sc=getServletContext();
			int goods_id=Integer.parseInt(request.getParameter("goods_id"));
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			/*String goods_name=request.getParameter("goods_name");
			String writer=request.getParameter("writer");
			int price=Integer.parseInt(request.getParameter("price"));
			String book_kinds=request.getParameter("book_kinds");*/
			String user_id=(String)session.getAttribute("user_id");
					/*if(quantity !=0) {  //購入確認の後に入れる
						BoughtBean order=new BoughtBean();
						order.setGoods_id(goods_id);
						order.setQuantity(quantity);
						order.setUser_id(user_id);
						orderlist.add(order);
					}*/
					CartBean cart=new CartBean();
							cart.setGoods_id(goods_id);
							cart.setQuantity(quantity);
							cart.setUser_id(user_id);
					CartBean.insertData(cart);
					ArrayList<CartBean> cartlist= CartBean.UClist(user_id);//カートの中　データベースから
					session.setAttribute("cartlist", cartlist);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/cart.jsp");
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try {//cuantity->個数　name->品名
			HttpSession session=request.getSession();
			String user_id=(String)session.getAttribute("user_id");
					
					ArrayList<CartBean> cartlist= CartBean.UClist(user_id);//カートの中　データベースから
					session.setAttribute("cartlist", cartlist);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/cart.jsp");
		rd.forward(request, response);
	}

}

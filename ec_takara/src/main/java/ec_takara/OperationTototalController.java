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
 * Servlet implementation class OperationTototalController
 */
@WebServlet("/OperationTototalController")
public class OperationTototalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationTototalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int pos = Integer.parseInt(request.getParameter("pos"));
		String sex=request.getParameter("sex");
		String book_kinds=request.getParameter("book_kinds");
		String generation=request.getParameter("era");
			HttpSession session=request.getSession();
			
			TakaraBean tb=new TakaraBean();
			if(!(sex.equals("null"))) {
			tb.setSex(sex);
			}
			if(!(book_kinds.equals("null"))){
			tb.setBook_kinds(book_kinds);
			}
			if(!(generation.equals("null"))) {
			tb.setGeneration(generation);
			}
			String relativePath = "/jsp/output.csv";
			String absolutePath = getServletContext().getRealPath(relativePath);
			ArrayList<TakaraBean> tblist2=TakaraBean.getTB(TakaraBean.searchtb(tb), tb,pos,absolutePath);
			session.setAttribute("tblist2", tblist2);
			PageBean pb3=new PageBean();
			if(tblist2.size()<20) {
				pb3.setNext(pos);
			}else {
				pb3.setNext(pos+20);
			}
			if((pos-20)<=0) {
				pb3.setPrevious(pos);
			}else {
				pb3.setPrevious(pos-20);
			}
			session.setAttribute("pb3", pb3);
			
		ServletContext context=getServletContext();
		RequestDispatcher rd=
				context.getRequestDispatcher("/jsp/total.jsp");
		rd.forward(request, response);
		}
		}



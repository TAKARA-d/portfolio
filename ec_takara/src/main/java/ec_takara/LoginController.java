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
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		LoginBean loginbean=null;
		String url=null;
		int pos = Integer.parseInt(request.getParameter("pos"));
		try {
			/*渡されたデータをloginnameとpasswode変数に格納*/
			String loginid = request.getParameter("loginid");
			String password = request.getParameter("password");
			LoginBean lb=LoginBean.Rinfo();
			if(loginid=="" || password=="")
			{
				url="/jsp/fail.jsp";
				System.out.println(2);
			}else {
				/*ユーザー名とパスワードの認証loginクラスのloginメソッドの呼び出し*/
				/*認証成功の場合は、「ログイン成功画面」（shohin.jsp）へ移行
				 認証失敗の場合は、「ログイン失敗画面 」（fail.jsp）へ移行
				 認証成功して特定(takara,takara)のID、パスワードは「管理者ページ」（operation.jsp）へ移行*/
			boolean memberbean=MemberBean.member(loginid);//ユーザー周りのBean useridの重複を確かめるmethod
				if(memberbean==true) {
					loginbean=LoginBean.login(loginid);
					
					if(loginid.equals(lb.getLoginid()) && password.equals(lb.getPassword())) {
						TakaraBean.age();
						TakaraBean.generation();
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
						session.setAttribute("login",true);
						url="/jsp/operation.jsp";
					}else if(loginbean.getLoginid().equals(loginid)&&loginbean.getPassword().equals(password))
				{
		 			url="/jsp/shohin.jsp";
					session.setAttribute("login", true);
					session.setAttribute("user_id",loginid);
					ArrayList<GoodBean> goodbeanlist=GoodBean.selectAllList(pos);
					session.setAttribute("goodbeanlist", goodbeanlist);
					PageBean pb = new PageBean();
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
				
					
				}else {
					url="/jsp/fail.jsp";
					System.out.println(1);
				}
				}else {
					url="/jsp/fail.jsp";
					System.out.println(1);
				}
			}
						
			
		}catch(Exception e) {
			e.printStackTrace();
		}
					ServletContext context=getServletContext();
					RequestDispatcher rd=
							context.getRequestDispatcher(url);
					rd.forward(request, response);
}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		session.invalidate();
		ServletContext context=getServletContext();
		RequestDispatcher rd=context.getRequestDispatcher("/jsp/login.jsp");
		rd.forward(request,response);
	}
}
				
			
		
	

package ec_takara;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*"/*urlPatterns = {"/jsp/login.jsp","/LoginController","/CartDeleteController","/LogoutController","/ShouhinToShousaiController","/ToBuyController","/ToConfirmController","/ShousaiToCart","/OperationTototalController","/jsp/buy.jsp","/jsp/cart.jsp","/jsp/complete.jsp","/jsp/fail.jsp","/jsp/logout.jsp","/jsp/operation.jsp","/jsp/shohin.jsp","/jsp/syousai.jsp","/jsp/total.jsp"}*/)
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest httprequest=(HttpServletRequest)request;
		HttpServletResponse httpresponse=(HttpServletResponse)response;
		
		HttpSession session=httprequest.getSession();
		if(session.getAttribute("login")==null) {
			session.setAttribute("login", false);
		}
		//リダイレクトの無限ループ対策
		if(session.getAttribute("member")==null) {
			session.setAttribute("member", false);
		}
		if(httprequest.getRequestURI().equals("/ec_takara/jsp/login.jsp")) {
			chain.doFilter(httprequest, httpresponse);
		//ログイン処理要求はそのままスルー
		}else if(httprequest.getRequestURI().equals("/ec_takara/LoginController")) {
			chain.doFilter(httprequest, httpresponse);
		//ログイン後はすべてのページに自由にアクセス
		}else if(httprequest.getRequestURI().equals("/ec_takara/jsp/Style.css")) {
			chain.doFilter(httprequest, httpresponse);
		//ログイン後はすべてのページに自由にアクセス
		}else if((Boolean)session.getAttribute("login")) {
			chain.doFilter(httprequest, httpresponse);
		}else if(httprequest.getRequestURI().equals("/ec_takara/jsp/member.jsp")) {
			chain.doFilter(httprequest, httpresponse);
		//ログイン処理要求はそのままスルー
		}else if(httprequest.getRequestURI().equals("/ec_takara/MemberController")) {
			chain.doFilter(httprequest, httpresponse);
		}else if((Boolean)session.getAttribute("member")) {
			chain.doFilter(httprequest, httpresponse);
		}else {
			httpresponse.sendRedirect("http://localhost:8080/ec_takara/jsp/login.jsp");
		}
		

		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

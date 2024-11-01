package ec_takara;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class LoginBean implements Serializable {
	private String loginid=null; //ユーザーID
	private String password=null; //パスワード
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static LoginBean login(String loginid) throws SQLException, NamingException{
		Connection conn=null;
		PreparedStatement st =null;
		ResultSet rs=null;
		LoginBean loginbean=null;
		
		try {
			loginbean=new LoginBean();
			conn=new ResourceFinder().getConnection();
			st=conn.prepareStatement("SELECT user_id,password FROM users where user_id=?");
			st.setString(1, loginid);//ここ要確認
			//↓バグ取りで使ったのでしょうか？表示の必要はないかと思います
			//System.out.println(loginid);
			rs=st.executeQuery(); 
			rs.next(); //ここでfor文とかいれるの…か？
			loginbean.setLoginid(rs.getString("user_id"));
			loginbean.setPassword(rs.getString("password"));
			//↓バグ取りで使ったのでしょうか？表示の必要はないかと思います
			//System.out.println(rs.getString("password"));
		}catch(SQLException e) {
			throw e;
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			}catch(SQLException e) {
				throw e;
			}
		}
		return loginbean;
	}

		
	public static LoginBean Rinfo(){
		Connection conn=null;
		Statement st =null;
		ResultSet rs=null;
		LoginBean rinfo=null;
		
		try {
			rinfo=new LoginBean();
			conn=new ResourceFinder().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("SELECT * FROM r_user");
			rs.first();
			rinfo.setLoginid(rs.getString("user_id"));
			rinfo.setPassword(rs.getString("password"));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return rinfo;
	}

	
	
}
package ec_takara;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberBean implements Serializable{ //何も信頼しないで
	private String user_name=null;
	private String sex=null;
	private String address=null;
	private Date b_day;
	private String password=null;
	private String user_id=null;
	
	public Date getB_day() {
		return b_day;
	}
	public void setB_day(Date b_day) {
		this.b_day = b_day;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//重複があったらtrue,なかったらfalseを返すメソッド　アカウント作成　MemberControllerで使用
	public static boolean member(String user_id) {
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		boolean b=true;
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.prepareStatement("select user_id from users where user_id=?");
			st.setString(1,user_id);
			rs=st.executeQuery();
			b=rs.first();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				conn.close();
			}catch(Exception e) {
			}
		}
		return b;
	}


	public static void insertData(MemberBean user) {
		//例12参考にしてる
		Connection conn=null;
		MemberBean memberbean = null; 
		PreparedStatement st =null;
		
		try {
			conn = new ResourceFinder().getConnection();
			st = conn.prepareStatement("insert into users(user_id,password,user_name,sex,address,b_day) values(?,?,?,?,?,?)"); 
			//ここで入力情報をデータベースにいれるんかな…
				memberbean=(MemberBean)user; //memberbeanlist?
				st.setString(1,memberbean.getUser_id());
				st.setString(2,memberbean.getPassword()); //この2つメソッドがあるのがLoginBean　loginbean.getPassword()?←後で聞く
				st.setString(3,memberbean.getUser_name());
				st.setString(4,memberbean.getSex());
				st.setString(5,memberbean.getAddress());
				st.setDate(6,memberbean.getB_day());
				st.executeUpdate();
				st.clearParameters();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				conn.close();
			}catch(Exception e) {
			}
		}
		
	}
}

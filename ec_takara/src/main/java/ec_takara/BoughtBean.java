package ec_takara;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BoughtBean /*extends CartBean*/ implements Serializable {
	private String user_id=null;
	private int goods_id;
	private int quantity;
	
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//購入情報をDBに格納
	public static void insertData(ArrayList<CartBean> clist) {
		Connection conn=null;
		PreparedStatement st=null;
		CartBean bought=null;
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.prepareStatement
					("insert into bought(user_id,goods_id,quantity) values(?,?,?)");
			for(int i=0;i<clist.size();i++) {
				bought=(CartBean)clist.get(i);
				st.setString(1,bought.getUser_id());
				st.setInt(2,bought.getGoods_id());
				st.setInt(3,bought.getQuantity());
				st.executeUpdate();
				st.clearParameters();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static ArrayList<BoughtBean> selectBoughttable(int pos) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BoughtBean> boughtbeanlist=new ArrayList<BoughtBean>();
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select * from bought");
			rs.next();
			for(int i=0;i<20;i++) {
				BoughtBean bought=new BoughtBean();
				bought.setGoods_id(rs.getInt("goods_id"));
				bought.setQuantity(rs.getInt("quantity"));
				bought.setUser_id(rs.getString("user_id"));
				boughtbeanlist.add(bought);
				boolean b=rs.next();
				if(!b)break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			}catch(Exception e) {
			}
		}return boughtbeanlist;
	}
	}


package ec_takara;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartBean implements Serializable {
	private String user_id;
	private int goods_id ;
	private String goods_name; 
	private int price;
	private int quantity;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//カートDBにデータ保存
	public static void insertData(CartBean cb) {
		Connection conn=null;
		CartBean icb = null;
		PreparedStatement st =null;
		ResultSet rs=null;
		int sum=0;
		boolean b;
		
		try {
			icb=cb;
			conn = new ResourceFinder().getConnection();
			st=conn.prepareStatement("select * from cart where user_id=? and goods_id=?");
			st.setString(1,icb.getUser_id());
			st.setInt(2,icb.getGoods_id());
			rs=st.executeQuery();
			b=rs.next();

			if(b==true) {
				sum=rs.getInt("quantity");
				st=conn.prepareStatement("delete from cart where user_id=? and goods_id=?");
				st.setString(1,icb.getUser_id());
				st.setInt(2,icb.getGoods_id());
				st.executeUpdate();
			}
			st.clearParameters();
			st = conn.prepareStatement("insert into cart(user_id,goods_id,quantity) values(?,?,?)"); 
				st.setString(1,icb.getUser_id());
				st.setInt(2,icb.getGoods_id());
				st.setInt(3,icb.getQuantity()+sum);
				st.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			}catch(Exception e) {
			}
		}
	}

	//カートDBからユーザー毎の商品情報を取り出す
	public static ArrayList<CartBean> UClist(String uid) {
		Connection conn=null;
		PreparedStatement st =null;
		ResultSet rs=null;
		CartBean cb=null;
		ArrayList<CartBean> clist=new ArrayList<CartBean>();
		
		try {
			
			conn=new ResourceFinder().getConnection();
			st=conn.prepareStatement("select C.user_id,C.goods_id,C.quantity,G.goods_name,G.price from cart C join goods G on C.goods_id=G.goods_id where C.user_id=?");
			st.setString(1, uid);
			rs=st.executeQuery();
			while(rs.next()) {
				cb=new CartBean();
				cb.setUser_id(rs.getString("C.user_id"));
				cb.setGoods_id(rs.getInt("C.goods_id"));
				cb.setQuantity(rs.getInt("C.quantity"));
				cb.setGoods_name(rs.getString("G.goods_name"));
				cb.setPrice(rs.getInt("G.price"));
				clist.add(cb);
			}
			}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return clist;
	}
	
	//カート内商品のキャンセル・変更
	public static String chData(CartBean cb) {
		Connection conn=null;
		CartBean dcb = null; 
		PreparedStatement st =null;
		ResultSet rs=null;
		
		try {
			dcb=cb;
			conn = new ResourceFinder().getConnection();
			
			st=conn.prepareStatement("select goods_name from goods where goods_id=?");
			st.setInt(1,dcb.getGoods_id());
			rs=st.executeQuery();
			rs.first();
			dcb.setGoods_name(rs.getString("goods_name"));
			
			st.clearParameters();
			st=conn.prepareStatement("delete from cart where user_id=? and goods_id=?");
			st.setString(1,dcb.getUser_id());
			st.setInt(2,dcb.getGoods_id());
			st.executeUpdate();
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
		return dcb.getGoods_name();
	}
	
	//購入完了後カート内の商品削除
	public static void dlAData(String uid) {
		Connection conn=null;
		PreparedStatement st =null;
		
		try {
			conn = new ResourceFinder().getConnection();
			st=conn.prepareStatement("delete from cart where user_id=?");
			st.setString(1,uid);
			st.executeUpdate();
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
}

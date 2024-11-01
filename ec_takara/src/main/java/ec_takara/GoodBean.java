package ec_takara;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GoodBean implements Serializable {

	
	private int goods_id ;
	private String goods_name; 
	private String writer;
	private int price;
	private String book_kinds;
	private String cname;
	
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBook_kinds() {
		return book_kinds;
	}
	public void setBook_kinds(String book_kinds) {
		this.book_kinds = book_kinds;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public static ArrayList<GoodBean> selectAllList(int pos){
		Connection conn=null;
		Statement st =null;
		ResultSet rs =null;
		ArrayList<GoodBean> goods=new ArrayList<GoodBean>();
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("SELECT * FROM goods");
			rs.absolute(pos);
			for(int i=0;i<20;i++) {
				GoodBean ctb =new GoodBean();
				ctb.setGoods_id(rs.getInt("goods_id"));
				ctb.setGoods_name(rs.getString("goods_name"));
				ctb.setWriter(rs.getString("writer"));
				ctb.setPrice(rs.getInt("price"));
				ctb.setBook_kinds(rs.getString("book_kinds"));
				//ctb.setCname(rs.getString("cname"));
				goods.add(ctb);
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return goods;
	}

	/*
	public static ArrayList<GoodBean> selectCcodeList(String ccode){
		Connection conn=null;
		PreparedStatement st =null;
		ResultSet rs =null;
		ArrayList<GoodBean> goods=new ArrayList<>();
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.prepareStatement("SELECT * FROM goods WHERE book_kinds=?");//何を検索するか？
			st.setString(1,ccode);
			rs=st.executeQuery();
			while(rs.next()){
				GoodBean ctb =new GoodBean();
				ctb.setGoods_id(rs.getInt("goods_id"));
				ctb.setGoods_name(rs.getString("goods_name"));
				ctb.setWriter(rs.getString("writer"));
				ctb.setPrice(rs.getInt("price"));
				ctb.setBook_kinds(rs.getString("book_kinds"));				
				ctb.setCname(rs.getString("cname"));
				goods.add(ctb);
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
	}
		return goods;
}
*/

	public static ArrayList<GoodBean> selectAllList(){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<GoodBean> goodbeanlist=new ArrayList<GoodBean>();
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select * from goods");
			while(rs.next()) {
				GoodBean gb=new GoodBean();
				gb.setGoods_id(rs.getInt("goods_id"));
				gb.setGoods_name(rs.getString("goods_name"));
				gb.setWriter(rs.getString("writer"));
				gb.setPrice(rs.getInt("price"));
				gb.setBook_kinds(rs.getString("book_kinds"));				
				//gb.setCname(rs.getString("cname"));
				goodbeanlist.add(gb);
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
		}
		return goodbeanlist;
	}
	
	public static GoodBean giSA(int gid) {
		Connection conn=null;
		PreparedStatement st =null;
		ResultSet rs=null;
		GoodBean cb=new GoodBean();
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.prepareStatement("select * from goods where goods_id=?");
			st.setInt(1, gid);
			rs=st.executeQuery();
				rs.next();
				cb.setGoods_id(rs.getInt("goods_id"));
				cb.setGoods_name(rs.getString("goods_name"));;
				cb.setWriter(rs.getString("writer"));
				cb.setPrice(rs.getInt("price"));
				cb.setBook_kinds(rs.getString("book_kinds"));
				//cb.setCname(rs.getString("cname"));
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
		return cb;
	}
}



	
package ec_takara;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class TakaraBean implements Serializable {
	private String user_id=null;
	private String user_name=null;
	private String sex=null;
	private String address=null;
	private Date b_day=null;
	private int age=0;
	private String generation=null;

	private int goods_id=0;
	private String goods_name=null;
	private String writer=null;
	private int price=0;
	private String book_kinds=null;
	private String cname=null;

	private int quantity=0;

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

	public Date getB_day() {
		return b_day;
	}

	public void setB_day(Date b_day) {
		this.b_day = b_day;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//ユーザー情報に年齢を追加
	public static void age() {
		Connection conn=null;
		TakaraBean dtb = null; 
		Statement st=null;
		PreparedStatement pst =null;
		ResultSet rs=null;
		ArrayList<TakaraBean> tblist=new ArrayList<TakaraBean>();		
		
		try {
			conn = new ResourceFinder().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select user_id,b_day from users");

			while(rs.next()) {
				dtb=new TakaraBean();
				dtb.setUser_id(rs.getString("user_id"));
				dtb.setAge(Period.between(rs.getDate("b_day").toLocalDate(), LocalDate.now()).getYears());
				tblist.add(dtb);
			}
			
			pst=conn.prepareStatement("update users set age=? where user_id=?");
			for(int i=0;i<tblist.size();i++) {
				pst.setInt(1,tblist.get(i).getAge());
				pst.setString(2,tblist.get(i).getUser_id());
				pst.executeUpdate();
				pst.clearParameters();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pst.close();
				st.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//世代を追加するために年齢世代変換をする
	public static String AtoG(int age) {
        if (age < 0) {
            return "無効な年齢";
        } else if (age < 10) {
            return "未成年";
        } else if (age < 20) {
            return "10代";
        } else if (age < 30) {
            return "20代";
        } else if (age < 40) {
            return "30代";
        } else if (age < 50) {
            return "40代";
        } else if (age < 60) {
            return "50代";
        } else if (age < 70) {
            return "60代";
        } else {
            return "70代以上";
        }
	}

	//ユーザー情報に世代を追加
	public static void generation() {
		Connection conn=null;
		TakaraBean dtb = null; 
		Statement st=null;
		PreparedStatement pst =null;
		ResultSet rs=null;
		ArrayList<TakaraBean> tblist=new ArrayList<TakaraBean>();		
		
		try {
			conn = new ResourceFinder().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select user_id,age from users");

			while(rs.next()) {
				dtb=new TakaraBean();
				dtb.setUser_id(rs.getString("user_id"));
				dtb.setGeneration(TakaraBean.AtoG(rs.getInt("age")));
				tblist.add(dtb);
			}
			
			pst=conn.prepareStatement("update users set generation=? where user_id=?");
			for(int i=0;i<tblist.size();i++) {
				pst.setString(1,tblist.get(i).getGeneration());
				pst.setString(2,tblist.get(i).getUser_id());
				pst.executeUpdate();
				pst.clearParameters();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pst.close();
				st.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	//取り出した情報をCSVファイルに書き出す
	private static void writeResultSetToCSV(ResultSet resultSet, String filePath) {
	    try (FileOutputStream fos = new FileOutputStream(filePath);
	         OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
	         BufferedWriter writer = new BufferedWriter(osw)) {
	        
	        // UTF-8 BOMを追加
	        writer.write("\uFEFF");

	        int columnCount = resultSet.getMetaData().getColumnCount();

	        // ヘッダー行を書き込む
	        for (int i = 1; i <= columnCount; i++) {
	            writer.write(resultSet.getMetaData().getColumnName(i));
	            if (i < columnCount) writer.write(",");
	        }
	        writer.newLine();

	        // データ行を書き込む
	        while (resultSet.next()) {
	            for (int i = 1; i <= columnCount; i++) {
	                writer.write(resultSet.getString(i));
	                if (i < columnCount) writer.write(",");
	            }
	            writer.newLine();
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	//購入された商品の購入者情報と商品情報をすべて取り出す
	public static ArrayList<TakaraBean> s_info(int pos,String absolutePath){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<TakaraBean> tblist=new ArrayList<TakaraBean>();
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select * from bought B join users U on U.user_id=B.user_id join goods G on G.goods_id=B.goods_id");
			
			TakaraBean.writeResultSetToCSV(rs, absolutePath);
			
			rs.absolute(pos);
			for(int i=0;i<20;i++) {
			
			//while(rs.next()) {
				TakaraBean tb=new TakaraBean();
				tb.setUser_id(rs.getString("U.user_id"));
				tb.setUser_name(rs.getString("U.user_name"));
				tb.setAddress(rs.getString("U.address"));
				tb.setSex(rs.getString("U.sex"));
				tb.setB_day(rs.getDate("U.b_day"));
				tb.setAge(rs.getInt("U.age"));
				tb.setGeneration(rs.getString("U.generation"));
				
				tb.setGoods_id(rs.getInt("G.goods_id"));
				tb.setGoods_name(rs.getString("G.goods_name"));
				tb.setWriter(rs.getString("G.writer"));
				tb.setPrice(rs.getInt("G.price"));
				tb.setBook_kinds(rs.getString("G.book_kinds"));				
				//tb.setCname(rs.getString("G.cname"));
				
				tb.setQuantity(rs.getInt("B.quantity"));
				tblist.add(tb);
			//}
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
				e.printStackTrace();
			}
		}
		return tblist;
	}
	
	//検索条件をフィルタリングするメソッド
	public static String searchtb(TakaraBean tb) {
	    StringBuilder results = new StringBuilder("select * from bought B join users U on U.user_id=B.user_id join goods G on G.goods_id=B.goods_id where 1=1");

	    if (tb.getGeneration() != null && !tb.getGeneration().isEmpty()) {
	    	results.append(" AND U.generation = ?");
	    	System.out.println(1);
	    	System.out.println(tb.getGeneration());
	        }
	    if (tb.getSex() != null && !tb.getSex().isEmpty()) {
	    	results.append(" AND U.sex = ?");
	    	System.out.println(2);
	    	System.out.println(tb.getSex());
	        }
	    if (tb.getBook_kinds() != null && !tb.getBook_kinds().isEmpty()) {
	    	results.append(" AND G.book_kinds = ?");
	    	System.out.println(3);
	    	System.out.println(tb.getBook_kinds());
	        }
	    return results.toString();
	}
	//検索情報を入れて情報を取り出す
	//使い方
	//TakaraBean.getTB(TakaraBean.serchtb(渡す情報の入ったTakaraBeanのオブジェクト),渡す情報の入ったTakaraBeanのオブジェクト)
	public static ArrayList<TakaraBean> getTB(String sql,TakaraBean tb2,int pos,String absolutePath){
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		ArrayList<TakaraBean> tblist=new ArrayList<TakaraBean>();
		
		try {
			conn=new ResourceFinder().getConnection();
			st=conn.prepareStatement(sql);
			
			int j=1;
			
			if(tb2.getGeneration() != null && !tb2.getGeneration().isEmpty()) {
				st.setString(j++, tb2.getGeneration());
		    	System.out.println(4);
			}
			if(tb2.getSex() != null && !tb2.getSex().isEmpty()) {
				st.setString(j++, tb2.getSex());
		    	System.out.println(5);
			}
			if(tb2.getBook_kinds() != null && !tb2.getBook_kinds().isEmpty()) {
				st.setString(j++, tb2.getBook_kinds());
		    	System.out.println(6);
			}
			rs=st.executeQuery();
	    	System.out.println(7);
			
			
			TakaraBean.writeResultSetToCSV(rs, absolutePath);
			
			rs.absolute(pos);
			for(int i=0;i<20;i++) {
			
			//while(rs.next()) {
				TakaraBean tb=new TakaraBean();
				tb.setUser_id(rs.getString("U.user_id"));
				tb.setUser_name(rs.getString("U.user_name"));
				tb.setAddress(rs.getString("U.address"));
				tb.setSex(rs.getString("U.sex"));
				tb.setB_day(rs.getDate("U.b_day"));
				tb.setAge(rs.getInt("U.age"));
				tb.setGeneration(rs.getString("U.generation"));
				
				tb.setGoods_id(rs.getInt("G.goods_id"));
				tb.setGoods_name(rs.getString("G.goods_name"));
				tb.setWriter(rs.getString("G.writer"));
				tb.setPrice(rs.getInt("G.price"));
				tb.setBook_kinds(rs.getString("G.book_kinds"));				
				//tb.setCname(rs.getString("G.cname"));
				
				tb.setQuantity(rs.getInt("B.quantity"));
				
				tblist.add(tb);
			//}
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
				e.printStackTrace();
			}
		}
		
		return tblist;
	}
	
	
	//検索情報を入れて情報を取り出す
	/*
	public static ArrayList<TakaraBean> seletCodeList(String wcode){ //wcode=検索ワード入れる変数
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		ArrayList<TakaraBean> tblist=new ArrayList<TakaraBean>();
		
		try {
			conn=new ResourceFinder().getConnection();
			st = conn.prepareStatement("select * from bought"
								+ " B join users U on U.user_id=B.user_id join goods G on G.goods_id=B.goods_id"
								+ " where U.sex =?" //U.sex から　wcode(検索ワード)に合致するものを抜き取る
								+ " order by U.user_id DESC"); //userID順ソート いらんかも…
			
			st.setString(1, wcode);
			rs = st.executeQuery();
			
			while(rs.next()) {
				TakaraBean tb=new TakaraBean();
				tb.setUser_id(rs.getString("U.user_id"));
				tb.setUser_name(rs.getString("U.user_name"));
				tb.setAddress(rs.getString("U.address"));
				tb.setSex(rs.getString("U.sex"));
				tb.setB_day(rs.getDate("U.b_day"));
				
				tb.setGoods_id(rs.getInt("G.goods_id"));
				tb.setGoods_name(rs.getString("G.goods_name"));
				tb.setWriter(rs.getString("G.writer"));
				tb.setPrice(rs.getInt("G.price"));
				tb.setBook_kinds(rs.getString("G.book_kinds"));				
				tb.setCname(rs.getString("G.cname"));
				
				tb.setQuantity(rs.getInt("B.quantity"));
				
				tblist.add(tb);
			}
			
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
		return tblist;
	}
*/
	
}

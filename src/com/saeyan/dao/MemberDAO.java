package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.saeyan.dto.MemberVO;

//데이터베이스 처리만 따로 관리하기 위한 DAO클래스 생성
public class MemberDAO {
	
	private MemberDAO() {
	}
	
	//private 외부 수정할 수 없고 얻을 수만 있음.
	
	private static MemberDAO instance =new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	//471p. 컨넥션을 얻어오는 메소드 추가
	public Connection getConnection() throws Exception{
		Connection conn=null;
		Context initContext = new InitialContext();  							// 객체생성
	    Context envContext  = (Context)initContext.lookup("java:/comp/env");	// 지정한 이름 찾기
	    DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");			// server.xml파일에서 지정한 이름 찾기
	    conn = ds.getConnection();
		return conn;
	}
	
	//481p. 사용자 인증시 사용하는 메소드
	public int userCheck(String userid, String pwd) {
	
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int result = -1; //회원이 존재하면 1, 회원이면서 아이디가 없으면 0, 그렇지 아니하면 -1값 리턴
		String sql="select pwd from member where userid=?";
				
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd)) {
					result=1;
				}else {
					result=0;
				}			
			}else {
				result=-1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//483p. 아이디로 회원 정보 가져오는 메소드
	public MemberVO getMember(String userid){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
				
		MemberVO mVo=null;
		String sql="select * from member where userid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				mVo=new MemberVO();
				mVo.setName(rs.getString("name"));
				mVo.setUserid(rs.getString("userid"));
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setAdmin(rs.getInt("admin"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return mVo;
	}
	
	
	//495p. 아이디 중복 체크를 위한 메소드 추가하기
	public int confirmID(String userid) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int result= -1;// 중복아이디이면 1, 그렇지 아니하면 -1
		String sql="select userid from member where userid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=1;
			}else {
				result=-1;	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close(); 
				if(pstmt != null) pstmt.close(); 
				if(conn != null) conn.close(); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//502p. 회원 정보를 DB에 추가하기 위한 메소드 추가하기
	public int insertMember(MemberVO mVo) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
				
		int result =-1;
		String sql="insert into member values(?,?,?,?,?,?)";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());
			pstmt.setInt(6, mVo.getAdmin());
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	//511p. 회원정보 변경하기 위한 메소드 추가하기
	public int updateMember(MemberVO mVo) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
				
		int result = -1;
		String sql="update member set pwd=?, email=?, phone=?, admin=? where userid=?";
		
		try {
			
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getUserid());
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return result;
	}
	
	
}

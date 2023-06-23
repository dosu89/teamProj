package expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DButil.DBcon;
import DButil.DBcrud;

public class FixedCostDAO implements DBcrud{

	@Override
	public boolean insert(Object dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		FixedCostDTO cost = (FixedCostDTO) dto;
		
		String query = "INSERT INTO fixedcost VALUE (?, ? ,?)";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cost.getF_name());
			pstmt.setInt(2, cost.getF_cost() );
			pstmt.setInt(3, cost.getF_date());
			int result = pstmt.executeUpdate();
			if (result == 1 )
				flag = true;
		} catch (Exception e) {
			System.out.println("고정비 입력 실패 : " + e.getMessage());
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public List<Object> getData() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<>();
		
		String query = "SELECT * FROM fixedcost";
		
		try {
			con = DButil.DBcon.getConn();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if( rs != null ) {
					String f_name = rs.getString("f_name");
					int f_cost = rs.getInt("f_cost");
					int f_date = rs.getInt("f_date");
					FixedCostDTO dto = new FixedCostDTO(f_name, f_cost, f_date);
					list.add(dto);
				}
			}
		} catch (Exception e) {
			System.out.println("고정비 리스트 반환 실패 : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return list;
	}

	@Override
	public boolean update(Object dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		FixedCostDTO cost = (FixedCostDTO)dto;
		boolean flag = false;
		
		String query = "UPDATE FROM fixedcost SET f_cost=?, f_date=? WHERE f_name=?";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cost.getF_cost());
			pstmt.setInt(2, cost.getF_date());
			pstmt.setString(3, cost.getF_name());
			int result = pstmt.executeUpdate();
			
			if (result == 1 )
				flag = true;
		} catch (Exception e) {
			System.out.println("고정비 수정 실패 : " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean dalete(Object dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		FixedCostDTO cost = (FixedCostDTO)dto;
		boolean flag = false;
		
		String query = "DELETE FROM fixedcost WHERE f_name=?";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cost.getF_name());
			int result = pstmt.executeUpdate();
			
			if (result ==1)
				flag = true;
			
		} catch (Exception e) {
			System.out.println("고정비 삭제 실패 : " +e.getMessage());
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return flag;
	}
	
}

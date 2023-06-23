package stock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DButil.DBcon;
import DButil.DBcrud;
import vo.AmountListVO;

public class StockDAO implements DBcrud{

	@Override
	public boolean insert(Object dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		StockDTO stock = (StockDTO)dto;
		
		String query = "INSERT INTO stock (m_code, s_amount, s_TotalAmount, recDate) "
				+ "VALUE (?, ?, ?, ?)";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, stock.getM_code());
			pstmt.setInt(2, stock.getS_amount());
			pstmt.setInt(3, stock.getS_totalAmount());
			pstmt.setTimestamp(4, Timestamp.valueOf(stock.getRecDate()));
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				flag = true;
			}
			
		} catch (Exception e) {
			System.out.println("재고 입력 실패 : " + e.getMessage());
			
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
	public List<Object> getData() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<>();
		
		String query = "SELECT * FROM stock";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				while(rs.next()) {
					int s_no = rs.getInt("s_no");
					String m_code = rs.getString("m_code");
					int s_amount = rs.getInt("s_amount");
					int s_totalAmount = rs.getInt("s_totalAmount");
					LocalDateTime recDate = rs.getTimestamp("recDate").toLocalDateTime();
					StockDTO dto = 
							new StockDTO(s_no, m_code, s_amount, s_totalAmount, recDate);
					list.add(dto);
				}
			}
			
		} catch (Exception e) {
			System.out.println("재고 리스트 반환 실패 : " + e.getMessage());
			
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
		boolean flag = false;
		StockDTO stock = (StockDTO)dto;
		
		String query = "UPDATE stock SET m_code = ?, s_amount = ?, s_totalAmount = ?, recDate = ? "
							+ "WHERE s_no = ?"; 
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, stock.getM_code());
			pstmt.setInt(2, stock.getS_amount());
			pstmt.setInt(3, stock.getS_totalAmount());
			pstmt.setTimestamp(4, Timestamp.valueOf(stock.getRecDate()));
			pstmt.setInt(6, stock.getS_no());
			int result = pstmt.executeUpdate();
			
			if (result == 1)
				flag = true;
			
		} catch (Exception e) {
			System.out.println("재고 업데이트 실패 : " + e.getMessage());
			
		} finally {
			try {
				if (pstmt != null )
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
		StockDTO stock = (StockDTO)dto;
		boolean flag = false;
		
		String query = "DELETE FROM stock WHERE s_no=?";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, stock.getS_no());
			int result = pstmt.executeUpdate();
			
			if (result == 1)
				flag = true;
			
		} catch (Exception e) {
			System.out.println("재고 삭제 실패 : "+ e.getMessage());
			
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
	
	// 특정 재료 재고 총량 불러오는 메서드 만들기
	public int totalAmount(String m_code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		
		String query = "SELECT s_totalAmount FROM stock WHERE m_code = ? ORDER BY recDate DESC LIMIT 1";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m_code);
			rs = pstmt.executeQuery();
			if (rs != null) {
				rs.next();
				total = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("특정 재고 총량 데이터 반환 실패 : " + e.getMessage());
			
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
		return total;
	}
	
	
	
	// 기간 사이의 재고 입출력 리스트 반환
	public List<StockDTO> getStockDateData(String date1, String date2) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StockDTO> list = new ArrayList<>();
		
		String query = "SELECT * FROM stock WHERE date(recDate) BETWEEN ? AND ?;";
		
		try {
			con = DButil.DBcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if( rs != null ) {
					int s_no = rs.getInt("s_no");
					String m_code = rs.getString("m_code");
					int s_amount = rs.getInt("s_amount");
					int s_TotalAmount = rs.getInt("s_TotalAmount");
					LocalDateTime recDate = rs.getTimestamp("recDate").toLocalDateTime();
					StockDTO dto = new StockDTO(s_no, m_code, s_amount, s_TotalAmount, recDate);
					list.add(dto);
				}
			}
		} catch (Exception e) {
			System.out.println("기간 데이터 반환 실패 : " + e.getMessage());
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
	
	// 현재 재고 총량 리스트 반환
	public List<AmountListVO> getTotal() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AmountListVO> list = new ArrayList<>();
		
		String query = "SELECT m.m_name, SUM(s.s_amount) " + 
				"FROM stock s , material m " + 
				"WHERE s.m_code = m.m_code " + 
				"GROUP BY s.m_code " + 
				"ORDER BY recDate DESC";
		
		try {
			con = DBcon.getConn();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					String name = rs.getString(1);
					int total = rs.getInt(2);
					
					AmountListVO a = new AmountListVO(name, total);
					list.add(a);
				}
			}
		} catch (Exception e) {
			System.out.println("총량 리스트 반환 실패 : " + e.getMessage());
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("연결 해제 실패 : " + e.getMessage());
			}
		}
		return list;
	}
}

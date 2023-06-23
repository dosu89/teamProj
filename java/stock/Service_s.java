package stock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import vo.AmountListVO;

public class Service_s {

		public void insertStock(String m_code, int s_amount) {
			StockDTO s = new StockDTO(m_code, s_amount);
			StockDAO dao = new StockDAO();
			
			// 최근 총량을 불러와 새로운 총량으로 계산
			int total = dao.totalAmount(s.getM_code());
			s.setS_totalAmount(total + s.getS_amount());
			s.setRecDate(LocalDateTime.now());
			if (dao.insert(s)) {
				System.out.println("재고 등록 완료");
			}
		}
		
		public void updateStock(StockDTO s) {
			StockDAO dao = new StockDAO();
			s.setRecDate(LocalDateTime.now());
			Object dto = s;
			if (dao.update(dto)) {
				System.out.println("수정 완료");
			}
		}
		
		// 모든 재고 리스트 받아와서 출력
		public void selectAllStock() {
			StockDAO dao = new StockDAO();
			List<Object> list = dao.getData();
			@SuppressWarnings("unchecked")
			List<StockDTO> slist = (List<StockDTO>)(Object)list;
			for(StockDTO s : slist) {
				System.out.println(s);
			}
		}
		
		public void deleteStock(StockDTO s) {
			StockDAO dao = new StockDAO();
			if (dao.dalete(s)) {
				System.out.println("삭제 완료");
			}
		}
		
		// 두 기간 사이의 재고 입출력 리스트 보여주기
		public void stockDateList(String date1, String date2) {
			StockDAO sdao = new StockDAO();
			List<StockDTO> eList = sdao.getStockDateData(date1, date2);
			for (StockDTO s : eList) {
				System.out.println(s);
			}
		}
		
		// 재고 총량 리스트 반환 후 보여주기
		public void getTotalList() {
			StockDAO sdao = new StockDAO();
			ArrayList<AmountListVO> alist = (ArrayList<AmountListVO>) sdao.getTotal();
			for(AmountListVO a : alist) {
				System.out.print(a + "     ");
			}
			System.out.println();
		}
}

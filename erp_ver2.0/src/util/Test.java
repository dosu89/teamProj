package util;

import java.util.List;

import dto.CalcStockDTO;

public class Test {

	public static void main(String[] args) {
		String[] products = {"PR001", "PR002", "PR003"};
		
		
		List<CalcStockDTO> clist = StockCalculator.getCalc(products);
		
		for(CalcStockDTO dto : clist) {
			System.out.println(dto);
		}
	}
}

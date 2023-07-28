package services;

import java.util.ArrayList;

import org.json.JSONArray;

import dao.OrderDAO;
import dao.ProductDAO;
import dto.Product;

public class PosService {
	private ProductDAO pDao = new ProductDAO();
	private OrderDAO oDao = new OrderDAO();
	public JSONArray productList() {
		JSONArray jarray = null;
		ArrayList<Product> plist = pDao.selectAll();
		
		if(plist != null) {
			jarray = new JSONArray(plist);
		}else {
			System.out.println("상품 리스트 없음");
		}
		
		return jarray;
	}
	
	public void regist(JSONArray jarray) {
		oDao.regist();
		for (Object object : jarray) {
			oDao.registOrderList((dto.OrderListVO) object);
		}
	}
}

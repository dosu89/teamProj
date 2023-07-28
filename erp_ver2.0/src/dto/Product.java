package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//상품 추상클래스
@AllArgsConstructor
@Data
public abstract class Product {
	private String pr_code;
	private String pr_name;
	private int pr_price;
	private String pr_ctgry;
	
	
	public abstract int count();
}

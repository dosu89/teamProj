package stock;

import java.time.LocalDateTime;

public class StockDTO {
	private int s_no;
	private String m_code;
	private int s_amount;
	private int s_totalAmount;
	private LocalDateTime recDate;
	
	public StockDTO() {}

	public StockDTO(String m_code, int s_amount) {
		super();
		this.m_code = m_code;
		this.s_amount = s_amount;
	}

	public StockDTO(int s_no, String m_code, int s_amount, int s_totalAmount, LocalDateTime recDate) {
		super();
		this.s_no = s_no;
		this.m_code = m_code;
		this.s_amount = s_amount;
		this.s_totalAmount = s_totalAmount;
		this.recDate = recDate;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getM_code() {
		return m_code;
	}

	public void setM_code(String m_code) {
		this.m_code = m_code;
	}

	public int getS_amount() {
		return s_amount;
	}

	public void setS_amount(int s_amount) {
		this.s_amount = s_amount;
	}

	public int getS_totalAmount() {
		return s_totalAmount;
	}

	public void setS_totalAmount(int s_totalAmount) {
		this.s_totalAmount = s_totalAmount;
	}

	public LocalDateTime getRecDate() {
		return recDate;
	}

	public void setRecDate(LocalDateTime recDate) {
		this.recDate = recDate;
	}

	@Override
	public String toString() {
		return "StockDTO [s_no=" + s_no + ", m_code=" + m_code + ", s_amount=" + s_amount + ", s_totalAmount="
				+ s_totalAmount + ", recDate=" + recDate + "]";
	}
}

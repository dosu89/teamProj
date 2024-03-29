package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FixedCostDTO {
	private int fi_no;
	private String fi_name;
	private int fi_cost;
	private int fi_date;
}

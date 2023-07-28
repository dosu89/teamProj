package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.CalcStockDTO;
import dto.RecipeDTO;
import services.Service_st;
import vos.TotalStockVO;

public class StockCalculator {
	
	public static List<CalcStockDTO> getCalc(String[] products) {
		Service_st st_serv = new Service_st();
		List<TotalStockVO> slist = st_serv.getTotalList();
		List<RecipeDTO> rlist = st_serv.getRecipeList();
		Map<String, Integer> smap = new HashMap<>();
		List<CalcStockDTO> clist = new ArrayList<>();
		
		
		slist = changeNameToCode(slist);
		smap = totalStockMap(slist);
		
		for(int i=products.length-1; i>=0; i--) {
			for(RecipeDTO dto : rlist) {
				if(products[i].equals(dto.getPr_code())) {
					CalcStockDTO cDto = new CalcStockDTO();
					cDto.setPr_name(products[i]);
					int a = calcEa(smap.get("MA001"), dto.getMa001());
					int b = calcEa(smap.get("MA002"), dto.getMa002());
					int c = calcEa(smap.get("MA003"), dto.getMa003());
					int result = CheckEa(a, b, c);
					smap.put("MA001", (Integer)(smap.get("MA001") - (int)(dto.getMa001() * result)));
					smap.put("MA002", (Integer)(smap.get("MA002") - (int)(dto.getMa002() * result)));
					smap.put("MA003", (Integer)(smap.get("MA003") - (int)(dto.getMa003() * result)));
					cDto.setPr_ea(result);
					clist.add(cDto);
				}
			}
		}
		
		slist = totalStockList(smap, slist);
		for(TotalStockVO vo : slist) {
			System.out.println("vo : "  + vo);
		}
		clist = changeCodeToName(clist);
		return clist;
	}
	
	// 리스트에 담긴 재료 이름을 재료 코드로 변경
	public static List<TotalStockVO> changeNameToCode(List<TotalStockVO> slist) {
		for(TotalStockVO vo : slist) {
			vo.setMa_name(NameCheck.MaNameToCode(vo.getMa_name()));
		}
		return slist;
	}
	
	public static List<CalcStockDTO> changeCodeToName(List<CalcStockDTO> clist) {
		for(CalcStockDTO dto : clist) {
			dto.setPr_name(NameCheck.PrCodeToName(dto.getPr_name()));
		}
		return clist;
	}
	
	// 재고 총량 리스트에 담긴 자료를 (key : 재료 이름, value :  현재 재고 총량)으로 만드는 Map 반환
	public static Map<String, Integer> totalStockMap(List<TotalStockVO> slist) {
		Map<String, Integer> smap = new HashMap<>();
		for(TotalStockVO vo : slist) {
			smap.put(vo.getMa_name(), vo.getTotalEa());
		}
		return smap;
	}
	
	public static List<TotalStockVO> totalStockList(Map<String, Integer> smap, List<TotalStockVO> slist) {
		for(TotalStockVO vo : slist) {
			vo.setTotalEa(smap.get(vo.getMa_name()));
		}
		return slist;
	}
	
	// 각각 재료에 따라 만들 수 있는 갯수를 입력 받아 가장 적은 값을 찾아서 반환
	public static int CheckEa(int a, int b, int c) {
		int result = a;
		if (b == -1) {
			if(c == -1) {
				return result;
			} else {
				if(c < a) {
					return c;
				}
			}
		} else {
			if(c == -1) {
				if(b < a) {
					return b;
				}
			} else {
				if (b < a) {
					result = b;
				}
				if (c < result) {
					result = c;
				}
			}
		}
		if(result < 0)
			result = 0;
		return result;
	}
	
	// 나누는 값이 0일 경우 -1 반환
	public static int calcEa(int x, double y) {
		if(y != 0) {
			return (int)(x/y);
			
		} else {
			return -1;
		}
	}
	
	public static int divideEa(int totalEa, int productCnt) {
		return totalEa/productCnt;
	}
}

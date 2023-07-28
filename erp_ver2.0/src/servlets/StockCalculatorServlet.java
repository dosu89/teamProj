package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dto.CalcStockDTO;
import dto.RecipeDTO;
import services.Service_st;
import util.StockCalculator;
import vos.TotalStockVO;

@WebServlet("/stockCalc")
public class StockCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		Service_st st_serv = new Service_st();
		List<RecipeDTO> rlist = st_serv.getRecipeList();
		
		request.setAttribute("rlist", rlist);
		request.setAttribute("page", "stockCalc.jsp");
		request.getRequestDispatcher("stock/stock.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Service_st st_serv = new Service_st();
		
		String[] products = request.getParameterValues("products");
		
		List<CalcStockDTO> clist = StockCalculator.getCalc(products);
		List<TotalStockVO> slist = st_serv.getTotalList();
		
		PrintWriter out = response.getWriter();
		
		JSONArray jarray = new JSONArray(clist);
		JSONArray jarray2 = new JSONArray(slist);
		
		System.out.println(jarray);
		out.print(jarray);
		out.print(jarray2);
	}
}

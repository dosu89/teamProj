package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.RecipeDTO;
import stock.Service_st;

@WebServlet("/stockCalc")
public class StockCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service_st st_serv = new Service_st();
		List<RecipeDTO> rlist = st_serv.getRecipeList();
		
		request.setAttribute("rlist", rlist);
		request.getRequestDispatcher("stock/stock.jsp?req=calc").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pr_name = request.getParameterValues("product");
		if (pr_name != null) {
			for (int i = 0; i < pr_name.length; i++) {
				System.out.println(pr_name[i]);
			}
		}
		PrintWriter out = response.getWriter();
		out.print("ㅇㅇ");
	}

}

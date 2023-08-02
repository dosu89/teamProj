package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FixedCostDTO;
import services.Service_ex;
import util.MakeJSON;

@WebServlet("/fixed-cost")
public class ExpenseFixedCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String req = request.getParameter("data");
		
		if(req != null && req.equals("1")) {
			Service_ex ex_serv = new Service_ex();
			PrintWriter out = response.getWriter();
			
			List<FixedCostDTO> flist = ex_serv.getFixedCostList();
			
			MakeJSON<FixedCostDTO> jobj = new MakeJSON<>();
			out.print(jobj.getJSONArray(flist));
			
			return;
		}
		
		request.setAttribute("page", "expenseFixedCost.jsp");
		request.getRequestDispatcher("/expense/expense.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

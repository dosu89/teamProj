package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service_st;
import util.OptionCheck;
import vos.TotalStockVO;

@WebServlet("/totalstock")
public class TotalStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		Service_st st_serv = new Service_st();
		OptionCheck optCheck = new OptionCheck();
		boolean alOpt = st_serv.getStockOption().isAlramOpt();
		int alNum = optCheck.alramStockNum();
		ArrayList<TotalStockVO> aList = st_serv.getTotalList();
		
		request.setAttribute("alNum", alNum);
		request.setAttribute("alOpt", alOpt);
		request.setAttribute("alist", aList);
		request.getRequestDispatcher("stock/stock.jsp?req=total").forward(request, response);
	}
}

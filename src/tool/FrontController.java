package tool;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;






/**
*Servlet implementation class FrontController
*/
@WebServlet("/FrontController")
public class FrontController extende HttlServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequst requst, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest requst, HttpServletResponse response) throws ServletException,IOException{
		//TODO Auto-generated method stub
		response.getWriter().append("Served at:").append(request.getContextPath());
	}

	/**
	 *  @see HttpServlet#doPost(HttpServletRequst requst, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest requst, HttpServletResponse response) throws ServletException,IOException{
		//TODO Auto-generated method stub
		doGet(request, response);
	}
}
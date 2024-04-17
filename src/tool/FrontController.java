package tool;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






/**
*Servlet implementation class FrontController
*/
@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequst requst, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest requst, HttpServletResponse response) throws ServletException,IOException{
		try{
			//パスを取得
			String path = requst.getServletPath().substring(1);
			//ファイル名を取得しクラス名に変換
			String name = path.replace(".a", "A").replace('/', '.');

			System.out.println("★ servlet path ->" + requst.getServletPath());
			System.out.println("★ class name ->" + name);

			//アクションクラスのインスタンスを返却
			Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

			//移先URLを取得
			action.execute(request, respons);
			//String url = action.execute(requst, response);
			//requst.getRequestDispatcher(url).forward(requst, response);

		} catch (Exception e) {
			e.printStackTrace();
			//エラーページへリダイレクト
			requst.getRequestDispatcher("error.jsp").forward(requst, response);
		}
	}
	@Override
	protectsd void doPost(HttpServletRequest requst, HttpServletResponse response) throws Servletexception, IOException{
		doGet(requst,response);
	}
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
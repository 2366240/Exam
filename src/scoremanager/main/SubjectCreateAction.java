package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class SubjectCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		//HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		//Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化
		School school = new School();
		school.setCd("oom");
		school.setName("大宮校");

		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setName("大宮花子");
		teacher.setPassword("password");
		teacher.setSchool(school);
		//リクエストパラメータ―の取得 2
		//なし
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}
}

package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		SubjectDao sDao =new SubjectDao();//学生Dao
		HttpSession session = req.getSession();//セッション
		//Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザーを取得
		ClassNumDao cNumDao = new ClassNumDao(); //クラス番号を初期化
		Map<String, String> errors = new HashMap<>(); //エラーメッセージ

		//リクエストパラメータ―の取得 2
		String cd = req.getParameter("cd");//学番

		School school = new School();
		school.setCd("oom");
		school.setName("大宮校");




		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setName("大宮花子");
		teacher.setPassword("password");
		teacher.setSchool(school);

		//DBからデータ取得 3
		Subject subject =  sDao.get(cd,school);//学生番号から学生インスタンスを取得
		List<Subject> subjects = sDao.filter(teacher.getSchool());

		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//条件で手順4,6の内容が分岐

		if (subject != null) {
			req.setAttribute("cd", subject.getCd());
			req.setAttribute("name", subject.getName());
		} else {
			errors.put("cd","科目が存在していません");
			req.setAttribute("errors", errors);
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}

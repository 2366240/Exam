package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String cd = "";
		String name = "";
		bean.Subject subject = null;
		SubjectDao sDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();


		School school = new School();
		school.setCd("oom");
		school.setName("大宮校");



		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setName("大宮花子");
		teacher.setPassword("password");
		teacher.setSchool(school);

		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");//科目番号
		name = req.getParameter("name");//氏名
		subject = sDao.get(cd,school);

		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐
		if(subject != null) {
			subject = new Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(teacher.getSchool());
			sDao.save(subject);
		} else {
			errors.put("cd", "科目が存在していません");
		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7


		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}

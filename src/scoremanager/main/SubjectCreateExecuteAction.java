package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String cd = "";
		String name = "";
		bean.Subject subject = null;
		SubjectDao subDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();
		HttpSession session = req.getSession(); //セッション
		//Teacher teacher = (Teacher)session.getAttribute("user");
		School school = new School();
		school.setCd("oom");
		school.setName("大宮校");

		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setName("大宮花子");
		teacher.setPassword("password");
		teacher.setSchool(school);

		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");//学生番号
		name = req.getParameter("name");//氏名


		//DBからデータ取得 3

		//ビジネスロジック 4
		if (cd.length() == 4) {// 入学年度が選択されていない場合
			errors.put("cd", "科目コードは3文字で入力してください");
		}else{

			subject = subDao.get(cd, teacher.getSchool());
			if (subject != null) {//科目が存在していた場合はエラー
				errors.put("cd","科目コードが重複しています");
			}
		}
		if(errors.isEmpty()){
			subject = new Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(teacher.getSchool());
				// 科目情報を登録
			subDao.save(subject);
			//
			req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
			} else {
				req.setAttribute("errors", errors);
				req.setAttribute("cd", cd);
				req.setAttribute("name", name);
				req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			}
		}
}



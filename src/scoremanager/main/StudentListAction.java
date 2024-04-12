package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;




public class StudentListAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();//TODO 自動生成されたメゾット・スタブ
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr=""; //入力された入学年度
		String classNum = ""; //入力されたクラス番号
		String isAttendStr = ""; //入力された在学フラグ
		int entYear = 0; //入学年度
		boolean isAttend = false; //在学フラグ
		List<Student> students = null; //学生リスト
		LocalDate todaysDate = LocalDate.now(); //LcalDateインスタンスを取得
		int year = todaysDate.getYear(); //現在の年を取得
		StudentDao sDao = new StudentDao(); //学生Dao
		ClassNumDao cNumDao = new ClassNumDao(); //クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>(); //エラーメッセージ
	}
	//リクエストパラメーターの取得2
	entYearStr = req.getParameter("f1");
	classNum = req.getParameter("f2");
	isAttendStr = req.getParameter("f3");
	//DBからデータ取得
	//ログインユーザーの学校コードを元にクラス番号の一覧を取得
	List<String> list = cNumDao.filter(teacher.getSchool());

	if (entYear != 0 && !classNum.equals("0")) {
		//入学年度とクラス番号を指定
		students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
	} else if (entYear != 0 && classNum.equals("0")) {
		//入学年度のみ指定
		student = sDao.filter(teacher.getSchool(), entYear, isAttend);
	} else if (entYear ==- 0 && classNum ==null || entYear == 0 && classNum.equals("0")) {
		//指定なしの場合
		//全学年情報を取得
		studennts = sDao.filter(teacher.getSchool(), is Attend);
	} else {
		errors.put("f1", "クラス指定する場合は入学年度も指定してください");
		req.setAttribute("errors", errors);
		//全学生情報を取得
		student = sDao.filter(teacher.getSchool(), isAttend);
	}
}
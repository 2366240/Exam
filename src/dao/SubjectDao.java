package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.Student;


public class SubjectDao extends Dao {

	private String baseSql = "select * from student where school_cd=? ";

	public Subject get(String cd, School school) throws Exception {
		//科目インスタンスを初期化
		Subject subject = new Subject();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from student where cd=? and school_cd = ?");
			//プリペアードステートメントに科目コードをバインド
			statement.setString(1, cd);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(2, school.getCd());
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();


			if (rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return subject;

	}


	public List<Subject> filter(School school) throws Exception {
		//リストを初期化
		List<Student> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject where school_cd=? order by cd");
			//プリペアードステートメントに学生コードをバインド
			statement.setString(1, school.getCd());
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//リザルトセットを全件走査
			while (rSet.next()) {
				//科目インスタンスの初期化
				Subject subject = new Subject();
				//科目インスタンスに値をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				//リストに追加
				list.add(subject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	public boolean save(Subject subject) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try {
			//データベースから学生を取得
			Student old = get(subject.getCd(), subject.getSchool());
			if(old == null) {
				//学生が存在しなかった場合
				//プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement("insert into subject(name, cd, school_cd) values(?, ?, ?)");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getCd());
				statement.setInt(3, subject.getSchool().getCd());
			} else {
				//学生が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("update student set name = ? where cd = ?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());
				statement.setInt(2, subject.getCd());
			}

			//プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch(SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0) {
			//実行件数が1件以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
		}

	}

	public boolean delete(Subject subject) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try {
			//プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement("delete from subject where cd = ?");
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, subject.getCd());
			//プリペアードステートメントを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if(statement !=null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0)	{
			//実行件数が1件以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
		}
	}
}
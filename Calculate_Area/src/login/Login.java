package login;
/**
处理用户登录
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import c_CalArea.mainBody;
import home_page.home_page;

public class Login {

	User admin;

	void setAdmin(User admin) {
		this.admin = admin;
		// System.out.println(this.admin.getPassword()+" " + this.admin.getID());
	}
	/*
	 * JudgeAdmin()方法
	 * 判断Admin的ID和密码是否正确，如果正确，显示登录成功
	 * 如果错误，弹出一个窗口，显示账号或密码错误
	 */
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf-8";
	private String user = "root";
	private String password = "123456";

	public boolean login(User admin) throws SQLException, ClassNotFoundException {
		String sql = "select * from admin where id=? and password=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, admin.getID());
		ps.setString(2, admin.getPassword());
		ResultSet rs = ps.executeQuery();
		int ans = 0;
		if (rs.next()) {
			ans = 1;
		}
		rs.close();
		ps.close();
		conn.close();
		if (ans == 1) {
			return true;
		} else
			return false;
	}

	int JudgeAdmin() {

		try {
			if (login(this.admin)) {
				System.out.println("登录成功");
				// frame.setVisible(false);
				// Welcome welcome=new Welcome();
				// mainBody mainBody = new mainBody();//如果登录成功直接进入主界面
				new home_page();
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("!!!!!!!!!");
		}
		return 0;

	}
}

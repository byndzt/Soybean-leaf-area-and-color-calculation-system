package login;
/*
����Աʵ��
*/

public class User {
	private String id; //编号
	private String name;  //姓名
	private String password;  //密码

	void setID(String id) {
		this.id = id;
	}

	void setName(String name) {
		this.name = name;
	}

	void setPassword(String password) {
		this.password = password;
	}

	String getID() {
		return this.id;
	}

	String getName() {
		return this.name;
	}

	String getPassword() {
		return this.password;
	}

}

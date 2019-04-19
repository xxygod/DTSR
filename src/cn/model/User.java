package cn.model;

public class User {

	private int userId;
	private String userName;
	private String userCommonName;
	private String userPass;
	private String userPic;
	private int userRank;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCommonName() {
		return userCommonName;
	}

	public void setUserCommonName(String userCommonName) {
		this.userCommonName = userCommonName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public int getUserRank() {
		return userRank;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userName, String userCommonName, String userPass, String userPic, int userRank) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userCommonName = userCommonName;
		this.userPass = userPass;
		this.userPic = userPic;
		this.userRank = userRank;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userCommonName=" + userCommonName
				+ ", userPass=" + userPass + ", userPic=" + userPic + ", userRank=" + userRank + "]";
	}

}

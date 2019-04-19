package cn.model;

public class UserHost {
	private int userId;
	private int hostId;

	public UserHost() {
		super();
	}

	public UserHost(int userId, int hostId) {
		super();
		this.userId = userId;
		this.hostId = hostId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

}

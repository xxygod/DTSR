package cn.model;

import cn.Cache.ThreadTime;

public class Host {
	private int hostId;
	private int userId;
	private String hostName;
	private int hostGuid;
	private int hostCode;
	private ThreadTime threadTime;

	public Host() {
		super();
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getHostGuid() {
		return hostGuid;
	}

	public void setHostGuid(int hostGuid) {
		this.hostGuid = hostGuid;
	}

	public int getHostCode() {
		return hostCode;
	}

	public void setHostCode(int hostCode) {
		this.hostCode = hostCode;
	}

	public ThreadTime getThreadTime() {
		return threadTime;
	}

	public void setThreadTime(ThreadTime threadTime) {
		this.threadTime = threadTime;
	}

	public Host(int hostId, int userId, String hostName, int hostGuid, int hostCode, ThreadTime threadTime) {
		super();
		this.hostId = hostId;
		this.userId = userId;
		this.hostName = hostName;
		this.hostGuid = hostGuid;
		this.hostCode = hostCode;
		this.threadTime = threadTime;
	}

	@Override
	public String toString() {
		return "Host [hostId=" + hostId + ", userId=" + userId + ", hostName=" + hostName + ", hostGuid=" + hostGuid
				+ ", hostCode=" + hostCode + ", threadTime=" + threadTime + "]";
	}

}
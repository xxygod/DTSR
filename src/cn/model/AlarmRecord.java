package cn.model;

import java.util.List;

public class AlarmRecord {
	private int arId;
	private int hostGuid;
	private int channelId;
	private String channelName;
	private String arTime;
	private int arPosition;
	private double arDate;
	private int arType;
	private List<String> phoneNumberList;
	private String hostName;

	public AlarmRecord() {
		super();
	}

	public AlarmRecord(int arId, int hostGuid, int channelId, String channelName, String arTime, int arPosition,
			double arDate, int arType, List<String> phoneNumberList, String hostName) {
		super();
		this.arId = arId;
		this.hostGuid = hostGuid;
		this.channelId = channelId;
		this.channelName = channelName;
		this.arTime = arTime;
		this.arPosition = arPosition;
		this.arDate = arDate;
		this.arType = arType;
		this.phoneNumberList = phoneNumberList;
		this.hostName = hostName;
	}

	public int getArId() {
		return arId;
	}

	public void setArId(int arId) {
		this.arId = arId;
	}

	public int getHostGuid() {
		return hostGuid;
	}

	public void setHostGuid(int hostGuid) {
		this.hostGuid = hostGuid;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getArTime() {
		return arTime;
	}

	public void setArTime(String arTime) {
		this.arTime = arTime;
	}

	public int getArPosition() {
		return arPosition;
	}

	public void setArPosition(int arPosition) {
		this.arPosition = arPosition;
	}

	public double getArDate() {
		return arDate;
	}

	public void setArDate(double arDate) {
		this.arDate = arDate;
	}

	public int getArType() {
		return arType;
	}

	public void setArType(int arType) {
		this.arType = arType;
	}

	public List<String> getPhoneNumberList() {
		return phoneNumberList;
	}

	public void setPhoneNumberList(List<String> phoneNumberList) {
		this.phoneNumberList = phoneNumberList;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public String toString() {
		return "AlarmRecord [arId=" + arId + ", hostGuid=" + hostGuid + ", channelId=" + channelId + ", channelName="
				+ channelName + ", arTime=" + arTime + ", arPosition=" + arPosition + ", arDate=" + arDate + ", arType="
				+ arType + ", phoneNumberList=" + phoneNumberList + ", hostName=" + hostName + "]";
	}
	public String getMessageDate(){
		String type;
		if(arType==2)
			type="故障";
		else if(arType==3)
			type="升温报警";
		else if(arType==4)
			type="定温报警";
		else
			type="未知报警";
		return arTime+" "+hostName+" "+channelName+" "+arPosition+"m,发生"+type+"温度"+arDate+"℃";
	}
	
}

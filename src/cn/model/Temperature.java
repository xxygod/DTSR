package cn.model;

import java.util.Arrays;

public class Temperature {
	private int temId;
	private int hostGuid;
	private int channelId;
	private String channelName;
	private String temTime;
	private int temOriginalLength;
	private int temLength;
	private String temStr;
	private String[] temperatureStr;
	private String temColor;
	private int number;

	public Temperature() {
		super();
	}

	public int getTemId() {
		return temId;
	}

	public void setTemId(int temId) {
		this.temId = temId;
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

	public String getTemTime() {
		return temTime;
	}

	public void setTemTime(String temTime) {
		this.temTime = temTime;
	}

	public int getTemOriginalLength() {
		return temOriginalLength;
	}

	public void setTemOriginalLength(int temOriginalLength) {
		this.temOriginalLength = temOriginalLength;
	}

	public int getTemLength() {
		return temLength;
	}

	public void setTemLength(int temLength) {
		this.temLength = temLength;
	}

	public String getTemStr() {
		return temStr;
	}

	public void setTemStr(String temStr) {
		this.temStr = temStr;
	}

	public String[] getTemperatureStr() {
		return temperatureStr;
	}

	public void setTemperatureStr(String[] temperatureStr) {
		this.temperatureStr = temperatureStr;
	}

	public String getTemColor() {
		return temColor;
	}

	public void setTemColor(String temColor) {
		this.temColor = temColor;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Temperature(int temId, int hostGuid, int channelId, String channelName, String temTime,
			int temOriginalLength, int temLength, String temStr, String[] temperatureStr, String temColor, int number) {
		super();
		this.temId = temId;
		this.hostGuid = hostGuid;
		this.channelId = channelId;
		this.channelName = channelName;
		this.temTime = temTime;
		this.temOriginalLength = temOriginalLength;
		this.temLength = temLength;
		this.temStr = temStr;
		this.temperatureStr = temperatureStr;
		this.temColor = temColor;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Temperature [temId=" + temId + ", hostGuid=" + hostGuid + ", channelId=" + channelId + ", channelName="
				+ channelName + ", temTime=" + temTime + ", temOriginalLength=" + temOriginalLength + ", temLength="
				+ temLength + ", temStr=" + temStr + ", temperatureStr=" + Arrays.toString(temperatureStr)
				+ ", temColor=" + temColor + ", number=" + number + "]";
	}

	public void initializationTemperatureStr() {
		temperatureStr = temStr.split(",");
	}
}

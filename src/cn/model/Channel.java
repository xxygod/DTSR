package cn.model;

public class Channel {
	private int channelId;
	private int hostGuid;
	private String channelName;
	private int channelCode;

	public Channel() {
		super();
	}

	public Channel(int channelId, int hostGuid, String channelName, int channelCode) {
		super();
		this.channelId = channelId;
		this.hostGuid = hostGuid;
		this.channelName = channelName;
		this.channelCode = channelCode;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getHostGuid() {
		return hostGuid;
	}

	public void setHostGuid(int hostGuid) {
		this.hostGuid = hostGuid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(int channelCode) {
		this.channelCode = channelCode;
	}

	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", hostGuid=" + hostGuid + ", channelName=" + channelName
				+ ", channelCode=" + channelCode + "]";
	}

}

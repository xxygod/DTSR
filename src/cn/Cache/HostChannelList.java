package cn.Cache;

import java.util.ArrayList;
import java.util.List;

import cn.model.Channel;
import cn.model.Host;

public class HostChannelList {
	private static HostChannelList instance = null;
	private static List<Host> hostList = new ArrayList<Host>();
	private static List<Channel> channelList = new ArrayList<Channel>();

	public static HostChannelList getInstance() {
		if (instance == null)
			instance = new HostChannelList();
		return instance;
	}

	public static List<Host> getHostList() {
		return hostList;
	}

	public static void setHostList(List<Host> hostList) {
		HostChannelList.hostList = hostList;
	}

	public static List<Channel> getChannelList() {
		return channelList;
	}

	public static void setChannelList(List<Channel> channelList) {
		HostChannelList.channelList = channelList;
	}

}

package cn.service;

import java.util.List;

import cn.model.AlarmRecord;
import cn.model.CabinetDiagram;
import cn.model.Channel;
import cn.model.Condition;
import cn.model.Host;
import cn.model.Temperature;
import cn.model.User;

public interface HostService {

	void addHost(Host host);

	void setHostName(Host host);

	void addChannel(Channel channel);

	void deleteChannel(Channel channel);

	void setChannelName(Channel channel);

	List<Host> getHostList();

	List<Channel> getChannelList();

	void setChannelCode(Channel channel);

	List<Integer> getChannelidList(Host host);

	void addTemperature(List<Temperature> temperatureList);

	void addAlarmRecord(AlarmRecord alarmRecord);

	List<AlarmRecord> getAlarmRecord(Condition condition);

	List<Temperature> getHostTemperatureList(Host host);

	Temperature getHostTemperature(Temperature temperature);

	List<AlarmRecord> getAlarmRecordToDay(Host host);

	void deleteTemperature(Host host);

	List<Temperature> getHostTimeTemperatureList(Condition condition);

	CabinetDiagram getCabinetDiagram(Host host);
}

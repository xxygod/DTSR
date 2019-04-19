package cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mapper.HostMapper;
import cn.mapper.UserMapper;
import cn.model.AlarmRecord;
import cn.model.CabinetDiagram;
import cn.model.Channel;
import cn.model.Condition;
import cn.model.Host;
import cn.model.Temperature;
import cn.service.HostService;

@Service
@Transactional // 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class HostServiceImpl implements HostService {

	@Autowired
	private HostMapper mapper;

	@Override
	public void addHost(Host host) {
		// TODO Auto-generated method stub
		mapper.addHost(host);
	}

	@Override
	public void setHostName(Host host) {
		// TODO Auto-generated method stub
		mapper.setHostName(host);
	}

	@Override
	public void addChannel(Channel channel) {
		// TODO Auto-generated method stub
		mapper.addChannel(channel);
	}

	@Override
	public void deleteChannel(Channel channel) {
		// TODO Auto-generated method stub
		mapper.deleteChannel(channel);
	}

	@Override
	public void setChannelName(Channel channel) {
		// TODO Auto-generated method stub
		mapper.setChannelName(channel);
	}

	@Override
	public List<Host> getHostList() {
		// TODO Auto-generated method stub
		return mapper.getHostList();
	}

	@Override
	public List<Channel> getChannelList() {
		// TODO Auto-generated method stub
		return mapper.getChannelList();
	}

	@Override
	public void setChannelCode(Channel channel) {
		System.out.println(channel);
		// TODO Auto-generated method stub
		mapper.setChannelCode(channel);
	}

	@Override
	public List<Integer> getChannelidList(Host host) {
		// TODO Auto-generated method stub
		return mapper.getChannelidList(host);
	}

	@Override
	public void addTemperature(List<Temperature> temperatureList) {
		// TODO Auto-generated method stub
		mapper.addTemperature(temperatureList);
	}

	@Override
	public void addAlarmRecord(AlarmRecord alarmRecord) {
		// TODO Auto-generated method stub
		mapper.addAlarmRecord(alarmRecord);
	}

	@Override
	public List<AlarmRecord> getAlarmRecord(Condition condition) {
		// TODO Auto-generated method stub
		condition.conditionS();
		return mapper.getAlarmRecord(condition);
	}

	@Override
	public List<Temperature> getHostTemperatureList(Host host) {
		// TODO Auto-generated method stub
		return mapper.getHostTemperatureList(host);
	}

	@Override
	public Temperature getHostTemperature(Temperature temperature) {
		// TODO Auto-generated method stub
		return mapper.getHostTemperature(temperature);
	}

	@Override
	public List<AlarmRecord> getAlarmRecordToDay(Host host) {
		// TODO Auto-generated method stub
		return mapper.getAlarmRecordToDay(host);
	}

	@Override
	public void deleteTemperature(Host host) {
		// TODO Auto-generated method stub
		mapper.deleteTemperature(host);
	}

	@Override
	public List<Temperature> getHostTimeTemperatureList(Condition condition) {
		// TODO Auto-generated method stub
		condition.timeS();
		return mapper.getHostTimeTemperatureList(condition);
	}

	@Override
	public CabinetDiagram getCabinetDiagram(Host host) {
		// TODO Auto-generated method stub
		return mapper.getCabinetDiagram(host);
	}

}

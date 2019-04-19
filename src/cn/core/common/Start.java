package cn.core.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import cn.Cache.HostChannelList;
import cn.Cache.ThreadTime;
import cn.model.Channel;
import cn.model.Host;
import cn.service.HostService;

public class Start implements ApplicationListener<ContextRefreshedEvent> {

	/*
	 * ReadWriteLock lock = new ReadWriteLock();
	 * 
	 * ThreadTime[] threadTime;
	 * 
	 * ThreadConfirmTime[] threadConfirmTime;
	 */
	ThreadTime[] threadTime;

	@Autowired
	private HostService hostService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getApplicationContext().getParent() == null) {// root
																// application
																// context
																// 没有parent，他就是老大.
			// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			/*
			 * List<Room> getRoom = roomService.getRoom();
			 * 
			 * // 线程创建 threadTime = new ThreadTime[getRoom.size()];
			 * threadConfirmTime = new ThreadConfirmTime[getRoom.size()]; for
			 * (int n = 0; n < getRoom.size(); ++n) {
			 * getRoom.get(n).setThreadTime(new ThreadTime());
			 * getRoom.get(n).setThreadConfirmTime(new ThreadConfirmTime());
			 * getRoom.get(n).getThreadConfirmTime().startGame();
			 * getRoom.get(n).getThreadTime().startGame();
			 * getRoom.get(n).getThreadTime().startGame(); } try {
			 * lock.lockWrite(); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 * GameRoomManager.setRoomList(getRoom); try { lock.unlockWrite(); }
			 * catch (InterruptedException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */

			// ThreadTime threadTime = new ThreadTime();
			// threadTime.start();

			List<Host> hostList = new ArrayList<Host>();
			hostList = hostService.getHostList();
			for (int n = 0; n < hostList.size(); ++n) {
				hostList.get(n).setThreadTime(new ThreadTime(hostList.get(n).getHostGuid()));
			}
			HostChannelList.setHostList(hostList);
			List<Channel> channel = new ArrayList<Channel>();
			channel = hostService.getChannelList();
			HostChannelList.setChannelList(channel);
			/*
			 * try { SendMessage.getInstance(); } catch (TooManyListeners e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
		}
	}
}

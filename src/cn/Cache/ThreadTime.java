package cn.Cache;

import cn.core.util.ReadWriteLock;
import cn.core.util.SystemTime;

public class ThreadTime {
	private int hostGuid;
	SystemTime time;
	private boolean code = false;
	private Thread thread = null;
	private int startTime, overTime, leftTime = 30;
	ReadWriteLock enterGameQueue = new ReadWriteLock();

	public ThreadTime(int hostGuid) {
		this.hostGuid = hostGuid;
	}

	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}

	public void start() {
		code = true;
		thread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				time = new SystemTime();
				startTime = time.getSumTime();
				while (code) {
					time = new SystemTime();
					overTime = time.getSumTime();
					leftTime = (overTime - startTime) / 60;
					// System.out.println("离线时间是：" + leftTime);
					if (leftTime >= 30) {
						for (int n = 0; n < HostChannelList.getHostList().size(); ++n) {
							if (HostChannelList.getHostList().get(n).getHostGuid() == hostGuid) {
								try {
									enterGameQueue.lockWrite();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								HostChannelList.getHostList().get(n).setHostCode(0);
								try {
									enterGameQueue.unlockWrite();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					} else {
						for (int n = 0; n < HostChannelList.getHostList().size(); ++n) {
							if (HostChannelList.getHostList().get(n).getHostGuid() == hostGuid) {
								try {
									enterGameQueue.lockWrite();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								HostChannelList.getHostList().get(n).setHostCode(1);
								try {
									enterGameQueue.unlockWrite();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};
		thread.start();
	}

	public void over() {
		code = false;
	}

	public void refresh() {
		startTime = time.getSumTime();
	}

	public boolean getCode() {
		return code;
	}
}

package cn.model;

import java.util.Arrays;

import cn.core.util.SystemTime;

public class Condition {
	private String hostGuid;
	private String channelId;
	private int[] channelIdList;
	private String beYear;
	private String beMonth;
	private String beDay;
	private String beHH;
	private String beMM;
	private String beSS;
	private String reYear;
	private String reMonth;
	private String reDay;
	private String beTime;
	private String reTime;
	private String arType2;
	private String arType3;
	private String arType4;
	private int[] arType;

	public Condition() {
		super();
	}

	public Condition(String hostGuid, String channelId, int[] channelIdList, String beYear, String beMonth,
			String beDay, String beHH, String beMM, String beSS, String reYear, String reMonth, String reDay,
			String beTime, String reTime, String arType2, String arType3, String arType4, int[] arType) {
		super();
		this.hostGuid = hostGuid;
		this.channelId = channelId;
		this.channelIdList = channelIdList;
		this.beYear = beYear;
		this.beMonth = beMonth;
		this.beDay = beDay;
		this.beHH = beHH;
		this.beMM = beMM;
		this.beSS = beSS;
		this.reYear = reYear;
		this.reMonth = reMonth;
		this.reDay = reDay;
		this.beTime = beTime;
		this.reTime = reTime;
		this.arType2 = arType2;
		this.arType3 = arType3;
		this.arType4 = arType4;
		this.arType = arType;
	}

	public String getHostGuid() {
		return hostGuid;
	}

	public void setHostGuid(String hostGuid) {
		this.hostGuid = hostGuid;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public int[] getChannelIdList() {
		return channelIdList;
	}

	public void setChannelIdList(int[] channelIdList) {
		this.channelIdList = channelIdList;
	}

	public String getBeYear() {
		return beYear;
	}

	public void setBeYear(String beYear) {
		this.beYear = beYear;
	}

	public String getBeMonth() {
		return beMonth;
	}

	public void setBeMonth(String beMonth) {
		this.beMonth = beMonth;
	}

	public String getBeDay() {
		return beDay;
	}

	public void setBeDay(String beDay) {
		this.beDay = beDay;
	}

	public String getReYear() {
		return reYear;
	}

	public void setReYear(String reYear) {
		this.reYear = reYear;
	}

	public String getReMonth() {
		return reMonth;
	}

	public void setReMonth(String reMonth) {
		this.reMonth = reMonth;
	}

	public String getReDay() {
		return reDay;
	}

	public void setReDay(String reDay) {
		this.reDay = reDay;
	}

	public String getBeTime() {
		return beTime;
	}

	public void setBeTime(String beTime) {
		this.beTime = beTime;
	}

	public String getReTime() {
		return reTime;
	}

	public void setReTime(String reTime) {
		this.reTime = reTime;
	}

	public String getArType2() {
		return arType2;
	}

	public void setArType2(String arType2) {
		this.arType2 = arType2;
	}

	public String getArType3() {
		return arType3;
	}

	public void setArType3(String arType3) {
		this.arType3 = arType3;
	}

	public String getArType4() {
		return arType4;
	}

	public void setArType4(String arType4) {
		this.arType4 = arType4;
	}

	public int[] getArType() {
		return arType;
	}

	public void setArType(int[] arType) {
		this.arType = arType;
	}

	public String getBeHH() {
		return beHH;
	}

	public void setBeHH(String beHH) {
		this.beHH = beHH;
	}

	public String getBeMM() {
		return beMM;
	}

	public void setBeMM(String beMM) {
		this.beMM = beMM;
	}

	public String getBeSS() {
		return beSS;
	}

	public void setBeSS(String beSS) {
		this.beSS = beSS;
	}

	@Override
	public String toString() {
		return "Condition [hostGuid=" + hostGuid + ", channelId=" + channelId + ", channelIdList="
				+ Arrays.toString(channelIdList) + ", beYear=" + beYear + ", beMonth=" + beMonth + ", beDay=" + beDay
				+ ", beHH=" + beHH + ", beMM=" + beMM + ", beSS=" + beSS + ", reYear=" + reYear + ", reMonth=" + reMonth
				+ ", reDay=" + reDay + ", beTime=" + beTime + ", reTime=" + reTime + ", arType2=" + arType2
				+ ", arType3=" + arType3 + ", arType4=" + arType4 + ", arType=" + Arrays.toString(arType) + "]";
	}

	public void conditionS() {
		if (hostGuid.length() == 0)
			hostGuid = "0";
		String[] cl = null;
		if (channelId != null) {
			cl = channelId.split(",");
			channelIdList = new int[cl.length];
			for (int i = 0; i < cl.length; i++) {
				channelIdList[i] = Integer.parseInt(cl[i]);
			}
		} else
			channelIdList = new int[0];
		if (0 == beYear.length())
			beYear = "2000";
		if (0 == beMonth.length())
			beMonth = "1";
		if (0 == beDay.length())
			beDay = "1";
		if (0 == reYear.length())
			reYear = "2100";
		if (0 == reMonth.length())
			reMonth = "12";
		if (0 == reDay.length())
			reDay = "31";
		beTime = beYear + "-" + beMonth + "-" + beDay;
		reTime = reYear + "-" + reMonth + "-" + reDay;
		int n = 0;
		if (arType2 != null)
			++n;
		if (arType3 != null)
			++n;
		if (arType4 != null)
			++n;
		arType = new int[n];
		if (arType2 != null) {
			arType[n - 1] = Integer.parseInt(arType2);
			--n;
		}
		if (arType3 != null) {
			arType[n - 1] = Integer.parseInt(arType3);
			--n;
		}
		if (arType4 != null) {
			arType[n - 1] = Integer.parseInt(arType4);
			--n;
		}
	}

	public void timeS() {
		SystemTime time;
		time = new SystemTime();
		if (0 == beYear.length())
			beYear = String.valueOf(time.getYY());
		if (0 == beMonth.length())
			beMonth = String.valueOf(time.getMM());
		if (0 == beDay.length())
			beDay = String.valueOf(time.getDD());
		if (0 == beHH.length())
			beHH = String.valueOf(time.getHH());
		if (0 == beMM.length())
			beMM = String.valueOf(time.getMm());
		if (0 == beSS.length())
			beSS = String.valueOf(time.getSS());
		beTime = beYear + "-" + beMonth + "-" + beDay + " " + beHH + ":" + beMM + ":" + beSS;
	}

}

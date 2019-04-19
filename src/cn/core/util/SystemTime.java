package cn.core.util;

import java.util.Calendar;

public class SystemTime {
	Calendar Cld = Calendar.getInstance();
	int YY = Cld.get(Calendar.YEAR);

	public Calendar getCld() {
		return Cld;
	}

	public void setCld(Calendar cld) {
		Cld = cld;
	}

	public int getYY() {
		return YY;
	}

	public void setYY(int yY) {
		YY = yY;
	}

	public int getMM() {
		return MM;
	}

	public void setMM(int mM) {
		MM = mM;
	}

	public int getDD() {
		return DD;
	}

	public void setDD(int dD) {
		DD = dD;
	}

	public int getHH() {
		return HH;
	}

	public void setHH(int hH) {
		HH = hH;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getSS() {
		return SS;
	}

	public void setSS(int sS) {
		SS = sS;
	}

	public int getMI() {
		return MI;
	}

	public void setMI(int mI) {
		MI = mI;
	}

	public int getSumTime() {
		return SS + mm * 60 + HH * 3600 + DD * 24 * 3600;
	}

	int MM = Cld.get(Calendar.MONTH) + 1;
	int DD = Cld.get(Calendar.DATE);
	int HH = Cld.get(Calendar.HOUR_OF_DAY);
	int mm = Cld.get(Calendar.MINUTE);
	int SS = Cld.get(Calendar.SECOND);
	int MI = Cld.get(Calendar.MILLISECOND);

	public String getSystemTime() {
		System.out.println(YY + "/" + MM + "/" + DD + "-" + HH + ":" + mm + ":" + SS + ":" + MI);
		return (YY + "/" + MM + "/" + DD + "-" + HH + ":" + mm + ":" + SS + ":" + MI);
	}
}

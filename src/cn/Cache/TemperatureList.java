package cn.Cache;

import java.util.ArrayList;
import java.util.List;
import cn.model.Temperature;

public class TemperatureList {
	private static TemperatureList instance = null;
	private static List<Temperature> temperatureList = new ArrayList<Temperature>();

	public static TemperatureList getInstance() {
		if (instance == null)
			instance = new TemperatureList();
		return instance;
	}

	public static List<Temperature> getTemperatureList() {
		return temperatureList;
	}

	public static void setTemperatureList(List<Temperature> temperatureList) {
		TemperatureList.temperatureList = temperatureList;
	}

}

package cn.Cache;

import java.util.ArrayList;
import java.util.List;

import cn.model.PositionList;

public class CabinetDiagramPositionList {
	private static CabinetDiagramPositionList instance = null;
	private static List<PositionList> positionListList = new ArrayList<PositionList>();

	public static CabinetDiagramPositionList getInstance() {
		if (instance == null)
			instance = new CabinetDiagramPositionList();
		return instance;
	}

	public static List<PositionList> getPositionListList() {
		return positionListList;
	}

	public static void setPositionListList(List<PositionList> positionListList) {
		CabinetDiagramPositionList.positionListList = positionListList;
	}

}

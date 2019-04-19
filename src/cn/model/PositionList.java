package cn.model;

import java.util.ArrayList;
import java.util.List;

public class PositionList {
	private int hostGuid;
	private List<Position> positionList = new ArrayList<Position>();

	public int getHostGuid() {
		return hostGuid;
	}

	public void setHostGuid(int hostGuid) {
		this.hostGuid = hostGuid;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	public PositionList() {
		super();
	}

	public PositionList(int hostGuid, List<Position> positionList) {
		super();
		this.hostGuid = hostGuid;
		this.positionList = positionList;
	}

	@Override
	public String toString() {
		return "PositionList [hostGuid=" + hostGuid + ", positionList=" + positionList + "]";
	}

}

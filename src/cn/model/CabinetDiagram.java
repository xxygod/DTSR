package cn.model;

public class CabinetDiagram {
	private int cdId;
	private int hostGuid;
	private String cdSrc;

	public int getCdId() {
		return cdId;
	}

	public void setCdId(int cdId) {
		this.cdId = cdId;
	}

	public int getHostGuid() {
		return hostGuid;
	}

	public void setHostGuid(int hostGuid) {
		this.hostGuid = hostGuid;
	}

	public String getCdSrc() {
		return cdSrc;
	}

	public void setCdSrc(String cdSrc) {
		this.cdSrc = cdSrc;
	}

	public CabinetDiagram(int cdId, int hostGuid, String cdSrc) {
		super();
		this.cdId = cdId;
		this.hostGuid = hostGuid;
		this.cdSrc = cdSrc;
	}

	public CabinetDiagram() {
		super();
	}

	@Override
	public String toString() {
		return "CabinetDiagram [cdId=" + cdId + ", hostGuid=" + hostGuid + ", cdSrc=" + cdSrc + "]";
	}

}

package cn.model;

public class Position {
	private double X;
	private double Y;
	private String time;
	private double position;
	private double value;
	private int type;

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Position() {
		super();
	}

	public Position(double x, double y, String time, double position, double value, int type) {
		super();
		X = x;
		Y = y;
		this.time = time;
		this.position = position;
		this.value = value;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Position [X=" + X + ", Y=" + Y + ", time=" + time + ", position=" + position + ", value=" + value
				+ ", type=" + type + "]";
	}

}
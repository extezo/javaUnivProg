/** ��������� ����� �����. **/
public class Point2d {
	/**
	 * ���������� X
	 **/
	private double xCoord;
	/**
	 * ���������� Y
	 **/
	private double yCoord;

	/**
	 * ����������� �������������
	 **/

	public Point2d(double x, double y) {
		xCoord = x;
		yCoord = y;
	}

	/**
	 * ����������� �� ���������.
	 **/

	public Point2d() {
		// �������� ����������� � ����� ����������� � ���������� ��������.
		this(0, 0);
	}

	/**
	 * ����������� ���������� X
	 **/

	public double getX() {
		return xCoord;

	}

	/**
	 * ����������� ���������� Y
	 **/

	public double getY() {
		return yCoord;
	}

	/**
	 * ��������� �������� ���������� X.
	 **/

	public void setX(double val) {
		xCoord = val;
	}

	/**
	 * ��������� �������� ���������� Y.
	 **/

	public void setY(double val) {
		yCoord = val;
	}
}

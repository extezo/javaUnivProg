
public class Point3d {
	private double x, y, z;
	public Point3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Point3d() {
		this(0, 0, 0);
	}
	//"�������" � "�������" ��� ����������� ������� � ����� ������
	/**
	 * @return x ���������� x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x ���������� x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return y ���������� y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y ���������� y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @return z ���������� z
	 */
	public double getZ() {
		return z;
	}
	/**
	 * @param z ���������� z
	 */
	public void setZ(double z) {
		this.z = z;
	}


	/**
	 * @return z ��������� ��������� �����
	 */
	public static boolean compare(Point3d p1, Point3d p2) { //����������� ����� ��������� �����
		return p1.getX() == p2.getX() && p1.getY() == p2.getY() && p1.getZ() == p2.getZ();
	}
	/**
	 * @param p ������ ����� ��� ���������� ����������
	 * @return distance ���������� ����� �������
	 */
	public double distanceTo(Point3d p) {
		double distance = Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2) + Math.pow(this.z - p.z, 2));
		return Math.round(distance * 100) / 100.0;
		//return distance;
	}

}

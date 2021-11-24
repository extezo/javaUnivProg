import java.util.Locale;
import java.util.Scanner;

public class Lab2 {
	// ���������� Scanner ��� ����������������� ����� �� ����� ���������� ���������
	public static Scanner in = new Scanner(System.in).useLocale(Locale.US);

	public static void main(String[] args) {
		// �� ������ ����� ������ ��� ���������� ������ Point3d
		Point3d p1 = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
		Point3d p2 = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
		Point3d p3 = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
		// ��������� ����� �� ���������
		if (Point3d.compare(p1, p2) || Point3d.compare(p3, p2) || Point3d.compare(p1, p3))
			System.out.println("��� ��� ����� ����� ����� ������ ����������!");
		else
			System.out.println("������� = " + computeArea(p1, p2, p3));
	}
	/**
	 *
	 * @param p1 ������ �����
	 * @param p2 ������ �����
	 * @param p3 ������ �����
	 * @return ������� ������������
	 */
	public static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
		// ������� ����� ������ ������������, ������������� ���� �������
		double a = p1.distanceTo(p2);
		double b = p2.distanceTo(p3);
		double c = p3.distanceTo(p1);
		// ������� ������������ ������������
		double halfPerimeter = (a + b + c) / 2;
		// ��������� ������� ������������ �� ������� ������
		return Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
	}
}

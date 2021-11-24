import java.util.Locale;
import java.util.Scanner;

public class Lab2 {
	// Используем Scanner для пользовательского ввода во время выполнения программы
	public static Scanner in = new Scanner(System.in).useLocale(Locale.US);

	public static void main(String[] args) {
		// На основе ввода создаём три экземпляра класса Point3d
		Point3d p1 = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
		Point3d p2 = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
		Point3d p3 = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
		// Проверяем точки на равенство
		if (Point3d.compare(p1, p2) || Point3d.compare(p3, p2) || Point3d.compare(p1, p3))
			System.out.println("Две или более точек имеют равные координаты!");
		else
			System.out.println("Площадь = " + computeArea(p1, p2, p3));
	}
	/**
	 *
	 * @param p1 Первая точка
	 * @param p2 Вторая точка
	 * @param p3 Третья точка
	 * @return Площадь треугольника
	 */
	public static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
		// Находим длины сторон треугольника, образованного трёмя точками
		double a = p1.distanceTo(p2);
		double b = p2.distanceTo(p3);
		double c = p3.distanceTo(p1);
		// Находим полупериметр треугольника
		double halfPerimeter = (a + b + c) / 2;
		// Вычисляем площадь треугольника по формуле Герона
		return Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
	}
}

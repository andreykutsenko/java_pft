package ru.stqa.pft.sandbox;

public class MainDistance {

  public static void main(String[] args) {

    // Создание первого объекта Point с начальными координатами точки (0;0)
    Point p1 = new Point(0, 0);
    // Задание новых координат точки с клавиатуры
    p1.inputPoint("первой");
    // Вывод координаты точки на экран:
    p1.printPoint();

    // Создание второго объекта Point с начальными координатами точки (0;0)
    Point p2 = new Point(0, 0);
    // Задание новых координат точки с клавиатуры
    p2.inputPoint("второй");
    // Вывод координаты точки на экран:
    p2.printPoint();

    //Вызов функции
//    System.out.println("Расстояние между точками: " + distance(p1, p2));
    //Вызов метода
    System.out.println("Расстояние между точками: " + p1.distance(p2));
  }

  // Функция расчета расстояния между двумя точками
  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }


}







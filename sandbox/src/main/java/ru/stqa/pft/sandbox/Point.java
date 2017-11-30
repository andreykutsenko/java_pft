package ru.stqa.pft.sandbox;

import java.util.Scanner;

public class Point {

  public double x;
  public double y;

  // Вывод координаты точки на экран:
  public void printPoint() {
    System.out.print("Координаты точки: ");
    System.out.println("(" + x + ";" + y + ")");
    System.out.println();
  }

  // Задание новых координат точки с клавиатуры
  public void inputPoint(String s) {
    System.out.println("Задайте координаты " + s + " точки: ");
    Scanner inp = new Scanner(System.in);
    System.out.print("Введите значение X: ");
    x = inp.nextDouble();
    System.out.print("Введите значение Y: ");
    y = inp.nextDouble();
  }

  // Метод расчета расстояния между двумя точками
  public double distance(Point p2) {
    return Math.sqrt((p2.x - x) * (p2.x - x) + (p2.y - y) * (p2.y - y));
  }

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

}

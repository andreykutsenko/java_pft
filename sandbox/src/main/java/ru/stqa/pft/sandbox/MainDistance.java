package ru.stqa.pft.sandbox;

import java.util.Random;

public class MainDistance {

  public static void main(String[] args) {

//    int[] p1 = new int[2];
//    int[] p2 = new int[2];
//    Random random = new Random();
//
//    //устанавливаем координаты точки №1
//    p1[0] = random.nextInt(10);
//    p1[1] = random.nextInt(10);
//
//    //устанавливаем координаты точки №2
//    p2[0] = random.nextInt(10);
//    p2[1] = random.nextInt(10);
//
//    System.out.println("Расстояние между точками: " + distance(p1,p2));
//    System.out.println("- координаты точки №1 (x1,y1): (" + p1[0] + "," + p1[1] + ")");
//    System.out.println("- координаты точки №2 (x2,y2): (" + p2[0] + "," + p2[1] + ")");

    Point p = new Point();

    System.out.println("Расстояние между точками: " + p.distance());
    System.out.println("- координаты точки №1 (x1,y1): (" + p.p1[0] + "," + p.p1[1] + ")");
    System.out.println("- координаты точки №2 (x2,y2): (" + p.p2[0] + "," + p.p2[1] + ")");

  }

  public static double distance(int[] p1,int[] p2) {
    return Math.sqrt((p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]));
  }

}






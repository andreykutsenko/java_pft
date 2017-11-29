package ru.stqa.pft.sandbox;

import java.util.Random;

public class Point {

  int[] p1 = new int[2];
  int[] p2 = new int[2];
  Random random = new Random();

//  public Point(int[] p1, int[] p2) {
//    this.p1 = p1;
//    this.p2 = p2;
//  }

  public double distance() {

    //устанавливаем координаты точки №1
    p1[0] = random.nextInt(10);
    p1[1] = random.nextInt(10);

    //устанавливаем координаты точки №2
    p2[0] = random.nextInt(10);
    p2[1] = random.nextInt(10);

    return Math.sqrt((p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]));
  }

}

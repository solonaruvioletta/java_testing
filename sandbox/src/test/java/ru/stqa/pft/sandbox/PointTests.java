package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 27.01.2017.
 */
public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(4, 3);
    Point p2 = new Point(8, 6);
    Assert.assertEquals(p2.distance(p1),5.0);
  }
}
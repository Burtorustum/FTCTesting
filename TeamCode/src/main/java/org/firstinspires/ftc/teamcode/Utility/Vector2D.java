package org.firstinspires.ftc.teamcode.Utility;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Vector;

public class Vector2D {
  public double x, y;

  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vector2D add(Vector2D other) {
    return new Vector2D(this.x + other.x, this.y + other.y);
  }

  public Vector2D scale(double s){
    return new Vector2D(this.x*s, this.y*s);
  }

  public double magnitude() {
    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
  }

  public Vector2D norm() {
    double mag = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    return (new Vector2D(x / mag, y / mag));
  }

  @Override
  public String toString() {
    return "(" + Double.toString(x) + ", " + Double.toString(y) + ")";
  }

}

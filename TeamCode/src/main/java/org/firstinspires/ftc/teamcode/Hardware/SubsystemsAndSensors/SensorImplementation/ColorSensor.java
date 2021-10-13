package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation;


import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.DriveTrains.IDriveTrain;

public class ColorSensor implements ISensor, IOutputFunc<Double> {

  ColorSensor colorSensor;

  public ColorSensor() {

  }

  @Override
  public void init(HardwareMap hwMap) {

  }

  @Override
  public Double getOutput() {
    return 0.0;
  }
}

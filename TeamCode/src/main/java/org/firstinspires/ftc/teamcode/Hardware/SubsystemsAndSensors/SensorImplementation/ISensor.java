package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.ISubsystem;

public interface ISensor<T> extends ISubsystem {

  T getOutput();

  @Override
  String toString();
}

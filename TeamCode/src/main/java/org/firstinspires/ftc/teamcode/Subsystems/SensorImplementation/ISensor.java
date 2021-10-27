package org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation;

import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;

public interface ISensor<T> extends ISubsystem {

  T getOutput();
}

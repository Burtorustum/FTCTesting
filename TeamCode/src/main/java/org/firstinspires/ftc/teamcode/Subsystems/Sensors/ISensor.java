package org.firstinspires.ftc.teamcode.Subsystems.Sensors;

import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;

public interface ISensor<T> extends ISubsystem {

  T getOutput();
}

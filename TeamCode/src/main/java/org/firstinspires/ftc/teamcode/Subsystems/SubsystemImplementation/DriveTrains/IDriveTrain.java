package org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains;

import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;

public interface IDriveTrain extends ISubsystem {

  void driveStraight(double power);

  void turnLeft(double power);

  void turnRight(double power);

  void setMotorPower(DTMotorPos[] positions, double[] powers);

  void setMotorRunMode(RunMode motorMode);

  void setMotorZeroPower(ZeroPowerBehavior behavior);

  boolean runToDistance(int distance, DistanceUnit units); // Make it take a position on the field

}

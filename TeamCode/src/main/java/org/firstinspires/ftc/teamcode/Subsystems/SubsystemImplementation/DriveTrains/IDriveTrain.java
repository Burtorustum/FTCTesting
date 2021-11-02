package org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains;

import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.sun.tools.javac.util.Pair;
import java.util.List;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;

public interface IDriveTrain extends ISubsystem {

  /**
   *
   * @param power
   */
  void turnRight(double power);

  /**
   *
   * @param powers
   */
  void setMotorPower(List<Pair<DTMotorPos, Double>> powers);

  /**
   *
   * @param motorMode
   */
  void setMotorRunMode(RunMode motorMode);

  /**
   *
   * @param behavior
   */
  void setMotorZeroPower(ZeroPowerBehavior behavior);

  /**
   *
   * @param targets
   */
  void setTargetPosition(List<Pair<DTMotorPos, Integer>> targets);

  /**
   *
   * @return
   */
  List<Pair<DTMotorPos, Integer>> getCurrentPosition();

}

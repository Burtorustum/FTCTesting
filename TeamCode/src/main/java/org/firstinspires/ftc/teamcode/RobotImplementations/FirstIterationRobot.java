package org.firstinspires.ftc.teamcode.RobotImplementations;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Robot.Start;
import org.firstinspires.ftc.teamcode.Robot.Team;

public class FirstIterationRobot extends BaseRobot {

  public FirstIterationRobot(HardwareMap hwMap) {
    super(hwMap);
  }

  @Override
  public boolean autoInit(HardwareMap hwMap) {
    return false;
  }

  @Override
  public boolean teleopInit(HardwareMap hwMap) {
    return false;
  }

  @Override
  public void autoInitLoop() {

  }

  @Override
  public void teleopInitLoop() {

  }

  @Override
  public void autoStart() {

  }

  @Override
  public void teleopStart() {

  }

  @Override
  public void runAuto(Team team, Start start) {

  }

  @Override
  public void runTeleop(Gamepad gamepad1, Gamepad gamepad2) {

  }

  @Override
  public void stop() {

  }
}

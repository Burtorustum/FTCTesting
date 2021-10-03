package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.List;
import org.firstinspires.ftc.teamcode.Robot.AutonSection;
import org.firstinspires.ftc.teamcode.Robot.Sensors.ISensor;
import org.firstinspires.ftc.teamcode.Robot.Start;
import org.firstinspires.ftc.teamcode.Robot.Team;

public interface ISubsystem {

  boolean autoInit(HardwareMap hwMap);

  boolean teleopInit(HardwareMap hwMap);

  void autoInitLoop();

  void teleopInitLoop();

  void autoStart();

  void teleopStart();

  void runAuto(Team team, Start start, AutonSection autonSection, List<ISensor> sensors);

  void runTeleop(Gamepad gamepad1, Gamepad gamepad2);

  void stop();
}

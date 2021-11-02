package org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopStates;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class FourMotorTankDrive extends ATeleopState {

  public FourMotorTankDrive(Gamepad gp1, Gamepad gp2) {
    super(gp1, gp2);
  }

  @Override
  public List<String> getTelemetry() {
    return new ArrayList<>();
  }

  @Override
  public List<GamepadButtons> getButtons() {
    List<GamepadButtons> buttons = new ArrayList<>();
    buttons.add(GamepadButtons.GAMEPAD_1_LSY);
    buttons.add(GamepadButtons.GAMEPAD_1_RSY);

    return buttons;
  }

  @Override
  public List<Class<? extends ISubsystem>> getSubsystems() {
    List<Class<? extends ISubsystem>> subsystemClasses = new ArrayList<>();
    subsystemClasses.add(MecanumDriveTrain.class);

    return subsystemClasses;
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
      driveTrain.setFLPower(-this.gp1.left_stick_y);
      driveTrain.setBLPower(-this.gp1.left_stick_y);
      driveTrain.setFRPower(-this.gp1.right_stick_y);
      driveTrain.setBRPower(-this.gp1.right_stick_y);
  }

}

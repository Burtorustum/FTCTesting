package org.firstinspires.ftc.teamcode.UtilOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.AOpMode;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.Auton.AutonStates.GyroTurn;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;
import org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopStates.ATeleopState;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.MecanumDriveRobot;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

@TeleOp(name = "PID Turn Tuner", group = "Tuning")
public class PIDTuner extends AOpMode {

  @Override
  protected IRobot setupRobot() {
    return new MecanumDriveRobot(hardwareMap,
        new StartParameters(StartParameters.Mode.TELEOP, StartParameters.Start.IRRELEVANT, StartParameters.Team.IRRELEVANT));
  }

  @Override
  protected List<ARobotState> setupStates() {
    List<ARobotState> stateList = new ArrayList<>();
    stateList.add(new TuneGyroPID(new GyroTurn(0,0,0, 180, true),
        gamepad1, gamepad2));
    return stateList;
  }
}

class TuneGyroPID extends ATeleopState {
  private final GyroTurn turnState;
  private final ElapsedTime timer;

  private double target, kp, ki, kd;
  private boolean direction;

  public TuneGyroPID(GyroTurn turnState, Gamepad gp1, Gamepad gp2) {
    super(gp1, gp2);

    this.turnState = turnState;
    this.timer = new ElapsedTime();

    this.target = 0;
    this.kp = 0;
    this.ki = 0;
    this.kd = 0;

    this.direction = false;
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    if (timer.seconds() > 0.5) {
      if (this.gp1.dpad_up) {
        if (this.gp1.a) {
          this.ki += 0.05;
        } else if (this.gp1.b) {
            this.kd += 0.05;
        } else if (this.gp1.x) {
          this.kp += 0.05;
        } else if (this.gp1.y && this.target < 360) {
          this.target += 15;
        }
        this.timer.reset();
      }
      else if (this.gp1.dpad_down) {
        if (this.gp1.a && this.ki > 0) {
          this.ki -= 0.05;
        } else if (this.gp1.b && this.kd > 0) {
          this.kd -= 0.05;
        } else if (this.gp1.x && this.kp > 0) {
          this.kp -= 0.05;
        } else if (this.gp1.y && this.target > 0) {
          this.target -= 15;
        }
        this.timer.reset();
      }
      else if (this.gp1.left_bumper) {
        this.direction = !this.direction;
        this.turnState.changeDirection(this.direction);
        timer.reset();
      }
    }
    if (this.gp1.right_trigger > 0.7) {
      this.turnState.setKVals(this.kp, this.ki, this.kd);
      this.turnState.setTarget(this.target);
      this.turnState.receiveMecanumDriveTrain(driveTrain);
    } else if (this.gp1.left_trigger > 0.7) {
      this.kp = 0;
      this.ki = 0;
      this.kd = 0;
    } else {
      driveTrain.stop();
    }
  }


  @Override
  public void receiveGyro(IMUGyro gyro) {
    this.turnState.receiveGyro(gyro);
  }

  @Override
  public List<String> getTelemetry() {
    List<String> telem = this.turnState.getTelemetry();
    telem.add("target: " + this.target);
    telem.add("kp: " + this.kp);
    telem.add("ki: " + this.ki);
    telem.add("kd: " + this.kd);

    return telem;
  }

  @Override
  public List<GamepadButtons> getButtons() {
    return Arrays.asList(GamepadButtons.values());
  }
}



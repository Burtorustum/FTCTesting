package org.firstinspires.ftc.teamcode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.AutonStateMachine;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.IStateMachine;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.TeleopStateMachine;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public abstract class AOpMode extends OpMode {
  private IRobot robot;
  private IStateMachine stateMachine;

  private boolean isInitialized = false;

  /**
   *
   * @return a complete instance of the robot object desired for this OpMode
   */
  protected abstract IRobot setupRobot();

  /**
   *
   * @return A list of states for this robot to iterate through in this OpMode. If an autonomous mode the states must be given in the order to be executed. If teleop order within the list does not matter.
   */
  protected abstract List<ARobotState> setupStates();

  private void setupStateMachine() {
    switch(this.robot.getParams().getMode()) {
      case TELEOP:
        this.stateMachine = new TeleopStateMachine(this.robot, this.setupStates());
        break;
      case AUTON:
        this.stateMachine = new AutonStateMachine(this.robot, this.setupStates());
        break;
    }
  }

  @Override
  public void init() {
    this.robot = this.setupRobot();
    this.setupStateMachine();
  }

  @Override
  public void init_loop() {
    if (this.isInitialized) {
      this.robot.initLoop();
    } else {
    this.isInitialized = this.robot.isInitialized();
    }
    this.telemetry.addLine("initialized? " + this.isInitialized);
    this.telemetry.update();
  }

  @Override
  public void start() {
    this.robot.start();
  }

  @Override
  public void loop() {
    List<String> telem = this.stateMachine.iterate();
    for (String s : telem) {
      this.telemetry.addLine(s);
    }
    this.telemetry.update();
  }

  @Override
  public void stop() {
    this.robot.stop();
  }
}

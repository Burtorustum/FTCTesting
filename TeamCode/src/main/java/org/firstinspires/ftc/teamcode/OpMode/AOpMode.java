package org.firstinspires.ftc.teamcode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.List;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.AutonStateMachine;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.IStateMachine;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.TeleopStateMachine;

public abstract class AOpMode extends OpMode {
  private IRobot robot;
  private IStateMachine stateMachine;

  /**
   *
   * @return an instance of the robot object desired for this OpMode
   */
  protected abstract IRobot setupRobot();

  /**
   *
   * @return A list of states for this robot to iterate through in this OpMode. If an autonomous mode the states must be given in the order to be executed. If teleop order within the list does not matter.
   */
  protected abstract List<IRobotController> setupStates();

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
    this.robot.initLoop();
  }

  @Override
  public void start() {
    this.robot.start();
  }

  @Override
  public void loop() {
    this.stateMachine.iterate();
  }

  @Override
  public void stop() {
    this.robot.stop();
  }
}

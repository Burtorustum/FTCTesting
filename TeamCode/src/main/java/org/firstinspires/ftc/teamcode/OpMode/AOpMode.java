package org.firstinspires.ftc.teamcode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.sun.tools.javac.util.Pair;
import java.util.Collection;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.PriorityStateMachine;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public abstract class AOpMode extends OpMode {

  private IRobot robot;
  private PriorityStateMachine stateMachine;

  private boolean isInitialized = false;

  /**
   * @return a complete instance of the robot object desired for this OpMode
   */
  protected abstract IRobot setupRobot();

  /**
   * @return A list of states for this robot to iterate through in this OpMode. If an autonomous
   * mode the states must be given in the order to be executed. If teleop order within the list does
   * not matter.
   */
  protected abstract Collection<Pair<Integer, ARobotState>> setupStates();

  private void setupStateMachine() {
    this.stateMachine = new PriorityStateMachine(this.robot, this.setupStates());
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
    List<String> outTelemetry = this.stateMachine.iterate();
    for (String s : outTelemetry) {
      this.telemetry.addLine(s);
    }
    this.telemetry.update();
  }

  @Override
  public void stop() {
    this.robot.stop();
  }
}

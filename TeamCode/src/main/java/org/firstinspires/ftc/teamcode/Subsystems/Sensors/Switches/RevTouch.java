package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class RevTouch extends ASensor<Boolean> {

  private RevTouchSensor touchSensor;

  public RevTouch(HardwareMap hwMap, Mode mode, String configName) {
    super(hwMap, mode, configName);
  }

  @Override
  public void autoInit(HardwareMap hwMap, String configName) {
    this.touchSensor = hwMap.get(RevTouchSensor.class, configName);
  }

  @Override
  public void teleopInit(HardwareMap hwMap, String configName) {
    this.touchSensor = hwMap.get(RevTouchSensor.class, configName);
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveRevTouchSensor(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  public Boolean getOutput() {
    return this.touchSensor.isPressed();
  }
}

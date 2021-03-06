package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsTouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.Collection;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class MRTouch extends ASensor<Boolean> {

  private ModernRoboticsTouchSensor touchSensor;

  public MRTouch(HardwareMap hwMap, Mode mode, String configName) {
    super(hwMap, mode, configName);
  }

  @Override
  public void autoInit(HardwareMap hwMap, String configName) {
    this.touchSensor = hwMap.get(ModernRoboticsTouchSensor.class, configName);
  }

  @Override
  public void teleopInit(HardwareMap hwMap, String configName) {
    this.touchSensor = hwMap.get(ModernRoboticsTouchSensor.class, configName);
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveMRTouchSensor(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  public Boolean getOutput() {
    return this.touchSensor.isPressed();
  }

  @Override
  public Collection<String> getInformation() {
    Collection<String> c  = new ArrayList<>();
    c.add("Sensor: " + this.touchSensor.getDeviceName() + " --------");
    c.add("Is pressed? " + this.getOutput());
    return c;
  }
}


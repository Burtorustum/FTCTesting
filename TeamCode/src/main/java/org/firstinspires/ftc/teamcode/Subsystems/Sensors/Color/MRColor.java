package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.HashMap;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class MRColor extends ASensor<HashMap<ARGB, Integer>> {
  private ModernRoboticsI2cColorSensor colorSensor;

  public MRColor(HardwareMap hwMap, Mode mode, String configName) {
    super(hwMap, mode, configName);
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveMRColor(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  protected void autoInit(HardwareMap hwMap, String configName) {
    this.colorSensor = hwMap.get(ModernRoboticsI2cColorSensor.class, configName);
  }

  @Override
  protected void teleopInit(HardwareMap hwMap, String configName) {
    this.colorSensor = hwMap.get(ModernRoboticsI2cColorSensor.class, configName);
  }

  @Override
  public HashMap<ARGB, Integer> getOutput() {
    HashMap<ARGB, Integer> values = new HashMap<>();
    values.put(ARGB.ALPHA, this.colorSensor.alpha());
    values.put(ARGB.RED, this.colorSensor.red());
    values.put(ARGB.GREEN, this.colorSensor.green());
    values.put(ARGB.BLUE, this.colorSensor.blue());

    return values;
  }
}

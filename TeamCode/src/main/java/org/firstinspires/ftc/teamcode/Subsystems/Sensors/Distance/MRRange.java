package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Distance;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class MRRange extends ASensor<Double> {
  private ModernRoboticsI2cRangeSensor rangeSensor;
  private final DistanceUnit unit;

  public MRRange(HardwareMap hwMap, Mode mode, String configName, DistanceUnit unit) {
    super(hwMap, mode, configName);
    this.unit = unit;
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveMRRange(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  protected void autoInit(HardwareMap hwMap, String configName) {
    this.rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, configName);
  }

  @Override
  protected void teleopInit(HardwareMap hwMap, String configName) {
    this.rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, configName);
  }

  @Override
  public Double getOutput() {
    return this.rangeSensor.getDistance(this.unit);
  }
}

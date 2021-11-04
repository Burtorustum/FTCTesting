package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Distance;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class Rev2M extends ASensor<Double> {

  private Rev2mDistanceSensor distSensor;
  private final DistanceUnit unit;

  public Rev2M(HardwareMap hwMap, StartParameters.Mode mode, String configName, DistanceUnit unit) {
    super(hwMap, mode, configName);
    this.unit = unit;
  }

  @Override
  protected void autoInit(HardwareMap hwMap, String configName) {
    this.distSensor = hwMap.get(Rev2mDistanceSensor.class, configName);
  }

  @Override
  protected void teleopInit(HardwareMap hwMap, String configName) {
    this.distSensor = hwMap.get(Rev2mDistanceSensor.class, configName);
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveRev2m(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  public Double getOutput() {
    return this.distSensor.getDistance(this.unit);
  }

}

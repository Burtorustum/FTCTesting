package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Distance;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.ASubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ISensor;

public class Rev2M extends ASubsystem implements ISensor<Double> {

  private  Rev2mDistanceSensor distSensor;
  private final DistanceUnit unit;

  private boolean isInit = false;

  public Rev2M(HardwareMap hwMap, StartParameters.Mode mode, DistanceUnit unit) {
    super(hwMap, mode);
    this.unit = unit;
  }

  @Override
  public void autoInit(HardwareMap hwMap) {
    this.distSensor = hwMap.get(Rev2mDistanceSensor.class, "revdist");
    this.isInit = true;
  }

  @Override
  public void teleopInit(HardwareMap hwMap) {
    this.distSensor = hwMap.get(Rev2mDistanceSensor.class, "revdist");
    this.isInit = true;
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveRev2m(this);
  }

  @Override
  public boolean isInitialized() {
    return this.isInit;
  }

  @Override
  public Double getOutput() {
    return this.distSensor.getDistance(this.unit);
  }

}

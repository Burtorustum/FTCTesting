package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class MRLimitSwitch extends ASensor<Double> {

  AnalogInput limitSwitch;

  public MRLimitSwitch(HardwareMap hwMap, Mode mode, String configName) {
    super(hwMap, mode, configName);
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveMRLimitSwitch(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  protected void autoInit(HardwareMap hwMap, String configName) {
    this.limitSwitch = hwMap.get(AnalogInput.class, configName);
  }

  @Override
  protected void teleopInit(HardwareMap hwMap, String configName) {
    this.limitSwitch = hwMap.get(AnalogInput.class, configName);
  }

  @Override
  public Double getOutput() {
    return this.limitSwitch.getVoltage();
  }
}

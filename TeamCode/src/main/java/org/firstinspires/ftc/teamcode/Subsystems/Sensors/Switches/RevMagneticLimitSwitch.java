package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class RevMagneticLimitSwitch extends ASensor<Boolean> {

  private DigitalChannel limitSwitch;

  public RevMagneticLimitSwitch(HardwareMap hwMap, Mode mode, String configName) {
    super(hwMap, mode, configName);
  }

  @Override
  public void autoInit(HardwareMap hwMap, String configName) {
    this.limitSwitch = hwMap.get(DigitalChannel.class, configName);
  }

  @Override
  public void teleopInit(HardwareMap hwMap, String configName) {
    this.limitSwitch = hwMap.get(DigitalChannel.class, configName);
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveRevMagLimSwitch(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  public Boolean getOutput() {
    return !this.limitSwitch.getState();
  }

}

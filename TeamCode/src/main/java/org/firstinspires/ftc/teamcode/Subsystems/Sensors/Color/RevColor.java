package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Color;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class RevColor extends ASensor<HashMap<ARGB, Integer>> {

  private RevColorSensorV3 colorSensor;

  public RevColor(HardwareMap hwMap, StartParameters.Mode mode, String configName) {
    super(hwMap, mode, configName);
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveRevColor(this);
  }

  @Override
  public boolean isInitialized() {
    return true;
  }

  @Override
  protected void autoInit(HardwareMap hwMap, String configName) {
    this.colorSensor = hwMap.get(RevColorSensorV3.class, configName);
  }

  @Override
  protected void teleopInit(HardwareMap hwMap, String configName) {
    this.colorSensor = hwMap.get(RevColorSensorV3.class, configName);
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

  @Override
  public Collection<String> getInformation() {
    HashMap<ARGB, Integer> colorMap = this.getOutput();

    Collection<String> c  = new ArrayList<>();
    c.add("Sensor: " + this.colorSensor.getDeviceName() + " --------");
    c.add("Alpha: " + colorMap.get(ARGB.ALPHA));
    c.add("Red: " + colorMap.get(ARGB.RED));
    c.add("Green: " + colorMap.get(ARGB.GREEN));
    c.add("Blue: " + colorMap.get(ARGB.BLUE));

    return c;
  }
}

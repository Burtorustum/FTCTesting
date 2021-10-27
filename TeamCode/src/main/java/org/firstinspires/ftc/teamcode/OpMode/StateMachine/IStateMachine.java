package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.List;

public interface IStateMachine {

  /**
   * Execute each state in order given, either once the previous has finished (AUTON) or all every iteration (TELEOP)
   * @return Any telemetry from the executed states
   */
  List<String> iterate();
}

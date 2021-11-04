package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.List;

public interface IStateMachine {

  /**
   * Execute each state by priority, either once the previous has finished (AUTON) or all every
   * iteration (TELEOP). If multiple states have same priority, execute them sequentially in
   * undefined order, but before lower priority states.
   * @return Any telemetry from the executed states
   */
  List<String> iterate();

}

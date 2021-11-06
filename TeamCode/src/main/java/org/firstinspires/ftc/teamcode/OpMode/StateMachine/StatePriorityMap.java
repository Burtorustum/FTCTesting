package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;

public class StatePriorityMap {

  private final HashMap<Integer, Collection<ARobotState>> map;

  public StatePriorityMap() {
    this.map = new HashMap<>();
  }

  public StatePriorityMap(Collection<Pair<Integer, ARobotState>> statePairs) {
    this.map = new HashMap<>();
    for (Pair<Integer, ARobotState> pair : statePairs) {
      this.put(pair.fst, pair.snd);
    }
  }

  public void put(Integer priority, ARobotState state) {
    Collection<ARobotState> associatedStates = this.map.get(priority);
    if (associatedStates == null) {
      associatedStates = new ArrayList<>();
      associatedStates.add(state);
      this.map.put(priority, associatedStates);
    } else {
      associatedStates.add(state);
    }
  }

  public Collection<ARobotState> get(Integer priority) {
    return this.map.get(priority);
  }

  public int size() {
    return this.map.size();
  }

  public boolean isEmpty() {
    return this.map.isEmpty();
  }

  public boolean containsPriority(Integer priority) {
    if (priority == null) {
      return false;
    }
    return this.map.containsKey(priority);
  }

  public boolean containsState(ARobotState value) {
    if (value == null) {
      return false;
    }

    for (Collection<ARobotState> states : this.map.values()) {
      if (states.contains(value)) {
        return true;
      }
    }
    return false;
  }

  public Collection<Integer> priorities() {
    return this.map.keySet();
  }

  public Collection<ARobotState> states() {
    return this.flatten(this.map.values());
  }

  public void clear() {
    this.map.clear();
  }

  private List<ARobotState> flatten(Collection<Collection<ARobotState>> toFlatten) {
    List<ARobotState> flattened = new ArrayList<>();

    for (Collection<ARobotState> fragment : toFlatten) {
      flattened.addAll(fragment);
    }

    return flattened;
  }

}

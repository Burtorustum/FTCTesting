package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import com.sun.tools.javac.util.Pair;

import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class PriorityStateMachine implements IStateMachine {

    private final IRobot robot;
    private final StatePriorityMap priorityMap;

    private boolean firstRun;
    // TODO: Convert from int to an enum that holds a list of other states to move to next
    //  --> true state machine
    private int currentPriority;

    /**
     *
     * @param robot  A constructed IRobot
     * @param statePairs A Collection of pairs that comprise of first an integer representing the
     *                   priority of the paired state (lower is better, typically 0 is lower bound)
     *                   and second a state for the robot to perform.
     *                   Pair(Integer >= 0, complete RobotState object).
     */
    public PriorityStateMachine(IRobot robot, Collection<Pair<Integer,ARobotState>> statePairs) {
        this.robot = robot;
        this.priorityMap = new StatePriorityMap(statePairs);

        this.firstRun = true;
        this.currentPriority = 0;
    }

    @Override
    public List<String> iterate() {
        if (this.firstRun) {
            if (this.doButtonsOverlap()) {
                throw new IllegalArgumentException("States have overlapping button usage.");
            }
            this.firstRun = false;
        }

        List<String> telemetry = new ArrayList<>();
        int finishedCounter = 0;
        Collection<ARobotState> currentStates = this.priorityMap.get(this.currentPriority);

        for (ARobotState state : currentStates) {
            this.robot.dispatchState(state);
            telemetry.addAll(state.getTelemetry());
            if (state.finishedExecution()) {
                finishedCounter++;
            }
        }

        if (currentStates.size() == finishedCounter) {
            this.currentPriority++;
        }

        telemetry.add("Current Priority: " + this.currentPriority);
        return telemetry;
    }

    private boolean doButtonsOverlap() {
        HashSet<GamepadButtons> set = new HashSet<>();
        for (ARobotState state : this.priorityMap.states()) {
            for (GamepadButtons button : state.getButtons()) {
                if (set.contains(button)) {
                    return true;
                } else {
                    set.add(button);
                }
            }
        }
        return false;
    }
}

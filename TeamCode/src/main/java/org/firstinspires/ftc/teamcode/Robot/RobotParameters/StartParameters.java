package org.firstinspires.ftc.teamcode.Robot.RobotParameters;

public class StartParameters {
    private final Mode mode;
    private final Start start;
    private final Team team;

    public StartParameters(Mode mode, Start start, Team team) {
        this.mode = mode;
        this.start = start;
        this.team = team;
    }

    public Mode getMode() {
        return mode;
    }

    public Start getStart() {
        return start;
    }

    public Team getTeam() {
        return team;
    }
}

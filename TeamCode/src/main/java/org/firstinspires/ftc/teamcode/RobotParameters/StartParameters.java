package org.firstinspires.ftc.teamcode.RobotParameters;

public class StartParameters {
    private Mode mode;
    private Start start;
    private Team team;

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

package org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopStates;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.DTMotorPos;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class FieldCentricDrive extends ATeleopState {
    private double curHeading;

    public FieldCentricDrive(Gamepad gp1, Gamepad gp2) {
        super(gp1, gp2);

        this.curHeading = 0;
    }

    @Override
    public List<String> getTelemetry() {
        return new ArrayList<>();
    }

    @Override
    public List<GamepadButtons> getButtons() {
        List<GamepadButtons> buttons = new ArrayList<>();
        buttons.add(GamepadButtons.GAMEPAD_1_LSY);
        buttons.add(GamepadButtons.GAMEPAD_1_LSX);
        buttons.add(GamepadButtons.GAMEPAD_1_RSX);

        return buttons;
    }

    @Override
    public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
        driveTrain.setMotorPower(new DTMotorPos[]{DTMotorPos.FRONT_LEFT, DTMotorPos.FRONT_RIGHT,
                        DTMotorPos.BACK_LEFT, DTMotorPos.BACK_RIGHT},
                this.fieldCentricDrive(gp1.left_stick_y, gp1.left_stick_x,
                        gp1.right_stick_x, this.curHeading));

    }

    @Override
    public void receiveGyro(IMUGyro gyro) {
        this.curHeading = gyro.getOutput();
    }

    private double[] fieldCentricDrive(double lStickY, double lStickX, double rStickX, double curHeading) {
        // Get the controller values
        double forward = (-1) * lStickY;
        double strafe = lStickX;
        double clockwise = rStickX;

        // Apply the turn modifier
        clockwise *= 1; //TODO: Test and see if should be lowered

        // Convert to Radians for Math.sin/cos
        double orient = Math.toRadians(curHeading);
        double sin = Math.sin(orient);
        double cos = Math.cos(orient);

        // Apply the rotation matrix
        double temp = forward * cos - strafe * sin;
        strafe = forward * sin + strafe * cos;
        forward = temp;

        // Set power values
        double flPow = forward + clockwise + strafe;
        double frPow = forward - clockwise - strafe;
        double rlPow = forward + clockwise - strafe;
        double rrPow = forward - clockwise + strafe;

        double max = Math.max(1, Math.max(flPow, Math.max(frPow, Math.max(rlPow, rrPow))));

        // Clip power values to within acceptable ranges for the motors
        flPow /= max;
        frPow /= max;
        rlPow /= max;
        rrPow /= max;

        // Send power values to motors
        return new double[]{flPow, frPow, rlPow, rrPow};
    }
}

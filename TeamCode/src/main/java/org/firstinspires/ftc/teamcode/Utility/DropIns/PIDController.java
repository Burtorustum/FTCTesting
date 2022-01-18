package org.firstinspires.ftc.teamcode.Utility.DropIns;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Sourced from: https://en.wikipedia.org/wiki/PID_controller
 * Parameter | Rise time      | Overshoot | Settling time | Steady-state error | Stability
 * ----------|----------------|-----------|---------------|--------------------|-------------
 * Kp        | Decrease       | Increase  | Small change  | Decrease           | Degrade
 * ----------|----------------|-----------|---------------|--------------------|-------------
 * Ki        | Decrease       | Increase  | Increase      | Eliminate          | Degrade
 * ----------|----------------|-----------|---------------|--------------------|-------------
 * Kd        | Little Change  | Decrease  | Decrease      | ~No Change         | Improve if kd small
 */

public class PIDController {
    private final ElapsedTime timer;

    private double kp;
    private double ki;
    private double kd;

    private double target;
    private double lastError;
    private double errorSum;
    private double maxErrorSum;

    private final double fA;
    private double prevFilterEstimate;

    private boolean firstUpdate;

    public PIDController(double kp, double ki, double kd, double target, double fA) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;

        this.newTarget(target);
        this.maxErrorSum = 1000000;
        this.fA = fA;

        this.firstUpdate = true;
        this.timer = new ElapsedTime();
    }

    public PIDController(double kp, double ki, double kd, double target) {
        this(kp, ki, kd, target, -1);
    }

    // If angle, give in degrees between 0-359
    public void newTarget(double target) {
        this.target = target;
        this.lastError = 0;
        this.errorSum = 0;
        this.prevFilterEstimate = 0;
        this.firstUpdate = true;
    }

    public void setGains(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    // TODO: advice was to try .25 = max*ki -> max = .25/ki
    public void setMaxErrorSum(double max) {
        this.maxErrorSum = max;
    }

    // Note: REV IMU gives normalized angle, so we must un-normalize, sub, then re-normalize
    public double updateAngle(double curAngle) {
       curAngle = (curAngle % 360 + 360) % 360;
        return this.update(AngleUnit.normalizeDegrees(this.target - curAngle));
    }

    public double updateEncoder(double curPos) {
        return this.update(this.target - curPos);
    }

    public boolean withinTolerance(double tolerance) {
        return Math.abs(this.lastError) < tolerance;
    }

    private double update(double error) {
        if (this.firstUpdate) {
            this.timer.reset();
            this.firstUpdate = false;
        }

        // Update Integral term
        this.errorSum += 0.5 * error * timer.seconds();
        this.errorSum = Math.copySign(1, this.errorSum) * Math.min(Math.abs(this.errorSum), this.maxErrorSum);

        // Update derivative term. Use filter if designated
        double derivative;
        if (this.fA != -1) {
            this.prevFilterEstimate = (this.fA * this.prevFilterEstimate) + ((1- this.fA) * (error - this.lastError));
            derivative = this.prevFilterEstimate / timer.seconds();
        } else {
            derivative = (error - this.lastError) / timer.seconds();
        }

        // kp*e(t) + ki*errorSum + kd*(de(t)/dt)
        double output =
                (this.kp * error) +
                (this.ki * this.errorSum) +
                (this.kd * derivative);

        // Reset variables as needed
        this.lastError = error;
        timer.reset();

        // TODO: make output range as input to object
        // Assuming we are looking for motor power:
        output = Math.max(-1, Math.min(output, 1));
        return output;
    }

}

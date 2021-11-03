package org.firstinspires.ftc.teamcode.Utility.MiniPID;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MiniPIDEx extends MiniPID {

    public MiniPIDEx(double p, double i, double d) {
        super(p, i, d);
    }

    /**
     *
     * @param actual A gyro value in degrees 0 <= actual < 360
     * @return PID output based on given sensor data and pre-given target and coefficients
     */
    public double getOutputGyro(double actual) {
        double output;
        double Poutput;
        double Ioutput;
        double Doutput;
        double Foutput;
        double error = AngleUnit.normalizeDegrees(this.setpoint - actual);

        // Ramp the setpoint used for calculations if user has opted to do so
        if(setpointRange!=0){
            setpoint=constrain(setpoint,actual-setpointRange,actual+setpointRange);
        }


        // Calculate F output. Notice, this depends only on the setpoint, and not the error.
        Foutput=F*setpoint;

        // Calculate P term
        Poutput=P*error;

        // If this is our first time running this, we don't actually _have_ a previous input or output.
        // For sensor, sanely assume it was exactly where it is now.
        // For last output, we can assume it's the current time-independent outputs.
        if(firstRun){
            lastActual=actual;
            lastOutput=Poutput+Foutput;
            firstRun=false;
        }

        // Calculate D Term
        // Note, this is negative. This actually "slows" the system if it's doing
        // the correct thing, and small values helps prevent output spikes and overshoot
        Doutput= -D*(actual-lastActual);
        lastActual=actual;

        // The Iterm is more complex. There's several things to factor in to make it easier to deal with.
        // 1. maxIoutput restricts the amount of output contributed by the Iterm.
        // 2. prevent windup by not increasing errorSum if we're already running against our max Ioutput
        // 3. prevent windup by not increasing errorSum if output is output=maxOutput
        Ioutput=I*errorSum;
        if(maxIOutput!=0){
            Ioutput=constrain(Ioutput,-maxIOutput,maxIOutput);
        }

        // And, finally, we can just add the terms up
        output=Foutput + Poutput + Ioutput + Doutput;

        // Figure out what we're doing with the error.
        if(minOutput!=maxOutput && !bounded(output, minOutput,maxOutput) ){
            errorSum=error;
            // reset the error sum to a sane level
            // Setting to current error ensures a smooth transition when the P term
            // decreases enough for the I term to start acting upon the controller
            // From that point the I term will build up as would be expected
        }
        else if(outputRampRate!=0 && !bounded(output, lastOutput-outputRampRate,lastOutput+outputRampRate) ){
            errorSum=error;
        }
        else if(maxIOutput!=0){
            errorSum=constrain(errorSum+error,-maxError,maxError);
            // In addition to output limiting directly, we also want to prevent I term
            // buildup, so restrict the error directly
        }
        else{
            errorSum+=error;
        }

        // Restrict output to our specified output and ramp limits
        if(outputRampRate!=0){
            output=constrain(output, lastOutput-outputRampRate,lastOutput+outputRampRate);
        }
        if(minOutput!=maxOutput){
            output=constrain(output, minOutput,maxOutput);
        }
        if(outputFilter!=0){
            output=lastOutput*outputFilter+output*(1-outputFilter);
        }

        // Get a test printline with lots of details about the internal
        // calculations. This can be useful for debugging.

        lastOutput=output;
        return output;
    }

}

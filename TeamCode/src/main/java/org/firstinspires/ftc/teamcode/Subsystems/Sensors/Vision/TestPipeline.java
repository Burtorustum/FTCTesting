package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Vision;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Vision.Pipelines.DetectBanana;

@TeleOp(name = "detect test", group = "")
public class TestPipeline extends OpMode {
    VisionCVWrapper vision;

    @Override
    public void init() {
        // this pipeline does no resizing, instead we use webcam at aspect ratio 320:240
        // Config name for webcam is "Webcam 1"
        this.vision = new VisionCVWrapper(StartParameters.Mode.TELEOP, new DetectBanana(), hardwareMap, "Webcam 1",
                320, 240);
    }


    @Override
    public void loop() {
        try {
            telemetry.addData("fps", vision.getCamFPS());
            telemetry.addData("CV Output: ", vision.getOutput());
            telemetry.update();
        } catch (Exception e) {
            // if error tell us what
            telemetry.addLine(e.getMessage());
        }
    }
}

package org.firstinspires.ftc.teamcode.VisionTesting;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "detect banananananana test", group = "")
public class TestPipeline extends OpMode {
    VisionCVWrapper vision;

    @Override
    public void init() {
        // this pipeline does no resizing, instead we use webcam at aspect ratio 320:240
        // Config name for webcam is "Webcam 1"
        this.vision = new VisionCVWrapper(new GripPipelineWrapper(new DetectBanana()),
                "Webcam 1", hardwareMap, 320, 240);
    }


    @Override
    public void loop() {
        try {

            telemetry.addData("fps", vision.getCamFPS());
            telemetry.update();
        } catch (Exception e) {
            // if error tell us what
            telemetry.addLine(e.getMessage());
        }
    }
}

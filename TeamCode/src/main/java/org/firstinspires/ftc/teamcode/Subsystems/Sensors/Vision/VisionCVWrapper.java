package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Vision.Pipelines.AGripPipeline;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VisionCVWrapper extends ASensor<VisionCVWrapper.ElementPosition> {

    private OpenCvWebcam webcam;
    private final AGripPipeline pipeline;

    private final int cameraPixelWidth, cameraPixelHeight;

    public VisionCVWrapper(StartParameters.Mode mode, AGripPipeline pipeline, HardwareMap hardwareMap, String configName,  int cameraPixelWidth, int cameraPixelHeight) {
        super(hardwareMap, mode, configName);
        this.pipeline = pipeline;
        this.cameraPixelWidth = cameraPixelWidth;
        this.cameraPixelHeight = cameraPixelHeight;
    }


    public void closeLiveview() {
        this.webcam.pauseViewport();
    }

    public float getCamFPS() {
        return this.webcam.getFps();
    }

    @Override
    protected void autoInit(HardwareMap hwMap, String configName) {
        // Get live-view port
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());

        // WEBCAM NOT CAMERA
        this.webcam = OpenCvCameraFactory.getInstance().createWebcam(hwMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        this.webcam.setMillisecondsPermissionTimeout(3000);
        this.webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                // Start camera streaming
                webcam.startStreaming(cameraPixelWidth, cameraPixelHeight, OpenCvCameraRotation.UPRIGHT);
                webcam.setPipeline(pipeline);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened (pray never)
                 */
                throw new RuntimeException("CAMERA UNABLE TO BE OPENED NOT SURE WHY TRY OTHER CAM?");
            }
        });
    }

    @Override
    protected void teleopInit(HardwareMap hwMap, String configName) {
        // FOR NOW WE WONT USE IN TELEOP: MAY NEED TO FIGURE SOMETHING OUT HERE IF THERE IS A USE CASE / DEALING WITH LATENCY
    }

    @Override
    public ElementPosition getOutput() {
        List<MatOfPoint> contours = pipeline.getContours();

        if (contours.size() == 0) {
            return ElementPosition.RIGHT;
        }

        contours.size();
        double areaSum = 0;
        double xAvg = 0;

        for (int i = 0; i < contours.size(); i++) {
            // Bound contour with a rectangle
            Rect boundingRect = Imgproc.boundingRect(contours.get(i));
            double areaLol = boundingRect.area();
            areaSum = areaSum + areaLol;
            xAvg = xAvg + areaLol * (boundingRect.x + boundingRect.width / 2d);
        }

        xAvg = xAvg / areaSum;

        if (xAvg > this.cameraPixelWidth / 2.0) {
            return ElementPosition.CENTER;
        }

        return ElementPosition.LEFT;
    }

    @Override
    public void dispatchState(ARobotState robotState) {
        robotState.receiveVisionCVWrapper(this);
    }

    @Override
    public boolean isInitialized() {
        return this.pipeline.getContours() != null;
    }


    @Override
    public Collection<String> getInformation() {
        ArrayList<String> out = new ArrayList<>();
        out.add("CV Output: " + this.getOutput());

        return out;
    }


    public enum ElementPosition {
        LEFT, CENTER, RIGHT
    }
}
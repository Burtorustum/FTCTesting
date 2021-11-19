package org.firstinspires.ftc.teamcode.VisionTesting;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class VisionCVWrapper {

    private OpenCvWebcam webcam;
    private GripPipelineWrapper pipeline;

    public VisionCVWrapper(GripPipelineWrapper pipeline, String configName, HardwareMap hardwareMap, int cameraPixelWidth, int cameraPixelHeight) {
        this.pipeline = pipeline;


        // Get live-view port
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // WEBCAM NOT CAMERA
        this.webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

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


    public void closeLiveview() {
        this.webcam.pauseViewport();
    }

    public float getCamFPS() {
        return this.webcam.getFps();
    }


}

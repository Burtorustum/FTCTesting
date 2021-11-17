package org.firstinspires.ftc.teamcode.VisionTesting;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public class GripPipelineWrapper {
    private final OpenCvCamera cvCamera;
    private final ACustomPipeline pipeline;

    public GripPipelineWrapper(ACustomPipeline pipeline, String configName, HardwareMap hardwareMap, int cameraPixelWidth, int cameraPixelHeight) {
        // Get live-view port
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // Get webcam from hwmap
        WebcamName webcamName = hardwareMap.get(WebcamName.class, configName);

        // Create camera with live preview
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                // Start camera streaming
                camera.startStreaming(cameraPixelWidth, cameraPixelHeight, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened (pray never)
                 */
            }
        });

        camera.setPipeline(pipeline);
        camera.showFpsMeterOnViewport(true);

        this.cvCamera = camera;
        this.pipeline = pipeline;
    }

    public ElementPosition lastDetectedPosition() {
        return this.pipeline.lastDetectedPosition();
    }

    public void closeLiveview() {
        this.cvCamera.pauseViewport();
    }

    public void stop() {
        // TODO: unclear if this works
        this.cvCamera.closeCameraDeviceAsync(() -> {

        });
    }
}

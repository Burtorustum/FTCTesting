package org.firstinspires.ftc.teamcode.VisionTesting;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class GripPipelineWrapper extends OpenCvPipeline {
    private final IGRIPPipeline pipeline;

    public GripPipelineWrapper(IGRIPPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public Mat processFrame(Mat input) {
        this.pipeline.process(input);
        return this.pipeline.getDisplayImage(input);
    }

}

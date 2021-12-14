package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Vision.Pipelines;

import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.List;
import java.util.Map;

public abstract class ADetectorPipeline<T> extends OpenCvPipeline {

    // Return the final determined contours
    public abstract Map<T, List<MatOfPoint>> getContours();
}



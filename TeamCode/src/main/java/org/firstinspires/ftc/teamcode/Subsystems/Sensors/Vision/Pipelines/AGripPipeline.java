package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Vision.Pipelines;

import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.List;

public abstract class AGripPipeline extends OpenCvPipeline {

    // Return the final determined contours
    public abstract List<MatOfPoint> getContours();
}



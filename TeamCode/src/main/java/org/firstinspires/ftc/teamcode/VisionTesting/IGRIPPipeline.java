package org.firstinspires.ftc.teamcode.VisionTesting;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.List;

public interface IGRIPPipeline {
    public Mat getDisplayImage(Mat source);
    public List<MatOfPoint> getContours();

    public void process(Mat source);
}

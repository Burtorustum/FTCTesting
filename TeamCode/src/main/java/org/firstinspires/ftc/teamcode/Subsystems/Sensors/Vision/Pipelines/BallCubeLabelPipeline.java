package org.firstinspires.ftc.teamcode.Subsystems.Sensors.Vision.Pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class BallCubeLabelPipeline extends OpenCvPipeline {

    private final Mat blurOut = new Mat();

    private final Mat threshCubeOut = new Mat();
    private final Mat erodeCubeOut = new Mat();
    private final Mat dilateCubeOut = new Mat();

    private final Mat threshBallOut = new Mat();
    private final Mat erodeBallOut = new Mat();
    private final Mat dilateBallOut = new Mat();

    private final List<MatOfPoint> contourCubeOut = new ArrayList<>();
    private final List<MatOfPoint> contourBallOut = new ArrayList<>();
    
    @Override
    public Mat processFrame(Mat input) {
        // BLUR
        final int blurWidth = 30;
        final int blurHeight = 30;

        Imgproc.blur(input, blurOut, new Size(blurWidth, blurHeight));

        // THRESHOLD ------------------------
        // Threshold Cubes

        Imgproc.cvtColor(blurOut, threshCubeOut, Imgproc.COLOR_BGR2HSV);
        Core.inRange(threshCubeOut,
                new Scalar(0, 128, 128),
                new Scalar(255, 255, 255),
                threshCubeOut);

        // Threshold Balls
        Imgproc.cvtColor(blurOut, threshBallOut, Imgproc.COLOR_BGR2HLS);
        Core.inRange(threshBallOut,
                new Scalar(0, 170, 0),
                new Scalar(255, 255, 255),
                threshBallOut);

        // ERODE/DILATE -----------------
        Point center = new Point(-1, -1);

        Imgproc.erode(threshCubeOut, erodeCubeOut, new Mat(), center, 3, Core.BORDER_CONSTANT);
        Imgproc.erode(threshBallOut, erodeBallOut, new Mat(), center, 3, Core.BORDER_CONSTANT);

        Imgproc.dilate(erodeCubeOut, dilateCubeOut, new Mat(), center, 10, Core.BORDER_CONSTANT);
        Imgproc.dilate(erodeBallOut, dilateBallOut, new Mat(), center, 10, Core.BORDER_CONSTANT);
        

        // CONTOUR --------------------------
        // Contour Cubes
        Imgproc.findContours(dilateCubeOut, contourCubeOut, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        // Contour Balls
        Imgproc.findContours(dilateBallOut, contourBallOut, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        // LABEL ----------------------------
        List<StringLabel> allLabels = new ArrayList<>();
        int numBalls = contourBallOut.size();
        int numCubes = contourCubeOut.size();

        int maxContours = Math.max(numCubes, numBalls);

        for (int i = 0; i < maxContours; i++) {
            if (i < numCubes) {
                Moments moments = Imgproc.moments(contourCubeOut.get(i));
                Point centroid = new Point();

                centroid.x = moments.get_m10() / moments.get_m00();
                centroid.y = moments.get_m01() / moments.get_m00();

                allLabels.add(new StringLabel("CUBE", centroid));
            }
            if (i < numBalls) {
                Moments moments = Imgproc.moments(contourBallOut.get(i));
                Point centroid = new Point();

                centroid.x = moments.get_m10() / moments.get_m00();
                centroid.y = moments.get_m01() / moments.get_m00();

                allLabels.add(new StringLabel("BALL", centroid));
            }
        }

        // DRAW -----------------------------
        // Draw contours
        Imgproc.drawContours(input, contourCubeOut, -1, new Scalar(255,0,0), 3);
        Imgproc.drawContours(input, contourBallOut, -1, new Scalar(0,0,255), 3);

        // Draw labels
        for (StringLabel label : allLabels) {
            Imgproc.putText(input, label.label, label.position,
                    Imgproc.FONT_HERSHEY_PLAIN, 3.0, new Scalar(0, 255, 255),
                    3, 0, true);
        }

        // Fancy text output
        return input;
    }

    public List<MatOfPoint> getCubeContours() {
        return contourCubeOut;
    }

    public List<MatOfPoint> getBallContours() {
        return contourBallOut;
    }

    public int numCubes() {
        return contourCubeOut.size();
    }

    public int numBalls() {
        return contourBallOut.size();
    }

    private static class StringLabel {
        public final String label;
        public final Point position;

        public StringLabel(String label, Point position) {
            this.label = label;
            this.position = position;
        }
    }
}

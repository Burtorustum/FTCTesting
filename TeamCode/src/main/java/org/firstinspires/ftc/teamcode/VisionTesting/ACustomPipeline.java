package org.firstinspires.ftc.teamcode.VisionTesting;

import org.openftc.easyopencv.OpenCvPipeline;

public abstract class ACustomPipeline extends OpenCvPipeline {
    public abstract ElementPosition lastDetectedPosition();
}

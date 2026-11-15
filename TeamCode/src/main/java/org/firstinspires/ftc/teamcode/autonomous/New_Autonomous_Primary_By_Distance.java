package org.firstinspires.ftc.teamcode.autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Robot: New Primary Autonomous By Distance", group="Robot")
public class New_Autonomous_Primary_By_Distance extends AutonomousFunctions {
    @Override
    public void runOpMode() {
        initialize_hardware();
        waitForStart();
        shootFor(10);
        driveByDistance(24, 0);
        driveByDistance(0, -36);
        driveByDistance(72, 0);
        driveByDistance(0, -12);
    }
}

package org.firstinspires.ftc.teamcode.autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Robot: New Secondary Autonomous By Distance", group="Robot")
public class New_Autonomous_Secondary_By_Distance extends AutonomousFunctions {
    @Override
    public void runOpMode() {
        initialize_hardware();
        waitForStart();
        sleep(10000);
        shootFor(7);
        driveByDistance(18, 0);
        driveByDistance(0, 48);
    }
}

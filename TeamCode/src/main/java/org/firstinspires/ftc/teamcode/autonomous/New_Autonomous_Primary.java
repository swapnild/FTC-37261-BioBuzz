package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous(name="Robot: New Primary Autonomous", group="Robot")
public class New_Autonomous_Primary extends AutonomousFunctions {
    @Override
    public void runOpMode() {
        initialize_hardware();
        waitForStart();
        shootFor(10);
        mecanumDriveFor(FORWARD_POWER, 0, 0, 1);
        mecanumDriveFor(0, -STRAFE_POWER, 0, 1.5);
        mecanumDriveFor(FORWARD_POWER, 0, 0, 3);
        mecanumDriveFor(0, -STRAFE_POWER, 0, 0.5);
    }
}

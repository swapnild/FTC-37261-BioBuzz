package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous(name="Robot: New Secondary Autonomous", group="Robot")
public class New_Autonomous_Secondary extends AutonomousFunctions {
    @Override
    public void runOpMode() {
        initialize_hardware();
        waitForStart();
        sleep(10000);
        shootFor(7);
        mecanumDriveFor(FORWARD_SPEED, 0, 0, 0.75);
        mecanumDriveFor(0, -STRAFE_SPEED, 0, 2);
    }
}


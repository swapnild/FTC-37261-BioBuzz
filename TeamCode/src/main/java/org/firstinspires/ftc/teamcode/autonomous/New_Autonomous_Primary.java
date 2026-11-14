package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous(name="Robot: Primary Autonomous", group="Robot")
public class New_Autonomous_Primary extends autonomous_functions {
    @Override
    public void runOpMode() {
        initialize_hardware();
        waitForStart();
        shootFor(10);
        mecanumDrive(FORWARD_SPEED, 0, 0, 1);
        mecanumDrive(0, -STRAFE_SPEED, 0, 1.5);
        mecanumDrive(FORWARD_SPEED, 0, 0, 3);
        mecanumDrive(0, -STRAFE_SPEED, 0, 0.5);
    }
}

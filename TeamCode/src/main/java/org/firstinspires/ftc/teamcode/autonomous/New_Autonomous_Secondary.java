package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous(name="Robot: Secondary Autonomous", group="Robot")
public class New_Autonomous_Secondary extends autonomous_functions {
    @Override
    public void runOpMode() {
        initialize_hardware();
        waitForStart();
        sleep(10000);
        shootFor(7);
        mecanumDrive(FORWARD_SPEED, 0, 0, 0.75);
        mecanumDrive(0, -STRAFE_SPEED, 0, 2);
    }
}


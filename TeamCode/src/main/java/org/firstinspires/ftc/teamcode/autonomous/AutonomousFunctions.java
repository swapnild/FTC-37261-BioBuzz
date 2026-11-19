/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.autonomous;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;


public abstract class AutonomousFunctions extends LinearOpMode {

    // Declare OpMode members.
    protected DcMotor leftFrontDrive = null;
    protected DcMotor leftBackDrive = null;
    protected DcMotor rightFrontDrive = null;
    protected DcMotor rightBackDrive = null;
    protected DcMotorEx launcher = null;
    protected DcMotor intake = null;
    protected CRServo leftIntakeServo = null;
    protected CRServo rightIntakeServo = null;
    protected CRServo windmillServo = null;

    public final int LAUNCHER_TARGET_VELOCITY = 1250; //2678 RPM
    //Setting LAUNCHER_MIN_VELOCITY VERY LOW. CHANGE BACK TO 1200 when done.
    public final int LAUNCHER_MIN_VELOCITY = 1200; //2571 RPM


    //Speed that the robot travels in specific direction measured in inches per second
    public final float FORWARD_SPEED = 30;
    public final float BACKWARD_SPEED = 30;
    public final float STRAFE_SPEED = 30;

    //Default power that we send to the drive motors in autonomous mode
    static final double FORWARD_POWER = 0.4;
    static final double STRAFE_POWER = 0.4;
    static final double TURN_POWER = 0.4;

    /*
     * These four variables store the power we need to apply to the motors. In other cases, we may
     * choose to declare these variables inside the mecanumDrive() function, instead we declare them
     * here so that we can access them in our main loop for telemetry.
     */
    double leftFrontPower;
    double rightFrontPower;
    double leftBackPower;
    double rightBackPower;
    // Create a variable to set to the intake.
    double intakePower;

    protected ElapsedTime runtime = new ElapsedTime();


    /**
     * Initializes all hardware components from the hardware map, sets motor directions,
     * configures brake behaviors, encoder run modes, and PIDF coefficients.
     */
    protected void initialize_hardware() {

        /*
         * Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step.
         */
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        windmillServo = hardwareMap.get(CRServo.class, "windmillServo");
        leftIntakeServo = hardwareMap.get(CRServo.class, "left_intake_servo");
        rightIntakeServo = hardwareMap.get(CRServo.class, "right_intake_servo");

        /*
         * To drive forward, most robots need the motor on one side to be reversed,
         * because the axles point in opposite directions. Pushing the left stick forward
         * MUST make robot go forward. So adjust these two lines based on your first test drive.
         * Note: The settings here assume direct drive on left and right wheels. Gear
         * Reduction or 90 Deg drives may require direction flips
         */
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        /*
         * Setting zeroPowerBehavior to BRAKE enables a "brake mode". This causes the motor to
         * slow down much faster when it is coasting. This creates a much more controllable
         * drivetrain. As the robot stops much quicker.
         */
        leftFrontDrive.setZeroPowerBehavior(BRAKE);
        rightFrontDrive.setZeroPowerBehavior(BRAKE);
        leftBackDrive.setZeroPowerBehavior(BRAKE);
        rightBackDrive.setZeroPowerBehavior(BRAKE);

        /*
         * Here we set our launcher to the RUN_USING_ENCODER runmode.
         * If you notice that you have no control over the velocity of the motor, it just jumps
         * right to a number much higher than your set point, make sure that your encoders are plugged
         * into the port right beside the motor itself. And that the motors polarity is consistent
         * through any wiring.
         */
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));

        /*
         * set Feeders to an initial value to initialize the servo controller
         */
        leftIntakeServo.setPower(0);
        rightIntakeServo.setPower(0);
        windmillServo.setPower(0);

        /*
         * Much like our drivetrain motors, we set the right intake servo to reverse so that both
         * servos work to pull elements into the intake.
         */
        rightIntakeServo.setDirection(DcMotorSimple.Direction.REVERSE);
        windmillServo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Drives the robot with specified movement vectors for a given duration in seconds.
     *
     * @param forward  Power for forward/backward movement (-1.0 to 1.0).
     * @param strafe   Power for right/left strafing (-1.0 to 1.0).
     * @param rotate   Power for clockwise/counter-clockwise rotation (-1.0 to 1.0).
     * @param seconds  Duration in seconds to execute the movement.
     */
    protected void mecanumDriveFor(double forward, double strafe, double rotate, double seconds) {
        telemetry.log().add(String.format("Driving (F:%.1f, S:%.1f, R:%.1f) for %.1fs", forward, strafe, rotate, seconds));
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < seconds) {
            mecanumDrive(forward, strafe, rotate);
        }
        mecanumDrive(0, 0, 0);
        telemetry.log().add("Drive complete.");
    }

    /**
     * Spins up the launcher motor to target velocity and runs the feeder servo
     * for a set duration to launch game elements.
     *
     * @param seconds Duration in seconds to run the launcher feeder.
     */
    protected void shootFor(double seconds) {
        telemetry.log().add(String.format("Shooting for %.1fs...", seconds));
        //this is setting the motor to the speed
        while (opModeIsActive() && launcher.getVelocity() < LAUNCHER_MIN_VELOCITY) {
            launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);
        }
        windmillServo.setPower(1);
        sleep((long) (1000 * seconds));

        launcher.setVelocity(0);
        windmillServo.setPower(0);
        telemetry.log().add("Shooting complete.");
    }

    /**
     * Runs the intake motor for a set duration to pull game elements into the robot.
     *
     * @param seconds Duration in seconds to run the intake.
     */
    protected void intakeFor(double seconds) {
        telemetry.log().add(String.format("Intaking for %.1fs...", seconds));
        intake.setPower(1);
        sleep((long) (seconds * 1000));
        intake.setPower(0);
        telemetry.log().add("Intake complete.");
    }

    /**
     * Drives the robot by specified distances using time-based navigation.
     * First moves forward or backward, stops, and then strafes right or left.
     * @param forward  Distance in inches to drive forward (positive) or backward (negative).
     * @param sideways Distance in inches to strafe right (positive) or left (negative).
     */
    void driveByDistance(float forward, float sideways) {
        // Drive forward/backward in inches (negative = backward)
        telemetry.log().add(String.format("Starting forward move: %.1f in", forward));
        runtime.reset();
        double forwardTime;
        if (forward > 0) {
            forwardTime = Math.abs(forward) / FORWARD_SPEED;
        }
        else {
            forwardTime = Math.abs(forward) / BACKWARD_SPEED;
        }
        double forwardPower = Math.signum(forward) * FORWARD_POWER;
        while (opModeIsActive() && (runtime.seconds() < forwardTime)) {
            mecanumDrive(forwardPower, 0, 0);
        }
        mecanumDrive(0, 0, 0);
        telemetry.log().add("Forward move complete.");

        // Strafe right/left in inches (negative = left)
        telemetry.log().add(String.format("Starting strafe move: %.1f in", sideways));
        runtime.reset();
        double sidewaysTime = Math.abs(sideways) / STRAFE_SPEED;
        double sidewaysPower = Math.signum(sideways) * STRAFE_POWER;
        while (opModeIsActive() && (runtime.seconds() < sidewaysTime)) {
            mecanumDrive(0, sidewaysPower, 0);
        }
        mecanumDrive(0, 0, 0);
        telemetry.log().add("Strafe move complete.");
    }
    /**
     * Calculates and applies motor powers for a 4-wheel Mecanum drivetrain using vector inputs.
     * Normalizes wheel powers if any value exceeds 1.0.
     *
     * @param forward  Forward/backward power vector (-1.0 to 1.0).
     * @param strafe   Right/left strafe power vector (-1.0 to 1.0).
     * @param rotate   Clockwise/counter-clockwise rotation power vector (-1.0 to 1.0).
     */
    void mecanumDrive(double forward, double strafe, double rotate) {
        leftFrontPower = forward + strafe + rotate;
        rightFrontPower = forward - strafe - rotate;
        leftBackPower = forward - strafe + rotate;
        rightBackPower = forward + strafe - rotate;

        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        /*
         * Send calculated power to wheels
         */
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);
    }
}
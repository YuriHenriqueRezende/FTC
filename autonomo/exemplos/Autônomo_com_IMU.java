package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name="Auto_IMU_Turn", group="Examples")
public class AutoImuTurn extends LinearOpMode {

    private DcMotorEx fl, fr, bl, br;
    private IMU imu;

    @Override
    public void runOpMode() {
        fl = hardwareMap.get(DcMotorEx.class, "frontLeft");
        fr = hardwareMap.get(DcMotorEx.class, "frontRight");
        bl = hardwareMap.get(DcMotorEx.class, "backLeft");
        br = hardwareMap.get(DcMotorEx.class, "backRight");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.resetYaw(); // define yaw=0 no início

        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();
        if (!opModeIsActive()) return;

        // gira +30 graus
        turnToYaw(30, 0.5, 2.0);

        // gira -60 (volta e passa)
        turnToYaw(-30, 0.5, 3.0);
    }

    private void turnToYaw(double targetDeg, double maxPower, double timeoutSec) {
        long start = System.currentTimeMillis();

        while (opModeIsActive() && (System.currentTimeMillis() - start) < timeoutSec * 1000) {
            double yaw = getYawDeg();
            double error = targetDeg - yaw;

            // condição de parada
            if (Math.abs(error) < 1.0) break;

            // P controller simples
            double kP = 0.02;
            double turn = Range.clip(kP * error, -maxPower, maxPower);

            fl.setPower(turn);  bl.setPower(turn);
            fr.setPower(-turn); br.setPower(-turn);

            telemetry.addData("Yaw", "%.1f", yaw);
            telemetry.addData("Target", "%.1f", targetDeg);
            telemetry.addData("Error", "%.1f", error);
            telemetry.update();
        }

        fl.setPower(0); bl.setPower(0);
        fr.setPower(0); br.setPower(0);
    }

    private double getYawDeg() {
        YawPitchRollAngles a = imu.getRobotYawPitchRollAngles();
        return a.getYaw(AngleUnit.DEGREES);
    }
}

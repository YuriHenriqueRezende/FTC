package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name="Auto_Encoder_Simples", group="Examples")
public class AutoEncoderSimples extends LinearOpMode {

    private DcMotorEx fl, fr, bl, br;

    // Ajuste conforme seu robô
    private static final double TICKS_PER_REV = 537.7;   // Ex: GoBILDA 312rpm
    private static final double WHEEL_CIRC_CM = 31.9;    // circunferência da roda (cm)

    @Override
    public void runOpMode() {
        fl = hardwareMap.get(DcMotorEx.class, "frontLeft");
        fr = hardwareMap.get(DcMotorEx.class, "frontRight");
        bl = hardwareMap.get(DcMotorEx.class, "backLeft");
        br = hardwareMap.get(DcMotorEx.class, "backRight");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        setBrake(true);

        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();
        if (!opModeIsActive()) return;

        // MOVE: anda 60 cm para frente
        driveCm(60, 0.6, 3.0);

        // TURN: gira no tempo (exemplo simples) - troque por IMU depois
        turnTime(0.5, 450); // potência, ms

        // PARK: recua 30 cm
        driveCm(-30, 0.5, 2.5);
    }

    private void driveCm(double cm, double power, double timeoutSec) {
        int ticks = (int) ((cm / WHEEL_CIRC_CM) * TICKS_PER_REV);

        setTarget(fl, ticks); setTarget(fr, ticks);
        setTarget(bl, ticks); setTarget(br, ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPowerAll(Math.abs(power));

        long start = System.currentTimeMillis();
        while (opModeIsActive()
                && (System.currentTimeMillis() - start) < timeoutSec * 1000
                && (fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy())) {
            telemetry.addData("Driving", "%.1f cm", cm);
            telemetry.update();
        }

        setPowerAll(0);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void turnTime(double power, long ms) {
        fl.setPower(-power); bl.setPower(-power);
        fr.setPower(power);  br.setPower(power);
        sleep(ms);
        setPowerAll(0);
    }

    private void setTarget(DcMotorEx m, int deltaTicks) {
        m.setTargetPosition(m.getCurrentPosition() + deltaTicks);
    }

    private void setMode(DcMotor.RunMode mode) {
        fl.setMode(mode); fr.setMode(mode); bl.setMode(mode); br.setMode(mode);
    }

    private void setPowerAll(double p) {
        fl.setPower(p); fr.setPower(p); bl.setPower(p); br.setPower(p);
    }

    private void setBrake(boolean brake) {
        DcMotor.ZeroPowerBehavior z = brake ? DcMotor.ZeroPowerBehavior.BRAKE : DcMotor.ZeroPowerBehavior.FLOAT;
        fl.setZeroPowerBehavior(z); fr.setZeroPowerBehavior(z);
        bl.setZeroPowerBehavior(z); br.setZeroPowerBehavior(z);
    }
}

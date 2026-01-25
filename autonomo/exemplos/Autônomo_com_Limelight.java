package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Auto_Limelight_AimAssist", group="Examples")
public class AutoLimelightAimAssist extends LinearOpMode {

    private DcMotorEx fl, fr, bl, br;
    private Limelight3A limelight;

    @Override
    public void runOpMode() {
        fl = hardwareMap.get(DcMotorEx.class, "frontLeft");
        fr = hardwareMap.get(DcMotorEx.class, "frontRight");
        bl = hardwareMap.get(DcMotorEx.class, "backLeft");
        br = hardwareMap.get(DcMotorEx.class, "backRight");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();

        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();
        if (!opModeIsActive()) return;

        // Ex shows: alinhar por até 2 segundos
        aimAssistTx(2.0);

        limelight.stop();
    }

    private void aimAssistTx(double timeoutSec) {
        long start = System.currentTimeMillis();
        double lastTx = 0;

        while (opModeIsActive() && (System.currentTimeMillis() - start) < timeoutSec * 1000) {
            LLResult r = limelight.getLatestResult();

            if (r != null && r.isValid()) {
                double tx = r.getTx();

                // deadband: se já está alinhado, para
                if (Math.abs(tx) < 1.0) {
                    setPowerAll(0);
                    break;
                }

                // Controle PD simples (estável)
                double kP = 0.03;
                double kD = 0.01;
                double steer = (kP * tx) + (kD * (tx - lastTx));
                lastTx = tx;

                steer = Range.clip(steer, -0.35, 0.35);

                // girar no próprio eixo
                fl.setPower(steer);  bl.setPower(steer);
                fr.setPower(-steer); br.setPower(-steer);

                telemetry.addData("tx", "%.2f", tx);
                telemetry.addData("steer", "%.2f", steer);
            } else {
                // Sem alvo -> para (ou faça varredura lenta)
                setPowerAll(0);
                telemetry.addLine("No target");
            }

            telemetry.update();
        }

        setPowerAll(0);
    }

    private void setPowerAll(double p) {
        fl.setPower(p); fr.setPower(p); bl.setPower(p); br.setPower(p);
    }
}

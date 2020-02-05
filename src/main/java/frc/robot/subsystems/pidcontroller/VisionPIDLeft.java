package frc.robot.subsystems.pidcontroller;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class VisionPIDLeft extends PIDSubsystem {
    private final VictorSPX m_leftMotor = new VictorSPX(1);
    private final Encoder m_encoder = new Encoder(0, 1, true, EncodingType.k4X);

    public VisionPIDLeft() {
        super(new PIDController(0.1, 0.001, 2.5), 0);
        m_leftMotor.setInverted(true);
        m_leftMotor.setNeutralMode(NeutralMode.Coast);
        m_encoder.setDistancePerPulse(0.00001);
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        m_leftMotor.set(ControlMode.PercentOutput, output);
    }

    @Override
    protected double getMeasurement() {
        return m_encoder.getRate();
    }
}
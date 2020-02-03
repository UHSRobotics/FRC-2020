package frc.robot.subsystems.pidcontroller;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class VisionPIDRight extends PIDSubsystem {
    private final VictorSPX m_rightMotor = new VictorSPX(1);
    private final Encoder m_encoder = new Encoder(2, 3, false, EncodingType.k4X);

    public VisionPIDRight() {
        super(new PIDController(0.1, 0.001, 2.5), 0);
        m_rightMotor.setInverted(false);
        m_rightMotor.setNeutralMode(NeutralMode.Coast);
        m_encoder.setDistancePerPulse(0.00001);
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        m_rightMotor.set(ControlMode.PercentOutput, output);
    }

    @Override
    protected double getMeasurement() {
        return m_encoder.getRate();
    }

    public boolean atSetpoint(){
        return m_controller.atSetpoint();
    }
}
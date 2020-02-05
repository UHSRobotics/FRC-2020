package frc.robot.subsystems.pidcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class VisionPIDRotation extends PIDSubsystem {
    private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
    private final Encoder m_leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
    private final Encoder m_rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);

    public VisionPIDRotation() {
        super(new PIDController(0.1, 0.001, 2.5), 0);
        m_leftEncoder.setDistancePerPulse(0.00001);
        m_rightEncoder.setDistancePerPulse(0.00001);
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        m_driveSubsystem.arcadeDrive(0, output);
    }

    @Override
    protected double getMeasurement() {
        return this.encoderDistToRot();
    }

    public boolean atSetpoint() {
        return m_controller.atSetpoint();
    }

    public double encoderDistToRot() {
        // TODO: this method should be able to convert the distances in L and R encoders
        // and convert it to rotation
        return -1;
    }
}
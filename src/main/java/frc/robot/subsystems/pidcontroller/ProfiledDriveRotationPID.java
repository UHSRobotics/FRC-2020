package frc.robot.subsystems.pidcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants.VisionControlConstants;
import frc.robot.subsystems.DriveSubsystem;

public class ProfiledDriveRotationPID extends ProfiledPIDSubsystem {
    private final DriveSubsystem m_driveSubsystem;
    private final Encoder m_encoder = new Encoder(0, 1, false, EncodingType.k4X);

    public ProfiledDriveRotationPID() {
        super(new ProfiledPIDController(VisionControlConstants.KpRot, VisionControlConstants.KiRot,
                VisionControlConstants.KdRot, new TrapezoidProfile.Constraints(10, 20)), 0);
        m_driveSubsystem = new DriveSubsystem();
        m_encoder.setDistancePerPulse(0.001);
        setGoal(0);
    }

    @Override
    protected double getMeasurement() {
        return m_encoder.getDistance();
    }

    public boolean atSetpoint() {
        return m_controller.atSetpoint();
    }

    @Override
    protected void useOutput(double output, State setpoint) {
        m_driveSubsystem.arcadeDrive(0, output);
    }
}
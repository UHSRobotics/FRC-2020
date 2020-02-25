package frc.robot.subsystems.pidcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants.VisionControlConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.TalonFXDriveSubsystem;

public class ProfiledDriveDistancePID extends ProfiledPIDSubsystem {
    private final TalonFXDriveSubsystem m_driveSubsystem;
    private final SimpleMotorFeedforward m_feedforward = new SimpleMotorFeedforward(0, 0);

    public ProfiledDriveDistancePID() {
        super(new ProfiledPIDController(VisionControlConstants.KpDist, VisionControlConstants.KiDist,
                VisionControlConstants.KdDist, new TrapezoidProfile.Constraints(10, 20)), 0);
        m_driveSubsystem = new TalonFXDriveSubsystem();
    }

    @Override
    protected double getMeasurement() {
        return m_driveSubsystem.getEncoderRight();
    }

    public boolean atSetpoint() {
        return m_controller.atSetpoint();
    }

    @Override
    protected void useOutput(double output, State setpoint) {
        double feedforward = m_feedforward.calculate(setpoint.position, setpoint.velocity);
        m_driveSubsystem.arcadeDrive(output + feedforward, 0);

    }

}
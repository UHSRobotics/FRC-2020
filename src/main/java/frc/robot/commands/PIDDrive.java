package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TalonFXDriveSubsystem;

public class PIDDrive extends CommandBase {
    private final TalonFXDriveSubsystem m_drive;
    private final BooleanSupplier m_activate;
    private final double m_pos;
    private boolean done = false;

    public PIDDrive(TalonFXDriveSubsystem subsystem, BooleanSupplier activateSupplier, double pos) {
        m_drive = subsystem;
        m_activate = activateSupplier;
        m_pos = pos;
        addRequirements(m_drive);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_activate.getAsBoolean()) {
            if (!done) {
                m_drive.motionMagicDrive(m_pos);
                done = true;
            }
        } else {
            done = false;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drive.motionMagicDrive(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}

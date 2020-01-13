package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_pow;
  private final DoubleSupplier m_turn;

  public ArcadeDrive(DriveSubsystem subsystem, DoubleSupplier powerSupplier, DoubleSupplier turnSupplier) {
    m_drive = subsystem;
    m_pow = powerSupplier;
    m_turn = turnSupplier;
    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(Math.abs(m_pow.getAsDouble()) > Constants.joystickDeadzone ? m_pow.getAsDouble() : 0,
        Math.abs(m_turn.getAsDouble()) > Constants.joystickDeadzone ? m_turn.getAsDouble() : 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class TankDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_left;
  private final DoubleSupplier m_right;

  public TankDrive(DriveSubsystem subsystem, DoubleSupplier leftSupplier, DoubleSupplier rigtSupplier) {
    m_drive = subsystem;
    m_left = leftSupplier;
    m_right = rigtSupplier;
    addRequirements(m_drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // SmartDashboard.putNumber("Controller axis ", m_left.getAsDouble());
    m_drive.tankDrive(Math.abs(m_left.getAsDouble()) > Constants.joystickDeadzone ? m_left.getAsDouble() : 0,
        Math.abs(m_right.getAsDouble()) > Constants.joystickDeadzone ? m_right.getAsDouble() : 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

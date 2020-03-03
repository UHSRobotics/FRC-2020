package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.pidcontroller.DriveRotationPID;
// import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_pow;
  private final DoubleSupplier m_turn;
  // private final BooleanSupplier m_reverse;
  // private final DriveRotationPID m_turnPID = new DriveRotationPID();

  public ArcadeDrive(DriveSubsystem subsystem, DoubleSupplier powerSupplier, DoubleSupplier turnSupplier) {
    m_drive = subsystem;
    m_pow = powerSupplier;
    m_turn = turnSupplier;
    // m_reverse = reverseSupplier;
    addRequirements(m_drive);
    // addRequirements(m_turnPID);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (m_reverse.getAsBoolean()) {
    // m_isReverse = !m_isReverse;
    // // m_turnPID.enable();
    // // m_turnPID.setSetpoint(180);
    // }
    // if (m_pow.getAsDouble() > Constants.joystickDeadzone || m_turn.getAsDouble()
    // > Constants.joystickDeadzone) {
    // // m_turnPID.disable();
    // }
    // if (!m_isReverse) {
    m_drive.arcadeDrive(m_pow.getAsDouble(),
        m_turn.getAsDouble());
    // } else {
    // m_drive.arcadeDrive(Math.abs(m_pow.getAsDouble()) >
    // Constants.joystickDeadzone ? m_pow.getAsDouble() : 0,
    // Math.abs(m_turn.getAsDouble()) > Constants.joystickDeadzone ? -1 *
    // m_turn.getAsDouble() : 0);
    // }
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

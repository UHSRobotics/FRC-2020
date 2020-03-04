
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem m_intake;
    private final DoubleSupplier m_data;

    public IntakeCommand(IntakeSubsystem intake, DoubleSupplier data) {
        m_intake = intake;
        m_data = data;
        addRequirements(m_intake);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (Math.abs(m_data.getAsDouble()) > OIConstants.joystickDeadzone) {
            System.out.println("exe");
            // m_intake.setSpeedMultiplier(1, true);
            m_intake.switchOn(m_data.getAsDouble());
        } else {
            m_intake.switchOff();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // m_intake.setSpeedMultiplier(0, true);
        // m_intake.intakeSwitch();
        m_intake.switchOff();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}

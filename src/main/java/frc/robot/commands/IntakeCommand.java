
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

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
        if (Math.abs(m_data.getAsDouble()) > Constants.joystickDeadzone) {
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

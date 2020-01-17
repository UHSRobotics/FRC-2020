/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSingleSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as
 * {@link DoubleSupplier}s). Written explicitly for pedagogical purposes -
 * actual code should inline a command this simple with
 * {@link edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class FwheelDebugSingleCommand extends CommandBase {
  private final FlywheelSingleSubsystem m_drive;
  private final DoubleSupplier m_forward;
  private final BooleanSupplier m_FullPow;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public FwheelDebugSingleCommand(FlywheelSingleSubsystem subsystem, DoubleSupplier speed, BooleanSupplier fullPow) {
    m_drive = subsystem;
    m_forward = speed;
    m_FullPow = fullPow;
    addRequirements(m_drive);
  }

  @Override
  public void execute() {
    if (m_forward.getAsDouble() != 0) {
      m_drive.setSpeed(m_forward.getAsDouble());
      System.out.println("pow: " + m_forward);
    } else {
      if (m_FullPow.getAsBoolean()) {
        m_drive.setSpeed(1);
      }
    }
  }
}

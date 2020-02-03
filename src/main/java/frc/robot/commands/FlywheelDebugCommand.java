/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.FlywheelSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class FlywheelDebugCommand extends CommandBase {
  private final FlywheelSubsystem m_flywheel;
  private final DoubleSupplier m_lforward;
  private final DoubleSupplier m_rforward;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public FlywheelDebugCommand(FlywheelSubsystem subsystem, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
    m_flywheel = subsystem;
    m_lforward = leftSpeed;
    m_rforward = rightSpeed;
    addRequirements(m_flywheel);
  }

  @Override
  public void execute() {
    m_flywheel.setSpeed(m_lforward.getAsDouble(), m_rforward.getAsDouble());
    // System.out.println("Left: "+ m_lforward.getAsDouble() + " right: " + m_rforward.getAsDouble());
  }
}

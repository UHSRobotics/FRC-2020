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
  private final FlywheelSingleSubsystem m_flywheel;
  private final DoubleSupplier m_speed;
  private final BooleanSupplier m_fullPow;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public FwheelDebugSingleCommand(FlywheelSingleSubsystem subsystem, DoubleSupplier speed, BooleanSupplier fullPow) {
    m_flywheel = subsystem;
    m_speed = speed;
    m_fullPow = fullPow;
    addRequirements(m_flywheel);
  }

  @Override
  public void execute() {

    if (m_speed.getAsDouble() != 0) {
      m_flywheel.setSpeed(m_speed.getAsDouble());
    } else {
      if (m_fullPow.getAsBoolean()) {
        m_flywheel.setSpeed(1);
      }else{
        m_flywheel.setSpeed(0);
      }
    }
  }
}

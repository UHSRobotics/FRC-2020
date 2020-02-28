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
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.NeoFwSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as
 * {@link DoubleSupplier}s). Written explicitly for pedagogical purposes -
 * actual code should inline a command this simple with
 * {@link edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class FlywheelCmd extends CommandBase {
  private final FlywheelSubsystem m_flywheel;
  private final NeoFwSubsystem m_neoFw;
  private final DoubleSupplier m_speed;
  private final BooleanSupplier m_fullPow;// , m_mag;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public FlywheelCmd(FlywheelSubsystem subsystem, DoubleSupplier speed, BooleanSupplier fullPow) {
    m_flywheel = subsystem;
    m_neoFw = null;
    m_speed = speed;
    m_fullPow = fullPow;
    // m_mag = mag;
    addRequirements(m_flywheel);
  }

  public FlywheelCmd(NeoFwSubsystem subsystem, DoubleSupplier speed, BooleanSupplier fullPow) {
    m_neoFw = subsystem;
    m_flywheel = null;
    m_speed = speed;
    m_fullPow = fullPow;
    // m_mag = mag;
    addRequirements(m_neoFw);
  }

  @Override
  public void execute() {
    // m_neoFw.readDI(m_mag.getAsBoolean());
    if (m_speed.getAsDouble() != 0) {
      if (m_flywheel != null) {
        m_flywheel.setSpeed(m_speed.getAsDouble());
      } else {
        m_neoFw.setSpeed(m_speed.getAsDouble());
      }
    } else {
      if (m_flywheel != null) {
        if (m_fullPow.getAsBoolean()) {
          m_flywheel.setSpeed(1);
        } else {
          m_flywheel.setSpeed(0);
        }
      } else {
        if (m_fullPow.getAsBoolean()) {
          m_neoFw.setSpeed(1); 
          // m_neoFw.setPIDTarget(1);
        } else {
          m_neoFw.setSpeed(0);
          // m_neoFw.setPIDTarget(0);
        }
      }
    }
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.HopperConstants;
import frc.robot.subsystems.*;
import frc.robot.subsystems.pidcontroller.RotPID;

public class ManualShootingCommand extends CommandBase {
  /**
   * Creates a new ManualShootingCommand.
   */
  FlywheelSubsystem m_flywheel;
  HopperSubsystem m_hopper;
  BooleanSupplier m_fullPow;
  RotPID m_rotPID;
  VisionSubsystem m_vision;
  private boolean started = false, firstTime = false;

  /**
   * should be put into a "whileHeld"
   */
  public ManualShootingCommand(FlywheelSubsystem flywheel, HopperSubsystem hopper, RotPID rotPID, DriveSubsystem drive,
      VisionSubsystem vision) {
    m_flywheel = flywheel;
    m_hopper = hopper;
    m_rotPID = rotPID;
    m_vision = vision;
    addRequirements(m_flywheel);
    addRequirements(m_hopper);
    addRequirements(drive);
    addRequirements(rotPID);
    started = false;
  }

  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("rot pid target", m_rotPID.getGoal());
    SmartDashboard.putBoolean("manual shooting enabled", started);

    if (m_vision.working()) {
      if (!started) {
        firstTime = true;
        m_rotPID.setGoalRelative((m_vision.getHorizontalAngle() / Math.PI) * -180.0);
        if (!m_rotPID.isEnabled())
          m_rotPID.enable();
        updateFlywheelSpeed();
        started = true;
      }
      if (m_rotPID.getController().atSetpoint()) {
        if (firstTime) {
          firstTime = false;
          updateFlywheelSpeed();
        }
        SmartDashboard.putBoolean("rot pid settled", true);

        if (m_flywheel.atSetPoint()) {
          SmartDashboard.putBoolean("moving hopper", true);
          m_hopper.setPIDTarget(HopperConstants.targetRPM);
        } else {
          SmartDashboard.putBoolean("moving hopper", false);
          m_hopper.setPIDTarget(0);
        }
      } else {
        SmartDashboard.putBoolean("rot pid settled", false);
        if (!m_rotPID.isEnabled())
          m_rotPID.enable();
        firstTime = true;
      }
    }
  }

  private void updateFlywheelSpeed() {
    if (m_vision.getX() < 5) {
      m_flywheel.setPIDTarget(FlywheelConstants.targetRPM);
    } else if (m_vision.getX() < 6) {
      m_flywheel.setPIDTarget(FlywheelConstants.targetRPM);
    } else if (m_vision.getX() < 7) {
      m_flywheel.setPIDTarget(FlywheelConstants.targetRPM + 200);
    } else {
      m_flywheel.setPIDTarget(FlywheelConstants.targetRPM + 700);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stop();
  }

  public void stop() {
    m_flywheel.setPIDTarget(0);
    if (m_rotPID.isEnabled())
      m_rotPID.disable();
    started = false;
    SmartDashboard.putBoolean("manual shooting enabled", started);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

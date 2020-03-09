/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pidcommand;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.HopperConstants;
import frc.robot.subsystems.*;

public class ManualShootingCommand extends CommandBase {
  /**
   * Creates a new ManualShootingCommand.
   */
  FlywheelSubsystem m_flywheel;
  HopperSubsystem m_hopper;
  BooleanSupplier m_fullPow;

  /**
   * should be put into a "whileHeld"
   */
  public ManualShootingCommand(FlywheelSubsystem flywheel, HopperSubsystem hopper) {
    m_flywheel = flywheel;
    m_hopper = hopper;
    addRequirements(m_flywheel);
    addRequirements(m_hopper);
  }

  @Override
  public void initialize() {
    m_flywheel.setPIDTarget(FlywheelConstants.targetRPM);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_flywheel.atSetPoint()){
      m_hopper.setPIDTarget(HopperConstants.targetRPM);
    }else{
      m_hopper.setPIDTarget(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

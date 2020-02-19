/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class HopperCommand extends CommandBase {
  /**
   * Creates a new HopperCommand.
   */

  private final HopperSubsystem m_hopper;
  private final BooleanSupplier m_actionvate; 

  public HopperCommand(HopperSubsystem subsystem, BooleanSupplier hopperActivition) {
    m_hopper = subsystem;
    m_actionvate = hopperActivition;
    addRequirements(m_hopper);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_actionvate.getAsBoolean()){
      m_hopper.switchON();
      Timer.delay(0.5);
      m_hopper.switchOFF();
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

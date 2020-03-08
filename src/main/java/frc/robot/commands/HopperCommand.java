/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class HopperCommand extends CommandBase {
  /**
   * Creates a new HopperCommand.
   */

  private final HopperSubsystem m_hopper;
  private final DoubleSupplier m_activate; 
  // double m_delay = 0.5;
  boolean finished = false;

  public HopperCommand(HopperSubsystem subsystem, DoubleSupplier hopperActivation) {
    m_hopper = subsystem;
    m_activate = hopperActivation;
    addRequirements(m_hopper);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_activate.getAsDouble() != 0){
      m_hopper.switchON(m_activate.getAsDouble());
    }else{
      m_hopper.switchOFF();

    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hopper.switchOFF();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}

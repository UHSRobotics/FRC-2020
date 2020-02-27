/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.pidcommand.DistancePIDCommand;
import frc.robot.commands.pidcommand.RotationPIDCommand;
import frc.robot.subsystems.TalonFXDriveSubsystem;

public class AutonPlaceholder extends SequentialCommandGroup {
  /**
   * Creates a new AutonPlaceholder.
   */
  public AutonPlaceholder(TalonFXDriveSubsystem drive) {
    // System.out.println("Putted");
    addCommands(new DistancePIDCommand(drive, 20), new RotationPIDCommand(drive, 10));
  }

  // Called when the command is initially scheduled.
  
}

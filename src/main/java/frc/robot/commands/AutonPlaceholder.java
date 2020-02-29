/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.pidcommand.DistancePIDCommand;
import frc.robot.commands.pidcommand.RotationPIDCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.NeoFwSubsystem;
import frc.robot.subsystems.TalonFXDriveSubsystem;

public class AutonPlaceholder extends SequentialCommandGroup {
  /**
   * Creates a new AutonPlaceholder.
   */
  public AutonPlaceholder(TalonFXDriveSubsystem drive, NeoFwSubsystem fw, HopperSubsystem hop) {
    addRequirements(drive);
    addRequirements(fw);
    addRequirements(hop);
    fw.setSpeed(1);
    // addCommands(new TargetCommand(0, 0, drive));
    hop.switchON(1);
    Timer.delay(5);
    hop.switchOFF();
    fw.setSpeed(0);
    drive.arcadeDrive(-0.3, 0);
    Timer.delay(4);
    drive.disable();
  }

  // Called when the command is initially scheduled.
  
}

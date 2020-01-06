/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.SpinSubsystem;;

/**
 * An example command that uses an example subsystem.
 */
public class SpinCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ColorSubsystem colorSubsystem;
  private final SpinSubsystem spinSubsystem;
  private int stage; // stage 2 or 3
  private int colorTarget; // target color to check
  private int colorChange;
  private int prevColor;
  private int currColor;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SpinCommand(ColorSubsystem sC, SpinSubsystem sF) {
    colorSubsystem = sC;
    spinSubsystem = sF;
    colorChange = 0;
    currColor = colorSubsystem.getColor();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorSubsystem);
    addRequirements(spinSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (stage == 3) {
      while (!colorSubsystem.matchColor(colorTarget)) {
        spinSubsystem.spin(1);
      }
    } else {
      while (colorChange < 26) {
        prevColor = currColor;
        currColor = colorSubsystem.getColor();
        if (currColor - prevColor == 1 || currColor - prevColor == 3) {
          colorChange++;
        }
        spinSubsystem.spin(1);
      }
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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.SpinSubsystem;;

/**
 * An example command that uses an example subsystem.
 */
public class SpinCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ColorSubsystem m_colorSubsystem;
  private final SpinSubsystem m_spinSubsystem;
  private int state;
  private static int colorChange = 0;
  private int prevColor;
  private int currColor;
  private boolean done;

  /**
   * @param state -1-0-1-2-3=rotation-blue-green-red-yellow
   */
  public SpinCommand(ColorSubsystem sC, SpinSubsystem sF, int state) {
    m_colorSubsystem = sC;
    m_spinSubsystem = sF;
    this.state = state;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_colorSubsystem);
    addRequirements(m_spinSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    currColor = m_colorSubsystem.getColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // debug purposes.
    boolean disableSpin = true;
    if (state > -1) {
      while (!m_colorSubsystem.matchColor(state)) {
        if (!disableSpin)
          m_spinSubsystem.spin(0.1);
      }
      done = true;
    } else {
      while (Math.abs(colorChange) < 26) {
        prevColor = currColor;
        currColor = m_colorSubsystem.getColor();
        if (currColor - prevColor == 1 || currColor - prevColor == 3) {
          colorChange++;
        } else if (currColor - prevColor == -1 || currColor - prevColor == -3) {
          colorChange--;
        }
        if (!disableSpin)
          m_spinSubsystem.spin(0.1);
      }
      done = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SingleSolenoidTestSubsystem extends SubsystemBase {
  private final Solenoid exampleDouble = new Solenoid(1);

  public void solenoidOff() {
    exampleDouble.set(false);
  }

  public void solenoidOn() {
    exampleDouble.set(true);
  }
}

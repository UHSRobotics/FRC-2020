/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class SolenoidTestSubsystem extends SubsystemBase {
  private final DoubleSolenoid exampleDouble = new DoubleSolenoid(1, 2);

  public void solenoidOff() {
    exampleDouble.set(kOff);
  }

  public void solenoidFoward() {
    exampleDouble.set(kForward);
  }

  public void solenoidBack() {
    exampleDouble.set(kReverse);
  }
}

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
  private final DoubleSolenoid exampleDouble = new DoubleSolenoid(0, 1);

  public void solenoidOff() {
    exampleDouble.set(kOff);
    System.out.println("Solenoid Off");
  }

  public void solenoidForward() {
    exampleDouble.set(kForward);
    System.out.println("Solenoid Forward");
  }

  public void solenoidBack() {
    exampleDouble.set(kReverse);
    System.out.println("Solenoid Back");

  }
}

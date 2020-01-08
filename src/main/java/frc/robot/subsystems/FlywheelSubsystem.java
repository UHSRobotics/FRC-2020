/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.Victor;;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class FlywheelSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private final VictorSPX m_leftMotor = new VictorSPX(0);


  // The motors on the right side of the drive.
private final VictorSPX m_rightMotor = new VictorSPX(1);

  // The robot's drive
  // private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void tankDrive(double l, double r) {
    if(l<0.2&&l>-0.2) l=0;
    
    if(r<0.2&&r>-0.2) r = 0;
    // l = l*0.1;
    // r = r*0.1;
    m_leftMotor.set(ControlMode.PercentOutput, l);
    m_rightMotor.set(ControlMode.PercentOutput, -r);
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    // m_drive.setMaxOutput(maxOutput);
  }
}
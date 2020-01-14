/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private final VictorSPX m_leftFrontMotor = new VictorSPX(1);
  private final VictorSPX m_rightFrontMotor = new VictorSPX(3);
  private final VictorSPX m_leftBackMotor = new VictorSPX(2);
  private final VictorSPX m_rightBackMotor = new VictorSPX(0);

  public DriveSubsystem() {
    m_leftFrontMotor.setNeutralMode(NeutralMode.Coast);
    m_rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    m_leftBackMotor.setNeutralMode(NeutralMode.Coast);
    m_rightBackMotor.setNeutralMode(NeutralMode.Coast);
  }

  public void tankDrive(double l, double r) {
    // l*=0.5;
    // r*=0.5;
    m_leftFrontMotor.set(ControlMode.PercentOutput, -l);
    m_leftBackMotor.set(ControlMode.PercentOutput, -l);
    m_rightFrontMotor.set(ControlMode.PercentOutput, r);
    m_rightBackMotor.set(ControlMode.PercentOutput,r);
  }

  public void arcadeDrive(double pow, double turn) {
    // pow*=0.5;
    // turn*=0.5;
    m_leftFrontMotor.set(ControlMode.PercentOutput, -pow+turn);
    m_leftBackMotor.set(ControlMode.PercentOutput, -pow+turn);
    m_rightFrontMotor.set(ControlMode.PercentOutput, pow+turn);
    m_rightBackMotor.set(ControlMode.PercentOutput, pow+turn);
  }
}

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

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private final VictorSPX m_leftFrontMotor = new VictorSPX(1);
  private final VictorSPX m_rightFrontMotor = new VictorSPX(3);
  private final VictorSPX m_leftBackMotor = new VictorSPX(2);
  private final VictorSPX m_rightBackMotor = new VictorSPX(0);

  private final ShuffleboardTab tab = Shuffleboard.getTab("Drive");
  private NetworkTableEntry speedEntry;
  private double speedMultiplier = 1;

  public DriveSubsystem() {
    m_leftFrontMotor.setNeutralMode(NeutralMode.Coast);
    m_rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    m_leftBackMotor.setNeutralMode(NeutralMode.Coast);
    m_rightBackMotor.setNeutralMode(NeutralMode.Coast);
  }

  public void tankDrive(double l, double r) {
    l *= speedMultiplier;
    r *= speedMultiplier;
    m_leftFrontMotor.set(ControlMode.PercentOutput, -l);
    m_leftBackMotor.set(ControlMode.PercentOutput, -l);
    m_rightFrontMotor.set(ControlMode.PercentOutput, r);
    m_rightBackMotor.set(ControlMode.PercentOutput, r);
  }

  public void arcadeDrive(double pow, double turn) {
    pow *= speedMultiplier;
    turn *= speedMultiplier;
    m_leftFrontMotor.set(ControlMode.PercentOutput, -pow + turn);
    m_leftBackMotor.set(ControlMode.PercentOutput, -pow + turn);
    m_rightFrontMotor.set(ControlMode.PercentOutput, pow + turn);
    m_rightBackMotor.set(ControlMode.PercentOutput, pow + turn);
  }

  public void setSpeedMultiplier(double speed, boolean updateNT) {
    if (0 <= speed && speed <= 2) {
      speedMultiplier = speed;
      if(updateNT){
        System.out.println("Putted Speed Multiplier NT entry");
        speedEntry.setDouble(speedMultiplier);
      }
    } else {
      System.out.println("Putted Speed Multiplier NT entry");
      speedEntry.setDouble(speedMultiplier);
    }
  }

  @Override
  public void periodic() {
    if(speedEntry==null){
      speedEntry = tab.add("Speed Multiplier", 1).getEntry();
      System.out.println("Added Speed Multiplier NT entry");
    }
    setSpeedMultiplier(speedEntry.getDouble(1.0), false);
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LiftSubsystem extends SubsystemBase {
  private final TalonSRX m_liftMotor = new TalonSRX(Constants.Ports.lift);
  private final TalonSRX m_follow = new TalonSRX(Constants.Ports.liftFollow);
  // private final
  private double speedMultiplier = 1;
  private NetworkTableEntry speedEntry, encoderEntry;
  private final ShuffleboardTab tab = Shuffleboard.getTab("Scoring");
  private static boolean init = false;

  public LiftSubsystem() {
    m_liftMotor.setNeutralMode(NeutralMode.Brake);
    m_follow.setNeutralMode(NeutralMode.Brake);
    m_follow.follow(m_liftMotor);
    m_liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
  }

  public void setSpeed(double s) {
    s *= speedMultiplier;
    m_liftMotor.set(ControlMode.PercentOutput, s);
    // System.out.println(m_liftMotor.getSelectedSensorPosition());
  }

  public double getEncoder(){
    return m_liftMotor.getSelectedSensorPosition();
  }

  public void setSpeedMultiplier(double speed) {
    setSpeedMultiplier(speed, true);
  }

  public void setSpeedMultiplier(double speed, boolean updateNT) {
    if (0 <= speed && speed <= 2) {
      speedMultiplier = speed;
      if (updateNT) {
        System.out.println("Putted Lift Speed Multiplier NT entry");
        speedEntry.setDouble(speedMultiplier);
      }
    } else {
      System.out.println("Putted Lift Speed Multiplier NT entry");
      speedEntry.setDouble(speedMultiplier);
    }
  }

  @Override
  public void periodic() {
    if (speedEntry == null) {
      speedEntry = tab.addPersistent("Lift Speed Multiplier", 1).getEntry();
      System.out.println("Added Lift Speed Multiplier NT entry");
    }
    if (encoderEntry == null) {
      encoderEntry = tab.addPersistent("Encoder Reading", 1).getEntry();
      System.out.println("Added Lift Encoder NT entry");
    }
    setSpeedMultiplier(speedEntry.getDouble(0.25), false);
    encoderEntry.setDouble(getEncoder());
  }


  public void initialized() {
    init = true;
  }

  public boolean getInit() {
    return init;
  }
}
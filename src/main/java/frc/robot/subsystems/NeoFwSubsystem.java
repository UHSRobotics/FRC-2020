/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NeoFwSubsystem extends SubsystemBase {
  /**
   * Creates a new NeoFwSubsystem.
   */
  private CANSparkMax m_motor = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax m_motorFollow = new CANSparkMax(1, MotorType.kBrushless);
  private final ShuffleboardTab tab = Shuffleboard.getTab("Scoring");
  private NetworkTableEntry speedEntry;
  private double speedMultiplier = 0.25;

  
  public NeoFwSubsystem() {
    m_motor.setIdleMode(IdleMode.kCoast);
    m_motorFollow.follow(m_motor);
  }

  public void setSpeed(double p) {
    p *= speedMultiplier;
    System.out.println(p);
    m_motor.set(p);
  }

  public void setSpeedMultiplier(double speed, boolean updateNT) {
    if (0 <= speed && speed <= 2) {
      speedMultiplier = speed;
      if (updateNT) {
        System.out.println("Putted Single Speed Multiplier NT entry");
        speedEntry.setDouble(speedMultiplier);
      }
    } else {
      System.out.println("Putted Single Speed Multiplier NT entry");
      speedEntry.setDouble(speedMultiplier);
    }
  }

  @Override
  public void periodic() {
    if (speedEntry == null) {
      speedEntry = tab.addPersistent("Single Speed Multiplier", 1).getEntry();
      System.out.println("Added Single Speed Multiplier NT entry");
    }
    setSpeedMultiplier(speedEntry.getDouble(1.0), false);
  }
}

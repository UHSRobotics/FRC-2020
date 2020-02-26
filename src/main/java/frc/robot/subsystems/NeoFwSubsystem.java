/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NeoFwSubsystem extends SubsystemBase {
  /**
   * Creates a new NeoFwSubsystem.
   */
  private final CANSparkMax m_motor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANPIDController c = m_motor.getPIDController();
  // private final CANSparkMax m_motorInverted = new CANSparkMax(1, MotorType.kBrushless);
  private final ShuffleboardTab tab = Shuffleboard.getTab("Scoring");
  private NetworkTableEntry speedEntry;
  private double speedMultiplier = 0.5;

  
  public NeoFwSubsystem() {
    m_motor.setIdleMode(IdleMode.kCoast);
    // m_motorInverted.setInverted(true);
    c.setP(Constants.FlyWheelPIDConstants.kP);
    c.setI(Constants.FlyWheelPIDConstants.kI);
    c.setD(Constants.FlyWheelPIDConstants.kD);
    c.setFF(Constants.FlyWheelPIDConstants.kF);
  }

  public void setSpeed(double p) {
    p *= speedMultiplier;
    System.out.println(p);
    m_motor.set(p);
    // m_motorInverted.set(p);
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

  // public void readDI(boolean r){
  //   if(r)
  //     System.out.println("Detected");
  // }

  public void setPIDTarget(double t){
    c.setReference(t, ControlType.kVelocity);
  }

  public boolean atSetPoint(double t){
    return m_motor.getEncoder().getVelocity() == t;
  }

  @Override
  public void periodic() {
    if (speedEntry == null) {
      speedEntry = tab.addPersistent("Single Speed Multiplier1", 1).getEntry();
      System.out.println("Added Single Speed Multiplier NT entry");
    }
    setSpeedMultiplier(speedEntry.getDouble(1.0), false);
  }
}
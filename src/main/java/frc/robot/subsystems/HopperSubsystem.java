/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;

public class HopperSubsystem extends SubsystemBase {

  /**
   * Creates a new HopperSubsystem.
   */
  private final CANSparkMax m_motor = new CANSparkMax(Ports.hopper, MotorType.kBrushless);

  public HopperSubsystem() {
  }

  public void switchON(double s){
    m_motor.set(0.5*s);
  }

  public void switchOFF(){
   m_motor.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

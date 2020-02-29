/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {

  /**
   * Creates a new HopperSubsystem.
   */
  private final CANSparkMax m_motor = new CANSparkMax(20, MotorType.kBrushless);

  public HopperSubsystem() {
  }

  public void switchON(){
    m_motor.set(0.5);
  }

  public void switchOFF(){
   m_motor.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

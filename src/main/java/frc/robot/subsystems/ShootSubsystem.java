/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShootSubsystem extends SubsystemBase {
  private final TalonFX m_shoot = new TalonFX(0);
  //private final SuffleBoardTab tab = Shuffleboard.getTab("Shoot");

  public ShootSubsystem() {
    m_shoot.setNeutralMode(NeutralMode.Brake); //set motor to Brake
    m_shoot.setInverted(false);; // if motor is inverted
    m_shoot.getSensorCollection().setIntegratedSensorPosition(0, 0);

  }

  public void periodic(double s) {
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelSubsystem extends SubsystemBase {
  private final VictorSPX m_leftMotor = new VictorSPX(0);
  private final VictorSPX m_rightMotor = new VictorSPX(1);

  private double speedLimit = 1;

  public FlywheelSubsystem(){
    m_leftMotor.setNeutralMode(NeutralMode.Coast);
    m_rightMotor.setNeutralMode(NeutralMode.Coast);
  }
  
  public void setSpeed(double l, double r) {
    if(l<0.2&&l>-0.2) l=0;    
    if(r<0.2&&r>-0.2) r = 0;

    l = l*speedLimit;
    r = r*speedLimit;
    m_leftMotor.set(ControlMode.PercentOutput, l);
    m_rightMotor.set(ControlMode.PercentOutput, -r);
  }

  public void setSpeedLimit(double sl) {
    if(sl<0||sl>1) return;
    speedLimit = sl;
  }
}
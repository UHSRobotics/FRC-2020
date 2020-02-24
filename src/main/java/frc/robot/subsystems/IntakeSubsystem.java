/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class IntakeSubsystem extends SubsystemBase {
  private final TalonFX m_motor = new TalonFX(1);

  private final ShuffleboardTab tab = Shuffleboard.getTab("Scoring");
  private NetworkTableEntry speedEntry;
  private double speedMultiplier = 1;

  public IntakeSubsystem() {
    // set motors to coast
    m_motor.setNeutralMode(NeutralMode.Coast);

    m_motor.setInverted(true);

    // Integrated PID control
    m_motor.config_kP(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kP, PIDConstants.kTimeoutMs);
    m_motor.config_kI(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kI, PIDConstants.kTimeoutMs);
    m_motor.config_kD(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kD, PIDConstants.kTimeoutMs);
    m_motor.config_kF(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kF, PIDConstants.kTimeoutMs);
    m_motor.config_IntegralZone(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kIzone,
            PIDConstants.kTimeoutMs);
    m_motor.configClosedLoopPeakOutput(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kPeakOutput,
            PIDConstants.kTimeoutMs);
    m_motor.configAllowableClosedloopError(PIDConstants.kSlot_Velocit, 0, PIDConstants.kTimeoutMs);

    intakeOff();
  }

  public void intakeOn() {
    m_motor.set(ControlMode.PercentOutput, 1.0);
  }

  public void intakeOff() {
    m_motor.set(ControlMode.PercentOutput, -1.0);
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
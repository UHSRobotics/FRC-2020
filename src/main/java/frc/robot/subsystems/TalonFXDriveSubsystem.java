/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TalonFXDriveSubsystem extends SubsystemBase {
    private final TalonFX m_leftMotor = new TalonFX(1);
    private final TalonFX m_rightMotor = new TalonFX(3);
    private final TalonFX m_leftFollowMotor = new TalonFX(2);
    private final TalonFX m_rightFollowMotor = new TalonFX(0);

    private final ShuffleboardTab tab = Shuffleboard.getTab("Drive (Falcon 500)");
    private NetworkTableEntry encoderEntry;
    private double speedMultiplier = 0.5;

    public TalonFXDriveSubsystem() {
        // set motors to coast
        m_leftMotor.setNeutralMode(NeutralMode.Coast);
        m_rightMotor.setNeutralMode(NeutralMode.Coast);
        m_leftFollowMotor.setNeutralMode(NeutralMode.Coast);
        m_rightFollowMotor.setNeutralMode(NeutralMode.Coast);
        // establish master
        m_leftFollowMotor.follow(m_leftMotor);
        m_rightFollowMotor.follow(m_rightMotor);
        // invert motor
        m_leftMotor.setInverted(true);
        m_rightMotor.setInverted(false);
        m_leftFollowMotor.setInverted(InvertType.FollowMaster);
        m_rightFollowMotor.setInverted(InvertType.FollowMaster);
        // reset encoder
        m_leftMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        m_rightMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        m_leftFollowMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        m_rightFollowMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
    }

    public void arcadeDrive(double pow, double turn) {
        m_leftMotor.set(ControlMode.PercentOutput, pow - turn);
        m_leftMotor.set(ControlMode.PercentOutput, pow + turn);
    }

    public void encoderTest() {
        encoderEntry.setDouble(m_rightMotor.getSensorCollection().getIntegratedSensorPosition());
    }

    public void setSpeedMultiplier(double speed, boolean updateNT) {
        if (0 <= speed && speed <= 2) {
            speedMultiplier = speed;

        }
    }

    @Override
    public void periodic() {
    }
}

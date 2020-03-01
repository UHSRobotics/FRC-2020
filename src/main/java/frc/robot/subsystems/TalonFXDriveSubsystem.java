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

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PIDConstants;

public class TalonFXDriveSubsystem extends SubsystemBase {
    private final TalonFX m_leftMotor = new TalonFX(7);
    private final TalonFX m_rightMotor = new TalonFX(5);
    private final TalonFX m_leftFollowMotor = new TalonFX(8);
    private final TalonFX m_rightFollowMotor = new TalonFX(6);

    private final ShuffleboardTab tab = Shuffleboard.getTab("Drive (Falcon 500)");
    private NetworkTableEntry encoderEntry;
    private double speedMultiplier = 1;
    private NetworkTableEntry speedEntry;

    private double aLimit = 0.07;
    private double pow0;

    public TalonFXDriveSubsystem() {
        pow0 = 0;
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
        // Acceleration

        // reset encoder

        m_leftMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        m_rightMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        m_leftFollowMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        m_rightFollowMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        // Integrated PID control
        m_rightMotor.config_kP(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kP, PIDConstants.kTimeoutMs);
        m_rightMotor.config_kI(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kI, PIDConstants.kTimeoutMs);
        m_rightMotor.config_kD(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kD, PIDConstants.kTimeoutMs);
        m_rightMotor.config_kF(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kF, PIDConstants.kTimeoutMs);
        m_rightMotor.config_IntegralZone(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kIzone,
                PIDConstants.kTimeoutMs);
        m_rightMotor.configClosedLoopPeakOutput(PIDConstants.kSlot_Velocit, PIDConstants.kGains_Velocit.kPeakOutput,
                PIDConstants.kTimeoutMs);
        m_rightMotor.configAllowableClosedloopError(PIDConstants.kSlot_Velocit, 0, PIDConstants.kTimeoutMs);
        // Configure cruise
        m_rightMotor.configMotionCruiseVelocity(15000, PIDConstants.kTimeoutMs);
        m_rightMotor.configMotionAcceleration(6000, PIDConstants.kTimeoutMs);

    }

    public void arcadeDrive(double pow, double turn) {
        pow *= speedMultiplier;
        if (pow - pow0 >= aLimit) {
            pow = pow0 + aLimit;
        }

        pow0 = pow;
        m_leftMotor.set(ControlMode.PercentOutput, pow - turn);
        m_rightMotor.set(ControlMode.PercentOutput, pow + turn);
    }

    public void pidDrive(double pos) {
        m_rightMotor.set(ControlMode.Position, pos, DemandType.ArbitraryFeedForward, 0);
        m_leftMotor.follow(m_rightMotor);
    }

    public void motionMagicDrive(double pos) {
        m_rightMotor.set(ControlMode.MotionMagic, pos);
        m_leftMotor.follow(m_rightMotor);
    }

    public void motionMagicTurn(double angle) {
        m_rightMotor.set(ControlMode.MotionMagic, angle);
        m_leftMotor.set(ControlMode.MotionMagic, -angle);
    }

    public void disable() {
        m_rightMotor.set(ControlMode.PercentOutput, 0);
        m_leftMotor.set(ControlMode.PercentOutput, 0);
    }

    public void encoderTest() {
        encoderEntry.setDouble(m_rightMotor.getSensorCollection().getIntegratedSensorPosition());
    }

    public double getEncoderLeft() {
        return m_leftMotor.getSelectedSensorPosition();
    }

    public double getEncoderRight() {
        return m_rightMotor.getSelectedSensorPosition();
    }

    public void setSpeedMultiplier(double speed, boolean updateNT) {
        if (0 <= speed && speed <= 2) {
            speedMultiplier = speed;
            if (updateNT) {
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
        if (speedEntry == null) {
            speedEntry = tab.addPersistent("Speed Multiplier", 1).getEntry();
            System.out.println("Added Speed Multiplier NT entry");
        }
        setSpeedMultiplier(speedEntry.getDouble(1.0), false);
    }
}

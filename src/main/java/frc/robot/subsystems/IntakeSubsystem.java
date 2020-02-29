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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IntakeSubsystem extends SubsystemBase {
    private final TalonSRX m_motor = new TalonSRX(26);

    private final ShuffleboardTab tab = Shuffleboard.getTab("Intake");
    private NetworkTableEntry speedEntry;
    private double speedMultiplier = 1;
    private double tempSpeed  = 0.3;

    public IntakeSubsystem() {
        // set motors to coast
        m_motor.setNeutralMode(NeutralMode.Coast);
    }

    public void switchOn() {
        m_motor.set(ControlMode.PercentOutput, 1);
    }

    public void switchOff(){
        m_motor.set(ControlMode.PercentOutput, 0);
    }

    // public void setSpeedMultiplier(double speed, boolean updateNT) {
    //     if (0 <= speed && speed <= 2) {
    //         speedMultiplier = speed;
    //         if (updateNT) {
    //             System.out.println("Putted Single Speed Multiplier NT entry");
    //             speedEntry.setDouble(speedMultiplier);
    //         }
    //     } else {
    //         System.out.println("Putted Single Speed Multiplier NT entry");
    //         speedEntry.setDouble(speedMultiplier);
    //     }
    // }

    @Override
    public void periodic() {
        // if (speedEntry == null) {
        //     speedEntry = tab.addPersistent("Intake", 1).getEntry();
        //     System.out.println("Added Single Speed Multiplier NT entry");
        // }
        // setSpeedMultiplier(speedEntry.getDouble(1.0), false);
    }
}
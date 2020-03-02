/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class WinchServoSubsystem extends SubsystemBase {
    private final Servo m_switch = new Servo(0);
    public static boolean toggleOn = false;
    private ShuffleboardTab servoTab = Shuffleboard.getTab("Servo Status");
    private NetworkTableEntry servoEntry;

    public void toggle() {
        toggleOn = !toggleOn;
    }

    @Override
    public void periodic() {
        if(servoEntry == null)
            servoEntry = servoTab.addPersistent("Servo", false).getEntry();
        servoEntry.setBoolean(toggleOn);
        if(toggleOn){
            m_switch.setAngle(200);
        } else {
            m_switch.setAngle(30);
        }
        // This method will be called once per scheduler run
    }
}

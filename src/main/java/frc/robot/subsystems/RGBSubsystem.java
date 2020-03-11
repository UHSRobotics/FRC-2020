/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class RGBSubsystem extends SubsystemBase {
  private final PWMSparkMax m_light = new PWMSparkMax(Ports.rgb);
  public static double m_colour = 0;
  
  /**
   * @param colour integer represents the colour
   */
  public static void setColour(double colour) {
    m_colour = colour;
  }

  public static void setToRed(){
    m_colour = RGBConstants.red;
  }

  public static void setToBlue(){
    m_colour = RGBConstants.blue;
  }

  public static void setToViolet(){
    m_colour = RGBConstants.violet;
  }

  @Override
  public void periodic() {
    m_light.set(m_colour);
    SmartDashboard.putNumber("RGB LED colour", m_colour);
    if(SmartDashboard.getNumber("RGB LED colour", 0) != m_colour)
      m_colour = SmartDashboard.getNumber("RGB LED colour", 0);
    // This method will be called once per scheduler run
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;

public class ColorSubsystem extends SubsystemBase {
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(Port.kOnboard);

  // use this when OI is figured out
  public boolean matchColor(int target) {
    if (target == getColor()) {
      System.out.println("Pass");
    } else {
      System.out.println("Not pass");
    }
    return target == getColor();
  }

  /**
   * @return colorCode 0-1-2-3=blue-green-red-yellow
   */
  public int getColor() {
    Color c = m_colorSensor.getColor();
    System.out.println(c.red + " " + c.green + " " + c.blue);
    if (c.blue > 255 - Constants.colorRange && c.green > 255 - Constants.colorRange && c.red < Constants.colorRange) {
      System.out.println("blue");
      return 0;
    }
    if (c.green > 255 - Constants.colorRange && c.red < Constants.colorRange && c.blue < Constants.colorRange) {
      System.out.println("green");
      return 1;
    }
    if (c.red > 255 - Constants.colorRange && c.green < Constants.colorRange && c.blue < Constants.colorRange) {
      System.out.println("red");
      return 2;
    }
    if (c.red > 255 - Constants.colorRange && c.green > 255 - Constants.colorRange && c.blue < Constants.colorRange) {
      System.out.println("yellow");
      return 3;
    }
    return -64;
  }
}
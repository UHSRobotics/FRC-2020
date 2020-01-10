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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import static frc.robot.Constants.ColorConstants;

public class ColorSubsystem extends SubsystemBase {
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(Port.kOnboard);
  private double[] color = new double[3];
  private Color c;
  private int min, output, error;

  // use this when OI is figured out
  public boolean matchColor(int target) {
    if (target == getColor()) {
      // System.out.println("Pass");
    } else {
      // System.out.println("Not pass");
    }
    return target == getColor();
  }

  /**
   * @return colorCode 0-1-2-3=blue-green-red-yellow
   */
  public int getColor() {
    c = m_colorSensor.getColor();

    color[0] = c.red * 255;
    color[1] = c.green * 255;
    color[2] = c.blue * 255;

    SmartDashboard.putNumber("Color Red", c.red * 255);
    SmartDashboard.putNumber("Color Green", c.green * 255);
    SmartDashboard.putNumber("Color Blue", c.blue * 255);

    min = 256;
    output = -64;

    for (int i = 0; i < 4; i++) {
      error = 0;
      for (int j = 0; j < 3; j++) {
        error += Math.abs(color[j] - ColorConstants.colorTarget[i][j]);
      }
      if (error < min && error<ColorConstants.colorRange) {
        min = error;
        output = i;
      }
    }

    switch (output) {
    case 0:
      SmartDashboard.putString("Color Detected", "Blue");
      break;
    case 1:
      SmartDashboard.putString("Color Detected", "Green");
      break;
    case 2:
      SmartDashboard.putString("Color Detected", "Red");
      break;
    case 3:
      SmartDashboard.putString("Color Detected", "Yellow");
      break;
    default:
      SmartDashboard.putString("Color Detected", "Unknown");
    }

    return output;

    /*
     * if (c.blue * 255 > 255 - Constants.colorRange && c.green * 255 > 255 -
     * Constants.colorRange && c.red * 255 < Constants.colorRange) {
     * SmartDashboard.putString("Color Detected", "Blue"); return 0; } if (c.green *
     * 255 > 255 - Constants.colorRange && c.red * 255 < Constants.colorRange &&
     * c.blue * 255 < Constants.colorRange) {
     * SmartDashboard.putString("Color Detected", "Green");
     * 
     * return 1; } if (c.red * 255 > 255 - Constants.colorRange && c.green * 255 <
     * Constants.colorRange && c.blue * 255 < Constants.colorRange) {
     * SmartDashboard.putString("Color Detected", "Red");
     * 
     * return 2; } if (c.red * 255 > 255 - Constants.colorRange && c.green * 255 >
     * 255 - Constants.colorRange && c.blue * 255 < Constants.colorRange) {
     * SmartDashboard.putString("Color Detected", "Yellow"); return 3; }
     * SmartDashboard.putString("Color Detected", "Unknown"); return -64;
     */
  }
}
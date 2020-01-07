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
  private final ColorSensorV3 colorSensor = new ColorSensorV3(Port.kOnboard);

  /**
   * Creates a new ExampleSubsystem.
   */
  public ColorSubsystem() {

  }

  @Override
  public void periodic() {
  }

  // use this when OI is figured out
  public boolean matchColor(int target) {
    Color c = colorSensor.getColor();
    // 0-1-2-3=blue-green-red-yellow
    switch (target) {
    case 0:
      return c.blue > 255 - Constants.colorRange && c.green > 255 - Constants.colorRange;
    case 1:
      return c.green > 255 - Constants.colorRange;
    case 2:
      return c.red > 255 - Constants.colorRange;
    case 3:
      return c.red > 255 - Constants.colorRange && c.green > 255 - Constants.colorRange;
    default:
      return false;
    }
  }

  public int getColor() {
    for (int i = 0; i < 4; i++) {
      if (matchColor(i)) {
        return i;
      }
    }
    return -1;
  }

}

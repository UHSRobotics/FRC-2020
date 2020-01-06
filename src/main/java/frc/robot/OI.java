package frc.robot;

import frc.robot.RobotContainer;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;

public class OI {

    public static boolean matchColor(int target) {
        Color c = RobotContainer.cs.getColor();
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
}
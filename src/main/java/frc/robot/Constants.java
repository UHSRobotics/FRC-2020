/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class ColorConstants {
        public static final int waitCycle = 5;
        public static final int colorRange = 30;

        /**
         * [i][j] i: 0-1-2-3=blue-green-red-yellow | j: 0-1-2=r-g-b
         */
        public static final int[][] colorTarget = { { 30, 100, 120 }, { 40, 150, 65 }, { 140, 80, 30 },
                { 80, 145, 30 } };
    }

    public static final int controllerPrecision = 1000;
    public static final double joystickDeadzone = 0.1;

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final double kDriverControllerCurvature = 3;
    }

    public static final class VisionControlConstants {
        // Amount of angle tolerated to be considered "aligned"
        public static final int angleDeadzone = 5;
        public static final int distanceDeadzone = 5;
        // Proportional control for vision adjust. Use PID later
        public static final double KpRot = -0.1;
        public static final double KpDist = -0.1;
        // Minimum force when driving
        public static final double minForce = 0.05;
    }

    public static final class DriveConstants {
        public static final int leftMotor = 1;
        public static final int rightMotor = 3;
        public static final int leftFollowMotor = 2;
        public static final int rightFollowMotor = 0;
    }

    public static final class FlywheelCons {
        public static final int flySingle = 2;
        public static final int leftMotor = 1;
        public static final int rightMotor = 0;
    }

    public static final class SpinCons {
        public static final int spinner = 2;
    }
}

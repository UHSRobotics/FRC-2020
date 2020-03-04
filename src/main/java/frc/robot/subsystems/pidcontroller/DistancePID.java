/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pidcontroller;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class DistancePID extends ProfiledPIDSubsystem {
  private DriveSubsystem driveSubsystem;
  public DistancePID(DriveSubsystem drive) {
    super(
        // The ProfiledPIDController used by the subsystem
        new ProfiledPIDController(0.01, 0, 0,
                                  // The motion profile constraints
                                  new TrapezoidProfile.Constraints(0, 0)));
    driveSubsystem = drive;
  }

  @Override
  public void useOutput(double output, TrapezoidProfile.State setpoint) {
    driveSubsystem.arcadeDriveAuton(output, 0);
  }

  @Override
  public double getMeasurement() {
    return driveSubsystem.getEncoderRight();
  }
}

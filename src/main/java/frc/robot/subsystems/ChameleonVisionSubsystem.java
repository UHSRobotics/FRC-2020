/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ChameleonVisionSubsystem extends SubsystemBase {
  /**
   * Creates a new ChameleonVisionSubsystem.
   */
  
  private double[] defaultArray;
  private NetworkTableEntry targetPose;

  public ChameleonVisionSubsystem() {
    defaultArray = new double[3];
    defaultArray[0] = 0.0;
    defaultArray[1] = 0.0;
    defaultArray[2] = 0.0;
    NetworkTableInstance table  = NetworkTableInstance.getDefault();
    NetworkTable cameraTable  = table.getTable("chameleon-vision").getSubTable("USB Camera-B4.09.24.1");
    targetPose = cameraTable.getEntry("targetPose");
  }

  public double getTargetX(){
    return targetPose.getDoubleArray(defaultArray)[0];
  }

  public double getTargetY(){
    return targetPose.getDoubleArray(defaultArray)[1];
  }

  public double getTargetAngle(){
    return targetPose.getDoubleArray(defaultArray)[2];
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

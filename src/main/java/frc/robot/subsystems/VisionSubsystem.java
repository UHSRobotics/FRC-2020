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

public class VisionSubsystem extends SubsystemBase {
  /**
   * Creates a new VisionSubsystem.
   */
  
  private double[] defaultArray;
  private NetworkTableEntry targetPose;
  private NetworkTableEntry yaw;

  public VisionSubsystem() {
    defaultArray = new double[3];
    defaultArray[0] = 0.0;
    defaultArray[1] = 0.0;
    defaultArray[2] = 0.0;
    NetworkTableInstance table  = NetworkTableInstance.getDefault();
    NetworkTable cameraTable  = table.getTable("chameleon-vision").getSubTable("USB Camera-B4.09.24.1");
    targetPose = cameraTable.getEntry("targetPose");
    yaw = cameraTable.getEntry("yaw");
  }

  public double getX(){
    return targetPose.getDoubleArray(defaultArray)[0];
  }

  public double getY(){
    return targetPose.getDoubleArray(defaultArray)[1];
  }

  public double getAngle(){
    return targetPose.getDoubleArray(defaultArray)[2];
  }
  //maybe inch
  public double getDistanceFromTarget(){
    double x = getX(), y = getY();
    return -1;
  } 
  public double getYaw(){
    return yaw.getDouble(0.0);
  }

  public double getRotationDeficit(){
    return -1;
  }

  public boolean possibleShootingPos(){
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

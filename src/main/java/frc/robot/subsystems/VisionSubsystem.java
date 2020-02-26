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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class VisionSubsystem extends SubsystemBase {
  /**
   * Creates a new VisionSubsystem.
   */
  
  private double[] defaultArray;
  private double scale = 0;
  private NetworkTableEntry targetPose;
  private NetworkTableEntry yaw;
  private final ShuffleboardTab tab = Shuffleboard.getTab("Vision");
  private NetworkTableEntry scaleEntry; 
  private double m_scale=1;
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
  //in radians
  public double getAngle(){
    return targetPose.getDoubleArray(defaultArray)[2];
  }

  public double getZ(){
    double x = getX(), y = getY(), a = getAngle();
    return  Math.tan(a)*Math.sqrt(x*x+y*y);
  } 
  public double getYaw(){
    return yaw.getDouble(0.0);
  }
  //maybe inch
  public double getDistanceFromTarget(){
    double x = getX(), y = getY(), z = getZ();
    scale = 98.5/y;
    return Math.pow(scale, 3)*Math.sqrt(x*x + y*y + z*z);
  }
  //inch
  public double getDistanceByAngle(){
    double x = getX(), y = getY();
    return Constants.VisionControlConstants.mToInch*Math.sqrt(x*x + y*y)/Math.cos(getAngle());
  }
  //angle to test if possible to shoot
  public double getHorizontalAngle(){
    double x = getX(), z = getZ();
    return Math.atan(x/z);
  }


  public double getRotationDeficit(){
    return -1;
  }

  public boolean possibleShootingPos(){
    return false;
  }
  public void setScale(double scale){
    scaleEntry.setDouble(scale);
    m_scale = scale;
  }

  @Override
  public void periodic() {
    tab.addPersistent("scale", 1).getEntry();

  }
}

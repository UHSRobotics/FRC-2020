// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.InvertType;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;

// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class DriveSubsystem extends SubsystemBase {
//   private final VictorSPX m_leftMotor = new VictorSPX(Constants.DriveConstants.leftMotor);
//   private final VictorSPX m_rightMotor = new VictorSPX(Constants.DriveConstants.rightMotor);
//   private final VictorSPX m_leftFollowMotor = new VictorSPX(Constants.DriveConstants.leftFollowMotor);
//   private final VictorSPX m_rightFollowMotor = new VictorSPX(Constants.DriveConstants.rightFollowMotor);

//   private final ShuffleboardTab tab = Shuffleboard.getTab("Drive");
//   private NetworkTableEntry speedEntry;
//   private double speedMultiplier = 1;

//   public DriveSubsystem() {
//     //sets all the motors to coast
//     m_leftMotor.setNeutralMode(NeutralMode.Coast);
//     m_leftFollowMotor.setNeutralMode(NeutralMode.Coast);
//     m_rightMotor.setNeutralMode(NeutralMode.Coast);
//     m_rightFollowMotor.setNeutralMode(NeutralMode.Coast);
//     //Makes one of the motors follow the other
//     m_leftFollowMotor.follow(m_leftMotor);
//     m_rightFollowMotor.follow(m_rightMotor);
//     //invert one of the sides (arbitrarily chosen)
//     m_leftMotor.setInverted(true);
//     m_rightMotor.setInverted(false);
//     //and then make the follow motor follow the invert
//     m_leftFollowMotor.setInverted(InvertType.FollowMaster);
//     m_rightFollowMotor.setInverted(InvertType.FollowMaster);
//   }

//   public void tankDrive(double l, double r) {
//     l *= speedMultiplier;
//     r *= speedMultiplier;
//     m_leftMotor.set(ControlMode.PercentOutput, l);
//     m_rightMotor.set(ControlMode.PercentOutput, r);
//   }

//   public void arcadeDrive(double pow, double turn) {
//     pow *= speedMultiplier;
//     turn *= speedMultiplier;
//     m_leftMotor.set(ControlMode.PercentOutput, pow - turn);
//     m_rightMotor.set(ControlMode.PercentOutput, pow + turn);
//   }

//   public void setSpeedMultiplier(double speed, boolean updateNT) {
//     if (0 <= speed && speed <= 2) {
//       speedMultiplier = speed;
//       if(updateNT){
//         System.out.println("Putted Speed Multiplier NT entry");
//         speedEntry.setDouble(speedMultiplier);
//       }
//     } else {
//       System.out.println("Putted Speed Multiplier NT entry");
//       speedEntry.setDouble(speedMultiplier);
//     }
//   }

//   @Override
//   public void periodic() {
//     if(speedEntry==null){
//       speedEntry = tab.addPersistent("Speed Multiplier", 1).getEntry();
//       System.out.println("Added Speed Multiplier NT entry");
//     }
//     setSpeedMultiplier(speedEntry.getDouble(1.0), false);
//   }
// }

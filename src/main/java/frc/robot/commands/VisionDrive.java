package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionControlConstants;
import frc.robot.subsystems.TalonFXDriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.pidcontroller.ProfiledDriveDistancePID;
import frc.robot.subsystems.pidcontroller.ProfiledDriveRotationPID;

public class VisionDrive extends CommandBase {

    private final TalonFXDriveSubsystem m_drive;
    private final VisionSubsystem m_data;

    public VisionDrive(VisionSubsystem visionSubsystem, TalonFXDriveSubsystem drive) {
        m_data = visionSubsystem;
        m_drive = drive;
        addRequirements(m_data);
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        boolean rotFlag = false;
        boolean distFlag = false;
        boolean exit = false;
        if (!m_data.possibleShootingPos()) {
            SmartDashboard.putString("Vision Info", "Not possible to autonomous at current position");
        } else {
            double dist = m_data.getDistanceFromTarget();
            double rot = m_data.getRotationDeficit();
            // if (rot > VisionControlConstants.angleDeadzone) {
            // rot += VisionControlConstants.minForce;
            // } else if (rot < VisionControlConstants.angleDeadzone) {
            // rot -= VisionControlConstants.minForce;
            // }
            // if (vAngle > VisionControlConstants.distanceDeadzone) {
            // dist += VisionControlConstants.minForce;
            // } else if (vAngle < VisionControlConstants.distanceDeadzone) {
            // dist -= VisionControlConstants.minForce;
            // }
            if (m_drive.getEncoderRight() != dist) {
                distFlag = false;
                m_drive.motionMagicDrive(dist);
            } else {
                m_drive.disable();
                distFlag = true;
            }
            if (m_drive.getEncoderLeft() != rot && m_drive.getEncoderRight() != rot) {
                rotFlag = false;
                m_drive.motionMagicTurn(rot);
            } else {
                m_drive.disable();
                rotFlag = true;
            }
            SmartDashboard.putString("Vision Info", "Position goal: " + dist + " Rotation: " + rot);
        }
        // double rotDeficit = 0;
        // double distDeficit = 0;

    }

    @Override
    public void end(boolean interrupted) {
        m_drive.disable();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionControlConstants;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.pidcontroller.ProfiledDriveDistancePID;
import frc.robot.subsystems.pidcontroller.ProfiledDriveRotationPID;

public class VisionDrive extends CommandBase {

    private final ProfiledDriveRotationPID m_rotationPID;
    private final ProfiledDriveDistancePID m_distancePID;
    private final VisionSubsystem m_data;

    public VisionDrive(VisionSubsystem visionSubsystem, ProfiledDriveRotationPID rotationPID,
            ProfiledDriveDistancePID distancePID) {
        m_data = visionSubsystem;
        m_rotationPID = rotationPID;
        m_distancePID = distancePID;
        addRequirements(m_data);
        addRequirements(m_rotationPID);
        addRequirements(m_distancePID);
    }

    @Override
    public void execute() {
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
            if (!m_rotationPID.isEnabled()) {
                m_rotationPID.enable();
                m_rotationPID.setGoal(rot);
            }
            if (m_rotationPID.atSetpoint() && !m_distancePID.isEnabled()) {
                m_distancePID.enable();
                m_distancePID.setGoal(dist);
            }
            SmartDashboard.putString("Vision Info", "Position goal: " + dist + " Rotation: " + rot);
        }
        // double rotDeficit = 0;
        // double distDeficit = 0;

    }

    @Override
    public void end(boolean interrupted) {
        m_rotationPID.disable();

        m_distancePID.disable();

    }

    @Override
    public boolean isFinished() {
        return m_rotationPID.atSetpoint() && m_distancePID.atSetpoint();
    }
}
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionControlConstants;
import frc.robot.subsystems.pidcontroller.VisionProfiledPIDDistance;
import frc.robot.subsystems.pidcontroller.VisionProfiledPIDRotation;

public class VisionDrive extends CommandBase {

    private NetworkTable table;

    // debug
    private final double speedMultiplier = 0.2;
    private NetworkTableEntry hAngleEntry;
    private NetworkTableEntry vAngleEntry;

    private final VisionProfiledPIDRotation m_rotationPID;
    private final VisionProfiledPIDDistance m_distancePID;

    public VisionDrive(VisionProfiledPIDRotation rotationPID, VisionProfiledPIDDistance distancePID) {
        m_rotationPID = rotationPID;
        m_distancePID = distancePID;
        table = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("vision");
        hAngleEntry = table.getEntry("targetyaw");
        vAngleEntry = table.getEntry("pitch");
        addRequirements(m_rotationPID);
        addRequirements(m_distancePID);
    }

    @Override
    public void execute() {
        double rotDeficit = 0;
        double distDeficit = 0;
        double hAngle = hAngleEntry.getDouble(0); // horizontal rotation
        double vAngle = vAngleEntry.getDouble(0); // vertical angle. Convert this to distance pls
        if (hAngle > VisionControlConstants.angleDeadzone) {
            rotDeficit += VisionControlConstants.minForce;
        } else if (hAngle < VisionControlConstants.angleDeadzone) {
            rotDeficit -= VisionControlConstants.minForce;
        }
        if (vAngle > VisionControlConstants.distanceDeadzone) {
            distDeficit += VisionControlConstants.minForce;
        } else if (vAngle < VisionControlConstants.distanceDeadzone) {
            distDeficit -= VisionControlConstants.minForce;
        }
        m_rotationPID.setGoal(rotDeficit);
        // TODO: Calculate where to shoot
        m_distancePID.setGoal(distDeficit);
        SmartDashboard.putString("Vision Info", "Speed: " + distDeficit + " Rotation: " + rotDeficit);

    }

    @Override
    public void end(boolean interrupted) {
        m_rotationPID.setGoal(0);
        m_distancePID.setGoal(0);

    }

    @Override
    public boolean isFinished() {
        return m_rotationPID.atSetpoint() && m_distancePID.atSetpoint();
    }
}
package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionControlConstants;
import frc.robot.subsystems.DriveSubsystem;

public class VisionDrive extends CommandBase {

    private NetworkTable table;

    private final DriveSubsystem m_driveSubsystem;
    // debug
    private final double speedMultiplier = 0.2;
    private NetworkTableEntry hAngleEntry;
    private NetworkTableEntry vAngleEntry;

    public VisionDrive(DriveSubsystem driveSubsystem) {
        m_driveSubsystem = driveSubsystem;
        table = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("vision");
        hAngleEntry = table.getEntry("yaw");
        vAngleEntry = table.getEntry("pitch");
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        double rotationChange = 0;
        double distanceChange = 0;
        double hAngle = hAngleEntry.getDouble(0); // horizontal rotation
        double vAngle = vAngleEntry.getDouble(0); // distance
        if (hAngle > VisionControlConstants.angleDeadzone) {
            rotationChange = VisionControlConstants.KpRot * hAngle + VisionControlConstants.minForce;
        } else if (hAngle < VisionControlConstants.angleDeadzone) {
            rotationChange = VisionControlConstants.KpRot * hAngle - VisionControlConstants.minForce;
        }
        if (vAngle > VisionControlConstants.distanceDeadzone) {
            distanceChange = VisionControlConstants.KpDist * vAngle + VisionControlConstants.minForce;
        } else if (vAngle < VisionControlConstants.distanceDeadzone) {
            distanceChange = VisionControlConstants.KpDist * vAngle - VisionControlConstants.minForce;
        }

        m_driveSubsystem.arcadeDrive(distanceChange * speedMultiplier, rotationChange);
        SmartDashboard.putString("Vision Info",
                "Speed: " + distanceChange * speedMultiplier + " Rotation: " + rotationChange);

    }

    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
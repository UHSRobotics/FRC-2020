package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.VisionControlConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.pidcontroller.VisionPIDLeft;
import frc.robot.subsystems.pidcontroller.VisionPIDRight;

public class VisionDrive extends CommandBase {

    private NetworkTable table;

    // debug
    private final double speedMultiplier = 0.2;
    private NetworkTableEntry hAngleEntry;
    private NetworkTableEntry vAngleEntry;

    private final VisionPIDLeft m_visionPIDLeft;
    private final VisionPIDRight m_visionPIDRight;

    public VisionDrive(VisionPIDLeft visionPIDLeft, VisionPIDRight visionPIDRight) {
        m_visionPIDLeft = visionPIDLeft;
        m_visionPIDRight = visionPIDRight;
        table = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("vision");
        hAngleEntry = table.getEntry("yaw");
        vAngleEntry = table.getEntry("pitch");
        addRequirements(visionPIDLeft);
        addRequirements(visionPIDRight);
    }

    @Override
    public void execute() {
        double rotationChange = 0;
        double distanceChange = 0;
        double hAngle = hAngleEntry.getDouble(0); // horizontal rotation
        double vAngle = vAngleEntry.getDouble(0); // distance
        if (hAngle > VisionControlConstants.angleDeadzone) {
            rotationChange += VisionControlConstants.minForce;
        } else if (hAngle < VisionControlConstants.angleDeadzone) {
            rotationChange -= VisionControlConstants.minForce;
        }
        if (vAngle > VisionControlConstants.distanceDeadzone) {
            distanceChange += VisionControlConstants.minForce;
        } else if (vAngle < VisionControlConstants.distanceDeadzone) {
            distanceChange -= VisionControlConstants.minForce;
        }

        m_visionPIDLeft.setSetpoint(distanceChange - rotationChange);
        m_visionPIDRight.setSetpoint(distanceChange + rotationChange);
        SmartDashboard.putString("Vision Info",
                "Speed: " + distanceChange * speedMultiplier + " Rotation: " + rotationChange);

    }

    @Override
    public void end(boolean interrupted) {
        m_visionPIDLeft.setSetpoint(0);
        m_visionPIDRight.setSetpoint(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class VisionDriveTest extends CommandBase {

    private NetworkTable table;

    private final DriveSubsystem m_driveSubsystem;
    private BooleanSupplier m_supplier;
    private NetworkTableEntry hAngleEntry;
    private NetworkTableEntry vAngleEntry;

    public VisionDriveTest(DriveSubsystem driveSubsystem, BooleanSupplier supplier) {
        m_driveSubsystem = driveSubsystem;
        m_supplier = supplier;
        table = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("cam1");
        hAngleEntry = table.getEntry("yaw");
        vAngleEntry = table.getEntry("pitch");
    }

    @Override
    public void execute() {
        double hAngle = hAngleEntry.getDouble(0);
        double vAngle = vAngleEntry.getDouble(0);

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
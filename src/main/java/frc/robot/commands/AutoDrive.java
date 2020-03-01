package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.NeoFwSubsystem;
import frc.robot.subsystems.TalonFXDriveSubsystem;

public class AutoDrive extends CommandBase {
    private final TalonFXDriveSubsystem m_drive;
    private final NeoFwSubsystem m_fw;
    private final HopperSubsystem m_hop;
    boolean finished = false;

    public AutoDrive(final TalonFXDriveSubsystem drive, NeoFwSubsystem fw, HopperSubsystem hop) {
        m_drive = drive;
        m_fw = fw;
        m_hop = hop;
        addRequirements(m_drive);
        addRequirements(m_fw);
        addRequirements(m_hop);

    }

    @Override
    public void execute(){
        // m_fw.setSpeed(1);
        // Timer.delay(2);
        // m_hop.switchON(1);
        // Timer.delay(5);
        m_fw.setSpeed(0);
        m_hop.switchOFF();
        m_drive.arcadeDrive(0.4, 0);
        Timer.delay(0.5);
        m_drive.arcadeDrive(0, 0);
        finished = true;



    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void end(boolean b) {
    }
}
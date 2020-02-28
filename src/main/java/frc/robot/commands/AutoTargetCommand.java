package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.pidcommand.DistancePIDCommand;
import frc.robot.commands.pidcommand.RotationPIDCommand;
import frc.robot.subsystems.TalonFXDriveSubsystem;
public class AutoTargetCommand extends SequentialCommandGroup {
    public AutoTargetCommand(double dis, double rot, TalonFXDriveSubsystem drive){
        addCommands(new RotationPIDCommand(drive, rot), new DistancePIDCommand(drive, dis));
    }
    
}
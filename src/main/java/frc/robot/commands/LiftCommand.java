package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.WinchServoSubsystem;
import frc.robot.subsystems.pidcontroller.LiftPID;

public class LiftCommand extends CommandBase {
    private final LiftSubsystem m_lift;
    private final WinchServoSubsystem m_winch;
    private final BooleanSupplier m_left, m_right, m_sensor;
    // private final BooleanSupplier m_pidToggle;
    // private final LiftPID m_liftPID;

    // public LiftCommand(LiftSubsystem lift, LiftPID liftPID, BooleanSupplier data, BooleanSupplier usePID) {
    //     m_lift = lift;
    //     m_data = data;
    //     m_liftPID = liftPID;
    //     m_pidToggle = usePID;
    //     m_sensor = null;
    //     addRequirements(m_lift);
    //     addRequirements(m_liftPID);
    // }
    
    public LiftCommand(LiftSubsystem lift, WinchServoSubsystem winch,
     BooleanSupplier left, BooleanSupplier right, BooleanSupplier magSensor){
        m_lift = lift;
        m_winch = winch;
        m_left = left;
        m_right = right;
        m_sensor = magSensor;
        addRequirements(m_lift);
        addRequirements(m_winch);
    }

    @Override
    public void execute() {
        if(m_left.getAsBoolean() && m_sensor.getAsBoolean()){
            if(!WinchServoSubsystem.toggleOn){
                m_winch.toggle();
            }
            m_lift.setSpeed(1);
        } else if(m_left.getAsBoolean() && m_right.getAsBoolean()){
            if(!WinchServoSubsystem.toggleOn){
                m_winch.toggle();
            }
            m_lift.setSpeed(-1);
        }
        if(((!m_sensor.getAsBoolean()) && (!m_right.getAsBoolean())) || ((!m_left.getAsBoolean()) && (!m_right.getAsBoolean()))){
            if(WinchServoSubsystem.toggleOn){
                m_lift.setSpeed(0);
                m_winch.toggle();
            }
        }
        // if (m_data.getAsBoolean()) {
        //     m_lift.setSpeed(1);
        // } else {
        //     if (m_pidToggle.getAsBoolean() && !m_liftPID.isEnabled()) {
        //         m_liftPID.setSetpoint(1);
        //         m_liftPID.enable();
        //     } else if (!m_pidToggle.getAsBoolean()) {
        //         m_liftPID.disable();
        //         m_lift.setSpeed(0);
        //     }
        // }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;

    }
}
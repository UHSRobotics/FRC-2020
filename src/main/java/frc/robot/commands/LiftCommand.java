package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.pidcontroller.LiftPID;

public class LiftCommand extends CommandBase {
    private final LiftSubsystem m_lift;
    // private final ServoSubsystem m_winch;
    private final BooleanSupplier m_left;
    private final BooleanSupplier m_right;
    private final ServoSubsystem m_servo;

    // private final BooleanSupplier m_pidToggle;
    // private final LiftPID m_liftPID;

    // public LiftCommand(LiftSubsystem lift, LiftPID liftPID, BooleanSupplier data,
    // BooleanSupplier usePID) {
    // m_lift = lift;
    // m_data = data;
    // m_liftPID = liftPID;
    // m_pidToggle = usePID;
    // m_sensor = null;
    // addRequirements(m_lift);
    // addRequirements(m_liftPID);
    // }

    public LiftCommand(LiftSubsystem lift, BooleanSupplier left, BooleanSupplier right, ServoSubsystem servo) {
        m_lift = lift;
        // m_winch = winch;
        m_left = left;
        m_right = right;
        m_servo = servo;

        // m_sensor = magSensor;
        addRequirements(m_lift);
        // addRequirements(m_winch);
    }

    @Override
    public void execute() {
        if (m_left.getAsBoolean()&&!m_servo.getToggle()) {
            // if (!ServoSubsystem.toggleOn) {
            //     m_winch.toggle();
            //     Timer.delay(0.5);
            // }
            m_lift.setSpeed(1);
            m_lift.initialized();

        } 
        else if(m_right.getAsBoolean()&&m_lift.getInit()){
            m_lift.setSpeed(-1);
            
        }
        else 
        {
            m_lift.setSpeed(0);
        }
        // else if (!m_left.getAsBoolean() && m_right.getAsBoolean()) {
        //     if (!ServoSubsystem.toggleOn) {
        //         m_winch.toggle();
        //         Timer.delay(0.5);
        //     }
        //     m_lift.setSpeed(-1);
        // } else {
        //     if (ServoSubsystem.toggleOn) {
        //         m_lift.setSpeed(0);
        //         Timer.delay(0.5);
        //         m_winch.toggle();
        
        //     }
        // }
        // if (m_data.getAsBoolean()) {
        // m_lift.setSpeed(1);
        // } else {F
        // if (m_pidToggle.getAsBoolean() && !m_liftPID.isEnabled()) {
        // m_liftPID.setSetpoint(1);
        // m_liftPID.enable();
        // } else if (!m_pidToggle.getAsBoolean()) {
        // m_liftPID.disable();
        // m_lift.setSpeed(0);
        // }
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
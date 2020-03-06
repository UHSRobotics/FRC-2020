package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ServoSubsystem;

public class LiftCommand extends CommandBase {
    private final LiftSubsystem m_lift;
    // private final ServoSubsystem m_winch;
    private final BooleanSupplier m_up;
    private final BooleanSupplier m_down;
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
    private int servoDelay = 0; //1 = 20ms

    public LiftCommand(LiftSubsystem lift, BooleanSupplier left, BooleanSupplier right, ServoSubsystem servo) {
        m_lift = lift;
        // m_winch = winch;
        m_up = left;
        m_down = right;
        m_servo = servo;

        // m_sensor = magSensor;
        addRequirements(m_lift);
        // addRequirements(m_winch);
    }

    @Override
    public void execute() {
        if(servoDelay>0)servoDelay--;
        if(servoDelay<0)servoDelay++;
        else if (m_up.getAsBoolean()) {
            //if ratchet engaged, disengage it
            if (m_servo.getToggle()) {
                m_servo.toggle();
                servoDelay = 50;
            }
            if(servoDelay==0){
                m_lift.setTarget(1);
                m_lift.initialize();
            }
        } else if (m_down.getAsBoolean() && m_lift.getInit()) {
            //if ratchet disengage, engage it
            if (!m_servo.getToggle()) {
                m_servo.toggle();
                servoDelay = -20;
            }
            if(servoDelay == 0)
                m_lift.setTarget(-1);
        } else {
            m_lift.setTarget(0);
        }
        if(servoDelay > 1){
            m_lift.setSpeed(0);  
        }
        // else if (!m_left.getAsBoolean() && m_right.getAsBoolean()) {
        // if (!ServoSubsystem.toggleOn) {
        // m_winch.toggle();
        // Timer.delay(0.5);
        // }
        // m_lift.setSpeed(-1);
        // } else {
        // if (ServoSubsystem.toggleOn) {
        // m_lift.setSpeed(0);
        // Timer.delay(0.5);
        // m_winch.toggle();

        // }
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
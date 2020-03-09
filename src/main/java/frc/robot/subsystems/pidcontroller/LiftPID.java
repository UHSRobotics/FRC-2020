package frc.robot.subsystems.pidcontroller;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.LiftConstants;
import frc.robot.Constants.Ports;
// import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LiftSubsystem;

public class LiftPID extends PIDSubsystem {
    private final LiftSubsystem m_lift;
    private double p = LiftConstants.Kp,i = LiftConstants.Ki,d = LiftConstants.Kd;
    private double lastSetpoint = 0;
    public LiftPID(LiftSubsystem lift) {
        super(new PIDController(LiftConstants.Kp, LiftConstants.Ki, LiftConstants.Kd), 0);
        m_lift = lift;
        SmartDashboard.putNumber("kp", p);
        SmartDashboard.putNumber("ki", i);
        SmartDashboard.putNumber("kd", d);
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        m_lift.setVelTarget(output);
        if(p!=SmartDashboard.getNumber("kp", 0.5)){
            p=SmartDashboard.getNumber("kp", 0.5);
            getController().setP(p);
        }
        if(i!=SmartDashboard.getNumber("ki", 0.5)){
            i=SmartDashboard.getNumber("ki", 0.5);
            getController().setI(i);
        }
        if(d!=SmartDashboard.getNumber("kd", 0.5)){
            d=SmartDashboard.getNumber("kd", 0.5);
            getController().setD(d);
        }
    }

    @Override
    protected double getMeasurement() {
        return m_lift.getEncoderVelocity();
    }

    public void setTarget(double setpoint){
        if(setpoint!=lastSetpoint)getController().reset();
        setSetpoint(setpoint);
    }

}
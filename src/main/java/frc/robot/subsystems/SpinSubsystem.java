package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SpinSubsystem extends SubsystemBase {
    private final TalonSRX m_spinner = new TalonSRX(2);

    public void spin(double pow) {
        SmartDashboard.putNumber("Spinner Speed", pow);
        m_spinner.set(ControlMode.PercentOutput, pow);
    }
}
// package frc.robot.subsystems.pidcontroller;

// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.CounterBase.EncodingType;
// import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj2.command.PIDSubsystem;
// import frc.robot.Constants.VisionControlConstants;
// import frc.robot.subsystems.DriveSubsystem;

// public class DriveRotationPID extends PIDSubsystem {
//     private final DriveSubsystem m_driveSubsystem;
//     private final Encoder m_leftEncoder;
//     private final Encoder m_rightEncoder;

//     public DriveRotationPID() {
//         super(new PIDController(VisionControlConstants.KpRot, VisionControlConstants.KiRot,
//                 VisionControlConstants.KdRot), 0);
//         m_driveSubsystem = new DriveSubsystem();
//         m_leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
//         m_rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
//         m_leftEncoder.setDistancePerPulse(0.001);
//         m_rightEncoder.setDistancePerPulse(0.001);
//     }

//     @Override
//     protected void useOutput(double output, double setpoint) {
//         m_driveSubsystem.arcadeDrive(0, output);
//     }

//     @Override
//     protected double getMeasurement() {
//         return m_leftEncoder.getDistance();
//     }

//     public boolean atSetpoint() {
//         return m_controller.atSetpoint();
//     }
// }
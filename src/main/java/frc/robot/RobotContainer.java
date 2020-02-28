package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.DualShockController.Button;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.TargetCommand;
import frc.robot.commands.AutonPlaceholder;
import frc.robot.commands.DropIntakeCommand;
import frc.robot.commands.FlywheelCmd;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PIDDrive;
import frc.robot.commands.pidcommand.*;
// import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DropIntakeSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeoFwSubsystem;
import frc.robot.subsystems.TalonFXDriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.WinchServoSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // private final FlywheelSubsystem m_flywheelSubsystem = new FlywheelSubsystem();
  private final NeoFwSubsystem m_neoFwSubsystem = new NeoFwSubsystem();
  private final DigitalInput m_magSwitch = new DigitalInput(3);

  // private final TalonFXDriveSubsystem m_driveSubsystem = new TalonFXDriveSubsystem();

  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  // private final DropIntakeSubsystem m_DropIntakeSubsystem = new DropIntakeSubsystem();
  private final WinchServoSubsystem m_servoSubsystem = new WinchServoSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();

  // private final SolenoidTestSubsystem m_solenoidTestSubsystem = new
  // SolenoidTestSubsystem();
  // private final ColorSubsystem m_colorSubsystem = new ColorSubsystem();
  // private final SpinSubsystem m_spinSubsystem = new SpinSubsystem();

  // private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // placeholder command for autonomous
  // private final Command m_simpleAutoCommand = new DistancePIDCommand(m_driveSubsystem, 1);
  // private final AutonPlaceholder m_autonPlaceholder = new AutonPlaceholder(m_driveSubsystem);
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  // Main Controller
  DualShockController m_driverController = new DualShockController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_driverController.initMapping(OIConstants.kDriverControllerCurvature);
    configureButtonBindings();
    // m_flywheelSubsystem.setDefaultCommand(new
    // DoubleFWheelCmd(m_flywheelSubsystem,
    // () -> m_driverController.getY(Hand.kLeft), () ->
    // m_driverController.getY(Hand.kRight)));
    // m_flywheelSubsystem.setDefaultCommand(new FlywheelCmd(m_flywheelSubsystem,
    // () -> m_driverController.getY(Hand.kLeft), () ->
    // m_driverController.getCrossButton()))

    m_neoFwSubsystem.setDefaultCommand(new FlywheelCmd(m_neoFwSubsystem,
    () -> m_driverController.getCrossButton()));

    // ManualDrive
    // m_driveSubsystem.setDefaultCommand(new ArcadeDrive(m_driveSubsystem,
    // () -> m_driverController.getYMapped(Hand.kLeft), () ->
    // m_driverController.getXMapped(Hand.kRight) * 0.75, () ->
    // m_driverController.getCrossButton()));
    // m_chooser.setDefaultOption("target", m_autonPlaceholder);
    // m_chooser.addOption("simple drive", m_simpleAutoCommand);
    Shuffleboard.getTab("Autonomous").add(m_chooser);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_driverController, Button.kTrig.value)
         .whileHeld(new IntakeCommand(m_IntakeSubsystem, () -> m_driverController.getTrigButtonPressed()));

    new JoystickButton(m_driverController, Button.kDisk.value)
        .whenPressed(new InstantCommand(m_servoSubsystem::toggle, m_servoSubsystem));
    // new JoystickButton(m_driverController, Button.kCross.value)
    //     .whenPressed(new VisionRotationPIDCommand(m_driveSubsystem, m_visionSubsystem));
    // new JoystickButton(m_driverController, Button.kRect.value)
    //     .whenPressed(new VisionDistancePIDCommand(m_driveSubsystem, m_visionSubsystem));
    
    // new JoystickButton(m_driverController, Button.kBumperLeft.value)
    //     .whileHeld(new DropIntakeCommand(m_DropIntakeSubsystem, 
    //     () -> m_driverController.getBumperPressed(Hand.kLeft), 
    //     () -> m_driverController.getBumperPressed(Hand.kRight) ));
    // new JoystickButton(m_driverController, Button.kBumperRight.value)
    //     .whenPressed(new AutoTargetCommand(m_visionSubsystem.getDistanceFromTarget(), m_visionSubsystem.getHorizontalAngle(), m_driveSubsystem));
    // new JoystickButton(m_driverController, Button.kCross.value)
    //     .whenPressed(new VisionRotationPIDCommand(m_driveSubsystem, m_visionSubsystem));
    // new JoystickButton(m_driverController, Button.kRect.value)
    //     .whenPressed(new VisionDistancePIDCommand(m_driveSubsystem, m_visionSubsystem));
    //
    // new JoystickButton(m_driverController, Button.kTrig.value)
    // .whenPressed(new PIDDrive(m_driveSubsystem, () ->
    // m_driverController.getTrigButtonPressed(), 4000 * 5));

    // new JoystickButton(m_driverController,
    // Button.kBumperRight.value).whenPressed(new VisionDrive(m_driveSubsystem));

    // new JoystickButton(m_driverController, Button.kBumperLeft.value)
    // .whenPressed(new SpinCommand(m_colorSubsystem, m_spinSubsystem, -1));
    // new JoystickButton(m_driverController, Button.kRect.value)
    // .whenPressed(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 0));
    // new JoystickButton(m_driverController, Button.kCross.value)
    // .whenPressed(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 1));
    // new JoystickButton(m_driverController, Button.kDisk.value)
    // .whenPressed(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 2));
    // new JoystickButton(m_driverController, Button.kTrig.value)
    // .whenPressed(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 3));

    // new JoystickButton(m_driverController,
    // Button.kDisk.value).toggleWhenPressed(new TankDrive(m_driveSubsystem,
    // () -> m_driverController.getYMapped(Hand.kLeft), () ->
    // m_driverController.getYMapped(Hand.kRight)));

    // new JoystickButton(m_driverController, Button.kTrig.value)
    // .whenPressed(new InstantCommand(m_solenoidTestSubsystem::solenoidForward,
    // m_solenoidTestSubsystem));
    // new JoystickButton(m_driverController, Button.kCross.value)
    // .whenPressed(new InstantCommand(m_solenoidTestSubsystem::solenoidBack,
    // m_solenoidTestSubsystem));
    // new JoystickButton(m_driverController, Button.kRect.value)
    // .whenPressed(new InstantCommand(m_solenoidTestSubsystem::solenoidOff,
    // m_solenoidTestSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * 
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}

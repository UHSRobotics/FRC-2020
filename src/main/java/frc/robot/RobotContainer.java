package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.Constants.OIConstants;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import static frc.robot.DualShockController.Button;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  /*
   * private final FlywheelSubsystem m_flywheelSubsystem = new
   * FlywheelSubsystem(); private final ColorSubsystem m_colorSubsystem = new
   * ColorSubsystem(); private final SpinSubsystem m_spinSubsystem = new
   * SpinSubsystem();
   */
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // placeholder command for autonomous
  private final Command m_autoCommand = new AutonPlaceholder();

  // Main Controller
  DualShockController m_driverController = new DualShockController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
    // m_flywheelSubsystem.setDefaultCommand(new
    // FlywheelDebugCommand(m_flywheelSubsystem,
    // () -> m_driverController.getY(Hand.kLeft), () ->
    // m_driverController.getY(Hand.kRight)));

    m_driveSubsystem.setDefaultCommand(new ArcadeDrive(m_driveSubsystem, () -> m_driverController.getY(Hand.kLeft),
        () -> m_driverController.getX(Hand.kRight)));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*
     * new JoystickButton(m_driverController, Button.kBumperLeft.value)
     * .whenPressed(new SpinCommand(m_colorSubsystem, m_spinSubsystem, -1)); new
     * JoystickButton(m_driverController, Button.kRect.value) .whenPressed(new
     * SpinCommand(m_colorSubsystem, m_spinSubsystem, 0)); new
     * JoystickButton(m_driverController, Button.kCross.value) .whenPressed(new
     * SpinCommand(m_colorSubsystem, m_spinSubsystem, 1)); new
     * JoystickButton(m_driverController, Button.kDisk.value) .whenPressed(new
     * SpinCommand(m_colorSubsystem, m_spinSubsystem, 2)); new
     * JoystickButton(m_driverController, Button.kTrig.value) .whenPressed(new
     * SpinCommand(m_colorSubsystem, m_spinSubsystem, 3));
     */

    /*
     * new JoystickButton(m_testController, 1).whenPressed(() -> {
     * System.out.println("Rumbled controller");
     * m_testController.setRumble(RumbleType.kLeftRumble, 1);
     * m_testController.setRumble(RumbleType.kRightRumble, 1); }); new
     * JoystickButton(m_testController, 1).whenReleased(() -> {
     * System.out.println("Stopped Rumbling");
     * m_testController.setRumble(RumbleType.kLeftRumble, 0);
     * m_testController.setRumble(RumbleType.kRightRumble, 0); });
     */
    new JoystickButton(m_driverController, Button.kDisk.value).toggleWhenPressed(new ArcadeDrive(m_driveSubsystem,
        () -> m_driverController.getY(Hand.kLeft), () -> m_driverController.getY(Hand.kRight)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * 
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}

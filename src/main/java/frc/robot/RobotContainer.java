package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.Constants.OIConstants;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import static edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final FlywheelSubsystem m_robotDrive = new FlywheelSubsystem();
  private final ColorSubsystem m_colorSubsystem = new ColorSubsystem();
  private final SpinSubsystem m_spinSubsystem = new SpinSubsystem();

  // placeholder command for autonomous
  private final Command m_autoCommand = new AutonPlaceholder();

  // Main Controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_robotDrive.setDefaultCommand(
        new FlywheelDebugCommand(
            m_robotDrive,
            () -> m_driverController.getY(GenericHID.Hand.kLeft),
            () -> m_driverController.getY(GenericHID.Hand.kRight)));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kBumperLeft.value)
        .whenHeld(new SpinCommand(m_colorSubsystem, m_spinSubsystem, -1));
    new JoystickButton(m_driverController, Button.kA.value)
        .whenHeld(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 1));
    new JoystickButton(m_driverController, Button.kB.value)
        .whenHeld(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 2));
    new JoystickButton(m_driverController, Button.kX.value)
        .whenHeld(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 0));
    new JoystickButton(m_driverController, Button.kY.value)
        .whenHeld(new SpinCommand(m_colorSubsystem, m_spinSubsystem, 3));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}

//idk why but it didn't update properly

// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import java.util.function.DoubleSupplier;

// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.music.Orchestra;

// import edu.wpi.first.wpilibj2.command.CommandBase;

// import frc.robot.subsystems.FlywheelSubsystem;

// /**
//  * A command to drive the robot with joystick input (passed in as
//  * {@link DoubleSupplier}s). Written explicitly for pedagogical purposes -
//  * actual code should inline a command this simple with
//  * {@link edu.wpi.first.wpilibj2.command.RunCommand}.
//  */
// public class Cursed_command extends CommandBase {
//     private final Orchestra m_player;
//     private final TalonFX track0 = new TalonFX(0);
//     private final TalonFX track1 = new TalonFX(1);
//     private final TalonFX track2 = new TalonFX(2);
//     private final TalonFX track3 = new TalonFX(3);

//     public Cursed_command() {
//         m_player = new Orchestra();
//         m_player.loadMusic("/src/main/deploy/megalovania.chrp");
//         m_player.addInstrument(track0);
//         m_player.addInstrument(track1);
//         m_player.addInstrument(track2);
//         m_player.addInstrument(track3);
//         m_player.play();
//     }

//     @Override
//     public void execute() {
//     }

//     @Override
//     public void end(boolean interrupted) {
//         m_player.stop();
//     }

//     @Override
//     public boolean isFinished() {
//         return false;
//     }
// }

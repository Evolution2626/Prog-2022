// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignTapeCommand;
import frc.robot.commands.AlignTapePIDCommand;
import frc.robot.commands.AvancerCmCommand;
import frc.robot.commands.DrivetrainDriveCommand;
import frc.robot.commands.GobeurTournerCommand;
import frc.robot.commands.SetPistonCommand;
import frc.robot.commands.TournerWinchsCommand;
import frc.robot.commands.TrajectoryTestCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Limelight;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Drivetrain drivetrain = new Drivetrain();
  private final Gobeur gobeur = new Gobeur();
  private final Climber climber = new Climber();
  private final Limelight limelight = new Limelight();
  private final XboxController driverController = new XboxController(Constants.USB.DRIVER_CONTROLLER);
  private final XboxController coDriverController = new XboxController(Constants.USB.CO_DRIVER_CONTROLLER);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    drivetrain.setDefaultCommand(new DrivetrainDriveCommand(drivetrain, driverController));
    gobeur.setDefaultCommand(new GobeurTournerCommand(gobeur, coDriverController));
    climber.setDefaultCommand(new TournerWinchsCommand(climber, coDriverController));
    configureButtonBindings();
  
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(driverController, Button.kA.value).whenPressed(new AvancerCmCommand(drivetrain, 200));
    new JoystickButton(coDriverController, Button.kRightBumper.value).whenPressed(new SetPistonCommand(climber, Value.kForward));
    new JoystickButton(coDriverController, Button.kLeftBumper.value).whenPressed(new SetPistonCommand(climber, Value.kReverse));
    new JoystickButton(driverController, Button.kB.value).whenPressed(new TrajectoryTestCommand(drivetrain).fieldRelative());
    new JoystickButton(driverController, Button.kX.value).whileHeld(new AlignTapePIDCommand(drivetrain, limelight));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}

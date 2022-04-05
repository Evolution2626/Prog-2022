// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignTapePIDCommand;
import frc.robot.commands.AllerVersBallonCommand;
import frc.robot.commands.AvancerCmCommand;
import frc.robot.commands.DivetrainTournerDegresCommand;
import frc.robot.commands.DrivetrainDriveCommand;
import frc.robot.commands.GobeurTournerCommand;
import frc.robot.commands.ResetWinchPositionCommand;
import frc.robot.commands.SetPistonCommand;
import frc.robot.commands.SetPistonGobeurCommand;
import frc.robot.commands.ShooterBallCommand;
import frc.robot.commands.TournerWinchsCommand;
import frc.robot.commands.autonomous.AutoTestCommand;
import frc.robot.commands.autonomous.GoberUnBallonCommand;
import frc.robot.commands.autonomous.AutonomousCommand.StartingPosition;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;
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
  private final Lanceur lanceur = new Lanceur();
  private final XboxController driverController = new XboxController(Constants.USB.DRIVER_CONTROLLER);
  private final XboxController coDriverController = new XboxController(Constants.USB.CO_DRIVER_CONTROLLER);
  

  private SendableChooser<StartingPosition> startPosition = new SendableChooser<>();
  


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    drivetrain.setDefaultCommand(new DrivetrainDriveCommand(drivetrain, driverController));
    gobeur.setDefaultCommand(new GobeurTournerCommand(gobeur, coDriverController));
    climber.setDefaultCommand(new TournerWinchsCommand(climber, driverController));
    configureButtonBindings();

    startPosition.addOption("Left Panel", StartingPosition.NEXT_TO_LEFT_PANEL);
    startPosition.addOption("Right Panel", StartingPosition.NEXT_TO_RIGHT_PANEL);

    SmartDashboard.putData("Starting Position", startPosition);
  
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(driverController, Button.kX.value).whenPressed(new SetPistonCommand(climber, Value.kForward));
    new JoystickButton(driverController, Button.kY.value).whenPressed(new SetPistonCommand(climber, Value.kReverse));
    new JoystickButton(driverController, Button.kB.value).whenPressed(new ResetWinchPositionCommand(climber, 0));
    new JoystickButton(driverController, Button.kA.value).whenPressed(new ResetWinchPositionCommand(climber, 1));
    new JoystickButton(coDriverController, Button.kB.value).whenPressed(new SetPistonGobeurCommand(gobeur, Value.kForward));
    new JoystickButton(coDriverController, Button.kA.value).whenPressed(new SetPistonGobeurCommand(gobeur, Value.kReverse));
    
                  
                          

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new GoberUnBallonCommand(drivetrain, gobeur, startPosition.getSelected());
  }
}

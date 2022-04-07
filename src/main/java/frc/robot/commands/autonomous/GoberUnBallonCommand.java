// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.AvancerCmCommand;
import frc.robot.commands.GobeurTournerCommand;
import frc.robot.commands.SetPistonGobeurCommand;
import frc.robot.commands.TrajectoryCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gobeur;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GoberUnBallonCommand extends AutonomousCommand {

  private Drivetrain drivetrain;
  private Gobeur gobeur;
  private static String pathToLeftBall = "/output/pathToLeftBall.wpilib.json";
  private static String pathToRightBall = "/output/pathToRightBall.wpilib.json";

  /** Creates a new GoberUnBallonCommand. */
  public GoberUnBallonCommand(Drivetrain drivetrain, Gobeur gobeur, SendableChooser<StartingPosition> startPosition) {
    this.gobeur = gobeur;
    this.drivetrain = drivetrain;
    addRequirements(drivetrain, gobeur);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    if (startPosition.getSelected() == StartingPosition.NEXT_TO_RIGHT_PANEL) {
      addCommands(new TrajectoryCommand(drivetrain, pathToRightBall));
    } else {
      addCommands(new TrajectoryCommand(drivetrain, pathToLeftBall));
    }
    addCommands(new SetPistonGobeurCommand(gobeur, Value.kForward));
    addCommands(new ParallelRaceGroup(new GobeurTournerCommand(gobeur, 0.5), new AvancerCmCommand(drivetrain, 50)));
    addCommands(new SetPistonGobeurCommand(gobeur, Value.kReverse));
    addCommands(new AvancerCmCommand(drivetrain, 100));
    
  }
}

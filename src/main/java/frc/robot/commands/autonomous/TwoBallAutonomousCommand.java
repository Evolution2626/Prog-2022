// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AvancerCmCommand;
import frc.robot.commands.GobeurTournerCommand;
import frc.robot.commands.ShooterBallCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAutonomousCommand extends AutonomousCommand {
  /** Creates a new TwoBallAutonomousCommand. */

  private Drivetrain drivetrain;
  private Lanceur lanceur;
  private double distance;
  private Gobeur gobeur;

  

  public TwoBallAutonomousCommand(StartingPosition startPosition, Drivetrain drivetrain, Lanceur lanceur, Gobeur gobeur) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    this.drivetrain = drivetrain;
    this.lanceur = lanceur;
    this.gobeur = gobeur;

    addCommands(tournerLanceurWhileCommand(new SequentialCommandGroup(
      new AvancerCmCommand(drivetrain, -distance), 
      executeForSeconds(new GobeurTournerCommand(gobeur, 0.1), 3)  //placeholder seconds and speed
      ), distance) );
  }

  public ParallelRaceGroup tournerLanceurWhileCommand(Command command, double distance){
    return new ParallelRaceGroup(command, new ShooterBallCommand(lanceur, distance));
  }

  

  

}

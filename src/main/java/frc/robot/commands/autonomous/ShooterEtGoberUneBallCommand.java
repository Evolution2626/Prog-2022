// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AvancerCmCommand;
import frc.robot.commands.DrivetrainTournerDegresCommand;
import frc.robot.commands.LanceurSetSpeedCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShooterEtGoberUneBallCommand extends AutonomousCommand {
  /** Creates a new ShooterEtGoberUneBallCommand. */
  private Drivetrain drivetrain;
  private Gobeur gobeur;
  private Lanceur lanceur;

  /** Creates a new GoberUnBallonCommand. */
  public ShooterEtGoberUneBallCommand(Drivetrain drivetrain, Gobeur gobeur, Lanceur lanceur, SendableChooser<StartingPosition> startPosition) {
    this.gobeur = gobeur;
    this.drivetrain = drivetrain;
    this.lanceur = lanceur;
    addRequirements(drivetrain, gobeur);
  
    addCommands(
      executeForSeconds(new LanceurSetSpeedCommand(lanceur, 1), 5),
      new AvancerCmCommand(drivetrain, -50),
      new DrivetrainTournerDegresCommand(drivetrain, 180),
      new AvancerCmCommand(drivetrain, -60),
      new GoberUnBallonCommand(drivetrain, gobeur, startPosition)
    );
  }
}

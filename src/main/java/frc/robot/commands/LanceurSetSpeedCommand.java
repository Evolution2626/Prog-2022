// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lanceur;

public class LanceurSetSpeedCommand extends CommandBase {
  private Lanceur lanceur;
  private double speed;
  /** Creates a new LanceurSetSpeedCommand. */
  public LanceurSetSpeedCommand(Lanceur lanceur, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.lanceur = lanceur;
    this.speed = speed;
    addRequirements(lanceur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lanceur.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    lanceur.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

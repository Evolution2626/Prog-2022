// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Gobeur;

public class GobeurTournerCommand extends CommandBase {
    /**
     * Creates a new DrivetrainDriveCommand.
     */
    private Gobeur gobeur;
    private XboxController controller;
  /** Creates a new GobeurTournerCommand. */
  public GobeurTournerCommand(Gobeur gobeur, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controller = controller;
    this.gobeur = gobeur;
    addRequirements(gobeur);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speedLeft = controller.getLeftTriggerAxis();
    double speedRight = controller.getRightTriggerAxis();
    double speed = speedRight - speedLeft;

    gobeur.tournerGobeur(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;


  }
}

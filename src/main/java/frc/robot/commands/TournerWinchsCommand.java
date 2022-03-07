// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class TournerWinchsCommand extends CommandBase {

  private Climber climber;
  private XboxController controller;
  /** Creates a new TournerWinchSpringCommand. */
  public TournerWinchsCommand(Climber climber, XboxController controller) {
    this.climber = climber;
    this.controller = controller;
    addRequirements(climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speedSpring;
    speedSpring = controller.getLeftY();
    climber.tournerWinchSpring(speedSpring);
    double speedPiston;
    speedPiston = controller.getRightY();
    climber.tournerWinchPiston(speedPiston);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class ShooterBallonCommand extends CommandBase {

  private Drivetrain drivetrain;
  private Limelight limelight;
  private double verticalAngle;
  private double distantHorizantalToTarget;
  private final double targetHeight = 0; // meters
  private final double cameraHeight = 0;
  /** Creates a new ShooterBallonCommand. */
  public ShooterBallonCommand(Drivetrain drivetrain, Limelight limelight) {
    this.drivetrain = drivetrain;
    this.limelight = limelight;
    addRequirements(drivetrain, limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setPipeline(2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    verticalAngle = limelight.getdegVerticalToTarget();

    distantHorizantalToTarget = (targetHeight - cameraHeight) / Math.tan(verticalAngle);
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

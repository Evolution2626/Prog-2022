// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class AllerVersBallonCommand extends CommandBase {
  
  private Drivetrain drivetrain;
  private Limelight limelight;

  private PIDController anglePID;
  private PIDController distancePID;


  /** Creates a new AllerVersBallonCommand. */
  public AllerVersBallonCommand(Drivetrain drivetrain, Limelight limelight) {
    this.drivetrain = drivetrain;
    this.limelight = limelight;
    addRequirements(drivetrain, limelight);

    this.anglePID = new PIDController(0.05, 0.05, 0.002);
    this.distancePID = new PIDController(0.05, 0, 0);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    anglePID.reset();
    distancePID.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


      
    double rotation = anglePID.calculate(limelight.getdegRotationToTarget(), 0);
    double vitesse = distancePID.calculate(limelight.getdegVerticalToTarget(), -19);
    
    if(limelight.getdegRotationToTarget() == 0){
      vitesse = 0;
      rotation = 0;
    }   
    drivetrain.arcadeDrive(-vitesse, -rotation);

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

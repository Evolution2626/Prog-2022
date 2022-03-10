// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AlignTapePIDCommand extends PIDCommand {
  /** Creates a new AlignTapePIDCommand. */
  private Limelight limelight;
  private Drivetrain drivetrain;

  public AlignTapePIDCommand(Drivetrain drivetrain, Limelight limelight) {
    super(
        // The controller that the command will use
        new PIDController(0.05, 0.05, 0.025),
        // This should return the measurement
        () -> limelight.getdegRotationToTarget(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          drivetrain.arcadeDrive(-output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    this.drivetrain = drivetrain;
    this.limelight = limelight;
    addRequirements(drivetrain, limelight);
  }

  @Override
  public void initialize(){
    super.initialize();
    limelight.setPipeline(2);
  }
  @Override
  public void end(boolean interrupted){
    super.end(interrupted);
    drivetrain.arcadeDrive(0);  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

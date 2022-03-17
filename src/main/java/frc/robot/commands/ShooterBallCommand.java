// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Lanceur.TargetHeight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShooterBallCommand extends PIDCommand {
  /** Creates a new ShooterBallCommand. */
  public ShooterBallCommand(Lanceur lanceur, Limelight limelight) {
    super(
        // The controller that the command will use
        new PIDController(0, 0, 0),
        // This should return the measurement
        () -> lanceur.getEncodersVelocity(),
        // This should return the setpoint (can also be a constant)
        () -> lanceur.getLanceurDesiredSpeedRPM(limelight.getTargetDistance(1.524), TargetHeight.LOW),
        // This uses the output
        output -> {
          // Use the output here
          lanceur.setSpeed(output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

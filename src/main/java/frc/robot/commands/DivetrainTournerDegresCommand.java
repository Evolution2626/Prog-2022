// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import frc.util.Range;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DivetrainTournerDegresCommand extends PIDCommand {

  private Drivetrain drivetrain;
  /** Creates a new DivetrainTournerDegresCommand. */
  public DivetrainTournerDegresCommand(Drivetrain drivetrain, double degrees) {
    super(
        // The controller that the command will use
        new PIDController(0.012, 0.0001, 0.0001),
        // This should return the measurement
        () -> drivetrain.getGyroAngle(),
        // This should return the setpoint (can also be a constant)
        () -> degrees,
        // This uses the output
        output -> {
        
          drivetrain.arcadeDrive(0, Range.minCoerce(0.25,Range.coerce(0.60, output)));
          // Use the output here
        });

        this.drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

    addRequirements(drivetrain);

  }

  @Override
  public void initialize(){
    super.initialize();
    drivetrain.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().getPositionError() <= 0;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AvancerCmCommand extends PIDCommand {
  /** Creates a new AvancerCmCommand. */
  private Drivetrain drivetrain;
  public AvancerCmCommand(Drivetrain drivetrain, double distanceCibleCM) {
    super(
        // The controller that the command will use
        new PIDController(0.0015, 0.0005, 0.00000),
        // This should return the measurement
        () -> drivetrain.getBothEncoderPositionCm(),
        // This should return the setpoint (can also be a constant)
        () -> distanceCibleCM,
        // This uses the output
        output -> {
          // Use the output here
          
          drivetrain.driveTank(output, output);
        
        });
      
        this.drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(drivetrain);
  }

  @Override
  public void initialize(){
    super.initialize();
    drivetrain.resetEncoder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().getPositionError() <= 0;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.CARACTERISATION;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DrivetrainRamseteCommand extends RamseteCommand {
  private boolean resetPosition;
  private Drivetrain drivetrain;
  private Trajectory trajectory;

  public DrivetrainRamseteCommand(Drivetrain drivetrain, Trajectory trajectory) {
    super(trajectory, 
      drivetrain::getPose, 
      new RamseteController(CARACTERISATION.kRamseteB, CARACTERISATION.kRamseteZeta), 
      new SimpleMotorFeedforward(CARACTERISATION.ksVolts, CARACTERISATION.kvVoltsPerMeter, CARACTERISATION.kaVoltsSquarePerMeter), 
      CARACTERISATION.kDriveKinematics, 
      drivetrain::getWheelSpeed, 
      new PIDController(CARACTERISATION.kpDriveVelocity, 0, 0),
      new PIDController(CARACTERISATION.kpDriveVelocity, 0, 0),
      drivetrain::driveTankVolts,
      drivetrain);

      this.drivetrain = drivetrain;
      this.trajectory = trajectory;
      this.resetPosition = true;
  }

  public DrivetrainRamseteCommand robotRelative(){
    this.resetPosition = true;
    return this;
  }

  public DrivetrainRamseteCommand fieldRelative(){
    this.resetPosition = false;
    return this;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
    if (resetPosition) {
      drivetrain.resetOdometry(trajectory.getInitialPose());
    }
  }
}

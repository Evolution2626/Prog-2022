// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.Constants.CARACTERISATION;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TrajectoryTestCommand extends DrivetrainRamseteCommand {
  private Drivetrain drivetrain;
  public TrajectoryTestCommand(Drivetrain drivetrain) {
    super(drivetrain, 
      TrajectoryGenerator.generateTrajectory(
        new Pose2d(0, 0, new Rotation2d(0)), 
        List.of(new Translation2d(1, 0)), 
        new Pose2d(2, 0, new Rotation2d(0)), 
        new TrajectoryConfig(
          CARACTERISATION.kMaxSpeedMetersPerSecond, 
          CARACTERISATION.kMaxAccelerationMetersPerSecondSquared
        )
        .setKinematics(CARACTERISATION.kDriveKinematics)
        .addConstraint(
          new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
              CARACTERISATION.ksVolts, 
              CARACTERISATION.kvVoltsPerMeter
            ), 
            CARACTERISATION.kDriveKinematics, 
            10
          )
        )
      )
    );
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.

}

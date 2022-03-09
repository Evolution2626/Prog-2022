// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Filesystem;
import frc.robot.Constants.CARACTERISATION;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TrajectoryTestCommand extends DrivetrainRamseteCommand {
  public static String trajectoryJSON = "paths/YourPath.wpilib.json";

  public static Trajectory getTrajectory(String trajectoryJSON){
    
    Trajectory trajectory = new Trajectory();
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return trajectory;
  }

  public TrajectoryTestCommand(Drivetrain drivetrain) {
    super(drivetrain, 
      TrajectoryGenerator.generateTrajectory(
        new Pose2d(0, 0, new Rotation2d(0)), 
        List.of(
          new Translation2d(2, 0), 
          new Translation2d(2, 2), 
          new Translation2d(3, 0), 
          new Translation2d(5, 0), 
          new Translation2d(6, -2), 
          new Translation2d(5, 0), 
          new Translation2d(3, 1), 
          new Translation2d(2, 1)), 
        new Pose2d(0, 0, new Rotation2d(0)), 
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
  }

  // Called when the command is initially scheduled.
  @Override
  public void end(boolean interrupted){
    super.end(interrupted);
    System.out.println("Rendu à destination");
  }
}

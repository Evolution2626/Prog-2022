// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public interface CAN{
        int FRONT_RIGHT_MOTOR = 4;
        int FRONT_LEFT_MOTOR = 3;
        int BACK_RIGHT_MOTOR = 14;
        int BACK_LEFT_MOTOR = 2;
        int[] MOTORS_WINCH = {10, 5};  //5,6,7 are placeholder
        int MOTOR_GOBEUR = 12;  //placeholder
        int MOTEUR_LANCEUR_UN = 9;  //placeholder
        int MOTEUR_LANCEUR_DEUX = 11;  //placeholder
    }

    public interface USB{
        int DRIVER_CONTROLLER = 0;
        int CO_DRIVER_CONTROLLER = 1;
        
    }
    public interface PCM{
        int PISTON_LEFT_FORWARD = 6;
        int PISTON_LEFT_REVERSE = 7;
        int PISTON_RIGHT_FORWARD = 8;
        int PISTON_RIGHT_REVERSE = 9;

        int PISTON_GOBEUR_LEFT_FORWARD = 5;
        int PISTON_GOBEUR_LEFT_REVERSE = 4;
        int PISTON_GOBEUR_RIGHT_FORWARD = 15;
        int PISTON_GOBEUR_RIGHT_REVERSE = 12;
 

    }

    public interface GAMES_CONSTANTS{
        int CAMERA_HEIGHT = 0;
    }

    public interface CARACTERISATION {
        double ksVolts = 0.2496;
        double kvVoltsPerMeter = 0.26883;
        double kaVoltsSquarePerMeter = 0.037066;

        double kpDriveVelocity = 0.00007182;
        double trackWidthMeters = 0.53;

        DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(trackWidthMeters);

        double kMaxSpeedMetersPerSecond = 1;
        double kMaxAccelerationMetersPerSecondSquared = 1;

        double kRamseteB = 2;
        double kRamseteZeta = 0.7;
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
        int FRONT_RIGHT_MOTOR = 0;
        int FRONT_LEFT_MOTOR = 0;
        int BACK_RIGHT_MOTOR = 0;
        int BACK_LEFT_MOTOR = 0;
        int MOTOR_WINCH = 0;

    }

    public interface USB{
        int DRIVER_CONTROLLER = 0;
        int CO_DRIVER_CONTROLLER = 1;
        
    }

    public interface AXES{

        int RIGHT_Y_AXIS = 0;
        int LEFT_Y_AXIS = 0;
    }

    

}

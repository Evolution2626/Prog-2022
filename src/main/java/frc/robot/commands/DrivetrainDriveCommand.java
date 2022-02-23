// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;



import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.util.Range;

public class DrivetrainDriveCommand extends CommandBase {
  /**
   * Creates a new DrivetrainDriveCommand.
   */
  private Drivetrain drivetrain;
  private XboxController controller;

  public DrivetrainDriveCommand(Drivetrain drivetrain, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controller = controller;
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotState.isTeleop()){
      double speedRight = -controller.getRawAxis(Constants.AXES.RIGHT_Y_AXIS);
      speedRight = Range.threshold(.1, speedRight);
      speedRight = Math.pow(speedRight, 3);
      double speedLeft = -controller.getRawAxis(Constants.AXES.LEFT_Y_AXIS);
      speedLeft = Range.threshold(.1, speedLeft);
      speedLeft = Math.pow(speedLeft, 3);
      if (controller.getRawButton(Button.kLeftBumper.value)) {
        drivetrain.driveTank(speedLeft * .3, speedRight * .3);
      }else {
        drivetrain.driveTank(speedLeft, speedRight);
        
      }
    }
    
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

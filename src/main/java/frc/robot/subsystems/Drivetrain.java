// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private CANSparkMax frontRight;
  private CANSparkMax frontLeft;
  private CANSparkMax backRight;
  private CANSparkMax backLeft;
  
  private DifferentialDriveOdometry odometry;

  private DifferentialDrive drive;

  private Gyro gyro;

  public Drivetrain() {

    frontRight = new CANSparkMax(Constants.CAN.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
    frontLeft = new CANSparkMax(Constants.CAN.FRONT_LEFT_MOTOR, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.CAN.BACK_RIGHT_MOTOR, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.CAN.BACK_LEFT_MOTOR, MotorType.kBrushless);

    gyro = new ADXRS450_Gyro(Port.kOnboardCS0);

    frontRight.setInverted(false); // sur vrai robot true
    frontLeft.setInverted(true); // sur vrai robot false
    backRight.setInverted(true); 
    backLeft.setInverted(false); 

    frontRight.setIdleMode(IdleMode.kBrake);
    frontLeft.setIdleMode(IdleMode.kBrake);
    backRight.setIdleMode(IdleMode.kBrake);
    backLeft.setIdleMode(IdleMode.kBrake);

    frontRight.setClosedLoopRampRate(500);
    frontLeft.setClosedLoopRampRate(500);
    backRight.setClosedLoopRampRate(500);
    backLeft.setClosedLoopRampRate(500);

    frontRight.follow(backRight);
    frontLeft.follow(backLeft);

    setAllCurrentLimit(40, 40);

    resetEncoder();
    resetGyro();

    backLeft.getEncoder().setVelocityConversionFactor(0.0699);
    backRight.getEncoder().setVelocityConversionFactor(0.0699);

    odometry = new DifferentialDriveOdometry(gyro.getRotation2d());

    drive = new DifferentialDrive(backLeft, backRight);
  }

  public void setAllCurrentLimit(int stall, int free){
    frontRight.setSmartCurrentLimit(stall,free);
    frontLeft.setSmartCurrentLimit(stall,free);
    backRight.setSmartCurrentLimit(stall,free);
    backLeft.setSmartCurrentLimit(stall,free);
  }

  public void resetEncoder(){
    frontRight.getEncoder().setPosition(0);
    frontLeft.getEncoder().setPosition(0);
    backRight.getEncoder().setPosition(0);
    backLeft.getEncoder().setPosition(0);
  }

  public double getLeftEncodersPosition(){
    return ((frontLeft.getEncoder().getPosition() + backLeft.getEncoder().getPosition()) /2);
  }

  public double getRightEncodersPosition(){
    return ((frontRight.getEncoder().getPosition() + backRight.getEncoder().getPosition()) /2);
  }

  public double getLeftEncoderPositionCm(){
    return getLeftEncodersPosition() / 10.71 * (Math.PI * 5.5705* 2.54); // circonference des roues
  }

  public double getRightEncoderPositionCm(){
    return getRightEncodersPosition() / 10.71 * (Math.PI * 5.5705* 2.54); // circonference des roues
  }

  public double getBothEncoderPositionCm(){
    return ((getRightEncoderPositionCm() + getLeftEncoderPositionCm()) /2);
  }

  public DifferentialDriveWheelSpeeds getWheelSpeed(){
    return new DifferentialDriveWheelSpeeds(backLeft.getEncoder().getVelocity()/60, backRight.getEncoder().getVelocity()/60);
    //retrun wheels speed in meters/second
  }

  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }

  public void driveTank(double left, double right){
    backRight.set(right);
    backLeft.set(left);
  }

  public void driveTankVolts(double left, double right){
    backLeft.setVoltage(left);
    backRight.setVoltage(right);
  }

  public void resetGyro(){
    gyro.reset();
  }

  public double getGyroAngle(){
    return gyro.getAngle();
  }

  public double getHeading(){
    return gyro.getRotation2d().getDegrees();
  }

  public Pose2d getPose(){
    return odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose){
    resetEncoder();
    resetGyro();
    odometry.resetPosition(pose, gyro.getRotation2d());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    odometry.update(gyro.getRotation2d(), getLeftEncoderPositionCm() / 100, getRightEncoderPositionCm() / 100);
    SmartDashboard.putNumber("Encoder Left", getLeftEncodersPosition());
    SmartDashboard.putNumber("Encoder Right", getRightEncodersPosition());
    SmartDashboard.putNumber("GyroAngle", getGyroAngle());
    SmartDashboard.putNumber("GyroAngle2D", getHeading());
    SmartDashboard.putNumber("Pose2d X", getPose().getX());
    SmartDashboard.putNumber("Pose2d Y", getPose().getY());
    SmartDashboard.putNumber("Encoder cm", getBothEncoderPositionCm());

  }
}

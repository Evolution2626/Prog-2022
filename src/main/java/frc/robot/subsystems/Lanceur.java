// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lanceur extends SubsystemBase {

  private CANSparkMax moteurUn;
  private CANSparkMax moteurDeux;
  private static double lanceurHeight = 0;
  private static double lanceurAngle = 65;
  private static double wheelDiametersInch = 3;

  public enum TargetHeight {
    LOW,
    HIGH
  }

  /** Creates a new Lanceur. */
  public Lanceur() {

    moteurUn = new CANSparkMax(Constants.CAN.MOTEUR_LANCEUR_UN, MotorType.kBrushless);
    moteurDeux = new CANSparkMax(Constants.CAN.MOTEUR_LANCEUR_DEUX, MotorType.kBrushless);

    moteurUn.setInverted(false);
    moteurDeux.setInverted(false);

    moteurUn.setIdleMode(IdleMode.kBrake);
    moteurDeux.setIdleMode(IdleMode.kBrake);
  }

  public void setSpeed(double speed){
    moteurUn.set(speed);
    moteurDeux.set(speed);
  }

  public double getEncoderUnVelocity(){
    return moteurUn.getEncoder().getVelocity();
  }

  public double getEncoderDeuxVelocity(){
    return moteurDeux.getEncoder().getVelocity();
  }

  public double getEncodersVelocity(){
    return (getEncoderUnVelocity() + getEncoderDeuxVelocity()) / 2;
  }

  public double getBallMetersPerSecond(double distanceMeters, TargetHeight targetHeight){

    double targetHeightDouble;

    if (targetHeight ==  TargetHeight.LOW) {
      targetHeightDouble = 1.04;
    } else {
      targetHeightDouble = 2.64;
    }


    double ballSpeed = Math.sqrt((-9.81 * Math.pow(distanceMeters, 2)) / 
                        (2 * Math.pow(Math.cos(lanceurAngle), 2)) * (((targetHeightDouble - Math.tan(lanceurAngle)) * (distanceMeters)-lanceurHeight)));
    return ballSpeed;

  }

  public double getLanceurDesiredSpeedRPM(double distanceMeters, TargetHeight targetHeight){

    return ((getBallMetersPerSecond(distanceMeters, targetHeight)) * 60) / (2.54 * wheelDiametersInch * Math.PI / 100);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

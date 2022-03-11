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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

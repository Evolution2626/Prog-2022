// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gobeur extends SubsystemBase {
  private CANSparkMax gobeur;
  private DoubleSolenoid pistonLeft;
  private DoubleSolenoid pistonRight;
  /** Creates a new Gobeur. */
  public Gobeur() {
    gobeur = new CANSparkMax(Constants.CAN.MOTOR_GOBEUR, MotorType.kBrushless);
    gobeur.setSmartCurrentLimit(30, 20);

    pistonLeft = new DoubleSolenoid(8,PneumaticsModuleType.REVPH, Constants.PCM.PISTON_GOBEUR_LEFT_FORWARD, Constants.PCM.PISTON_GOBEUR_LEFT_REVERSE);
    pistonRight = new DoubleSolenoid(8,PneumaticsModuleType.REVPH, Constants.PCM.PISTON_GOBEUR_RIGHT_FORWARD, Constants.PCM.PISTON_GOBEUR_RIGHT_REVERSE);
  }

  public void tournerGobeur (double speed){
    gobeur.set(speed);

  }

  public void setPistonPosition(Value position){
    pistonLeft.set(position);
    pistonRight.set(position);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

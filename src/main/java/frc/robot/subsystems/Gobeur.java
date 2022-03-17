// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gobeur extends SubsystemBase {
  private TalonSRX gobeur;
  private DoubleSolenoid pistonLeft;
  private DoubleSolenoid pistonRight;
  /** Creates a new Gobeur. */
  public Gobeur() {
    gobeur = new TalonSRX(Constants.CAN.MOTOR_GOBEUR);

  }

  public void tournerGobeur (double speed){
    gobeur.set(ControlMode.PercentOutput, speed);

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

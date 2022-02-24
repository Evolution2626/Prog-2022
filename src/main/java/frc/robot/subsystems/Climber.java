// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
 
  private TalonSRX winch; 
  private DoubleSolenoid piston;
  /** Creates a new Climber. */
  public Climber() {

    winch = new TalonSRX(Constants.CAN.MOTOR_WINCH);
    piston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCM.PISTON_FORWARD, Constants.PCM.PISTON_REVERSE);
  }


  public void tournerWinch(double speed){
    winch.set(ControlMode.PercentOutput, speed);

  }

  public void setPistonPosition(Value position){
    piston.set(position);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
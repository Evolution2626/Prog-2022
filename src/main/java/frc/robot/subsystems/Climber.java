// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
 
  private CANSparkMax[] winch = new CANSparkMax[2];
  private DoubleSolenoid pistonLeft;
  private DoubleSolenoid pistonRight;
  private Value pistonPosition;

  


  /** Creates a new Climber. */
  public Climber() {

    
    for(int i = 0; i < 2; i++){
      winch[i] = new CANSparkMax(Constants.CAN.MOTORS_WINCH[i], MotorType.kBrushless);
      winch[i].setIdleMode(IdleMode.kBrake);
    }
    

    pistonLeft = new DoubleSolenoid(8, PneumaticsModuleType.REVPH, Constants.PCM.PISTON_LEFT_FORWARD, Constants.PCM.PISTON_LEFT_REVERSE);
    pistonRight = new DoubleSolenoid(8, PneumaticsModuleType.REVPH, Constants.PCM.PISTON_RIGHT_FORWARD, Constants.PCM.PISTON_RIGHT_REVERSE);

  }


  public void tournerWinch(int numeroWinch, double speed){

    winch[numeroWinch].set(speed);
    //winch[1].set(ControlMode.PercentOutput, speed);
    SmartDashboard.putNumber("Vitesse winch", speed);
  }

  public void tournerWinchPiston(double speed){
   // winch[2].set(ControlMode.PercentOutput, speed);
    //winch[3].set(ControlMode.PercentOutput, speed);
  }

  public void setPistonPosition(Value position){
    pistonLeft.set(position);
    pistonRight.set(position);
    pistonPosition = position;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    if (pistonPosition == Value.kForward) {
      SmartDashboard.putString("Piston", "Forward");
    } else {
      SmartDashboard.putString("Piston", "Reverse");
    }
  }
}
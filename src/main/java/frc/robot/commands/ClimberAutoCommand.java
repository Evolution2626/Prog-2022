// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.autonomous.AutonomousCommand;
import frc.robot.subsystems.Climber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ClimberAutoCommand extends AutonomousCommand {
  /** Creates a new ClimberAutoCommand. */

  public ClimberAutoCommand(Climber climber) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new MonterWinchCommand(0, climber), 
      new MonterWinchCommand(1, climber), 
      new WaitCommand(3),
      new DescendreWinchCommand(1, climber),
      new SetPistonCommand(climber, Value.kReverse),
      new DescendreWinchCommand(0, climber, 50)
    );
  }


  
  
}

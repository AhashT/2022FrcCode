// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  private Shooter shoot; 
 
  /** Creates a new Shoot. */
  public Shoot(Shooter shoot) {
    this.shoot = shoot;
    addRequirements(shoot);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shoot.shooterStartASync();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void Shoot(float rpm, boolean useCargoSensor)
  {
    //wait async for both motors to reach speed

    //advance cargo feeder
    
    //wait async for 3 seconds

    //set motor speed to zero
    
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StopShooter extends CommandBase {
  private Shooter shooter;
  private boolean isRunning; 
 
  /** Creates a new Shoot. */
  public StopShooter(Shooter shooter) {
    addRequirements(shooter);
    this.shooter = shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("***StopShooterInit");
    isRunning = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("***StopShooterExecute");
      shooter.shooterStop();
      isRunning = false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("***StopShooterIsFinished: "+!isRunning);
    return !isRunning;
  }

  public void Shoot(float rpm, boolean useCargoSensor)
  {
    //wait async for both motors to reach speed

    //advance cargo feeder
    
    //wait async for 3 seconds

    //set motor speed to zero
  }
}

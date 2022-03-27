// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StartShooter extends CommandBase {
  private Shooter shooter;
  private boolean isRunning; 
 
  /** Creates a new Shoot. */
  public StartShooter(Shooter shooter) {
    //System.out.println("***StartShooterExecute");
    this.shooter = shooter;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("***StartShooterInit");
    isRunning= true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("***StartShooterExecute");
    shooter.shooterStart();
    isRunning = false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shooterStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("***StartShooterIsFinished: "+!isRunning);
    return !isRunning;
  }

  
}

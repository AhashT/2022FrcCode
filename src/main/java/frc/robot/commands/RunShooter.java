// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class RunShooter extends CommandBase {
  private ShooterSubsystem SHOOTER_SUBSYSTEM;
  private boolean isRunning; 
 
  /** Creates a new Shoot. */
  public RunShooter(ShooterSubsystem ss) {
    //System.out.println("***StartShooterExecute");
    this.SHOOTER_SUBSYSTEM = ss;
    addRequirements(SHOOTER_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("***StartShooterInit");
    isRunning = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("***StartShooterExecute");
    SHOOTER_SUBSYSTEM.shooterStart();
    isRunning = false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SHOOTER_SUBSYSTEM.shooterStop();
    //shooter.shooterStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println("***StartShooterIsFinished: "+!isRunning);
    return !isRunning;
  }

  
}

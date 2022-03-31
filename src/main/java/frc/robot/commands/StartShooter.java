// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class StartShooter extends CommandBase {
  private ShooterSubsystem SHOOTER_SUBSYSTEM;
  
  public StartShooter(ShooterSubsystem ss) {
    this.SHOOTER_SUBSYSTEM = ss;
    addRequirements(SHOOTER_SUBSYSTEM);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    //System.out.println("***StartShooterExecute");
    SHOOTER_SUBSYSTEM.shooterStart();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SHOOTER_SUBSYSTEM.shooterStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
}

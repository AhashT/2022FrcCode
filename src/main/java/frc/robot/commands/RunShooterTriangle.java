// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class RunShooterTriangle extends CommandBase {
  /** Creates a new RunShooterAtVoltage. */
  ShooterSubsystem SHOOTER_SUBSYSTEM;
  double startTime;

  double target_rpm;
  double target_seconds;

  public RunShooterTriangle(ShooterSubsystem ss, double rpm_goal, double time_seconds) {
    this.SHOOTER_SUBSYSTEM = ss;
    this.target_rpm = rpm_goal*3.41;
    this.target_seconds = time_seconds;
    addRequirements(SHOOTER_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentTime = System.currentTimeMillis()-startTime;
    double currentTimeSeconds = currentTime/1000;

    if(currentTimeSeconds > target_seconds) {
      currentTimeSeconds = target_seconds;
    }

    SHOOTER_SUBSYSTEM.setRPM(target_rpm*(currentTimeSeconds/target_seconds));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

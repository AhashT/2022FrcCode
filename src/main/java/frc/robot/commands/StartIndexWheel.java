// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerWheelSubsystem;

public class StartIndexWheel extends CommandBase {
  private IndexerWheelSubsystem indexerWheel;

  /** Creates a new StartIndexWheel. */
  public StartIndexWheel(IndexerWheelSubsystem indexerWheel) {
    this.indexerWheel =indexerWheel;
    // Use addRequirements() here to declare subsystem dependencies.
  addRequirements(indexerWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    indexerWheel.start(false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexerWheel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

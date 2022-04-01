// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;

public class FeedOne extends CommandBase {
  private FeederSubsystem FEEDER_SUBSYSTEM;
  
  /** Creates a new FeedOne. */
  public FeedOne(FeederSubsystem fs) {
    this.FEEDER_SUBSYSTEM = fs;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(FEEDER_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("***Feeder start");
    FEEDER_SUBSYSTEM.FeedOneCargo();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("***Feeder end");
    FEEDER_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

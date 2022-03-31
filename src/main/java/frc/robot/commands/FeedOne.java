// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class FeedOne extends CommandBase {
  private Feeder feeder;
  private boolean isRunning;

  /** Creates a new FeedOne. */
  public FeedOne(Feeder feeder) {
    this.feeder = feeder;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(feeder);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isRunning = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.FeedOneCargo();
    isRunning = false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //feeder.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !isRunning;
  }
}

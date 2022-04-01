// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardTimed extends CommandBase {

  private final DriveSubsystem DRIVE_SUBSYSTEM;
  
  Timer timer;
  /** Creates a new DriveForwardTimed. */
  public DriveForwardTimed(DriveSubsystem ds) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.DRIVE_SUBSYSTEM = ds;
    addRequirements(DRIVE_SUBSYSTEM);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {   
    timer.reset();
    timer.start();
    
    while(timer.get() < Constants.DriveForwardtime){
      DRIVE_SUBSYSTEM.driveForward(Constants.AutoSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

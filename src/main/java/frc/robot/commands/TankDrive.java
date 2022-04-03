// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Input;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveSubsystem;

public class TankDrive extends CommandBase {
  
  DriveSubsystem DRIVE_SUBSYSTEM;
  
  public TankDrive(DriveSubsystem ds) {
    this.DRIVE_SUBSYSTEM = ds;
    addRequirements(DRIVE_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    Subsystems.DRIVE_SUBSYSTEM.setRight(Input.driver.getLeftY());
    Subsystems.DRIVE_SUBSYSTEM.setLeft(Input.driver.getRightY());
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

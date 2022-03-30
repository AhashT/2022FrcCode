// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class RotateVisionCommand extends CommandBase {
  /** Creates a new RotateVisionCommand. */


  DriveSubsystem DRIVE_SUBSYSTEM;
  LimelightSubsystem LIMELIGHT_SUBSYSTEM;
  double kP = 0.015;

  public RotateVisionCommand(DriveSubsystem ds, LimelightSubsystem ls) {
    this.DRIVE_SUBSYSTEM = ds;
    this.LIMELIGHT_SUBSYSTEM = ls;

    addRequirements(DRIVE_SUBSYSTEM);
    addRequirements(LIMELIGHT_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double output = kP * (LIMELIGHT_SUBSYSTEM.getX());
    SmartDashboard.putNumber("Output: ", output);
    DRIVE_SUBSYSTEM.setLeft(output);
    DRIVE_SUBSYSTEM.setRight(-output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //DT.stop();
    return false;
  }
}

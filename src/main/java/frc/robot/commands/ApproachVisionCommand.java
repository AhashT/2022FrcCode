// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.interfaces.LimelightInterface.ledMode;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class ApproachVisionCommand extends CommandBase {
  /** Creates a new RotateVisionCommand. */


  DriveSubsystem DRIVE_SUBSYSTEM;
  LimelightSubsystem LIMELIGHT_SUBSYSTEM;
  double kP_r = 0.015;
  double kP_d = .1;
  double area_goal = 2;
  double output;
  public ApproachVisionCommand(DriveSubsystem ds, LimelightSubsystem ls) {
    this.DRIVE_SUBSYSTEM = ds;
    this.LIMELIGHT_SUBSYSTEM = ls;

    addRequirements(DRIVE_SUBSYSTEM);
    addRequirements(LIMELIGHT_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LIMELIGHT_SUBSYSTEM.Limelight.setLEDMode(ledMode.ON);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(LIMELIGHT_SUBSYSTEM.getX()) > 4) {
      output = kP_r * (LIMELIGHT_SUBSYSTEM.getX());
      DRIVE_SUBSYSTEM.setLeft(output);
      DRIVE_SUBSYSTEM.setRight(-output);
    } else {
      output = kP_d * (LIMELIGHT_SUBSYSTEM.getArea()-area_goal);
      DRIVE_SUBSYSTEM.setLeft(-output);
      DRIVE_SUBSYSTEM.setRight(-output);
    }
    
    SmartDashboard.putNumber("x offset: ", LIMELIGHT_SUBSYSTEM.getX());
    SmartDashboard.putNumber("area: ", LIMELIGHT_SUBSYSTEM.getArea());
    SmartDashboard.putNumber("Output: ", output);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LIMELIGHT_SUBSYSTEM.Limelight.setLEDMode(ledMode.OFF);
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //DT.stop();
    return false;
  }
}

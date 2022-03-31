// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.ejml.dense.row.mult.SubmatrixOps_FDRM;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class PIDDriveInches extends CommandBase {

  DriveSubsystem DRIVE_SUBSYSTEM;
  double goal_inches;
  double wheel_diameter = 6;
  double gear_ratio = 64/12;
  double ticks_per_inch = (gear_ratio)/(wheel_diameter*Math.PI);
  double goal_ticks;

  double kP = 0.1;
  double kI = 0;
  double kD = 0;

  PIDController leftController = new PIDController(kP, kI, kD);
  PIDController rightController = new PIDController(kP, kI, kD);


  double tick_threshhold = .15;
  double time_threshhold = 5;
  double within_threshold_counter = 0;

  double leftError;
  double rightError;

  double left_e;
  double right_e;

  /** Creates a new PIDDriveInches. */
  public PIDDriveInches(DriveSubsystem ds, double goal) {
    this.DRIVE_SUBSYSTEM = ds;
    this.goal_inches = goal;
    this.goal_ticks = goal_inches * ticks_per_inch;
    SmartDashboard.putNumber("TARGET: ", goal_ticks);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    within_threshold_counter = 0;
    DRIVE_SUBSYSTEM.resetEncoders();
    SmartDashboard.putNumber("PID", 1);
    left_e = DRIVE_SUBSYSTEM.getLeftEncoder();
    right_e = DRIVE_SUBSYSTEM.getRightEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    left_e = DRIVE_SUBSYSTEM.getLeftEncoder();
    right_e = DRIVE_SUBSYSTEM.getRightEncoder();
    SmartDashboard.putNumber("PID", 2);
    DRIVE_SUBSYSTEM.setLeft(-leftController.calculate(-left_e, goal_ticks));
    DRIVE_SUBSYSTEM.setRight(-rightController.calculate(-right_e, goal_ticks));

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("INTER", interrupted);
    SmartDashboard.putNumber("PID", 3);
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    leftError = Math.abs(goal_ticks + left_e);
    rightError = Math.abs(goal_ticks + right_e);
    SmartDashboard.putNumber("LEFT ERROR: ", leftError);
    SmartDashboard.putNumber("RIGHT ERROR: ", rightError);
    if(leftError < tick_threshhold && rightError < tick_threshhold) {
      within_threshold_counter++;
    }

    SmartDashboard.putNumber("WITHIN THRESH", within_threshold_counter);
    SmartDashboard.putBoolean("IS FINISHED", within_threshold_counter >= time_threshhold);
    return within_threshold_counter >= time_threshhold;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class PIDDriveInches extends CommandBase {

  DriveSubsystem DRIVE_SUBSYSTEM;
  double goal_inches;
  double wheel_diameter = 5;
  double ticks_per_inch = 42/(wheel_diameter*Math.PI);
  double goal_ticks;

  double kP = 0.01;
  double kI = 0;
  double kD = 0;

  PIDController leftController = new PIDController(kP, kI, kD);
  PIDController rightController = new PIDController(kP, kI, kD);


  double tick_threshhold;
  double time_threshhold;
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
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    left_e = DRIVE_SUBSYSTEM.getLeftEncoder();
    right_e = DRIVE_SUBSYSTEM.getRightEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    left_e = DRIVE_SUBSYSTEM.getLeftEncoder();
    right_e = DRIVE_SUBSYSTEM.getRightEncoder();

    DRIVE_SUBSYSTEM.setLeft(leftController.calculate(left_e, -goal_ticks));
    DRIVE_SUBSYSTEM.setRight(rightController.calculate(right_e, goal_ticks));

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    leftError = Math.abs(goal_ticks - left_e);
    rightError = Math.abs(goal_ticks - right_e);

    if(leftError < tick_threshhold && rightError < tick_threshhold) {
      within_threshold_counter++;
    }

    return within_threshold_counter >= time_threshhold;
  }
}

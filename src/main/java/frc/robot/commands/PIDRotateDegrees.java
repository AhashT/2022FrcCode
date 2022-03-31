// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;

public class PIDRotateDegrees extends CommandBase {

  DriveSubsystem DRIVE_SUBSYSTEM;
  GyroSubsystem GYRO_SUBSYSTEM;

  double kP = 0.02;
  double kI = 0;
  double kD = 0.0025;

  double output = 0;

  PIDController controller = new PIDController(kP, kI, kD);

  double goal;

  double degree_threshold = 3;
  double time_threshhold = 10;
  double within_threshold_counter = 0;

  double error = 0;

  /** Creates a new PIDRotateDegrees. */
  public PIDRotateDegrees(DriveSubsystem ds, GyroSubsystem gs, double target) {
    this.DRIVE_SUBSYSTEM = ds;
    this.GYRO_SUBSYSTEM = gs;
    this.goal = target;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    GYRO_SUBSYSTEM.zero();
    within_threshold_counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Gyro Heading: ", GYRO_SUBSYSTEM.getHeading());
    output = controller.calculate(GYRO_SUBSYSTEM.getHeading(), goal);
    if(output > .5) {
      output = .5;
    }
    if(output < -.5) {
      output = -.5;
    }
    SmartDashboard.putNumber("ROTATE OUTPUT", output);
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
    error = Math.abs(goal - GYRO_SUBSYSTEM.getHeading());
    if(error < degree_threshold) {
      within_threshold_counter++;
    }

    return within_threshold_counter >= time_threshhold;
  }
}

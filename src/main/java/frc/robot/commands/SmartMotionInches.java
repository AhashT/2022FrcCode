// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class SmartMotionInches extends CommandBase {

  DriveSubsystem DRIVE_SUBSYSTEM;


  double within_goal_counter = 0;
  double time_threshold = 5;
  double position_threshold = 1;


  double accel;
  double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;

  double inches_per_rpm = (Math.PI*6)/(16/3);
  double goal;

  SparkMaxPIDController leftController;
  SparkMaxPIDController rightController;

  double leftError = 0;
  double rightError = 0;

  double left_e;
  double right_e;

  /** Creates a new SmartMotionInches. */
  public SmartMotionInches(DriveSubsystem ds, double inch_goal) {
    this.DRIVE_SUBSYSTEM = ds;
    this.goal = inches_per_rpm*inch_goal;
    addRequirements(DRIVE_SUBSYSTEM);

    

    kP = 5e-5;
    kI = 0;
    kD = 0;
    kIz = 0;
    kFF = .000156;
    kMaxOutput = 1;
    kMinOutput = -1;
    maxRPM = 5700;
    allowedErr = .5;

    maxVel = 2000;
    maxAcc = 1500;

    leftController.setP(kP);
    rightController.setP(kP);
    leftController.setI(kP);
    rightController.setI(kP);
    leftController.setD(kP);
    rightController.setD(kP);
    leftController.setIZone(kP);
    rightController.setIZone(kP);
    leftController.setFF(kP);
    rightController.setFF(kP);

    
    leftController.setOutputRange(kMinOutput, kMaxOutput);
    rightController.setOutputRange(kMinOutput, kMaxOutput);

    int smartMotionSlot = 0;

    leftController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    rightController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);

    leftController.setSmartMotionMinOutputVelocity(maxVel, smartMotionSlot);
    rightController.setSmartMotionMinOutputVelocity(maxVel, smartMotionSlot);

    leftController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    rightController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);

    leftController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);
    rightController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    within_goal_counter = 0;
    SmartDashboard.putBoolean("IN SMART MOTION?", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftController.setReference(-goal, CANSparkMax.ControlType.kSmartMotion);
    rightController.setReference(goal, CANSparkMax.ControlType.kSmartMotion);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("IN SMART MOTION?", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    left_e = DRIVE_SUBSYSTEM.getLeftEncoder();
    right_e = DRIVE_SUBSYSTEM.getRightEncoder();

    leftError = Math.abs(-goal - left_e);
    rightError = Math.abs(goal - right_e);

    if(leftError < position_threshold && rightError < position_threshold) {
      within_goal_counter++;
    }

    return within_goal_counter >= time_threshold;
  }
}

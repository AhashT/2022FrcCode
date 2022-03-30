// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimelightSubsystem;

public class RotateVisionCommand extends CommandBase {
  /** Creates a new RotateVisionCommand. */

  DriveTrain DT;
  LimelightSubsystem LIMELIGHT;

  double kP = 0.015;

  public RotateVisionCommand(DriveTrain dt, LimelightSubsystem limelight) {
    this.DT = dt;
    this.LIMELIGHT = limelight;
    addRequirements(DT);
    addRequirements(LIMELIGHT);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double output = kP * (LIMELIGHT.Limelight.getHorizontalOffset());
    SmartDashboard.putNumber("Output: ", output);
    DT.setLeft(output);
    DT.setRight(-output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //DT.stop();
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.interfaces.LimelightInterface;
import frc.robot.interfaces.LimelightInterface.ledMode;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new LimelightSubsystem. */

  public final LimelightInterface Limelight = new LimelightInterface();

  // height in inches of camera from ground
  private double limelight_height;
  // height in inches of center of target from ground
  private double target_height;
  // limelight mounting angle above positive x axis in degrees
  private double limelight_mount_angle;

  public LimelightSubsystem() {
    Limelight.setLEDMode(ledMode.OFF);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("x angle: ", getX());
    SmartDashboard.putNumber("y angle:", getY());
    SmartDashboard.putNumber("area: ", getArea());
    SmartDashboard.putNumber("distance to target: ", distanceToTarget());

  }

  public double getX() {
    return Limelight.getHorizontalOffset();
  }

  public double getY() {
    return Limelight.getVerticalOffset();
  }

  public double getArea() {
    return Limelight.getArea();
  }

  public double distanceToTarget() {
    // Returns distance to target assuming 
    return (target_height - limelight_height) / (Math.tan(Math.toRadians(limelight_mount_angle - getY())));
  }


}

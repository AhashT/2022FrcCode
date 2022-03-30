// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.interfaces.LimelightInterface;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new LimelightSubsystem. */

  public final LimelightInterface Limelight = new LimelightInterface();

  public LimelightSubsystem() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("x offset", getX());
  }

  public double getX() {
    return Limelight.getHorizontalOffset();
  }


}

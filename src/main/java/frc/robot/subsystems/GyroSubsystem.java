// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

public class GyroSubsystem extends SubsystemBase {

  AHRS ahrs;

  /** Creates a new GyroSubsystem. */
  public GyroSubsystem() {
    ahrs = new AHRS(SPI.Port.kMXP);
    zero();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Gyro Heading: ", getHeading());
  }

  public double getHeading() {
    return ahrs.getYaw();
  }

  public void zero() {
    ahrs.reset();
  }

}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Feeds the cargo from Indexer to Shooter
 */
public class Feeder extends SubsystemBase {
  private PWMSparkMax FeederMotorTop;
  private PWMSparkMax FeederMotorBottom;

  /** Creates a new Feeder. */
  public Feeder() {
    FeederMotorTop = new PWMSparkMax(Constants.FeederTopPWM);
    FeederMotorBottom = new PWMSparkMax(Constants.FeederBottomPWM);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**Present a single cargo to the shooter */
  public void Feed(){
   
  }
}

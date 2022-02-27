// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Moves cargo from intake to Feeder. Max two Cargo. 
 */
public class Indexer extends SubsystemBase {

  /**
   * Reports the current number of cargo held in robot
   */
  public byte CargoCount;

  /** Creates a new Indexer. */
  public Indexer() {}

  /**
   * Automatically accepts cargo from intake 
   */
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //if CargoCount >= 2, return (note: if CargoCount > 2, there is an error)
    //if cargo not detected at intake, return
    //if CargoCount = 0, run motor until FirstCargoDetector is true
    //if CargoCount = 1, run motor until FirstCargoDetector & SecondCargoDetector are true
    //CargoCount += 1;
  }

  /**
   * Moves a single cargo from index into Feeder and decrements CargoCount
   */
  public void PresentCargoToFeeder()
  {
    //if CargoCount == 0, return
    //if CargoCount == 2, run motor until FirstCargoDetector is false and SecondCargoDetector is true
    //if CargoCount == 1, run motor until FirstCargoDetector is false and SecondCargoDetector is false
    //CargoCount -=1;
  }
}

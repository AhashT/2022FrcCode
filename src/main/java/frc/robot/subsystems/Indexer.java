// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("Indexer");
  private NetworkTableEntry nte_IndexerPower = tab.add("IndexerPower", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .getEntry();

  /** deivers cargo to feeder */
  private PWMSparkMax indexMotor;
  private double indexerPower;

  /** Creates a new Indexer. */
  public Indexer() {
    indexMotor = new PWMSparkMax(Constants.IndexerPWM);
  }

  @Override
  public void periodic() {
    indexerPower = nte_IndexerPower.getDouble(0.4);
  }

  public void IndexerStart(){
    indexMotor.set(indexerPower);
  }

  public void IndexerStop(){
    indexMotor.set(0);
  }
}

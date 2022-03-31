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
import static frc.robot.Constants.*;

/** deivers cargo to feeder */
public class IndexerSubsystem extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("Indexer");
  private NetworkTableEntry nte_IndexerPower = tab.add("IndexerPower", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .getEntry();

  private NetworkTableEntry nte_IndexerStart_button = tab.add("Start", false)
      .withWidget(BuiltInWidgets.kToggleButton)
      .getEntry();

  private PWMSparkMax indexMotor;
  private double indexerPower = IndexerPower;
  private boolean startButtonPressed;
  private boolean testRunning;

  /** Creates a new Indexer. */
  public IndexerSubsystem() {
    indexMotor = new PWMSparkMax(IndexerPWM);
  }

  @Override
  public void periodic() {
  }

  public void IndexerStart(boolean reverse) {
    System.out.println("IndexerStart: " + indexerPower * (reverse ? -1.0 : 1.0));
    indexMotor.set(indexerPower * (reverse ? -1.0 : 1.0));
  }

  public void IndexerStop() {
    System.out.println("IndexerStop");
    indexMotor.set(0);
  }

  public void testInit() {
    nte_IndexerPower.setDouble(indexerPower);
  }

  public void testPeriodic() {
    indexerPower = nte_IndexerPower.getDouble(IndexerPower);
    /* check for test button state change */
    startButtonPressed = nte_IndexerStart_button.getBoolean(false);
    if (startButtonPressed) {
      if (!testRunning) {
        IndexerStart(false);
        testRunning = true;
      }
    } else {
      if (testRunning) {
        IndexerStop();
        testRunning = false;
      }
    }
  }
}

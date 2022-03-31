// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.*;

import java.util.Map;

public class IndexerWheel extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("IndexerWheel");
  private NetworkTableEntry nte_IndexerWheelPower = tab.add("IndexerWheelPower", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .getEntry();

  private NetworkTableEntry nte_IndexerWheelStart_button = tab.add("Start", false)
      .withWidget(BuiltInWidgets.kToggleButton)
      .getEntry();

  PWMSparkMax m_indexwheel;
  private double power = Constants.IndexerWheelPower;
  private boolean startButtonPressed;
  private boolean testRunning;

  /** Creates a new IndexerWheel. */
  public IndexerWheel() {
    m_indexwheel = new PWMSparkMax(IndexerWheelPWM);
    m_indexwheel.set(0);
    m_indexwheel.setInverted(true);
  }

  public void start(boolean reverse) {
    System.out.println("IndexerWheelStart: " + power * (reverse ? -1.0 : 1.0));
    m_indexwheel.set(power * (reverse ? -1.0 : 1.0));
  }

  public void stop() {
    System.out.println("IndexerWheelStop");
    m_indexwheel.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void testInit() {
    nte_IndexerWheelPower.setDouble(power);
  }

  public void testPeriodic() {
    power = nte_IndexerWheelPower.getDouble(power);

    /* check for test button state change */
    startButtonPressed = nte_IndexerWheelStart_button.getBoolean(false);
    if (startButtonPressed) {
      if (!testRunning) {
        start(false);
        testRunning = true;
      }
    } else {
      if (testRunning) {
        stop();
        testRunning = false;
      }
    }
  }
}

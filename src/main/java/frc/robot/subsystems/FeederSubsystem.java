// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import java.util.Map;

public class Feeder extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("Feeder");
  private NetworkTableEntry nte_FeederPower = tab.add("FeederPower", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .getEntry();

  private NetworkTableEntry nte_FeederStart_button = tab.add("Start", false)
      .withWidget(BuiltInWidgets.kToggleButton)
      .getEntry();

  PWMSparkMax m_feeder;
  DigitalInput index0Sensor = new DigitalInput(index0SensorPort);
  private double power = FeederPower;
  private boolean startButtonPressed;
  private boolean testRunning;

  public FeederSubsystem() {
    m_feeder = new PWMSparkMax(FeederPWM);
    m_feeder.set(0);
    m_feeder.setInverted(true);
  }

  public void FeedOneCargo() {
    // if (!index0Sensor.get()) return;
    m_feeder.set(power);
    // long start_time = System.currentTimeMillis();
    // long wait_time = 500;
    // long end_time = start_time + wait_time;
    // System.out.println("***FeedOneCargo wait started: " + index0Sensor.get());
    // while (System.currentTimeMillis() < end_time ){//&& !index0Sensor.get()) {
    // try {
    // Thread.sleep(20);
    // } catch (InterruptedException ex) {
    // System.err.format("IOException: %s%n", ex);
    // }
    // }
    // m_feeder.set(0);
    // System.out.println("***FeedOneCargo wait ended: " + index0Sensor.get());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stop() {
    m_feeder.set(0);
  }

  public void testInit() {
    nte_FeederPower.setDouble(power);

    /* check for test button state change */
    startButtonPressed = nte_FeederStart_button.getBoolean(false);
    if (startButtonPressed) {
      if (!testRunning) {
        FeedOneCargo();
        testRunning = true;
      }
    } else {
      if (testRunning) {
        stop();
        testRunning = false;
      }
    }
  }

  public void testPeriodic() {
  }
}

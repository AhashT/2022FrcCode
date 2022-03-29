// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.*;

public class Feeder extends SubsystemBase {
  /** Creates a new Feeder. */
  PWMSparkMax m_feeder;
  DigitalInput index0Sensor = new DigitalInput(index0SensorPort);

  public Feeder() {
    m_feeder = new PWMSparkMax(FeederPWM);
    m_feeder.set(0);
    m_feeder.setInverted(true);
  }

  public void FeedOneCargo() {
    //if (!index0Sensor.get()) return;
    m_feeder.set(.7);
    // long start_time = System.currentTimeMillis();
    // long wait_time = 500;
    // long end_time = start_time + wait_time;
    // System.out.println("***FeedOneCargo wait started: " + index0Sensor.get());
    // while (System.currentTimeMillis() < end_time ){//&& !index0Sensor.get()) {
    //   try {
    //     Thread.sleep(20);
    //   } catch (InterruptedException ex) {
    //     System.err.format("IOException: %s%n", ex);
    //   }
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
}

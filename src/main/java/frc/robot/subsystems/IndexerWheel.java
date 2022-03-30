// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.*;

public class IndexerWheel extends SubsystemBase {
  PWMSparkMax m_indexwheel;
  private double power = Constants.IndexerWheelPower;
  
  /** Creates a new IndexerWheel. */
  public IndexerWheel() {
    m_indexwheel = new PWMSparkMax(IndexerWheelPWM);
    m_indexwheel.set(0);
    m_indexwheel.setInverted(true);
  }

  public void start(){
    m_indexwheel.set(power);
  }

  public void stop(){
    m_indexwheel.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

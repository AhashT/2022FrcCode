// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
import this library for Talon:
https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix-frc2022-latest.json
 */

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Shooter extends SubsystemBase {
  TalonFX m_front;
  TalonFX m_rear;

  public double RPM = 1000.0;
  public double targetRPM = 2000.0; 

  /** Creates a new Shooter. */
  public Shooter() {
    m_front = new TalonFX(Constants.shooter_top_motor);
    m_rear= new TalonFX(Constants.shooter_bottom_motor);

    //just guessing here KSM 2022-03-03
    m_front.configMotionAcceleration(4000);
    m_rear.configMotionAcceleration(4000);
  }

  @Override
  public void periodic() {  
    // This method will be called once per scheduler run
  }

  /**Called when button is pressed */
public void shooterStartASync(){
    m_front.set(ControlMode.PercentOutput, 100);
    m_rear.set(ControlMode.PercentOutput, 100);

  }

  /**Called when button is released */
  public void shooterStopASync(){
    m_front.set(ControlMode.PercentOutput, 0);
    m_rear.set(ControlMode.PercentOutput, 0);

  }

}
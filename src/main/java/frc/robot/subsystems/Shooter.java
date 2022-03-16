// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/*
import this library for Talon:
https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix-frc2022-latest.json
 */

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Shooter extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  private NetworkTableEntry nte_ShooterTargetRPM = tab.add("ShooterTargetRPM", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 6380))
      .getEntry();

      private NetworkTableEntry nte_ShooterTopRPM = tab.add("ShooterTopRPM", 0)
      .withWidget(BuiltInWidgets.kNumberBar)
      .withProperties(Map.of("min", 0, "max", 6380))
      .getEntry();

      private NetworkTableEntry nte_ShooterBtmRPM = tab.add("ShooterBtmRPM", 0)
      .withWidget(BuiltInWidgets.kNumberBar)
      .withProperties(Map.of("min", 0, "max", 6380))
      .getEntry();

  TalonFX m_front;
  TalonFX m_rear;

  /**Fake top motorRPM for testing shuffleboard */
  public double topRPM;

  /**Fake bottom motorRPM for testing shuffleboard */
  public double btmRPM;

  /**Desired top and bottom RPM */
  public double targetRPM; 

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
    targetRPM = nte_ShooterTargetRPM.getDouble(-1);
    topRPM = targetRPM/2.0;
    btmRPM = targetRPM/2.0;
    nte_ShooterTopRPM.setValue(topRPM);
    nte_ShooterBtmRPM.setValue(btmRPM);
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
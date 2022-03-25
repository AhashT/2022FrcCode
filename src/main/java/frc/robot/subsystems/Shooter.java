// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import static frc.robot.Constants.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/*
import this library for Talon:
https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix-frc2022-latest.json
 */

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sim.PhysicsSim;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Shooter extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  private NetworkTableEntry nte_ShooterTargetRPM = tab.add("ShooterTargetRPM", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .getEntry();

      private NetworkTableEntry nte_ShooterTopRPM = tab.add("ShooterTopRPM", 0)
      .withWidget(BuiltInWidgets.kNumberBar)
      .withProperties(Map.of("min", 0.0, "max", 10000.0))
      .getEntry();

      private NetworkTableEntry nte_ShooterBtmRPM = tab.add("ShooterBtmRPM", 0)
      .withWidget(BuiltInWidgets.kNumberBar)
      .withProperties(Map.of("min", 0.0, "max", 10000.0))
      .getEntry();

      WPI_TalonFX m_top;
  
  /**Fake top motorRPM for testing shuffleboard */
  public double frontRPM;

  /**Fake bottom motorRPM for testing shuffleboard */
  public double rearRPM;

  /**Desired top and bottom RPM */
  public double targetRPM; 

  /** Creates a new Shooter. */
  public Shooter() {
    m_top = new WPI_TalonFX(shooter_top_motor);
    m_top.configFactoryDefault();
   
    //just guessing here KSM 2022-03-03
    m_top.configMotionAcceleration(1000);
   
  }

  @Override
  public void periodic() {  
    // This method will be called once per scheduler run
    targetRPM = nte_ShooterTargetRPM.getDouble(0);
    nte_ShooterTopRPM.setDouble(m_top.getSelectedSensorVelocity());
    nte_ShooterBtmRPM.setDouble(0);
  }

  /**Called when button is pressed */
public void shooterStart(){
    m_top.set(ControlMode.PercentOutput, targetRPM);
  }

  /**Called when button is released */
  public void shooterStop(){
    m_top.set(ControlMode.PercentOutput, 0);

  }

  public void simulationInit() {
	  PhysicsSim.getInstance().addTalonFX(m_top, 0.75, 20660);
	}

  @Override
  public void simulationPeriodic()
  {
    PhysicsSim.getInstance().run();
  }



}
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
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

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

  public WPI_TalonFX m_top;
  public WPI_TalonFX m_btm;

  /**Desired Shooter rpm for both motors */
  public double targetRPM;

  /** Creates a new Shooter. */
  public Shooter() {
    m_top = new WPI_TalonFX(Constants.shooter_top_motor);
    m_btm = new WPI_TalonFX(Constants.shooter_bottom_motor);
    
    /* Factory Default all hardware to prevent unexpected behaviour */
		m_top.configFactoryDefault();
		m_btm.configFactoryDefault();

		/* Config neutral deadband to be the smallest possible */
		m_top.configNeutralDeadband(0.002);
		m_btm.configNeutralDeadband(0.002);

		/* Config sensor used for Primary PID [Velocity] */
		m_top.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
				Constants.kTimeoutMs);
		m_btm.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
				Constants.kTimeoutMs);

		/* Config the peak and nominal outputs */
		m_top.configNominalOutputForward(0, Constants.kTimeoutMs);
		m_top.configNominalOutputReverse(0, Constants.kTimeoutMs);
		m_top.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_top.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		m_btm.configNominalOutputForward(0, Constants.kTimeoutMs);
		m_btm.configNominalOutputReverse(0, Constants.kTimeoutMs);
		m_btm.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_btm.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		m_top.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		m_top.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		m_top.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
		m_top.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);

		m_btm.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		m_btm.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		m_btm.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
		m_btm.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    targetRPM = nte_ShooterTargetRPM.getDouble(-1);
    nte_ShooterTopRPM.setValue(m_top.getSelectedSensorVelocity(0));
    nte_ShooterBtmRPM.setValue(m_btm.getSelectedSensorVelocity(0));
    
  }

  /** Called when button is pressed */
  public void shooterStartASync() {
    m_top.set(ControlMode.Velocity, targetRPM);
    m_btm.set(ControlMode.Velocity, targetRPM);
  }

  /** Called when button is released */
  public void shooterStopASync() {
    m_top.set(ControlMode.PercentOutput, 0);
    m_btm.set(ControlMode.PercentOutput, 0);
  }

}
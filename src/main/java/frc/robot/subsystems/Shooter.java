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
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Shooter extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  private NetworkTableEntry nte_ShooterTargetRPM = tab.add("ShooterTargetRPM", 3000)
      .withWidget(BuiltInWidgets.kTextView)
      .withProperties(Map.of("min", 0.0, "max", 6380.0))
      .withPosition(0,0)
      .getEntry();

  private NetworkTableEntry nte_ShooterTopRPM = tab.add("ShooterTopRPM", 0)
      .withWidget(BuiltInWidgets.kNumberBar)
      .withProperties(Map.of("min", 0.0, "max", 6380.0))
      .withPosition(2,0)
      .getEntry();

  private NetworkTableEntry nte_ShooterBtmRPM = tab.add("ShooterBtmRPM", 0)
      .withWidget(BuiltInWidgets.kNumberBar)
      .withProperties(Map.of("min", 0.0, "max", 6380.0))
      .withPosition(3, 0)
      .getEntry();

    private NetworkTableEntry nte_kP= tab.add("kP",0.1)
      .withWidget(BuiltInWidgets.kTextView)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .withPosition(0, 1)
      .getEntry();
  
    private NetworkTableEntry nte_kI= tab.add("kI",0.001)
      .withWidget(BuiltInWidgets.kTextView)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .withPosition(0, 2)
      .getEntry();
  
    private NetworkTableEntry nte_kD= tab.add("kD",0.1)
      .withWidget(BuiltInWidgets.kTextView)
      .withProperties(Map.of("min", 0.0, "max", 10.0))
      .withPosition(0, 3)
      .getEntry();
  
    private NetworkTableEntry nte_kF= tab.add("kF",0.00495159)
      .withWidget(BuiltInWidgets.kTextView)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .withPosition(0, 4)
      .getEntry();
      
    private NetworkTableEntry nte_Iz= tab.add("Iz",300)
      .withWidget(BuiltInWidgets.kTextView)
      .withProperties(Map.of("min", 0, "max", 1000))
      .withPosition(1, 1)
      .getEntry();
  
    private NetworkTableEntry nte_Peak= tab.add("Peak",1.0)
      .withWidget(BuiltInWidgets.kTextView)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .withPosition(1, 2)
      .getEntry();
  
      private NetworkTableEntry nte_Vplot= tab.add("Velocity",1.0)
      .withWidget(BuiltInWidgets.kGraph)
      //.withProperties(Map.of("min", 0.0, "max", 1.0))
      .withPosition(2, 1)
      .getEntry();

      private NetworkTableEntry nte_Eplot= tab.add("Error",1.0)
      .withWidget(BuiltInWidgets.kGraph)
      //.withProperties(Map.of("min", 0.0, "max", 1.0))
      .withPosition(2, 4)
      .getEntry();
  
    WPI_TalonFX m_top;

  /** Desired top and bottom RPM */
  public double targetRPM;

  /** Creates a new Shooter. */
  public Shooter() {
    m_top = new WPI_TalonFX(shooter_top_motor);
    m_top.configFactoryDefault();

    // just guessing here KSM 2022-03-03
    m_top.configMotionAcceleration(1000);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    targetRPM = nte_ShooterTargetRPM.getDouble(0);
    var topRPM = m_top.getSelectedSensorVelocity() / 3.4133;
    nte_ShooterTopRPM.setDouble(topRPM);
    nte_ShooterBtmRPM.setDouble(0);
    nte_Vplot.setValue(m_top.getSelectedSensorVelocity());
    nte_Eplot.setValue(m_top.getErrorDerivative());

    /* Config the Velocity closed loop gains in slot0 */
		m_top.config_kF(kPIDLoopIdx, nte_kF.getDouble(0.00495159), kTimeoutMs);
		m_top.config_kP(kPIDLoopIdx, nte_kP.getDouble(0.1), kTimeoutMs);
		m_top.config_kI(kPIDLoopIdx, nte_kI.getDouble(0.001), kTimeoutMs);
		m_top.config_kD(kPIDLoopIdx, nte_kD.getDouble(5), kTimeoutMs);
	
  }

  /**Called when button is pressed */
public void shooterStart(){
    m_top.set(ControlMode.Velocity, targetRPM*3.4133);
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

  public void robotInit() {
    m_top.configFactoryDefault();
		
		/* Config neutral deadband to be the smallest possible */
		m_top.configNeutralDeadband(0.001);

		/* Config sensor used for Primary PID [Velocity] */
        m_top.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                                            kPIDLoopIdx, 
											                      kTimeoutMs);
											

		/* Config the peak and nominal outputs */
		m_top.configNominalOutputForward(0, kTimeoutMs);
		m_top.configNominalOutputReverse(0, kTimeoutMs);
		m_top.configPeakOutputForward(1, kTimeoutMs);
		m_top.configPeakOutputReverse(-1, kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		m_top.config_kF(kPIDLoopIdx, kGains_Velocit.kF, kTimeoutMs);
		m_top.config_kP(kPIDLoopIdx, kGains_Velocit.kP, kTimeoutMs);
		m_top.config_kI(kPIDLoopIdx, kGains_Velocit.kI, kTimeoutMs);
		m_top.config_kD(kPIDLoopIdx, kGains_Velocit.kD, kTimeoutMs);
		/*
		 * Talon FX does not need sensor phase set for its integrated sensor
		 * This is because it will always be correct if the selected feedback device is integrated sensor (default value)
		 * and the user calls getSelectedSensor* to get the sensor's position/velocity.
		 * 
		 * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#sensor-phase
		 */
        // m_top.setSensorPhase(true);
  }



}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Extends roller(?) to grab cargo and advance it to indexer.
 */
public class Intake extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  /** Opens (forward) and closes intake arm */
  private DoubleSolenoid intakeSolenoid =  new DoubleSolenoid(Constants.PHubdID, Constants.PHubType, Constants.IntakeSolenoidForwardChannel, Constants.IntakeSolenoidReverseChannel);

  /**deivers cargo to indexer */
  private TalonFX intakeMotor;

  private PWMSparkMax indexMotor;

  public Intake() {
    super();
    intakeSolenoid.set(Value.kReverse);
    intakeMotor = new TalonFX(Constants.IntakeCanID);
    indexMotor = new PWMSparkMax(Constants.IndexerPWM);

    //just guessing here KSM 2022-03-03
    intakeMotor.configMotionAcceleration(4000);
    
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**Called when button is pressed */
  public void IntakeCargoStartAsync() {
      
      // extend pickup arm
      intakeSolenoid.set(Value.kForward);

      //give arm time to extend
      try { Thread.sleep(100);} 
      catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }

      //start motors
      intakeMotor.set(ControlMode.PercentOutput,.4);
      indexMotor.set(.4);
  }

  /**Called when button is released */
  public void IntakeCargoStoptAsync() {
      //stop motors
      intakeMotor.set(ControlMode.PercentOutput,0);
      indexMotor.set(0);
      // extend pickup arm
      intakeSolenoid.set(Value.kReverse);

      //give arm time to extend
      try { Thread.sleep(100);} 
      catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }
  }
}

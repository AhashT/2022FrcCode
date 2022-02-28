// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/**
 * Extends roller(?) to grab cargo and advance it to indexer.
 */
public class Intake extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  /** Opens (forward) and closes intake arm */
  private DoubleSolenoid intakeSolenoid =  new DoubleSolenoid(Constants.PHubdID, Constants.PHubType, Constants.IntakeSolenoidForwardChannel, Constants.IntakeSolenoidReverseChannel);

  /**deivers cargo to indexer */
  private PWMSparkMax intakeMotor;

  public Intake() {
    super();
    intakeSolenoid.set(Value.kReverse);
    intakeMotor = new PWMSparkMax(Constants.IntakePWM);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void IntakeCargoAsync() {
    new Thread(() -> {
      // Do whatever
      // cannot intake if cargo is already in intake
      if (RobotContainer.IntakeSensor.get())
        return;

      // extend pickup arm
      intakeSolenoid.set(Value.kForward);

      //give arm time to extend
      try { Thread.sleep(500);} 
      catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }

      //run motor until cargo is loaded or half second elapses
      intakeMotor.set(.5);
      long timeout = System.currentTimeMillis()+500;
      while(RobotContainer.IntakeSensor.get()==false)
      {
        try { Thread.sleep(10);} 
        catch (InterruptedException e) {break;}

        if(System.currentTimeMillis()>timeout)break;
      }
      intakeMotor.set(0);  
    }).start();
  }
}

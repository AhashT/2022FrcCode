// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

/**
 * Extends roller(?) to grab cargo and advance it to indexer.
 */
public class Intake extends SubsystemBase {
  private ShuffleboardTab tab = Shuffleboard.getTab("Intake");
  private NetworkTableEntry nte_IntakeTargetRPM = tab.add("IntakeTargetRPM", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .getEntry();

  /** Opens (forward) and closes intake arm */

  /** deivers cargo to indexer */
  private TalonFX intakeMotor;

  //private PWMSparkMax indexMotor;
  private double targetRPM;

  private final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PHubdID, PHubType, IntakeSolenoidForwardChannel,
      IntakeSolenoidReverseChannel);

  public Intake() {
    intakeSolenoid.set(Value.kReverse);
    intakeMotor = new TalonFX(IntakeCanID);
    //indexMotor = new PWMSparkMax(IndexerPWM);

    // just guessing here KSM 2022-03-03
    intakeMotor.configMotionAcceleration(4000);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    targetRPM = nte_IntakeTargetRPM.getDouble(0.4);

  }

  /** Called when button is pressed */
  public void IntakeCargoStartAsync() {

    // extend pickup arm
    intakeSolenoid.set(Value.kForward);

    // give arm time to extend
    /*
     * try { Thread.sleep(100);}
     * catch (InterruptedException e) {
     * e.printStackTrace();
     * return;
     * }
     */

    // start motors
    intakeMotor.set(ControlMode.PercentOutput, targetRPM);
    //indexMotor.set(.4);
  }

  /** Called when button is released */
  public void IntakeCargoStoptAsync() {
    // stop motors
    intakeMotor.set(ControlMode.PercentOutput, 0);
    //indexMotor.set(0);
    // extend pickup arm
    intakeSolenoid.set(Value.kReverse);

    // give arm time to extend
    /*
     * try { Thread.sleep(100);}
     * catch (InterruptedException e) {
     * e.printStackTrace();
     * return;
     * }
     */
  }
}

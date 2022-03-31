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
  private NetworkTableEntry nte_IntakePower = tab.add("IntakePower", 0)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0.0, "max", 1.0))
      .getEntry();

      private NetworkTableEntry nte_IntakeStart_button = tab.add("Start", false)
      .withWidget(BuiltInWidgets.kToggleButton)
      .withPosition(1, 0)
      .getEntry();


  /** Opens (forward) and closes intake arm */

  /** deivers cargo to indexer */
  private TalonFX intakeMotor;

  private double intakePower = IntakePower;

  private final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PHubdID, PHubType, IntakeSolenoidForwardChannel,
      IntakeSolenoidReverseChannel);
  private boolean startButtonPressed;
  private boolean testRunning;

  public Intake() {
    intakeSolenoid.set(Value.kReverse);
    intakeMotor = new TalonFX(IntakeCanID);
   
    // just guessing here KSM 2022-03-03
    intakeMotor.configMotionAcceleration(4000);
  }

  @Override
  public void periodic() {
  }

  
  public void IntakeStart(boolean reverse) {
    System.out.println("IntakeStart: "+intakePower*(reverse?-1.0:1.0));
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

    intakeMotor.set(ControlMode.PercentOutput, intakePower*(reverse?-1.0:1.0));
  }

  /** Called when button is released */
  public void IntakeStop() {
    System.out.println("IntakeStop");
    intakeMotor.set(ControlMode.PercentOutput, 0);
    // retract pickup arm
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

public void testInit(){
  nte_IntakePower.setDouble(intakePower);
}

public void testPeriodic() {
  intakePower = nte_IntakePower.getDouble(0.4);

  /* check for test button state change */
  startButtonPressed = nte_IntakeStart_button.getBoolean(false);
  if (startButtonPressed) {
          if (!testRunning) {
                  IntakeStart(false);
                  testRunning = true;
          }
  } else {
          if (testRunning) {
                  IntakeStop();
                  testRunning = false;
          }
  }
  
  }
}

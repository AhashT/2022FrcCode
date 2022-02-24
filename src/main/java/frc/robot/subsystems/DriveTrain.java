// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveTrain extends SubsystemBase {
 
  CANSparkMax m_L1;
  CANSparkMax m_L2;
  CANSparkMax m_R1;
  CANSparkMax m_R2;
  
  MotorControllerGroup m_Lcontroller;
  MotorControllerGroup m_Rcontroller;

  DifferentialDrive drive;
  XboxController xbox;

  /** Creates a new DriveTrain. */
  public DriveTrain() {

     m_L1 = new CANSparkMax(Constants.left_motor1, MotorType.kBrushless);
     m_L2 = new CANSparkMax(Constants.left_motor2, MotorType.kBrushless);
     m_R1 = new CANSparkMax(Constants.right_motor1, MotorType.kBrushless);
     m_R2 = new CANSparkMax(Constants.right_motor2, MotorType.kBrushless);
  
     m_Lcontroller = new MotorControllerGroup(m_L1, m_L2);
     m_Rcontroller = new MotorControllerGroup(m_R1, m_R2);
    
     drive = new DifferentialDrive(m_Lcontroller, m_Rcontroller); 

     m_L1.setInverted(false);
     m_L2.setInverted(false);
     m_R1.setInverted(true);
     m_R2.setInverted(true);

     xbox = new XboxController(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveJoysticks(XboxController dJoystick, double speed){
    
    drive.arcadeDrive(dJoystick.getRawAxis((int) (Constants.controller_y_axis*speed)), dJoystick.getRawAxis((int) (Constants.controller_x_axis*speed)));
    //drive.tankDrive(xbox.getLeftY(), xbox.getRightY() * -1.0);
    drive.arcadeDrive(xbox.getLeftY(), -1.0 * xbox.getRightX());
  }

  public void driveForward(double speed){
    drive.tankDrive(speed, speed);
  }

  public void stop(){
    drive.stopMotor();
  }
}

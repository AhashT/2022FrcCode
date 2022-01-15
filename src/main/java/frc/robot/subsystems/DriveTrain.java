// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveTrain extends SubsystemBase {
 
  PWMSparkMax m_L1;
  PWMSparkMax m_L2;
  PWMSparkMax m_R1;
  PWMSparkMax m_R2;
  
  MotorControllerGroup m_Lcontroller;
  MotorControllerGroup m_Rcontroller;

  DifferentialDrive drive;

  /** Creates a new DriveTrain. */
  public DriveTrain() {

     m_L1 = new PWMSparkMax(Constants.left_motor1);
     m_L2 = new PWMSparkMax(Constants.left_motor2);
     m_R1 = new PWMSparkMax(Constants.right_motor1);
     m_R2 = new PWMSparkMax(Constants.right_motor2);
     m_Lcontroller = new MotorControllerGroup(m_L1, m_L2);
     m_Rcontroller = new MotorControllerGroup(m_R1, m_R2);
    
     drive = new DifferentialDrive(m_Lcontroller, m_Rcontroller); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveJoysticks(XboxController controller1, double speed){
    
    drive.arcadeDrive(controller1.getRawAxis((int) (Constants.controller_y_axis*speed)), controller1.getRawAxis((int) (Constants.controller_x_axis*speed)));
  }

  public void driveForward(double speed){
    drive.tankDrive(speed, speed);
  }

  public void stop(){
    drive.stopMotor();
  }
}

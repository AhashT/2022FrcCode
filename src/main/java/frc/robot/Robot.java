// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  public double shooterRPM;


 @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();   
    m_robotContainer.robotInit();  
  }

  @Override
  public void robotPeriodic(){
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit(){}

  @Override
  public void disabledPeriodic(){}

  @Override
  public void autonomousInit(){
  }

  @Override
  public void autonomousPeriodic(){
    m_autonomousCommand.execute();
  }

  @Override
  public void teleopInit(){
   }

  @Override
  public void teleopPeriodic(){}

  @Override
  public void testInit(){}
  
  @Override
  public void testPeriodic(){}

  public void simulationInit() {
    m_robotContainer.simulationInit();
  }

	
}

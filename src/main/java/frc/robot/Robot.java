// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.interfaces.LimelightInterface.ledMode;


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
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_robotContainer = new RobotContainer();   
    m_robotContainer.robotInit(); 
    Subsystems.DRIVE_SUBSYSTEM.resetEncoders();
    Subsystems.GYRO_SUBSYSTEM.zero();
    Subsystems.LIMELIGHT_SUBSYSTEM.Limelight.setLEDMode(ledMode.OFF);
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
    m_autonomousCommand = m_robotContainer.getAutonmousCommand();
  }

  @Override
  public void autonomousPeriodic(){
    m_autonomousCommand.execute();
  }

  @Override
  public void teleopInit(){
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();

      /**For debugging - remove before competition */
      m_autonomousCommand.initialize();
   }
  }

  @Override
  public void teleopPeriodic(){

  }

  @Override
  public void testInit(){
    m_robotContainer.testInit();
  }
  
  @Override
  public void testPeriodic(){
    m_robotContainer.testPeriodic();
  }

  public void simulationInit() {
    m_robotContainer.simulationInit();
  }

	
}

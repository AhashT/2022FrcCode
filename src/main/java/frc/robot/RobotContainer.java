// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.StartShooter;
import frc.robot.commands.StopShooter;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

public class RobotContainer {
    private final XboxController xbox = new XboxController(port_number);
    private final Shooter shooter = new Shooter();
    private final StartShooter startShooter = new StartShooter(shooter);
    private final StopShooter stopShooters = new StopShooter(shooter);
     
    public RobotContainer(){        
                
        /**X button */
        JoystickButton shootButton = new JoystickButton(xbox, 1);
        shootButton.whenPressed(startShooter);
        shootButton.whenReleased(stopShooters);

    }

    public void simulationInit() {
        shooter.simulationInit();
	}

    public void robotInit() {
        shooter.robotInit();
    }

    public void testInit(){
        shooter.testInit();
    }
    
    public void testPeriodic(){
        shooter.testPeriodic();
    }
  
}
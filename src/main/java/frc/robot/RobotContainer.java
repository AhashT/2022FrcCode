// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StartShooter;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StopShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

public class RobotContainer {
    private final XboxController xbox = new XboxController(port_number);
    private final DriveTrain driveT= new DriveTrain();
    private final DriveWithJoysticks driveWithJoysticks = new DriveWithJoysticks(driveT, xbox);;
    private final Shooter shooter = new Shooter();
     private final Intake intake = new Intake();        
    private final StartIntake startIntake = new StartIntake(intake);
    private final StopIntake stopIntake = new StopIntake(intake);
    private final StartShooter startShooter = new StartShooter(shooter);
    private final StopShooter stopShooters = new StopShooter(shooter);
     
    public RobotContainer(){        
        driveT.setDefaultCommand(driveWithJoysticks);        
                
        /**Right bumper */
        JoystickButton intakeButton = new JoystickButton(xbox, 6);
        intakeButton.whenPressed(startIntake);
        intakeButton.whenReleased(stopIntake);        

        /**X button */
        JoystickButton shootButton = new JoystickButton(xbox, 1);
        shootButton.whenPressed(startShooter);
        shootButton.whenReleased(stopShooters);


        // Autonomous

        // Sequence
         //driveTIMED = new SequentialCommandGroup(driveBackward.andThen(driveForward));
    }

    public Command getAutonmousCommand(){
        return new DriveTimed(driveT, -0.4, 4).andThen(new DriveTimed(driveT, 0.4, 4));
    }
}
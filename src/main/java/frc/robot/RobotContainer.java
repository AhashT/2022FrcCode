// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DoNothing;
import frc.robot.commands.PIDDriveInches;
import frc.robot.commands.PIDRotateDegrees;
import frc.robot.commands.ResetEncoderCommand;
import frc.robot.commands.RotateVisionCommand;
import frc.robot.subsystems.DriveSubsystem;

import static frc.robot.Constants.*;

public class RobotContainer {
    //private final DriveWithJoysticks driveWithJoysticks = new DriveWithJoysticks(driveT, xbox);;
    //private final DriveForwardTimed driveForwardTimed = new DriveForwardTimed(driveT);
    //private final Shooter shooter = new Shooter();
    //private final Feeder feeder = new Feeder();
    //private final Intake intake = new Intake();        

    public RobotContainer() {
        
        Subsystems.setDefaultCommands();
        //Input.BUTTON_A.whileHeld(new RotateVisionCommand(Subsystems.DRIVE_SUBSYSTEM, Subsystems.LIMELIGHT_SUBSYSTEM), true);
        Input.BUTTON_A.toggleWhenPressed(new PIDDriveInches(Subsystems.DRIVE_SUBSYSTEM, 60));
        Input.BUTTON_B.toggleWhenPressed(new PIDRotateDegrees(Subsystems.DRIVE_SUBSYSTEM, Subsystems.GYRO_SUBSYSTEM, 90));
        /**Right bumper */
        //JoystickButton intakeButton = new JoystickButton(xbox, 6);
        //intakeButton.whenPressed(startIntake);
        //intakeButton.whenReleased(stopIntake);        

        /** X button */
        //JoystickButton shootButton = new JoystickButton(xbox, 1);
        //shootButton.whenPressed(startShooter
        //.andThen(waitForTargetRPM)
        //.andThen(feedOne));
        //shootButton.whenReleased(stopShooters);
    }
    
    public void simulationInit() {
        //shooter.simulationInit();
    }

    public void robotInit() {
        //shooter.robotInit();
  }

    public void testInit() {
        //shooter.testInit();
    }

    public void testPeriodic() {
        //shooter.testPeriodic();
    }

    // public Command getAutonmousCommand(){
    //     return driveForwardTimed;
    // }
}
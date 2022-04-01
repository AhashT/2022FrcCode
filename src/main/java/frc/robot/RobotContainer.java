// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.port_number;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ApproachVisionCommand;
import frc.robot.commands.DelayCommand;
import frc.robot.commands.DoNothing;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.FeedOne;
import frc.robot.commands.PIDDriveInches;
import frc.robot.commands.PIDRotateDegrees;
import frc.robot.commands.ResetEncoderCommand;
import frc.robot.commands.RunShooterReverse;
import frc.robot.commands.StartIndexWheel;
import frc.robot.commands.StartIndexWheelReverse;
import frc.robot.commands.StartIndexer;
import frc.robot.commands.StartIndexerReverse;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StartIntakeReverse;
import frc.robot.commands.StartShooter;
import frc.robot.commands.WaitForTargetRPM;
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
        //Input.BUTTON_A.toggleWhenPressed(new PIDDriveInches(Subsystems.DRIVE_SUBSYSTEM, 60));
        //Input.BUTTON_B.toggleWhenPressed(new PIDRotateDegrees(Subsystems.DRIVE_SUBSYSTEM, Subsystems.GYRO_SUBSYSTEM, 90));
        Input.BUTTON_B.whileHeld(new ApproachVisionCommand(Subsystems.DRIVE_SUBSYSTEM, Subsystems.LIMELIGHT_SUBSYSTEM), false);
        
        Input.intakeButton.whileHeld(new StartIntake(Subsystems.INTAKE_SUBSYSTEM).alongWith(new StartIndexer(Subsystems.INDEXER_SUBSYSTEM).alongWith(new StartIndexWheel(Subsystems.INDEXER_WHEEL_SUBSYSTEM))));//.alongWith(new RunShooterReverse()));            
        /**Left bumper  - Intake Reverse*/
        Input.intakeReverseButton.whileHeld(new StartIntakeReverse(Subsystems.INTAKE_SUBSYSTEM).alongWith(new StartIndexerReverse(Subsystems.INDEXER_SUBSYSTEM).alongWith(new StartIndexWheelReverse(Subsystems.INDEXER_WHEEL_SUBSYSTEM))));             

        /** X button - Shoot */
        Input.shootButton.whileHeld(
            new ParallelCommandGroup(
                new ParallelCommandGroup(
                    new StartShooter(Subsystems.SHOOTER_SUBSYSTEM),
                    new SequentialCommandGroup(
                        new DelayCommand().withTimeout(0.7),
                        new ParallelCommandGroup(
                            new FeedOne(Subsystems.FEEDER_SUBSYSTEM),
                            new StartIndexer(Subsystems.INDEXER_SUBSYSTEM),
                            new StartIndexWheel(Subsystems.INDEXER_WHEEL_SUBSYSTEM)
                        )
                    )
                )
            )
        );
 
    }
    
    public void simulationInit() {
        Subsystems.SHOOTER_SUBSYSTEM.simulationInit();        
    }

    public void robotInit() {
        Subsystems.SHOOTER_SUBSYSTEM.robotInit();
    }

    public void testInit() {
        Subsystems.SHOOTER_SUBSYSTEM.testInit();
        Subsystems.INTAKE_SUBSYSTEM.testInit();
        Subsystems.INDEXER_SUBSYSTEM.testInit();
        Subsystems.INDEXER_WHEEL_SUBSYSTEM.testInit();
        Subsystems.FEEDER_SUBSYSTEM.testInit();
    }

    public void testPeriodic() {
        Subsystems.SHOOTER_SUBSYSTEM.testPeriodic();
        Subsystems.INTAKE_SUBSYSTEM.testPeriodic();
        Subsystems.INDEXER_SUBSYSTEM.testPeriodic();
        Subsystems.INDEXER_WHEEL_SUBSYSTEM.testPeriodic();
        Subsystems.FEEDER_SUBSYSTEM.testPeriodic();
   }

    public Command getAutonmousCommand() {
        return new DriveForwardTimed(Subsystems.DRIVE_SUBSYSTEM);
    }
}
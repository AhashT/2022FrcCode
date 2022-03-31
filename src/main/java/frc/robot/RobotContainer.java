// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.port_number;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.FeedOne;
import frc.robot.commands.StartIndexWheel;
import frc.robot.commands.StartIndexWheelReverse;
import frc.robot.commands.StartIndexer;
import frc.robot.commands.StartIndexerReverse;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StartIntakeReverse;
import frc.robot.commands.StartShooter;
import frc.robot.commands.WaitForTargetRPM;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IndexerWheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
    private final XboxController xbox = new XboxController(port_number);
    private final DriveTrain driveT = new DriveTrain();
    private final DriveWithJoysticks driveWithJoysticks = new DriveWithJoysticks(driveT, xbox);;
    private final DriveForwardTimed driveForwardTimed = new DriveForwardTimed(driveT);
    private final Shooter shooter = new Shooter();
    private final Feeder feeder = new Feeder();
    private final Indexer indexer = new Indexer();
    private final IndexerWheel indexerWheel = new IndexerWheel();
    private final Intake intake = new Intake();
    private final StartShooter startShooter = new StartShooter(shooter);
    private final WaitForTargetRPM waitForTargetRPM = new WaitForTargetRPM(shooter);
    private final FeedOne feedOne = new FeedOne(feeder);

    public RobotContainer() {
        driveT.setDefaultCommand(driveWithJoysticks);        

        /**Right bumper  - Intake */
        JoystickButton intakeButton = new JoystickButton(xbox, 6);
        intakeButton.whileHeld(new StartIntake(intake).alongWith(new StartIndexer(indexer).alongWith(new StartIndexWheel(indexerWheel))));             

        /**Left bumper  - Intake Reverse*/
        JoystickButton intakeReverseButton = new JoystickButton(xbox, 5);
        intakeReverseButton.whileHeld(new StartIntakeReverse(intake).alongWith(new StartIndexerReverse(indexer).alongWith(new StartIndexWheelReverse(indexerWheel))));             

        /** X button - Shoot */
        JoystickButton shootButton = new JoystickButton(xbox, 1);
        shootButton.whileHeld(startShooter
        //.andThen(waitForTargetRPM)
        .andThen(feedOne.alongWith(new StartIndexer(indexer).alongWith(new StartIndexWheel(indexerWheel)))));
        
    }

    public void simulationInit() {
        shooter.simulationInit();        
    }

    public void robotInit() {
        shooter.robotInit();
    }

    public void testInit() {
        shooter.testInit();
        intake.testInit();
        indexer.testInit();
        indexerWheel.testInit();
        feeder.testInit();
    }

    public void testPeriodic() {
        shooter.testPeriodic();
        intake.testPeriodic();
        indexer.testPeriodic();
        indexerWheel.testPeriodic();
        feeder.testPeriodic();
   }

    public Command getAutonmousCommand() {
        return driveForwardTimed;
    }
}
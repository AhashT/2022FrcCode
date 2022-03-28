// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveBackwardTimed;
import frc.robot.commands.DriveTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StartShooter;
import frc.robot.commands.StopShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

    private final DriveWithJoysticks driveWithJoysticks;
    private final DriveTrain driveT;
    private final DriveBackwardTimed driveForwardTimed;
    public static XboxController xbox;
    public final Shooter shooter;
    public final Intake intake;         
    public final PneumaticHub PHub;
    public final DriveTimed driveBackward;
    public final DriveTimed driveForward;
    private SequentialCommandGroup driveTIMED;

    public RobotContainer(){
        xbox = new XboxController(Constants.port_number);

        driveT = new DriveTrain();
        driveWithJoysticks = new DriveWithJoysticks(driveT, xbox);
        driveWithJoysticks.addRequirements(driveT);
        driveT.setDefaultCommand(driveWithJoysticks);

        intake = new Intake();
        StartIntake startIntake = new StartIntake(intake);
        startIntake.addRequirements(intake);
        StopIntake stopIntake = new StopIntake(intake);
        stopIntake.addRequirements(intake);
       
        /**Right bumper */
        JoystickButton intakeButton = new JoystickButton(xbox, XboxController.Button.kRightBumper.ordinal());
        intakeButton.whenPressed(startIntake);
        intakeButton.whenReleased(stopIntake);
        
        shooter = new Shooter();
        StartShooter startShooter = new StartShooter(shooter);
        startShooter.addRequirements(shooter);
        StopShooter stopShooters = new StopShooter(shooter);
        stopShooters.addRequirements(shooter);

        /**X button */
        JoystickButton shootButton = new JoystickButton(xbox, XboxController.Button.kX.ordinal());
        shootButton.whenPressed(startShooter);
        shootButton.whenReleased(stopShooters);

        PHub = new PneumaticHub(Constants.PHubdID);

        // Drive Forward Test Auto
        driveForwardTimed = new DriveBackwardTimed(driveT);
        driveForwardTimed.addRequirements(driveT);
        // Autonomous
        driveForward = new DriveTimed(driveT, 0.4, 4);
        driveForward.addRequirements(driveT);
        driveBackward = new DriveTimed(driveT, -0.4, 4);
        driveBackward.addRequirements(driveT);

        // Sequence
         //driveTIMED = new SequentialCommandGroup(driveBackward.andThen(driveForward));
    }

    public Command getAutonmousCommand(){
        return driveForwardTimed;
    }
}
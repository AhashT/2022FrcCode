// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StopIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController.Button;

public class RobotContainer {

    private final DriveWithJoysticks driveWithJoysticks;
    private final DriveTrain driveT;
    private final DriveForwardTimed driveForwardTimed;
    public static XboxController xbox;
    public final Shooter shooter;
    public final Intake intake;         
    public final PneumaticHub PHub;

    public RobotContainer(){
        driveT = new DriveTrain();
        driveWithJoysticks = new DriveWithJoysticks(driveT);
        driveWithJoysticks.addRequirements(driveT);
        driveT.setDefaultCommand(driveWithJoysticks);

        driveForwardTimed = new DriveForwardTimed(driveT);
        driveForwardTimed.addRequirements(driveT);

        xbox = new XboxController(Constants.port_number);

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
        PHub = new PneumaticHub(Constants.PHubdID);
    }

    public Command getAutonmousCommand(){
        return driveForwardTimed;
    }
}
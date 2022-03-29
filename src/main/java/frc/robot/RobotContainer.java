// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.FeedOne;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StartShooter;
import frc.robot.commands.StopFeeder;
import frc.robot.commands.WaitForTargetRPM;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

public class RobotContainer {
    private final XboxController xbox = new XboxController(port_number);
    private final DriveTrain driveT= new DriveTrain();
    private final DriveWithJoysticks driveWithJoysticks = new DriveWithJoysticks(driveT, xbox);;
    private final DriveForwardTimed driveForwardTimed = new DriveForwardTimed(driveT);
    private final Shooter shooter = new Shooter();
    private final Feeder feeder = new Feeder();
    private final Intake intake = new Intake();        
    private final StartIntake startIntake = new StartIntake(intake);
    private final StopFeeder stopFeeder = new StopFeeder(feeder);
    private final StartShooter startShooter = new StartShooter(shooter);
    private final WaitForTargetRPM waitForTargetRPM = new WaitForTargetRPM(shooter);
    private final FeedOne feedOne = new FeedOne(feeder);

    public RobotContainer() {
        driveT.setDefaultCommand(driveWithJoysticks);        

        /**Right bumper */
        JoystickButton intakeButton = new JoystickButton(xbox, 6);
        intakeButton.whileHeld(startIntake);             

        /** X button */
        JoystickButton shootButton = new JoystickButton(xbox, 1);
        shootButton.whileHeld(startShooter
        //.andThen(waitForTargetRPM)
        .andThen(feedOne));
    }

    public void simulationInit() {
        shooter.simulationInit();
    }

    public void robotInit() {
        shooter.robotInit();
  }

    public void testInit() {
        shooter.testInit();
    }

    public void testPeriodic() {
        shooter.testPeriodic();
    }

 public Command getAutonmousCommand(){
        return driveForwardTimed;
    }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.PneumaticsModuleType;


public class RobotContainer {

    private final DriveWithJoysticks driveWithJoysticks;
    private final DriveTrain driveT;
    private final DriveForwardTimed driveForwardTimed;
    public static XboxController xbox;
    public final Shooter shooter;
    public final PneumaticHub PHub;

    /**
     * True if a cargo is loaded and ready to shoot
     */
    public static DigitalInput CargoSensor = new DigitalInput(Constants.CargoSensorDI);

    /**
     * True if a cargo has been presented to Indexer by Intake
     */
    public static DigitalInput IntakeSensor = new DigitalInput(Constants.IntakeSensorDI);

    public RobotContainer(){
        driveT = new DriveTrain();
        driveWithJoysticks = new DriveWithJoysticks(driveT);
        driveWithJoysticks.addRequirements(driveT);
        driveT.setDefaultCommand(driveWithJoysticks);

        driveForwardTimed = new DriveForwardTimed(driveT);
        driveForwardTimed.addRequirements(driveT);

        xbox = new XboxController(Constants.port_number);

        shooter = new Shooter();
        PHub = new PneumaticHub(Constants.PHubdID);
    }


    public Command getAutonmousCommand(){
        return driveForwardTimed;
    }
}
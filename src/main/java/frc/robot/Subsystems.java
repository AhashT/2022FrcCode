// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DoNothing;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/** Add your docs here. */
public class Subsystems {

    public static final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem();
    public static final LimelightSubsystem LIMELIGHT_SUBSYSTEM = new LimelightSubsystem();
    public static final ShooterSubsystem SHOOTER_SUBSYSTEM = new ShooterSubsystem();
    public static final IntakeSubsystem INTAKE_SUBSYSTEM = new IntakeSubsystem();
    public static final FeederSubsystem FEEDER_SUBSYSTEM = new FeederSubsystem();
    public static final ClimberSubsystem CLIMBER_SUBSYSTEM = new ClimberSubsystem();
    public static final GyroSubsystem GYRO_SUBSYSTEM = new GyroSubsystem();

    public static void setDefaultCommands() {
        //DRIVE_SUBSYSTEM.setDefaultCommand(new DriveWithJoysticks(DRIVE_SUBSYSTEM, Input.xbox));
        //DRIVE_SUBSYSTEM.setDefaultCommand(new DoNothing(DRIVE_SUBSYSTEM));
    }


}

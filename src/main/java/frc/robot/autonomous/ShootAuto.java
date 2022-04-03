// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Subsystems;
import frc.robot.commands.DelayCommand;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveForwardTimedReverse;
import frc.robot.commands.FeedOne;
import frc.robot.commands.StartIndexWheel;
import frc.robot.commands.StartIndexer;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StartShooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootAuto extends SequentialCommandGroup {
  /** Creates a new ShootAuto. */
  public ShootAuto() {
    // Add your commands in the addCommands() call, e.g.


  //   new ParallelCommandGroup(
  //     new StartShooter(Subsystems.SHOOTER_SUBSYSTEM).withTimeout(3),
  //     new SequentialCommandGroup(
  //         new DelayCommand().withTimeout(0.9),
  //         new ParallelCommandGroup(
  //             new FeedOne(Subsystems.FEEDER_SUBSYSTEM),
  //             new StartIndexer(Subsystems.INDEXER_SUBSYSTEM),
  //             new StartIndexWheel(Subsystems.INDEXER_WHEEL_SUBSYSTEM, Constants.IndexerWheelPowerShoot)).withTimeout(1)
  //             )
  //         ),
  // new DriveForwardTimed(Subsystems.DRIVE_SUBSYSTEM)


    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new ParallelCommandGroup(
            new StartShooter(Subsystems.SHOOTER_SUBSYSTEM).withTimeout(3),
            new SequentialCommandGroup(
                new DelayCommand().withTimeout(0.9),
                new ParallelCommandGroup(
                    new FeedOne(Subsystems.FEEDER_SUBSYSTEM),
                    new StartIndexer(Subsystems.INDEXER_SUBSYSTEM),
                    new StartIndexWheel(Subsystems.INDEXER_WHEEL_SUBSYSTEM, Constants.IndexerWheelPowerShoot)).withTimeout(1)
                    )
                ),
        new ParallelCommandGroup(
            new SequentialCommandGroup(
              new DelayCommand().withTimeout(1),
              new DriveForwardTimed(Subsystems.DRIVE_SUBSYSTEM, Constants.AutoSpeed).withTimeout(Constants.DriveForwardtime),
              new DelayCommand().withTimeout(.5),
              new DriveForwardTimed(Subsystems.DRIVE_SUBSYSTEM, -Constants.AutoSpeed).withTimeout(Constants.DriveForwardtime)
            ),
            new ParallelCommandGroup(
              new StartIntake(Subsystems.INTAKE_SUBSYSTEM).withTimeout(Constants.DriveForwardtime+2),
              new StartIndexer(Subsystems.INDEXER_SUBSYSTEM).withTimeout(Constants.DriveForwardtime+2),
              new StartIndexWheel(Subsystems.INDEXER_WHEEL_SUBSYSTEM, Constants.IndexerWheelPowerIntake).withTimeout(Constants.DriveForwardtime+2)
            )
      
        ),

        new ParallelCommandGroup(
            new StartShooter(Subsystems.SHOOTER_SUBSYSTEM).withTimeout(3),
            new SequentialCommandGroup(
                new DelayCommand().withTimeout(0.9),
                new ParallelCommandGroup(
                    new FeedOne(Subsystems.FEEDER_SUBSYSTEM),
                    new StartIndexer(Subsystems.INDEXER_SUBSYSTEM),
                    new StartIndexWheel(Subsystems.INDEXER_WHEEL_SUBSYSTEM, Constants.IndexerWheelPowerShoot)).withTimeout(1)
                    )
                )
    );

  }
}

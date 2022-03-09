// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/** Add your docs here. */
public class Constants {

    // CAN IDs
    public static final int left_front_motor  = 1;
    public static final int left_rear_motor  = 2;
    public static final int right_front_motor = 3;
    public static final int right_rear_motor = 4;
    public static final int shooter_top_motor = 5;
    public static final int shooter_bottom_motor = 6;
    public static final int ClimbLeaderCanID = 7;
    public static final int ClimbFollwerCanID = 8;
    public static final int PHubdID = 21;

    //PWM Channels
    public static final int IntakePWM = 0;
    public static final int IndexerPWM = 1;
 
    //Digital IO Channels
     
    //Pnuematic Hub 
    public static final PneumaticsModuleType PHubType = PneumaticsModuleType.REVPH;
    public static final int IntakeSolenoidForwardChannel = 0;
    public static final int IntakeSolenoidReverseChannel = 1;
    
    public static int controller_y_axis = 1;
    public static int controller_x_axis = 0; 

    public static final double driveTrainSpeed = 0.5;
    public static final int DriveForwardtime = 3;
    public static final double AutoSpeed = 0.2;
    public static final int port_number = 0; 

}

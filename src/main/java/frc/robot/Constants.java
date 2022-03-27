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
    public static final int shooter_btm_motor = 6;
    public static final int ClimbLeaderCanID = 7;
    public static final int ClimbFollwerCanID = 8;
    public static final int IntakeCanID = 9;
    public static final int PHubdID = 21;
    
    //PWM Channels
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
    public static final double AutoSpeed = -0.4;
    public static final int port_number = 0; 

    /***************************************************************/
    	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon FX supports multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 30;

	/**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 20660 represents Velocity units at 100% output
     * 
	 * 	                                    			  kP   	 kI    kD      kF          Iz    PeakOut */
   public final static Gains kGains_Velocit  = new Gains( 0.1, 0.001,  5, 1023.0/20660.0,  300,  1.00);
    public static ShooterGains[] GainsAr = new ShooterGains[] {
            new ShooterGains(4131, ShooterGains.top, new Gains(0.0075, 0.004, 0.5, 0.005, 300, 1.00)),
            new ShooterGains(5200, ShooterGains.top, new Gains(0.0075, 0.00035, 0.5, 0.005, 300, 1.00)),
            new ShooterGains(6380, ShooterGains.top, new Gains(0.0075, 0.00035, 0.5, 0.005, 300, 1.00)),
            new ShooterGains(4131, ShooterGains.btm, new Gains(0.0075, 0.004, 0.5, 0.005, 300, 1.00)),
            new ShooterGains(5200, ShooterGains.btm, new Gains(0.0075, 0.0004, 0.5, 0.005, 300, 1.00)),
            new ShooterGains(6380, ShooterGains.btm, new Gains(0.0075, 0.00035, 0.5, 0.005, 300, 1.00))
    };
   

   }

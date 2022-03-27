// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.security.PublicKey;

/** Add your docs here. */
public class ShooterGains {
    
    public ShooterGains(int speed, int position, frc.robot.Gains gains) {
        this.speed = speed;
        this.position = position;
        this.Gains = gains;
    }
    public int speed;
    public int position;
    public Gains Gains;

    public static final int top =0;
    public static final int btm =1;

}

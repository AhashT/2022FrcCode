// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class Input {

    public static final XboxController driver = new XboxController(0);
    public static final XboxController operator = new XboxController(1);

    public static JoystickButton BUTTON_A = new JoystickButton(driver, 2);
    public static JoystickButton BUTTON_B = new JoystickButton(driver, 3);
   // public static JoystickButton BUTTON_X = new JoystickButton(xbox, 1);
    public static JoystickButton intakeButton = new JoystickButton(operator, 6);
    public static JoystickButton intakeReverseButton = new JoystickButton(operator, 5);
    public static JoystickButton shootButton = new JoystickButton(operator, 1);

    // public static JoystickButton BUTTON_Y = new JoystickButton(xbox, 1);
    // public static JoystickButton BUTTON_LEFT_BUMPER = new JoystickButton(xbox, 1);
    // public static JoystickButton BUTTON_RIGHT_BUMPER = new JoystickButton(xbox, 1);
    // public static JoystickButton BUTTON_SELECT = new JoystickButton(xbox, 1);
    // public static JoystickButton BUTTON_START = new JoystickButton(xbox, 1);
    // public static JoystickButton BUTTON_LEFT_STICK = new JoystickButton(xbox, 1);
    // public static JoystickButton BUTTON_RIGHT_STICK = new JoystickButton(xbox, 1);


}

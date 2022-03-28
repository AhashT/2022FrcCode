// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.GainsAr;
import static frc.robot.Constants.kPIDLoopIdx;
import static frc.robot.Constants.kTimeoutMs;
import static frc.robot.Constants.shooter_btm_motor;
import static frc.robot.Constants.shooter_top_motor;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/*
import this library for Talon:
https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix-frc2022-latest.json
 */

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.ShooterGains;
import frc.robot.sim.PhysicsSim;

public class Shooter extends SubsystemBase {
    private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
    private NetworkTableEntry nte_ShooterTargetRPM = tab.add("ShooterTargetRPM", 3000)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 6380.0))
            .withPosition(0, 0)
            .getEntry();

    private NetworkTableEntry nte_top_RPM = tab.add("Top RPM", 0)
            .withWidget(BuiltInWidgets.kNumberBar)
            .withProperties(Map.of("min", 0.0, "max", 6380.0))
            .withPosition(1, 0)
            .getEntry();

    private NetworkTableEntry nte_top_kP = tab.add("Top kP", 0.1)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(1, 1)
            .getEntry();

    private NetworkTableEntry nte_top_kI = tab.add("Top kI", 0.001)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(1, 2)
            .getEntry();

    private NetworkTableEntry nte_top_kD = tab.add("Top kD", 0.1)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 10.0))
            .withPosition(1, 3)
            .getEntry();

    private NetworkTableEntry nte_top_kF = tab.add("Top kF", 0.00495159)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(1, 4)
            .getEntry();

    private NetworkTableEntry nte_top_Iz = tab.add("Top Iz", 300)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0, "max", 1000))
            .withPosition(1, 5)
            .getEntry();

    private NetworkTableEntry nte_btm_RPM = tab.add("Btm RPM", 0)
            .withWidget(BuiltInWidgets.kNumberBar)
            .withProperties(Map.of("min", 0.0, "max", 6380.0))
            .withPosition(2, 0)
            .getEntry();

    private NetworkTableEntry nte_btm_kP = tab.add("Btm kP", 0.1)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(2, 1)
            .getEntry();

    private NetworkTableEntry nte_btm_kI = tab.add("Btm kI", 0.001)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(2, 2)
            .getEntry();

    private NetworkTableEntry nte_btm_kD = tab.add("Btm kD", 0.1)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 10.0))
            .withPosition(2, 3)
            .getEntry();

    private NetworkTableEntry nte_btm_kF = tab.add("Btm kF", 0.00495159)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(2, 4)
            .getEntry();

    private NetworkTableEntry nte_btm_Iz = tab.add("Btm Iz", 300)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0, "max", 1000))
            .withPosition(2, 5)
            .getEntry();

    private NetworkTableEntry nte_top_Peak = tab.add("Top Peak", 1.0)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(0, 1)
            .getEntry();

    private NetworkTableEntry nte_btm_Peak = tab.add("Btm Peak", 1.0)
            .withWidget(BuiltInWidgets.kTextView)
            .withProperties(Map.of("min", 0.0, "max", 1.0))
            .withPosition(0, 2)
            .getEntry();

    private NetworkTableEntry nte_top_Vplot = tab.add("Top Velocity", 1.0)
            .withWidget(BuiltInWidgets.kGraph)
            .withPosition(3, 0)
            .getEntry();

    private NetworkTableEntry nte_btm_Vplot = tab.add("Btm Velocity", 1.0)
            .withWidget(BuiltInWidgets.kGraph)
            .withPosition(8, 0)
            .getEntry();

    private NetworkTableEntry nte_top_Eplot = tab.add("Top Error", 1.0)
            .withWidget(BuiltInWidgets.kGraph)
            .withPosition(3, 3)
            .getEntry();

    private NetworkTableEntry nte_btm_Eplot = tab.add("Btm Error", 1.0)
            .withWidget(BuiltInWidgets.kGraph)
            .withPosition(8, 3)
            .getEntry();

    private NetworkTableEntry nte_TestShoot_button = tab.add("Shoot", false)
            .withWidget(BuiltInWidgets.kToggleButton)
            .withPosition(7, 3)
            .getEntry();

    WPI_TalonFX m_top;
    WPI_TalonFX m_btm;

    /** Desired top and bottom RPM */
    public double targetRPM;
    private ShooterGains btmGains = GainsAr[0];
    private ShooterGains topGains = GainsAr[3];
    private int initCounter;
    private int periodicCounter;
    private boolean testButtonPressed;
    private boolean testRunning;
    private boolean feederFlag;

    /** Creates a new Shooter. */
    public Shooter() {
        m_top = new WPI_TalonFX(shooter_top_motor);
        m_top.configFactoryDefault();
        m_top.configMotionAcceleration(1000);

        m_btm = new WPI_TalonFX(shooter_btm_motor);
        m_btm.configFactoryDefault();
        m_btm.setInverted(true);
        m_btm.configMotionAcceleration(1000);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        var avgVelocity = (m_top.getSelectedSensorVelocity() + m_btm.getSelectedSensorVelocity()) / 2;
        var avgRPM = avgVelocity / 3.4133;
        feederFlag = avgRPM * .95 > targetRPM;
        //System.out.println("TargetRPM: " + targetRPM+" AvgRpm: "+avgRPM);
    }

    /** Called when button is pressed */
    public void shooterStart() {
        m_top.set(ControlMode.Velocity, targetRPM * 3.4133);
        m_btm.set(ControlMode.Velocity, targetRPM * 3.4133);
    }

    public boolean waitForRpm() {
        long start_time = System.currentTimeMillis();
        long wait_time = 2000;
        long end_time = start_time + wait_time;
        //System.out.println("***WaitForRpm wait started flag: " + feederFlag);
        while (System.currentTimeMillis() < end_time && !feederFlag) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }
        //System.out.println("***WaitForRpm wait ended flag: " + feederFlag);
        return feederFlag;
    }

    /** Called when button is released */
    public void shooterStop() {
        m_top.set(ControlMode.PercentOutput, 0);
        m_btm.set(ControlMode.PercentOutput, 0);
    }

    public void simulationInit() {
        PhysicsSim.getInstance().addTalonFX(m_top, 0.75, 20660);
        PhysicsSim.getInstance().addTalonFX(m_btm, 0.75, 20660);
    }

    @Override
    public void simulationPeriodic() {
        PhysicsSim.getInstance().run();
    }

    public void robotInit() {
        targetRPM = topGains.speed;
        m_top.configFactoryDefault();
        m_btm.configFactoryDefault();

        /* Config neutral deadband to be the smallest possible */
        m_top.configNeutralDeadband(0.001);
        m_btm.configNeutralDeadband(0.001);

        /* Config sensor used for Primary PID [Velocity] */
        m_top.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);
        m_btm.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);

        /* Config the peak and nominal outputs */
        m_top.configNominalOutputForward(0, kTimeoutMs);
        m_top.configNominalOutputReverse(0, kTimeoutMs);
        m_top.configPeakOutputForward(1, kTimeoutMs);
        m_top.configPeakOutputReverse(-1, kTimeoutMs);
        m_btm.configNominalOutputForward(0, kTimeoutMs);
        m_btm.configNominalOutputReverse(0, kTimeoutMs);
        m_btm.configPeakOutputForward(1, kTimeoutMs);
        m_btm.configPeakOutputReverse(-1, kTimeoutMs);

        /* Select preconfigured gains from tuning tests */
        topGains = GainsAr[0];
        btmGains = GainsAr[3];
        feederFlag = false;

        /* Config the Velocity closed loop gains in slot0 */
        m_top.config_kP(kPIDLoopIdx, topGains.Gains.kP, kTimeoutMs);
        m_top.config_kI(kPIDLoopIdx, topGains.Gains.kI, kTimeoutMs);
        m_top.config_kD(kPIDLoopIdx, topGains.Gains.kD, kTimeoutMs);
        m_top.config_kF(kPIDLoopIdx, topGains.Gains.kF, kTimeoutMs);
        m_btm.config_kP(kPIDLoopIdx, btmGains.Gains.kP, kTimeoutMs);
        m_btm.config_kI(kPIDLoopIdx, btmGains.Gains.kI, kTimeoutMs);
        m_btm.config_kD(kPIDLoopIdx, btmGains.Gains.kD, kTimeoutMs);
        m_btm.config_kF(kPIDLoopIdx, btmGains.Gains.kF, kTimeoutMs);

        /*
         * Talon FX does not need sensor phase set for its integrated sensor
         * This is because it will always be correct if the selected feedback device is
         * integrated sensor (default value)
         * and the user calls getSelectedSensor* to get the sensor's position/velocity.
         * 
         * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#
         * sensor-phase
         */
        // m_top.setSensorPhase(true);
    }

    public void testInit() {

        System.out.println("***Shooter testInit() " + initCounter++);
        nte_TestShoot_button.setValue(false);

    }

    public void testPeriodic() {
        System.out.println("***Shooter testPeriodic() " + periodicCounter++);

        if (!Robot.isReal())
            simulationPeriodic();

        /* check for test button state change */
        testButtonPressed = nte_TestShoot_button.getBoolean(false);
        if (testButtonPressed) {
            if (!testRunning) {
                shooterStart();
                testRunning = true;
            }
        } else {
            if (testRunning) {
                shooterStop();
                testRunning = false;
            }
        }
        targetRPM = nte_ShooterTargetRPM.getDouble(0);
        var topRPM = m_top.getSelectedSensorVelocity() / 3.4133;
        nte_top_RPM.setDouble(topRPM);
        nte_top_Vplot.setValue(m_top.getSelectedSensorVelocity());
        nte_top_Eplot.setValue(m_top.getClosedLoopError());

        var btmRPM = m_btm.getSelectedSensorVelocity() / 3.4133;
        nte_btm_RPM.setDouble(btmRPM);
        nte_btm_Vplot.setValue(m_btm.getSelectedSensorVelocity());
        nte_btm_Eplot.setValue(m_btm.getClosedLoopError());

        /* Config the Velocity closed loop gains in slot0 */
        m_top.config_kP(kPIDLoopIdx, nte_top_kP.getDouble(0.0075), kTimeoutMs);
        m_top.config_kI(kPIDLoopIdx, nte_top_kI.getDouble(0.00035), kTimeoutMs);
        m_top.config_kD(kPIDLoopIdx, nte_top_kD.getDouble(5), kTimeoutMs);
        m_top.config_kF(kPIDLoopIdx, nte_top_kF.getDouble(0.00495159), kTimeoutMs);
        m_btm.config_kP(kPIDLoopIdx, nte_btm_kP.getDouble(0.0075), kTimeoutMs);
        m_btm.config_kI(kPIDLoopIdx, nte_btm_kI.getDouble(0.00035), kTimeoutMs);
        m_btm.config_kD(kPIDLoopIdx, nte_btm_kD.getDouble(5), kTimeoutMs);
        m_btm.config_kF(kPIDLoopIdx, nte_btm_kF.getDouble(0.00495159), kTimeoutMs);

    }

}
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class NormalDrive extends CommandBase {
  private DriveTrain dT;

  /** Creates a new StartIntake. */
  public NormalDrive(DriveTrain dT) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dT = dT;
    addRequirements(dT);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dT.normalDrive();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
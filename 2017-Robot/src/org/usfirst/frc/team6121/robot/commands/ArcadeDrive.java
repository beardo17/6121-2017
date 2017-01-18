

package org.usfirst.frc.team6121.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6121.robot.OI;
import org.usfirst.frc.team6121.robot.Robot;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.arcadeDrive(OI.xboxController_1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
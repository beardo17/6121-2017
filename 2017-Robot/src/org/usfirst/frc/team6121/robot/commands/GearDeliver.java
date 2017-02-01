package org.usfirst.frc.team6121.robot.commands;

import org.usfirst.frc.team6121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearDeliver extends Command {
	boolean delivered = false;

    public GearDeliver() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	delivered = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.shooterSubsystem.aimValue() > 185) {
    		Robot.driveSubsystem.turn(-0.2, 0.2);
    	} else if (Robot.shooterSubsystem.aimValue() < 175) {
    		Robot.driveSubsystem.turn(0.2, -0.2);
    	} else {
    		if (Robot.vision.getDistance() >= 10) {
    			Robot.driveSubsystem.drive(0.5, 0);
    		} else {
    			delivered = true;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return delivered;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

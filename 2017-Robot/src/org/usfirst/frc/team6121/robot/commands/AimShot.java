package org.usfirst.frc.team6121.robot.commands;

import org.usfirst.frc.team6121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimShot extends Command {
	
	boolean aimed = false;

    public AimShot() {
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	aimed = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.shooterSubsystem.aimValue() > 185) {
    		Robot.driveSubsystem.turn(-0.2, 0.2);
    	} else if (Robot.shooterSubsystem.aimValue() < 175) {
    		Robot.driveSubsystem.turn(0.2, -0.2);
    	} else {
    		aimed = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return aimed;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.turn(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

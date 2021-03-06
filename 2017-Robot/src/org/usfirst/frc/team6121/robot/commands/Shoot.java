package org.usfirst.frc.team6121.robot.commands;

import org.usfirst.frc.team6121.robot.Robot;
import org.usfirst.frc.team6121.robot.subsystems.Vision.Target;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.vision.getTarget() == Target.Boiler) {
    		Robot.shooterSubsystem.setRPM(Robot.shooterSubsystem.toRPM());
    	} else {
    		Robot.shooterSubsystem.setRPM(1200);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterSubsystem.setRPM(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

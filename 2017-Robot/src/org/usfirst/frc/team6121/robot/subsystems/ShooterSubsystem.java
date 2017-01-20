package org.usfirst.frc.team6121.robot.subsystems;

import org.usfirst.frc.team6121.robot.Robot;
import org.usfirst.frc.team6121.robot.RobotMap;
import org.usfirst.frc.team6121.robot.subsystems.Vision.Target;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	private final double angle = Math.toRadians(60);
	private final double y = 97;
    private final double g = 32.174 * 12;
    
    Vision v = Robot.vision;

    public void initDefaultCommand() {
    }
    
    public void shooting(double s) {
    	RobotMap.shooterMotor.set(s);
    }
    
    public double getVelocity() {
    	double d = v.getDistance() + 10.75;
    	return Math.sqrt((d * d * g)/(d * Math.sin(angle * 2) - 2 * y * Math.cos(angle) * Math.cos(angle))) / 12;
    }
    
    public double toRPM() {
    	return (getVelocity() * 60 / (3.14159 / 3));
    }
    
    public void aim() {
    	if (v.getTarget() == Target.Boiler) {
 //   		v.getCenterX(Target.Boiler) 
    		//TODO: Create algorithm for aiming the shooter based on the distance and offset from camera
    	}
    }
    
}


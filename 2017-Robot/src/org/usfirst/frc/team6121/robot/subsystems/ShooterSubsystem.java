package org.usfirst.frc.team6121.robot.subsystems;

import org.usfirst.frc.team6121.robot.Robot;
import org.usfirst.frc.team6121.robot.RobotMap;
import org.usfirst.frc.team6121.robot.commands.Shoot;
import org.usfirst.frc.team6121.robot.subsystems.Vision.Target;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	private final double shootAngle = Math.toRadians(60);
	private final double y = 97;
    private final double g = 32.174 * 12;
    private final double xShooterOffset = 0;
    private final double xGearOffset = 0;
    
    PIDController shooter = new PIDController(RobotMap.pShooterBadder, RobotMap.iShooterBadder, RobotMap.dShooterBadder);
    Vision v = Robot.vision;

    public void initDefaultCommand() {
    	setDefaultCommand(new Shoot());
    }
    
    public double getVelocity() {
    	double d = v.getDistance() + 10.75;
    	return Math.sqrt((d * d * g) / (d * Math.sin(shootAngle * 2) - 2 * y * Math.cos(shootAngle) * Math.cos(shootAngle))) / 12;
    }
    
    public double toRPM() {
    	return (getVelocity() * 60 / (3.14159 / 3));
    }
    
    public double getRPM() {
    	return RobotMap.shooter.getRate() * 60;
    }
    
    public void setSpeed(double val) {
    	RobotMap.shooterMotor.set(val);
    }
    
    /**
	 * Method used to set rpm for the shooter, uses PID in combination with
	 * feedforward to reach speed. Inputed rpm is sent into a linear function to
	 * calculate needed feedforward value.
	 * 
	 * @param rpm
	 *            rpm for the shooter
	 */
	public void setRPM(double rpm) {
		double output = shooter.calcPIDVelocity(rpm, getRPM(), 50, 0.6);
		setSpeed(output + rpm * RobotMap.kForward + RobotMap.bForward);
	}
    
    public double aimValue() {
    	double c = 0;
    	if (v.getTarget() == Target.Boiler) {
    		c = v.getWidth(Target.Boiler) * xShooterOffset / 15;
    	} else if (v.getTarget() == Target.Gear) {
    		c = v.getWidth(Target.Boiler) * xGearOffset / 10.25;
    	}
		return c;
    }
}


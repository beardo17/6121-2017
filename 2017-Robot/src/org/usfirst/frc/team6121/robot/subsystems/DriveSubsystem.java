
package org.usfirst.frc.team6121.robot.subsystems;

import org.usfirst.frc.team6121.robot.Robot;
import org.usfirst.frc.team6121.robot.RobotMap;
import org.usfirst.frc.team6121.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public double x = Robot.oi.getX();
	public double y = Robot.oi.getY();
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ArcadeDrive());
    }
    
    public void arcadeDrive(Joystick stick) {
    	RobotMap.DriveTrain.arcadeDrive(y, -x, false);
    }
    
    public void driveFoward(double a) {
    	RobotMap.DriveTrain.setLeftRightMotorOutputs(a, a);
    }
}


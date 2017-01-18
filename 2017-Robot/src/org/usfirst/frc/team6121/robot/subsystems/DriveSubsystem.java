
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


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDrive());
    }
    
    public void arcadeDrive(Joystick stick) {
    	double x = stick.getRawAxis(RobotMap.X_AXIS);
    	double y = stick.getRawAxis(RobotMap.Y_AXIS) * (stick.getRawAxis(RobotMap.R_TRIGGER) + 0.5);
    	RobotMap.driveTrain.arcadeDrive(y, -x, false);
    }
    
    public void driveForward(double a) {
    	RobotMap.driveTrain.setLeftRightMotorOutputs(a, a);
    }
    
    public void turnLeft(double a) {
    	RobotMap.driveTrain.setLeftRightMotorOutputs(-a, a);
    }
    public void turnRight(double a) {
    	RobotMap.driveTrain.setLeftRightMotorOutputs(a, -a);
    }
    public void drivebackward(double a) {
    	RobotMap.driveTrain.setLeftRightMotorOutputs(-a, -a);
    }
}


package org.usfirst.frc.team6121.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	/****** Motors ********/
	
	public static int Front_Left_Motor = 0;
	public static int Rear_Left_Motor = 1;
	public static int Front_Right_Motor = 2;
	public static int Rear_Right_Motor = 3;
	
	/****** Controller ******/
	
	public static final int XBOX_CONTROLLER = 0;
	
	public static final int Y_AXIS = 1;
	public static final int X_AXIS = 2;
	
	public static final int A_BUTTON = 1;
	
	/****** Speed Controllers *******/
	
	public static SpeedController flMotor = new Spark(Front_Left_Motor);
	public static SpeedController rlMotor = new Spark(Rear_Left_Motor);
	public static SpeedController frMotor = new Victor(Front_Right_Motor);
	public static SpeedController rrMotor = new Victor(Rear_Right_Motor);
	
	public static RobotDrive DriveTrain = new RobotDrive(flMotor, rlMotor, frMotor, rrMotor);
	
	}
	
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;


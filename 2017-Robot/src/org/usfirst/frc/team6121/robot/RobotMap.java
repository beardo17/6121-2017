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
	
	/************ MOTORS ***********/
	
	public static final int FRONT_LEFT_MOTOR = 0;
	public static final int REAR_LEFT_MOTOR = 1;
	public static final int FRONT_RIGHT_MOTOR = 2;
	public static final int REAR_RIGHT_MOTOR = 3;
	
	/********* CONTROLLERS *********/
	
	public static final int XBOX_CONTROLLER = 0;
	
	public static final int Y_AXIS = 1;
	public static final int X_AXIS = 2;
	
	public static final int A_BUTTON = 1;
	
	/******* SPEED CONTROLLERS *****/
	
	public static SpeedController flMotor = new Spark(FRONT_LEFT_MOTOR);
	public static SpeedController rlMotor = new Spark(REAR_LEFT_MOTOR);
	public static SpeedController frMotor = new Victor(FRONT_RIGHT_MOTOR);
	public static SpeedController rrMotor = new Victor(REAR_RIGHT_MOTOR);
	
	public static RobotDrive driveTrain = new RobotDrive(flMotor, rlMotor, frMotor, rrMotor);
	
}

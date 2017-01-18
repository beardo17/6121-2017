package org.usfirst.frc.team6121.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
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
	
//	public static final int CLIMB_MOTOR = 4;
//	
//	public static final int SHOOTER_MOTOR = 5;
//	
//	public static final int BALL_INTAKE_MOTOR = 6;
	
	/********* CONTROLLERS *********/
	
	public static final int XBOX_CONTROLLER = 0;
	
	public static final int Y_AXIS = 1;
	public static final int X_AXIS = 2;
	public static final int R_TRIGGER = 3;
	
	public static final int A_BUTTON = 1;
	
	public static SpeedController flMotor;
	public static SpeedController rlMotor;
	public static SpeedController frMotor;
	public static SpeedController rrMotor;
	
	public static RobotDrive driveTrain;
	
	public static void init()  {
	
	/******* SPEED CONTROLLERS *****/
	
	flMotor = new Victor(FRONT_LEFT_MOTOR);
	rlMotor = new Victor(REAR_LEFT_MOTOR);
	frMotor = new Victor(FRONT_RIGHT_MOTOR);
	rrMotor = new Victor(REAR_RIGHT_MOTOR);
	
	driveTrain = new RobotDrive(flMotor, rlMotor, frMotor, rrMotor);
	
	driveTrain.setSafetyEnabled(true);
	driveTrain.setExpiration(0.1);
	driveTrain.setSensitivity(0.5);
	driveTrain.setMaxOutput(1.0);
	
//	public static SpeedController climbMotor = new Talon(CLIMB_MOTOR);
//	
//	public static SpeedController shooterMotor = new Talon(SHOOTER_MOTOR);
//	
//	public static SpeedController ballIntakeMotor = new Spark(BALL_INTAKE_MOTOR);
	
	}
	
}

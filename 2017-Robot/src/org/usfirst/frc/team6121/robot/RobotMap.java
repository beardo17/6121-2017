package org.usfirst.frc.team6121.robot;

import edu.wpi.first.wpilibj.Encoder;
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
	
	public static final int CLIMB_MOTOR_1 = 4;
	public static final int CLIMB_MOTOR_2 = 5;
	public static final int BALL_INTAKE_MOTOR = 6;
	public static final int SHOOTER_MOTOR = 7;
	public static final int AGITATOR_MOTOR = 8;

	/************ Sensors **********/
	
	public static final int SHOOT_ENCODER_A = 0;
	public static final int SHOOT_ENCODER_B = 0;
	
	/********* CONTROLLERS *********/
	
	public static final int XBOX_CONTROLLER_1 = 0;
	public static final int XBOX_CONTROLLER_2 = 1;

	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	public static final int L_TRIGGER = 2;
	public static final int R_TRIGGER = 3;
	
	public static final int A_BUTTON = 1;
	public static final int X_BUTTON = 2;
	public static final int Y_BUTTON = 3;
	public static final int B_BUTTON = 4;
	public static final int L_BUTTON = 5;
	public static final int R_BUTTON = 6;

	/******* SPEED CONTROLLERS *****/
	
	public static SpeedController flMotor;
	public static SpeedController rlMotor;
	public static SpeedController frMotor;
	public static SpeedController rrMotor;
	
	public static RobotDrive driveTrain;
	
	public static SpeedController climbMotor_1;
	public static SpeedController climbMotor_2;
	public static SpeedController ballIntakeMotor;
	public static SpeedController shooterMotor;
	public static SpeedController agitatorMotor;
	
	/************ Sensors **********/
	
	public static Encoder shooter;
	
	/******* PID CONSTANTS *********/
	 
	public static final double pShooterBadder = 0.00023;
	public static final double iShooterBadder = 0.0000;
	public static final double dShooterBadder = 0.0000;
	public static final double kForward	= 0.000137500;
	public static final double bForward	= 0.080000000;
	
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
	
	climbMotor_1 = new Victor(CLIMB_MOTOR_1);
	climbMotor_2 = new Victor(CLIMB_MOTOR_2);
	ballIntakeMotor = new Spark(BALL_INTAKE_MOTOR);
	shooterMotor = new Victor(SHOOTER_MOTOR);
	agitatorMotor = new Spark(AGITATOR_MOTOR);
	
	shooter = new Encoder(SHOOT_ENCODER_A, SHOOT_ENCODER_B, false, Encoder.EncodingType.k4X);
	shooter.setDistancePerPulse(4 * Math.PI);
	}
	
}
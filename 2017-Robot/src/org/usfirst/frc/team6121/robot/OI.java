package org.usfirst.frc.team6121.robot;


import org.usfirst.frc.team6121.robot.commands.AimShot;
import org.usfirst.frc.team6121.robot.commands.BallIntake;
import org.usfirst.frc.team6121.robot.commands.Climbing;
import org.usfirst.frc.team6121.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick xboxController_1;
	public static Joystick xboxController_2;
	
	OI() {
		
		xboxController_1 = new Joystick(RobotMap.XBOX_CONTROLLER_1);
		xboxController_2 = new Joystick(RobotMap.XBOX_CONTROLLER_2);
		
		Button climbButton = new JoystickButton(xboxController_1, RobotMap.A_BUTTON);
		climbButton.whileHeld(new Climbing(0.25));
		
		Button ballIntakeButton = new JoystickButton(xboxController_1, RobotMap.R_BUTTON);
		ballIntakeButton.whenPressed(new BallIntake(0.25));
		
		Button shooterTrigger = new JoystickButton(xboxController_2, RobotMap.R_BUTTON);
		shooterTrigger.whileHeld(new Shoot());
		
		Button aimButton = new JoystickButton(xboxController_2, RobotMap.L_BUTTON);
		aimButton.whileHeld(new AimShot());
	}
	
    
	
}


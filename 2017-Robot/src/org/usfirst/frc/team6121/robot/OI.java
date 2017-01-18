package org.usfirst.frc.team6121.robot;


import org.usfirst.frc.team6121.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick xboxController;
	
	OI() {
		
		xboxController = new Joystick(RobotMap.XBOX_CONTROLLER);
		
		Button driveButton = new JoystickButton(xboxController, RobotMap.A_BUTTON);

		driveButton.whileHeld(new DriveForward(0.25));
	}
	
	public double getY() {
		return xboxController.getRawAxis(RobotMap.Y_AXIS);
	}
	
	public double getX() {
		return xboxController.getRawAxis(RobotMap.X_AXIS);
	}
	
}


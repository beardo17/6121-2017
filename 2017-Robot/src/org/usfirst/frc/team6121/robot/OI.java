package org.usfirst.frc.team6121.robot;


import org.usfirst.frc.team6121.robot.commands.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick xboxController_1;
	
	OI() {
		
		xboxController_1 = new Joystick(RobotMap.XBOX_CONTROLLER_1);
		
		Button driveButton = new JoystickButton(xboxController_1, RobotMap.A_BUTTON);
		driveButton.whileHeld(new Drive(0.25, 0));
	}
	
    
	
}


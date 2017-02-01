
package org.usfirst.frc.team6121.robot.commands;

import org.usfirst.frc.team6121.robot.subsystems.AutoSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 */
public class Autonomous extends CommandGroup {
	
    
	/**
	 * Handles all of the autonomous stuff based on inputs from the DS
	 * 
	 * @param strat Strategy for Autonomous
	 * @param pos Position for Autonomous
	 * @param team Alliance Color
	 */
    public  Autonomous(AutoSubsystem.strat strat, AutoSubsystem.position pos, AutoSubsystem.alliance team) {
    	
    	switch(team) {
    	case BLUE:
    		switch(pos) {
			case LEFT:
				switch(strat) {
    				case SHOOT:
    					addSequential(new Drive(0.5, 0.0025569325, 0.25)); // Turn for the boiler
    					addSequential(new AimShot());
    					addSequential(new Shoot());
    					break;
    				case GEAR:
    					addSequential(new Drive(0.5, 0.0025569325, 0.5)); // To the peg
    					addSequential(new WaitCommand(0.5));
    					addSequential(new GearDeliver());
    					break;
    				case WIN:
    					addSequential(new Drive(0.5, 0.0025569325, 0.5)); // To the peg
    					addSequential(new GearDeliver());
    					addSequential(new Drive(-0.5, 0, 0.125));         // Back it up
    					addSequential(new Drive(-0.5, -0.25, 0.5));       // To the hopper
    					addSequential(new Drive(0, -0.25, 0.125));        // Catch the balls
    					addSequential(new WaitCommand(2));
    					addSequential(new AimShot());
    					addSequential(new Shoot());
    					break;
    				default:
				}
				break;
			case MID:
				break;
			case RIGHT:
				break;
			default:
				break;
    		}
    		break;
    	case RED:
    		switch(pos) {
			case LEFT:
				switch(strat) {
				case SHOOT:
					addSequential(new Drive(0.5, -0.0025569325, 0.25)); // Turn for the boiler
					addSequential(new AimShot());
					addSequential(new Shoot());
					break;
				case GEAR:
					addSequential(new Drive(0.5, -0.0025569325, 0.5));  // To the peg
					addSequential(new WaitCommand(0.5));
					addSequential(new GearDeliver());
					break;
				case WIN:
					addSequential(new Drive(0.5, -0.0025569325, 0.5));  // To the peg
					addSequential(new GearDeliver());
					addSequential(new Drive(-0.5, 0, 0.125));           // Back it up
					addSequential(new Drive(-0.5, 0.25, 0.5));          // To the hopper
					addSequential(new Drive(0, 0.25, 0.125));           // Catch the balls
					addSequential(new WaitCommand(2));
					addSequential(new AimShot());
					addSequential(new Shoot());
					break;
				default:
		}
				break;
			case MID:
				break;
			case RIGHT:
				break;
			default:
				break;
    		}
    		break;
    	default:
    	}
    	
    }
}
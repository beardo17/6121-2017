package org.usfirst.frc.team6121.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutoSubsystem extends Subsystem {
    
    public enum strat {
		SIT,
		SHOOT,
		GEAR,
		WIN
	}
    
    public enum position {
    	LEFT,
    	MID,
    	RIGHT
    }
    
    public enum alliance {
    	BLUE,
    	RED
    }
    	
    public void initDefaultCommand() {
    }
    
    public static double autoTurn(position pos, alliance team) {
    	if (pos == position.LEFT && team == alliance.BLUE) {
    		return 11.576;
    	} else if (pos == position.LEFT && team == alliance.RED) {
    		return -11.576;
    	} else if (pos == position.RIGHT && team == alliance.RED) {
    		return 11.576;
    	} else if (pos == position.RIGHT && team == alliance.BLUE) {
    		return -11.576;
    	}
    	return 0;
    }
}


package org.usfirst.frc.team6121.robot.subsystems;

import org.usfirst.frc.team6121.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {

	NetworkTable vision = Robot.table;
	double[] defaultValue = new double[0];
	int[] targets = new int[3];
	
	double[] areas = vision.getNumberArray("area", defaultValue);
	double[] centerX = vision.getNumberArray("centerX", defaultValue);
	double[] centerY = vision.getNumberArray("centerY", defaultValue);
	double[] width = vision.getNumberArray("width", defaultValue);
	double[] height = vision.getNumberArray("height", defaultValue);
	
	final double bH = 10/12;
	final double gH = 4/12;
	final double fovP = 240;
	final double camVertAngle = 34.3; //TODO: Check the vertical camera angle
	double tP;
	
	public enum Target {
		None,
		Boiler,
		Gear;
	}

    public void initDefaultCommand() {
    	
    }
    
    public void printVision() {
    	for (int i = 0; i < areas.length; i ++) {
    		System.out.print("Contour " + (i + 1) + ":");
    		System.out.print(areas[i] + " | ");
    		System.out.print(centerX[i] + " | ");
    		System.out.print(centerY[i] + " | ");
    		System.out.print(width[i] + " | ");
    		System.out.println(height[i]);
    	}
    }
    
    public Target getTarget() {
    	Target target = Target.None;
    	double blRatio, bhRatio, bwRatio, blScore, bhScore, bwScore;
    	double gtRatio, ghRatio, gwRatio, gtScore, ghScore, gwScore;
    	for (int i = 0; i < areas.length - 1; i++) {
    		blRatio = ((centerX[i] - (width[i] / 2) - (centerX[i+1] - (width[i+1])) / width[i]) + 1);
    		bhRatio = (height[i] / (height[i+1] * 2));
    		bwRatio = (width[i] / width[i+1]);
    		
    		blScore = (100 - (100 * Math.abs(1-blRatio)));
    		bhScore = (100 - (100 * Math.abs(1-bhRatio)));
    		bwScore = (100 - (100 * Math.abs(1-bwRatio)));
    		
    		if (blScore + bhScore + bwScore >= 250) {
    			target = Target.Boiler;
    			targets[0] = i;
    			targets[1] = i + 1;
    			break;
    		}
    		
    		//TODO: Create the Gear score logic. Also the logic for detecting a spring covering
    		//      the reflective tape.
    		
    		if (Math.abs(centerX[i] - centerX[i+1]) >= 5) {
    			if (((height[i] + height[i+1]) / 2 + (centerY[i] + centerY[i+1]) / 2) - (height[i+2] / 2 + centerY[i+2]) >= 5) {
    				
    			}
    		} else if (Math.abs(centerX[i+1] - centerX[i+2]) >= 5) {
    			if (((height[i+1] + height[i+2]) / 2 + (centerY[i+1] + centerY[i+2]) / 2) - (height[i] / 2 + centerY[i]) >= 5) {
    				
    			}
    		} else {
    			gtRatio = ((centerY[i] + (height[i] / 2) - (centerY[i+1] + (height[i+1] / 2)) / height[i]) + 1);
    			gwRatio = (width[i] / width[i+1]);
    			ghRatio = (height[i] / height[i+1]);
    		}
    	}
		return target;
    }
    
    public double getTargetHeight(Target t) {
    	double h = 0;
    	double T = 0, B = 0;
    	for (int i = 0; i < targets.length; i++) {
    		switch (t) {
    		case Boiler:
    			T = (centerY[targets[0]] + centerY[targets[1]]) / 2 + (height[targets[0]] / 2 + height[targets[1]] / 2) / 2;
    			B = (centerY[targets[0]] + centerY[targets[1]]) / 2 - (height[targets[0]] / 2 + height[targets[1]] / 2) / 2;
    			break;
    		case Gear:
    			//TODO: Fix this for covered tape
    			T = (centerY[targets[i]] + centerY[targets[i+1]]) / 2 + (height[targets[i]] / 2 + height[targets[i+1]] / 2) / 2;
    			B = (centerY[targets[i]] + centerY[targets[i+1]]) / 2 - (height[targets[i]] / 2 + height[targets[i+1]] / 2) / 2;
    			break;
    		default:
    		}
    		break;
    	}
    	h = T - B;
		return h;
    }
    
    public Double getDistance() {
    	double d = 0;
    	switch (getTarget()) {
    	case Boiler:
        	tP = getTargetHeight(Target.Boiler);
        	d = bH * fovP / (2 * tP * Math.tan(camVertAngle));
    		break;
    	case Gear:
        	tP = getTargetHeight(Target.Gear);
        	d = bH * fovP / (2 * tP * Math.tan(camVertAngle));
    		break;
    	default:
    	}
		return d;
    }
}


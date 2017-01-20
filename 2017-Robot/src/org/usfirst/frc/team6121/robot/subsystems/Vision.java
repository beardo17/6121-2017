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
	final double gH = 5/12;
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
    	double gtRatio = 0, ghRatio = 0, gwRatio = 0, gtScore, ghScore, gwScore;
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
    		
    		if (Math.abs(centerX[i] - centerX[i+1]) <= 5) {
    			if (((height[i] + height[i+1]) / 2 + (centerY[i] + centerY[i+1]) / 2) - (height[i+2] / 2 + centerY[i+2]) >= 5) {
    				gtRatio = (((centerY[i] + height[i] / 2) - (centerY[i+2] + height[i+2] / 2)) / (centerY[i+2] + (height[i+2] / 2)));
    				gwRatio = (width[i] / width[i+2]);
    				ghRatio = ((centerY[i] + height[i] / 2) - (centerY[i+1] + height[i+1] / 2)) / (height[i+2]);
    			}
    		} else if (Math.abs(centerX[i+1] - centerX[i+2]) <= 5) {
    			if (((height[i+1] + height[i+2]) / 2 + (centerY[i+1] + centerY[i+2]) / 2) - (height[i] / 2 + centerY[i]) >= 5) {
    				gtRatio = (((centerY[i+1] + height[i+1] / 2) - (centerY[i+2] + height[i+2] / 2)) / (centerY[i] + (height[i] / 2)));
    				gwRatio = (width[i] / width[i+2]);
    				ghRatio = ((centerY[i+2] + height[i+2] / 2) - (centerY[i+1] + height[i+1] / 2)) / (height[i]);
    			}
    		} else {
    			gtRatio = ((centerY[i] + (height[i] / 2) - (centerY[i+1] + (height[i+1] / 2)) / height[i]) + 1);
    			gwRatio = (width[i] / width[i+1]);
    			ghRatio = (height[i] / height[i+1]);
    		}

    		gtScore = (100 - (100 * Math.abs(1-gtRatio)));
    		ghScore = (100 - (100 * Math.abs(1-ghRatio)));
    		gwScore = (100 - (100 * Math.abs(1-gwRatio)));
    		
    		if (gtScore + ghScore + gwScore >= 250) {
    			target = Target.Gear;
    			targets[0] = i;
    			targets[1] = i + 1;
    			targets[2] = i + 2;
    			break;
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
    			if (Math.abs(centerX[i] - centerX[i+1]) <= 5) {
    				T = (centerY[targets[i]] + height[targets[i]] / 2);
    				B = (centerY[targets[i+1]] + height[targets[i+1]] / 2);
    			} else if (Math.abs(centerX[i+1] - centerX[i+2]) <= 5) {
    				T = (centerY[targets[i]] + height[targets[i]] / 2);
    				B = (centerY[targets[i]] + height[targets[i]] / 2);
    			} else {
    				T = (centerY[targets[i]] + height[targets[i]] / 2);
    				B = (centerY[targets[i]] + height[targets[i]] / 2);
    			}
    			break;
    		default:
    		}
    	}
    	h = T - B;
		return h;
    }
    
    public double getDistance() {
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
    
    public double getCenterX(Target t) {
    	if (t == Target.Boiler) {
    		return centerX[0];
    	} else if (t == Target.Gear) {
    		return centerX[0] * 1.025625;
    	} else {
    		return 0;
    	}
    }
}
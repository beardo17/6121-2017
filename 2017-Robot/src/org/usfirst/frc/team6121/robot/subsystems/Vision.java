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
	final double camAngle = 34.3;
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
    	double glRatio, ghRatio, gwRatio, glScore, ghScore, gwScore;
    	for (int i = 0; i < areas.length - 1; i++) {
    		blRatio = ((centerX[i] - (width[i]) - (centerX[i+1] - (width[i+1])) / width[i]) + 1);
    		bhRatio = (height[i] / (height[i+1] * 2));
    		bwRatio = (width[i] / width[i+1]);
    		
    		blScore = (100 - (100 * Math.abs(1-blRatio)));
    		bhScore = (100 - (100 * Math.abs(1-bhRatio)));
    		bwScore = (100 - (100 * Math.abs(1-bwRatio)));
    		
    		if (blScore + bhScore + bwScore >= 250) {
    			target = Target.Boiler;
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
    			T = centerY[targets[i]] + (height[targets[i]] / 2);
    			B = centerY[targets[i]] - (height[targets[i]] / 2);
    			break;
    		case Gear:
    			T = centerY[targets[i]] + (height[targets[i]] / 2);
    			B = centerY[targets[i]] - (height[targets[i]] / 2);
    			break;
    		default:
    		}
    	}
    	h = T - B;
		return h;
    }
    
    public Double getDistance() {
    	double d = 0;
    	switch (getTarget()) {
    	case Boiler:
        	tP = getTargetHeight(Target.Boiler);
        	d = bH * fovP / (2 * tP * Math.tan(camAngle));
    		break;
    	case Gear:
        	tP = getTargetHeight(Target.Gear);
        	d = bH * fovP / (2 * tP * Math.tan(camAngle));
    		break;
    	default:
    	}
		return d;
    }
}


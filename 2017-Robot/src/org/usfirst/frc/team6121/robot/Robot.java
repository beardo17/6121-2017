
package org.usfirst.frc.team6121.robot;

import org.usfirst.frc.team6121.robot.commands.Autonomous;
import org.usfirst.frc.team6121.robot.subsystems.AutoSubsystem.alliance;
import org.usfirst.frc.team6121.robot.subsystems.AutoSubsystem.position;
import org.usfirst.frc.team6121.robot.subsystems.AutoSubsystem.strat;
import org.usfirst.frc.team6121.robot.subsystems.BallIntakeSubsystem;
import org.usfirst.frc.team6121.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team6121.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team6121.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team6121.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveSubsystem driveSubsystem;
	public static ClimbSubsystem climbSubsystem;
	public static BallIntakeSubsystem ballIntakeSubsystem;
	public static ShooterSubsystem shooterSubsystem;
	public static Vision vision;

    Command autonomousCommand;
    SendableChooser<Autonomous> chooser;
    
    public static NetworkTable table;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	table = NetworkTable.getTable("GRIP/targets");
    	
    	RobotMap.init();
    	vision = new Vision();
    	driveSubsystem = new DriveSubsystem();
    	climbSubsystem = new ClimbSubsystem();
    	ballIntakeSubsystem = new BallIntakeSubsystem();
    	shooterSubsystem = new ShooterSubsystem();
		oi = new OI();

        chooser = new SendableChooser<Autonomous>();
        chooser.addDefault("Blue: Left: Both", new Autonomous(strat.WIN, position.LEFT, alliance.BLUE));
        chooser.addObject("Blue: Left: Shoot", new Autonomous(strat.SHOOT, position.LEFT, alliance.BLUE));
        chooser.addObject("Blue: Left: Both", new Autonomous(strat.GEAR, position.LEFT, alliance.BLUE));
        chooser.addObject("Blue: Mid: Both", new Autonomous(strat.WIN, position.MID, alliance.BLUE));
        chooser.addObject("Blue: Mid: Shoot", new Autonomous(strat.SHOOT, position.MID, alliance.BLUE));
        chooser.addObject("Blue: Mid: Gear", new Autonomous(strat.GEAR, position.MID, alliance.BLUE));
        chooser.addObject("Blue: Right: Both", new Autonomous(strat.WIN, position.RIGHT, alliance.BLUE));
        chooser.addObject("Blue: Right: Shoot", new Autonomous(strat.SHOOT, position.RIGHT, alliance.BLUE));
        chooser.addObject("Blue: Right: Gear", new Autonomous(strat.GEAR, position.RIGHT, alliance.BLUE));
        chooser.addObject("Red: Left: Both", new Autonomous(strat.WIN, position.LEFT, alliance.RED));
        chooser.addObject("Red: Left: Shoot", new Autonomous(strat.SHOOT, position.LEFT, alliance.RED));
        chooser.addObject("Red: Left: Gear", new Autonomous(strat.GEAR, position.LEFT, alliance.RED));
        chooser.addObject("Red: Mid: Both", new Autonomous(strat.WIN, position.MID, alliance.RED));
        chooser.addObject("Red: Mid: Shoot", new Autonomous(strat.SHOOT, position.MID, alliance.RED));
        chooser.addObject("Red: Mid: Gear", new Autonomous(strat.GEAR, position.MID, alliance.RED));
        chooser.addObject("Red: Right: Both", new Autonomous(strat.WIN, position.RIGHT, alliance.RED));
        chooser.addObject("Red: Right: Shoot", new Autonomous(strat.SHOOT, position.RIGHT, alliance.RED));
        chooser.addObject("Red: Right: Gear", new Autonomous(strat.GEAR, position.RIGHT, alliance.RED));
        SmartDashboard.putData("Auto mode", chooser);
    }
    
    
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

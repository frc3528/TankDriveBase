
package org.usfirst.frc.team3528.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
    
	
	DoubleSolenoid high;
	
	Compressor compressor;
	
	RobotDrive myRobot;
   
	Joystick stick;

	Talon frontLeft;
	Talon frontRight;
	Talon backLeft;
	Talon backRight;
	
	double leftPower;
	double rightPower;
    
	
    public Robot() {
        
    }
    
    public void robotInit() {
    	high = new DoubleSolenoid(1, 1, 2);
    	
    	compressor = new Compressor(1);
    	
    	compressor.setClosedLoopControl(true);
    	
    	frontLeft = new Talon(1);
    	frontRight = new Talon(0);
    	backLeft = new Talon(3);
    	backRight = new Talon(2);
    	
    	myRobot = new RobotDrive(frontLeft, frontRight, backLeft, backRight);
        myRobot.setExpiration(0.1);
        
        stick = new Joystick(0);  
    }
    
    public void autonomous() {

    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        myRobot.setSafetyEnabled(false);
        
        leftPower = stick.getRawAxis(1);
        rightPower = stick.getRawAxis(5);
        
        while (isOperatorControl() && isEnabled()) {
            
            leftPower = stick.getRawAxis(1);
            rightPower = stick.getRawAxis(5);
        	
        	myRobot.tankDrive(leftPower, rightPower);
            
            
            if(stick.getRawButton(7) == true) {
            	System.out.println("Button 7 true.");
            	high.set(DoubleSolenoid.Value.kForward);
            }
            
            if(stick.getRawButton(8) == true) {
            	System.out.println("Button 8 true");
            	high.set(DoubleSolenoid.Value.kReverse);
            }
            
            Timer.delay(0.005);
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}

/***********************************************************
* File:	DesktopAlertRunner.java
*
* @author 		Anupom (http://anupom.wordpress.com)
*
* @description 	A Thread extended class that runs a DesktopAlert
*				in a separate thread.
*
* @version 0.00 August 11,2006
*
***********************************************************/

import java.awt.*;
import javax.swing.JDialog;
import javax.swing.plaf.metal.*;

public class DesktopAlertRunner extends Thread
{
	// the DesktopAlert that will be shown in a separate thread
	DesktopAlert dAlert;
	//isUpward is true when the Alert pops up verticaly, false otherwise
	boolean isUpward;
	//Speed of the alert's movement
	int speed;
	//total time of alert's visibility
	int time;
	
	/**
     * Constructor of DesktopAlertRunner
     * 
     * @param dAlert	The DesktopAlert that will be shown by 
     *					this runner class.
     * @param isUpward	isUpward is true when the alert pops up from the bottom
     *					of the desktop (vertical), false when the alert pops up 
     *					from the right side (horizontal).
     * @param speed		Speed of the alert's movement (usual value is 10 to 50)
     * @param time		total time (in miliseconds) of alert's visibility
     *					(usual value is 1000 to 5000)
     */
    public DesktopAlertRunner(DesktopAlert dAlert, boolean isUpward, int speed, int time)
	{
		//initializing the class vars
		this.dAlert = dAlert;	
		this.isUpward = isUpward;
		this.speed = speed;
		this.time = time;
	}
	
	public void run()
	{
		//pop up 
		dAlert.popUp(isUpward,speed,time);
	}
}
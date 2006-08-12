/***********************************************************
* File:	AlertDriver.java
*
* @author 		Anupom (http://anupom.wordpress.com)
*
* @description 	The driver class for testing DesktopAlert
*
* @version 0.01 August 11,2006
*
***********************************************************/

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.ImageIcon;

public class DesktopAlertDriver
{
	/**
	 * Method main
	 *
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String title = "Mindoms Live Messenger";
		String desc  = "anupom<br> has just signed in";
		int time	 = 5000;
		MetalLookAndFeel.setCurrentTheme(new DesertMetalTheme());
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		DesktopAlert dAlert = 	new DesktopAlert(title,desc,
								new ImageIcon("test.gif").getImage(),
								new Color(225, 225, 250), new Color(205, 205, 230),
								new Dimension(200,115));
		dAlert.popUp(true,10,time);
		
		dAlert.removeAlertImage();
		desc  = "You have 673 new messages in your e-mail Inbox.";
		dAlert.setDescText(desc);
		dAlert.popUp(false,10,time);
		
		dAlert.setAlertImage(new ImageIcon("test.gif").getImage());
		DesktopAlertRunner dRunner1 = new DesktopAlertRunner(dAlert,true,10,time);
		DesktopAlertRunner dRunner2 = new DesktopAlertRunner(dAlert,true,10,time);
		
		dRunner1.start();
		dRunner2.start();
		
	}
	
	private static void wait(int time)
	{
		try 
		{
			Thread.sleep(time);
		}
		catch(InterruptedException ie){}
	}
}
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
package com.syamantics.desktopalert;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DesktopAlertDriver
{
	/**
	 * Method main
	 *
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//setting up some variables for testing
		String title 	= "Mindoms Live Messenger";
		String desc1  	= "anupom<br> has just signed in";
		String desc2  	= "You have 673 new messages in your e-mail Inbox.";
		String imgName 	= "test.gif";
		Color gradient1	= new Color(225, 225, 250);
		Color gradient2	= new Color(205, 205, 230);
		int width		= 200;
		int height		= 115;
		int time	 	= 5000;
		
		//setting the the metal theme
		MetalLookAndFeel.setCurrentTheme(new SkyMetalTheme());
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		//creating the desktop-alert
		DesktopAlert dAlert = 	new DesktopAlert(title,desc1,
								new ImageIcon(imgName).getImage(),
								gradient1,gradient2 ,
								new Dimension(width,height));
                
               SimpleFrame frame = new SimpleFrame(dAlert);
               frame.setVisible(true);
               
		/*TESTING WITHOUT THREADS*/
                
               //pop-up
		System.out.println("Vertical pop-up with image, text = desc1");
		dAlert.popUp(true,10,time);
		//removing alert image
		dAlert.removeAlertImage();
		//setting the description text
		dAlert.setDescText(desc2);
		//again popup
		System.out.println("Horizontal pop-up without image, text = desc2");
		dAlert.popUp(false,10,time);
		
		/*TESTING WITH THREADS*/
		// setting the image again
		dAlert.setAlertImage(new ImageIcon(imgName).getImage());
		//creating runner classes to run threaded alerts
		DesktopAlertRunner dRunner1 = new DesktopAlertRunner(dAlert,true,10,time);
		DesktopAlertRunner dRunner2 = new DesktopAlertRunner(dAlert,true,10,time);
		//starting threads
		System.out.println("Vertical pop-up with image (threaded) #1");
		dRunner1.start();
		//a little time delay between two popups
		wait(500);
		System.out.println("Vertical pop-up with image (threaded) #2");
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

class SimpleFrame extends JFrame implements ActionListener
{
    DesktopAlert dAlert;
    
    public SimpleFrame(DesktopAlert dAlert)
    {
       this.dAlert = dAlert;
       
       JButton button = new JButton("Click here to Pop-Up another!");
       button.setForeground(Color.darkGray);
       button.setBorder(BorderFactory.createLineBorder(Color.darkGray));
       this.add(button);
       button.addActionListener(this);
       
       this.setSize(300, 100);
       this.setTitle("ActionListener Test");
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   
    public void actionPerformed(ActionEvent e)
    {
        DesktopAlertRunner dRunner3 = new DesktopAlertRunner(this.dAlert, true, 10, 5000);
        dRunner3.start();
    }
}
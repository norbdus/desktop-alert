/***********************************************************
* File:	DesktopAlert.java
*
* @author 		Anupom (http://anupom.wordpress.com)
*
* @description 	A popup alert that appears in the lower 
*				right part of the desktop. Can be used with or w/o Image
*
* @version 0.01 August 11,2006
*
***********************************************************/
package desktopalert;

import java.awt.*;
import javax.swing.*;

public class DesktopAlert extends JDialog
{
	//the main panel of the alert that has gradient background
	private DesktopAlertPanel mainPane;
	//text of the title bar
	private String title;
	//the text message that will appear in the body of the alert
	private String desc;
	//upper & lower gradient color of the alert
	private Color color1, color2;
	//size of the alert box
	private Dimension size;
	//image that will be shown in the left side of the alert
	private Image image = null;
	
	/**
     * Constructor of DesktopAlert without image
     * 
     * @param title		Title of the alert
     * @param desc		Description that will appear inside the alert
     * @param color1	Upper gradient color
     * @param color2	Lower gradient color
     * @param size		Size of the DesktopAlert
     */
	public DesktopAlert(String title, String desc,
						Color color1, Color color2, Dimension size)
	{
		this.title 	= title;
		this.desc 	= desc;
		this.color1 = color1;
		this.color2 = color2;
		this.size	= size;
		initContainer();
	}
	
	/**
     * Constructor of DesktopAlert with image
     * 
     * @param title		Title of the alert
     * @param desc		Description that will appear inside the alert
     * @param image		Image that will appear inside the alert
     *					The Image will be automatically resized according to
     *					the size of the Alert. Using images with the same 
     *					width:height ratio of the alert is recommended
     * @param color1	Upper gradient color
     * @param color2	Lower gradient color
     * @param size		Size of the DesktopAlert
     */
     public DesktopAlert(String title, String desc, Image image,
						Color color1, Color color2, Dimension size)
	{
		this.title 	= title;
		this.desc 	= desc;
		this.color1 = color1;
		this.color2 = color2;
		this.size	= size;
		this.image = image;
		initContainer();
	}
	
	
	/**
     * Initializes components of the alert
     */
    private void initContainer()
	{
		this.setTitle(title);
		//setting the alert always on top
		this.setAlwaysOnTop(true);
		//setting the size
		this.setSize(size);
		//setting the alert non-resizable
		this.setResizable(false);
		
		//if image is not null then use the AlertPanel's constructor that 
		//takes an image
		if(image!=null)
		{
			mainPane = new DesktopAlertPanel(image, desc, color1, color2, size);
		}
		// when image is null use the constructor that does not need an Image
		else
		{
			mainPane = new DesktopAlertPanel(desc, color1, color2, size);
		}
			
		//add the panel
		this.getContentPane().add(mainPane);
	}

	/**
     * Sets the alert visible & Moves the alert
     * 
     * @param isUpward	isUpward is true when the alert pops up from the bottom
     *					of the desktop (vertical), false when the alert pops up 
     *					from the right side (horizontal).
     * @param speed		Speed of the alert's movement (usual value is 10 to 50)
     * @param time		Total time (in miliseconds) of alert's visibility
     *					(usual value is 1000 to 5000)
     */
	public void popUp(boolean isUpward, int speed, int time)
	{
		//validate the speed, if speed is not a non-zero positive number
		//then assign the value 10 to speed
		int speedValidated = (speed>0) ? speed : 10;
		//move's the alert to the needed position of the screen
		positionScreen(isUpward, speedValidated);
		//wait for a certain time then dispose
		waitAndDispose(time);
	}

    /**
     * Moves & shows the alert
     * 
     * @see #popUp(boolean,int,int)
     */
	private void positionScreen(boolean isUpward, int speed)
	{
		//get the size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//the x position of the alert's final location
		int x = (int) (screenSize.getWidth() - getWidth()-20);
		//the y position of the alert's final location
		int y = (int) (screenSize.getHeight() - getHeight()-30);
		//if isUpward is true move the alert upward frm the bottom of the screen
		if(isUpward)
		{
			//the initial Y position of the alert
			int varY = (int) screenSize.getHeight();
			setLocation(x,varY);
			setVisible(true);
		
			while(y<varY) 
			{
				try 
				{
					//move the alert verticaly
					varY-=speed;
					setLocation(x, varY);
					//use some delay time
					Thread.sleep(10);
				}
				catch(InterruptedException ie){}
			}
			//move the alert to the final location
			setLocation(x,y);
		}
		else
		{
			//the initial X position of the alert
			int varX = (int) screenSize.getWidth() + getWidth();
			setLocation(varX,y);
			setVisible(true);
		
			while(x<varX) 
			{
				try 
				{
					//move the alert horizontaly
					varX-=speed;
					setLocation(varX, y);
					Thread.sleep(10);
				}
				catch(InterruptedException ie){}
			}
			//move the alert to the final location
			setLocation(x,y);
		}
	}
	
	/**
     * Sets the alert visible for a certain time
     * 
	 * @see #popUp(boolean,int,int)
     */
	private void waitAndDispose(int time)
	{
		if(time !=0) 
		{
			try 
			{
				//wait
				Thread.sleep(time);
				//dispose
				dispose();
			}
			catch(InterruptedException ie){}
		}
	}
	
	/**
     * Sets text of the title bar
     * 
     * @param title		Text of the title-bar
     */	
	public void setTitleText(String title)
	{
		this.setTitle(title);
	}
	
	/**
     * Sets main text content of the alert
     *
     * @param text		Text that will appear inside the alert
     */	
    public void setDescText(String text)
	{
		mainPane.setDescText(text);
	}
	
	/**
     * Sets the image of the alert
     *
     * @param image 	The image that will appear inside the alert
     */	
    public void setAlertImage(Image image)
	{
		mainPane.setAlertImage(image);
	}
	
	/**
     * Removes the image from the alert.
     * The alert will contain no image after removal
     */	
    public void removeAlertImage()
	{
		mainPane.removeAlertImage();
	}
	
	/**
     * Gets text of the title bar
     * 
     * @return	Text of the title
     */	
	public String getTitleText()
	{
		return this.title;
	}
	
	/**
     * Gets the main text content of the alert
     *
     * @return	Main text content
     */	
    public String getDescText()
	{
		return mainPane.getDescText();
	}
	
	/**
     * Sets the font of the content text of the alert
     */	
    public void setDescFont(Font font)
	{
		mainPane.setDescFont(font);
	}
} 
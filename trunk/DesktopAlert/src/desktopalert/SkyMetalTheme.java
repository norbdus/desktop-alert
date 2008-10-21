/***********************************************************
* File:	SkyMetalTheme.java
*
* @author 		Anupom (http://anupom.wordpress.com)
*
* @description 	This class describes a theme using "blue" colors.
* 				The L&F is specially designed for the DesktopAlert
*
* @version 0.00 August 11,2006
*
***********************************************************/
package desktopalert;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Enumeration;

public class SkyMetalTheme extends DefaultMetalTheme 
{
    //just giving a name of the theme
    public String getName() { return "SkyBlue"; }
    
    //defining 2 colors
    private final ColorUIResource color1 = new ColorUIResource(204, 204, 255);
    private final ColorUIResource color2 = new ColorUIResource(180, 180, 230);
	//when the window is active
    protected ColorUIResource getPrimary3() { return color1; }
    public ColorUIResource getPrimaryControl() { return color2; }
	public ColorUIResource getPrimaryControlShadow() { return color2; }
	public ColorUIResource getPrimaryControlHighlight() { return color2;}
	public ColorUIResource getPrimaryControlDarkShadow() { return color2;}
	public ColorUIResource getPrimaryControlInfo() { return color2; }
	//when the window is not active
	protected ColorUIResource getSecondary3() { return color1; }
	public ColorUIResource getControl() { return color2; }
	public ColorUIResource getControlShadow() { return color2; }
	public ColorUIResource getControlHighlight() { return color2;}
	public ColorUIResource getControlDarkShadow() { return color2;}
	public ColorUIResource getControlInfo() { return color2; }
}

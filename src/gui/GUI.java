////////////////////////////////////////////////
// GUI.java
// A class for GUI. Do not edit.
// Johan Book
// 2015-09-12
////////////////////////////////////////////////

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import character.Character;

@SuppressWarnings("serial")
public class GUI extends JFrame
{
    private BufferedImage image;
    private List<Character> Characters;
    private final int CHARACTER_SIZE = 5;

    // Constructor
    public GUI(List<Character> Characters, java.awt.Dimension dimension)
    {
	this.Characters = Characters;

	// Menu
	final JPanel menu = new JPanel();
	final JButton btnSave = new JButton("Save Image");

	btnSave.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		saveImage("image");
	    }
	});
	menu.add(btnSave);

	// Info menu
	Panel panel = new Panel();
	panel.setForeground(Color.white);
	panel.setPreferredSize(dimension);

	add(panel, BorderLayout.CENTER);
	add(menu, BorderLayout.SOUTH);

	setTitle("A Vicious Walk");
	pack();
	setVisible(true);
	requestFocus();
    }
    
    public void update()
    {
	repaint();
    }

    // ================ Panel ================
    private class Panel extends JPanel
    {
	@Override
	protected void paintComponent(Graphics gr)
	{
	    super.paintComponent(gr);
	    try
	    {
		image = drawToImage(this.getWidth(), this.getHeight());
	    } catch (Exception e)
	    {
		e.printStackTrace();
		System.out.println("ERROR: Unable to draw image to panel");
	    }
	    gr.drawImage(image, 0, 0, null);
	}
    }

    // Method for drawing graphics and storing it in an image
    private BufferedImage drawToImage(int X, int Y)
    {
	BufferedImage image = new BufferedImage(X, Y, BufferedImage.TYPE_INT_ARGB);
	Graphics gr = image.createGraphics();

	for (Character Character : Characters)
	{
	    gr.setColor(Character.getColor());
	    gr.fillOval((int) Character.getPosition()[0], (int) Character.getPosition()[1], CHARACTER_SIZE,
		    CHARACTER_SIZE);
	    gr.setColor(Color.BLACK);
	    gr.drawOval((int) Character.getPosition()[0], (int) Character.getPosition()[1], CHARACTER_SIZE,
		    CHARACTER_SIZE);
	}

	return image;
    }

    private void saveImage(String string)
    {
	try
	{
	    File outputfile = new File("data/" + string + ".png");
	    javax.imageio.ImageIO.write(image, "png", outputfile);
	    System.out.println("Image saved");
	} catch (Exception e)
	{
	    System.out.println("ERROR: Unable to save image to file");
	}
    }
}
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.util.*;
/**
 * DO NOT MODIFY THIS CLASS.
 * 
 * @originalAuthor Benjamin Smith
 * @version 0.7
 * @since 2018-03-18
 */
public class ImageUtils 
{
  // Frame to display on screen.
  private JFrame frame;
  private JTabbedPane tabbedPane;

  ImageUtils() 
  {
    // Create a new frame to display on screen.
    frame = new JFrame("Project Images");

    // The exit application default window close operation.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set up TabbedPanes to switch between images.
    tabbedPane = new JTabbedPane();

    // Set the Content pane
    frame.setContentPane(tabbedPane);
  }
  
  public Color[][] loadImage(String filepath) 
  {
    // Load in the image.
    BufferedImage buffImg = loadBufferedImage(filepath);
    // Convert that image to the 2D array of colors and return it.
    Color[][] colorImg = convertTo2DFromBuffered(buffImg);
    return colorImg;
  }

  public void addImage(Color[][] img, String tabName) 
  {
    // Convert the 2D Color array to BufferedImage
    BufferedImage buffImg = convertToBufferedFrom2D(img);

    //Icon Settings
    ImageIcon icon = new ImageIcon(buffImg);
    icon.getImage().flush();

    ImageIcon tabIcon = new ImageIcon(
        buffImg.getScaledInstance(32, 32, Image.SCALE_SMOOTH));

    JLabel label = new JLabel();
    label.setIcon(icon);

    tabbedPane.addTab(tabName, tabIcon, label);
  }
  

  public void display() 
  {
    frame.pack();

    frame.setMinimumSize(frame.getPreferredSize());

    frame.setVisible(true);
  }
  
  public static Color[][] cloneArray(Color[][] orig) {
    // Create array that is the copy
    Color[][] copy = new Color[orig.length][orig[0].length];
    // Go through each value copying it over.
    for (int i = 0; i < orig.length; i++) {
      for (int j = 0; j < orig[i].length; j++) {
        copy[i][j] = orig[i][j];
      }
    }
    // Return the copy.
    return copy;
  }
 
  private static BufferedImage loadBufferedImage(String filepath) 
  {
    BufferedImage img = null;
    try 
    {
      img = ImageIO.read(new File(filepath));
    } 
    catch (IOException e) 
    {
      System.out.println("Could not load the image, please ensure the filepath"
          + " was properly specified.");
      e.printStackTrace();
      System.exit(1);
    }
    return img;
  }

  private static BufferedImage convertToBufferedFrom2D(Color[][] img) 
  {
    int width = img.length;
    int height = img[0].length;
    BufferedImage bufImg = new BufferedImage(width, height, 1);

    for (int x = 0; x < width; x++) {
      for(int y = 0; y < height; y++) {
        bufImg.setRGB(x,  y, img[x][y].getRGB());
      }
    }

    return bufImg;
  }
  private static Color[][] convertTo2DFromBuffered(BufferedImage img) 
  {
    int width = img.getWidth();
    int height = img.getHeight();
    Color[][] result = new Color[height][width];
    
    for (int row = 0; row < width; row++) 
    {
      for (int col = 0; col < height; col++) 
      {
        int intRGB = img.getRGB(row, col);
        int red = (intRGB >> 16)&255;
        int green = (intRGB >> 8)&255;
        int blue = intRGB&255;
        result[row][col] = new Color(red, green, blue);
      }
    }
    return result;
  }
}

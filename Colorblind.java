import java.awt.Color;
import java.util.Scanner;

public class Colorblind 
{
	public static void main(String[] args)
	{
	 //Declaration
	 Scanner input = new Scanner(System.in);
	 ImageUtils utils = new ImageUtils();
	 
	 //User Interaction
	 String userSelect = "";
	 
	 System.out.println("I am a program that can run any of the following colorblind simulators and other filters");
	 System.out.println("\t1) Protanopia: weak perception of RED");
	 System.out.println("\t2) Deuteranopia: weak perception of GREEN");
	 System.out.println("\t3) Tritanopia: weak perception of BLUE");
	 System.out.println("\t4) Protanomaly: no perception of RED");
	 System.out.println("\t5) Deuteranomaly: no perception of GREEN");
	 System.out.println("\t6) Tritanomaly: no perception of BLUE");
	 System.out.println("\t7) Monochromacy: no perception of color");
	 System.out.print("\nEnter the number of the simulator you wish to view: ");
	 userSelect = input.nextLine();
	 
	 //Load Image
	 Color[][] orig = utils.loadImage("src/LennaCV.png");
	 
	 //Original Image Display
	 utils.addImage(orig, "Original image.");
	 
	 //Custom Image Displays
	 Color[][] selectColor = colorblindFilter(orig,userSelect);
	 utils.addImage(selectColor, "Colorblind Simulator");
	 utils.display();
	 
	 Color[][] grayScale = colorPick(orig);
	 utils.addImage(grayScale, "Isolated Color");
	 utils.display();
	 
	 Color[][] negativeImage = switchColor(orig); 
     utils.addImage(negativeImage, "Swtiched RGB Values");
	 utils.display();
	 
	}
	
	public static Color[][] switchColor(Color[][] img)
	{
	 Color[][] tmp = ImageUtils.cloneArray(img);
	 
	 for(int row = 0; row < tmp.length; row++)
	 {
	  for(int col = 0; col < tmp[row].length; col++)
	  {
		   //RGB 0 - 255
			  Color pixel = tmp[row][col];
			  
			  int r = pixel.getRed(); 
			  int g = pixel.getGreen();
			  int b = pixel.getBlue();
			   
			  tmp[row][col] = new Color(g,b,r);          	  
	  }
	 }
	 return tmp;
	}
	
	public static Color[][] colorblindFilter(Color[][] img,String userSelect)
	{
     int newR = 0;
     int newG = 0;
     int newB = 0;
	 Color[][] tmp = ImageUtils.cloneArray(img);
	 
	 for(int row = 0; row < tmp.length; row++)
	 {
	  for(int col = 0; col < tmp[row].length; col++)
	  {  
			  Color pixel = tmp[row][col];
			  
			  int r = pixel.getRed(); 
			  int g = pixel.getGreen();
			  int b = pixel.getBlue();
			 
			  if(userSelect.equals("1"))
			  {
			   newR = (int)((r*0.567) + (g*0.433) + (b*0.0));
			   newG = (int)((r*0.558) + (g*0.442) + (b*0.0));
			   newB = (int)((r*0.0) + (g*0.242) + (b*0.758));  
			  }
			  else if(userSelect.equals("2"))
			  {
			   newR = (int)((r*0.625) + (g*0.375) + (b*0.0));
			   newG = (int)((r*0.7) + (g*0.3) + (b*0.0));
			   newB = (int)((r*0.0) + (g*0.3) + (b*0.7));  
			  }
			  else if(userSelect.equals("3"))
			  {
			   newR = (int)((r*0.95) + (g*0.05) + (b*0.0));
			   newG = (int)((r*0.0) + (g*0.433) + (b*0.567));
			   newB = (int)((r*0.0) + (g*0.475) + (b*0.525));  
			  }
			  else if(userSelect.equals("4"))
			  {
			   newR = (int)((r*0.817) + (g*0.183) + (b*0.0));
			   newG = (int)((r*0.333) + (g*0.667) + (b*0.0));
			   newB = (int)((r*0.0) + (g*0.125) + (b*0.875));  
			  }
			  else if(userSelect.equals("5"))
			  {
			   newR = (int)((r*0.8) + (g*0.2) + (b*0.0));
			   newG = (int)((r*0.258) + (g*0.742) + (b*0.0));
			   newB = (int)((r*0.0) + (g*0.142) + (b*0.858));  
			  }
			  else if(userSelect.equals("6"))
			  {
			   newR = (int)((r*0.967) + (g*0.033) + (b*0.0));
			   newG = (int)((r*0.0) + (g*0.733) + (b*0.267));
			   newB = (int)((r*0.0) + (g*0.183) + (b*0.817));  
			  }
			  else 
			  {
			   newR = (int)((r*0.299) + (g*0.587) + (b*0.114));
			   newG = newR;
			   newB = newG;  
			  }
			  
			  //checks if colors are in RGB range
			  if(newR > 255)
			  {
			   newR = 255;
			  }
			  else if(newR < 0)
			  {
			   r = 0;
			  }
			  if(newG > 255)
			  {
			   newG = 255;
			  }
			  else if(newG < 0)
			  {
			   newG = 0;
			  }
			  if(newB > 255)
			  {
			   newB = 255;
			  }
			  else if(newB < 0)
			  {
			   newB = 0;
			  }
			  
			   tmp[row][col] = new Color(newR,newG,newB);        	  
	  }
	 }
	 return tmp;
	}
	
	public static Color[][] colorPick(Color[][] img)
	{
	 Color[][] tmp = ImageUtils.cloneArray(img);
	 
	 for(int row = 0; row < tmp.length; row++)
	 {
	  for(int col = 0; col < tmp[row].length; col++)
	  {
			  Color pixel = tmp[row][col];
			  
			  int r = pixel.getRed(); 
			  int g = pixel.getGreen();
			  int b = pixel.getBlue();
			   
			  //isolates color values
			  if( ((r > 80 && r < 165) && (g > 19 && g < 140) && (b > 100 && b < 200))  ||  ((r > 70 && r < 95) && (g < 20) && (b > 65 && b < 90)))
			  {
			   int newR = r;
			   int newG = g;
			   int newB = b;
			   
			   //color range check again
			   if(newR > 255)
			   {
				 newR = 255;
			   }
			   if(newG > 255)
			   {
				 newG = 255;
			   }
			   if(newB > 255)
			   {
				 newB = 255;
			   }
			   //Switched around colors to make feather appear in different colors
			   tmp[row][col] = new Color(newB,newG,newR);   
			  }
			  else
			  {
			   int sum = (r + b + g)/3;
			   tmp[row][col] = new Color(sum,sum,sum);   
			  }		         
	  }
	 }
	 return tmp;
	}
	
	public static Color[][] cloneArray(Color[][] orig) 
	{
	    Color[][] copy = new Color[orig.length][orig[0].length];
	    for (int i = 0; i < orig.length; i++) 
	    {
	      for (int j = 0; j < orig[i].length; j++) 
	      {
	        copy[i][j] = orig[i][j];
	      }
	    }
	    return copy;
	}
}

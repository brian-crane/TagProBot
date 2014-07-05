import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.Robot;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JWindow;

public class Main extends JPanel
{
	int res =10;//10 normally
	boolean redraw = false;
	Color[][] colors = new Color[800][600];
	ArrayList<Obj> balls = new ArrayList<Obj>();
	Obj mainBall;
	int leftCount = 0, rightCount=0,upCount=0,downCount=0;
	Obj left,up,right,down;
	int drawRange = 0;
	int xPower = 0, yPower = 0;
	boolean activated = true, foundTarget=false;
	//int x=0, y=0;
	JButton act, window;
	JSlider radSlider,radSlider2, rate,randSlider,resSlider,huntSlider;
	JLabel upper,lower;
	ArrayList<Obj> actives = new ArrayList<Obj>();
	
	public static void main (String args[]) throws AWTException
	{
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_LEFT);
		
		
		
		  final Main main = new Main();
		  
		  
			main.left = new Obj(400,800,7);
			main.down = new Obj(440,800,7);
			main.right = new Obj(480,800,7);
			main.up = new Obj(440,760,7);
			

			
			
			
		JFrame frame = new JFrame("TagPro");
		frame.setContentPane(main);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(new Dimension(900,1050));
		    //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		    
		    main.radSlider = new JSlider();
		    main.radSlider.setFocusable(false);
		    main.rate = new JSlider();
		    main.rate.setFocusable(false);
		    main.radSlider2 = new JSlider();
		    main.radSlider2.setFocusable(false);
		    main.huntSlider = new JSlider();
		    main.huntSlider.setFocusable(false);
		    
		    main.resSlider = new JSlider();
		    main.resSlider.setFocusable(false);
		    main.randSlider = new JSlider();
		    main.randSlider.setFocusable(false);
		    
		    frame.getContentPane().add(main.randSlider);

		    
		    //main.upper = new JLabel("Upper: "+main.radSlider2.getValue()*2);
		    main.lower = new JLabel("  Lower: "+main.radSlider.getValue()*2);
		    frame.getContentPane().add(main.lower);
		    //frame.getContentPane().add(main.upper);
		    
		    

		    frame.getContentPane().add(main.radSlider);
		    frame.getContentPane().add(main.radSlider2);
		    frame.getContentPane().add(main.rate);
		    frame.getContentPane().add(main.resSlider);
		    frame.getContentPane().add(main.huntSlider);
		    main.act = new JButton("Deactivate");
		    main.window = new JButton("ShowWindow");
		   
		    //main.act.setLocation(100	, 400);
		    frame.getContentPane().add(main.act);
		    frame.getContentPane().add(main.window);
	        //Add action listener to button
		    main.act.addActionListener(new ActionListener() {
		    	 	//final boolean m = main.activated;
	            public void actionPerformed(ActionEvent e)
	            {
	            		String p = main.act.getText();
	                //Execute when button is pressed
	                System.out.println("You clicked the button");
	                if(p.equals("Deactivate"))
	                {
	                		main.act.setText("Activate");
	                }
	                else
	                {
	                		main.act.setText("Deactivate");
	                }
	            }
	        });    
		    
		    main.window.addActionListener(new ActionListener() {
	    	 	//final boolean m = main.activated;
            public void actionPerformed(ActionEvent e)
            {
            		String p = main.window.getText();
                //Execute when button is pressed
               // System.out.println("You clicked the button");
                if(p.equals("ShowWindow"))
                {
                		main.window.setText("HideWindow");
                }
                else
                {
                		main.window.setText("ShowWindow");
                }
            }
        });    
		    
		    
		    Rectangle captureSize = new Rectangle(900, 150, 800, 600);
			BufferedImage image = robot.createScreenCapture(captureSize);
			JLabel label = new JLabel(new ImageIcon(image));
			//frame.getContentPane().add(label);
			//label.setBounds(0, -600, 800, 600);
			 
			main.processImage(image);
			 //main.typeWord(robot, "wow this is one of the better matchups I have been a part of");

		 
		  frame.setVisible(true);
		  long j = 0;
		  long k = 1;
		  
		  int c = 0;
		  
		  while(k<10000)
		  {
			  if(k==10000-1)
			  {
				  main.leftCount = 0;
				  main.rightCount = 0;
				  main.upCount = 0;
				  main.downCount = 0;
				  k=0;
			  }
			  
			  if(k%100  == 0 && j==0)
			  {
				  main.lower.setText("Hunt: " + main.huntSlider.getValue() + ", thresh: " + main.resSlider.getValue()/10+ ", drag: " + (Double)(main.randSlider.getValue()/100.0) + ", " + main.radSlider.getValue()*2 + "->" + main.radSlider2.getValue()*2 + " at " + main.rate.getValue()/4);
				  int upper = main.radSlider2.getValue()*2;
				  int lower = main.radSlider.getValue()*2;
				  //if(c%10==0)System.out.println("c = " + c);
				  
				  if(c<upper)c+=main.rate.getValue()/4;
				  else
				  {
					  
					  
					  if(Math.random()>.99)
					  {
						  //main.randomComment(robot);
					  }
					  
					    
					    
					  //System.out.println("first slider: " + main.radSlider.getValue() + " second slider: " + main.radSlider2.getValue());
					  c=lower;
				  }
				  
				 main.drawRange = c;
				 
				  
				  if(Math.random()>.9)
				  {
					 //main.typeWord(robot, "mb");

					  // of the better matchups i have been a part of
					  
					  //main.typeWord(robot, "wow this is one of the better matchups I have been a part of");
				  }
				  
				  main.foundTarget=false;
				  main.redraw = true;
				  image = robot.createScreenCapture(captureSize);
				  //label = new JLabel(new ImageIcon(image));
				  
				  //label.setIcon(new ImageIcon(image));
				  //label.setVerticalAlignment(1);
				  //frame.getContentPane().removeAll();
				  //frame.getContentPane().add(label);
				  
				  frame.validate();
				  main.balls.clear();
				main.processImage(image);
				if(c>5)main.updateBall(robot, true, 20+2*c);
				//else main.updateBall(robot, false, 60+c);

				
				main.repaint();
				/*
				  if(main.res>9)
				  {
					  main.res-=8;
				  }
				  else
				  {
					  for(int i=0; i<800; i++)
						{
							for(int m=0; m<600; m++)
							{
								main.colors[i][m]=new Color(255,255,255,0);
							}
						}
						
					  main.res=64;
				  }
				  */
				//System.out.println("Color at 55,55 = " + main.colors[55][55]);
			  }
			  
			  if(j>100000)
			  {
				  
				  k++;
				  j=0;
			  }
			  else 
			  {
				  j++;
			  }
			  
		  }
	}
	
	private void randomComment(Robot robot)
	{	
		int choice = (int)(Math.random() * 2);
		System.out.println("hey");
		if(choice == 0)
		{
			typeWord(robot, ("Wow this game is like Monopoly from " + (int)(Math.random())*20));
		}
		
	}
	
	public void updateBall(Robot robot, boolean flip, int rang)
	{
	
		if(mainBall == null || act.getText().equals("Activate"))
		{
			
			return;
		}
		int x=0, y=0;
		
		
		for(int i =0; i<balls.size(); i++)
		{
			int xDiff = balls.get(i).x-mainBall.x;
			int yDiff = balls.get(i).y-mainBall.y;
			
			if(flip)
			{
				xDiff = -xDiff;
				yDiff = -yDiff;
				//xDiff *= 2;
				//yDiff *= 2;

			}
			
			
			
			int range = rang;
			int type =1;
			if(mainBall.type==4)type =0;
			//System.out.println("I am a ball of type: " + mainBall.type + ", I hunt a ball of type: " + type);
			int idle = 0;
			int spike=48;
			if(Math.random()>.5)spike=48;
			int ballWeight = huntSlider.getValue();
			if(foundTarget == false && (balls.get(i).type == type || balls.get(i).type == type+3) && xDiff<400+range*2 && xDiff>0  && Math.abs(yDiff)<range)
			{
				foundTarget=true;
				balls.get(i).mark=true;
				x+=-ballWeight;
			}
			else if(xDiff<range && xDiff>0  && Math.abs(yDiff)<range/2)
			{
				//balls.get(i).type = 9;
				//balls.add(new Obj(balls.get(i).x, balls.get(i).y, 9));
				//System.out.println(xDiff);
				balls.get(i).mark=true;
				if(balls.get(i).type == 5 && xDiff<=spike)x+=7;
				x++;
				if(balls.get(i).type == type)x+=-2;
				//if(xDiff<=64)x+=1;
				//if(xDiff>=-32)x+=2;
				//else x++;
			}
			else if(foundTarget == false && (balls.get(i).type == type || balls.get(i).type == type+3) && xDiff>-range*2-400 && xDiff<0  && Math.abs(yDiff)<range)
			{
				foundTarget=true;
				balls.get(i).mark=true;
				x+=ballWeight;
			}
			else if(xDiff>-range && xDiff<0 && Math.abs(yDiff)<range/2)
			{
				//balls.get(i).type = 9;
				x--;
				balls.get(i).mark=true;
				if(balls.get(i).type == 5 && xDiff>=-spike)x+=-7;
				if(balls.get(i).type == type)x+=2;
				//if(xDiff>=-64)x-=1;
				//if(xDiff<32)x-=2;
				//else x--;
				
			}
			
			if(foundTarget == false && (balls.get(i).type == type || balls.get(i).type == type+3) && yDiff<400+range*2 && yDiff>0  && Math.abs(xDiff)<range)
			{
				foundTarget=true;
				balls.get(i).mark=true;
				y+=ballWeight;
			}
			else if(yDiff<range && yDiff>0 && Math.abs(xDiff)<range/2)
			{
				//balls.get(i).type = 9;
				//y+= - (range-yDiff);
				if(balls.get(i).type == 5 && yDiff>=-spike)y+=-7;
				if(balls.get(i).type == type)y+=2;
				//if(yDiff>=-64)y-=1;
				//if(yDiff>=-32)y-=2;
				y--;
				
			}
			else if(foundTarget == false && (balls.get(i).type == type || balls.get(i).type == type+3) && yDiff>-00-range*2 && yDiff<0  && Math.abs(xDiff)<range)
			{
				foundTarget=true;
				balls.get(i).mark=true;
				y+=-ballWeight;
			}
			else if(yDiff>-range && yDiff<0 && Math.abs(xDiff)<range/2)
			{
				//balls.get(i).type = 9;
				//System.out.println("type = " + type);
				if(balls.get(i).type == 5 && yDiff<=spike)y+=7;
				//y+= (range-yDiff);
				//System.out.println("up " + yDiff);
				if(balls.get(i).type == type)y+=-2;
				//if(yDiff<=64)y+=1;
				//if(yDiff<=32)y+=2;
				
				else y++;
			}
		}
		//System.out.println("type " + x + ", " + y);
		
		xPower = x;
		yPower = y;
		
		int range = resSlider.getValue()/10;
		int size = 8;
		Double rand = .96;
		
		rand = (Double)(randSlider.getValue()/100.0);
		
		//if(Math.random()>.5)range = 0;
		if(x<-range && x>-15 && Math.random()<rand || leftCount*size<rightCount)
		{
			//System.out.println("left " + x);
			leftCount++;
			robot.keyRelease(KeyEvent.VK_RIGHT);
			robot.keyPress(KeyEvent.VK_LEFT);
			right.type = 7;
			left.type = 8;
		}
		else if(x>range && x<15 && Math.random()<rand || rightCount*size<rightCount)
		{
			rightCount++;
			//System.out.println("right " + x);
			robot.keyRelease(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_RIGHT);
			right.type = 8;
			left.type = 7;
		}
		else if(x==0 && Math.random()<rand)
		{
			
			//idle++;
			if(Math.random()<.05)
			{
				robot.keyRelease(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
				right.type = 7;
				left.type = 7;
			}
			
		}
		if(y>range && y<15 && Math.random()<rand  || upCount *size<downCount)
		{
			upCount++;
			//System.out.println("up " + y);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_UP);
			down.type = 7;
			up.type = 8;
		}
		else if(y<-range &&y>-15 && Math.random()<rand   || downCount *size<upCount)
		{
			downCount++;
			//System.out.println("down " + y);
			robot.keyRelease(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_DOWN);
			down.type = 8;
			up.type = 7;
		}
		else if(y==0 && Math.random()<rand)
		{
			//idle++;
			if(Math.random()<.05)
			{
					robot.keyRelease(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_DOWN);
			down.type = 7;
			up.type = 7;
			}
		
		}
		//System.out.println("left = " + leftCount + ", right = " + rightCount + ", up = " + upCount + " down = " + downCount);
		
		
	}
	
	public void paintComponent (Graphics g)
    { 
		super.paintComponent(g); 
		Graphics2D g2d = (Graphics2D) g.create();
		
		
		
		if(redraw == true)
		{
			//g2d.clearRect(0, 0, 2000, 2000);
			left = new Obj(400,800,left.type);
			down = new Obj(455,800,down.type);
			right = new Obj(510,800,right.type);
			up = new Obj(455,745,up.type);
			
			balls.add(left);
			balls.add(down );
			balls.add(right);
			balls.add(up);
            
            redraw = false;
		}
		//System.out.println("type = " + balls.size());
        
		for(int i=0; i<800; i++)
		{
			for(int j=0; j<600; j++)
			{
				Color c =  colors[i][j];

				
				if(c!=null)
				{
					g2d.setPaint(c);
					
					//c = new Color(c.getRed(), c.getBlue(), c.getGreen(), (float)0.9);
					//miniscreen 
					g2d.fill(new Rectangle(i*res/2, 632+j*res/2, res/2, res/2));
					
					if((window.getText().equals("HideWindow"))  &&  Math.abs(360-i*res)<40 && Math.abs(300-j*res)<40)g2d.fill(new Rectangle(10+i*res, 10+j*res, res, res));
					//if(Math.abs(410-i*res)<36 && Math.abs(265-j*res)<10)g2d.fill(new Rectangle(10+i*res, 10+j*res, res, res));

					if(mainBall != null)
					{
						//if(mainBall.x<i*res && mainBall.x+50>i*res && mainBall.y<j*res && mainBall.y+50>j*res)
							//g2d.fill(new Rectangle(10+i*res, 10+j*res, res, res));

					}

					//g2d.fill(new Rectangle(10+i*res, 10+j*res, res, res));
				}
					
					
			}
				
				
		
				
		}
		
		
		g2d.setPaint(Color.RED);
		Ellipse2D.Double temp = new Ellipse2D.Double(360-2*drawRange, 300-2*drawRange, 20 + 4*drawRange, 20+ 4*drawRange);
		g2d.draw(temp);
		
		
		
		
		g2d.drawLine(360, 300, 360+xPower*res, 300);
		g2d.drawLine(360, 301, 360+xPower*res, 301);
		g2d.drawLine(360, 300, 360, 300-yPower*res);
		g2d.drawLine(361, 300, 361, 300-yPower*res);
		
		for(int k=0; k<balls.size(); k++)
        {
			Obj b=null;
			try{
			b = balls.get(k);}
			catch(Exception e){}
        		if(b!=null)
        		{

            		if(b.type == 0)
            		{
            			g2d.setPaint(Color.BLUE);
            			Ellipse2D.Double circle = new Ellipse2D.Double(b.x, b.y, 32, 32);
    					g2d.fill(circle);
            		}
            		else if(b.type == 1)
            		{
            			g2d.setPaint(Color.RED);
            			Ellipse2D.Double circle = new Ellipse2D.Double(b.x, b.y, 32, 32);
    					g2d.fill(circle);
            		}
            		else if(b.type == 2)
            		{
            			g2d.setPaint(Color.GRAY);
            			g2d.fill(new Rectangle(b.x, b.y, 32, 32));
            		}
            		else if(b.type == 3)
            		{
            			g2d.setPaint(Color.CYAN);
            			Ellipse2D.Double circle = new Ellipse2D.Double(b.x, b.y, 32, 32);
    					g2d.fill(circle);
            		}
            		else if(b.type == 4)
            		{
            			
            			g2d.setPaint(Color.MAGENTA);
            			Ellipse2D.Double circle = new Ellipse2D.Double(b.x, b.y, 32, 32);
    					g2d.fill(circle);
            		}
            		else if(b.type == 5)
            		{
            			
            			g2d.setPaint(Color.BLACK);
            			Ellipse2D.Double circle = new Ellipse2D.Double(b.x, b.y, 32, 32);
    					g2d.fill(circle);
            		}
            		else if(b.type == 7) //unpressed
            		{
            			
            			g2d.setPaint(new Color(14,178,3));
            			g2d.draw(new Rectangle(b.x, b.y, 48, 48));
            			
            		}
            		else if(b.type == 8) //pressed
            		{
            			
            			g2d.setPaint(new Color(9,119,12));
            			g2d.fill(new Rectangle(b.x, b.y, 48, 48));
            		}
            		else if(b.type == 9) //pressed
            		{
            			
            			g2d.setPaint(new Color(9,119,12));
            			g2d.fill(new Rectangle(b.x, b.y, 32, 32));
            		}
            		if(b.mark == true)
            		{
            			g2d.setPaint(Color.BLACK);
            			g2d.draw(new Rectangle(b.x-2, b.y-2, 36, 36));
            			g2d.draw(new Rectangle(b.x-3, b.y-3, 38, 38));
            		}
        		}
        		
        		

        			//
				
				//Ellipse2D.Double circle = new Ellipse2D.Double(10+i*res, 10+j*res, res, res);
				//g2d.fill(circle);
        }
	}

    public void typeWord(Robot robot, String word)
    {
    		robot.keyPress(KeyEvent.VK_ENTER);
    		try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
    		for(int i =0; i<word.length(); i++)
    		{
    			try {
    			    Thread.sleep(1000);
    			} catch(InterruptedException ex) {
    			    Thread.currentThread().interrupt();
    			}
    			char temp = word.toLowerCase().charAt(i);
    			
    			
    			
    			
    			switch(temp)
    			{
    				case ' ':
					robot.keyPress(KeyEvent.VK_SPACE);
				break;
    				case 'a':
    					robot.keyPress(KeyEvent.VK_A);
    				break;
    				case 'b':
    					robot.keyPress(KeyEvent.VK_B);
    					break;
    				case 'c':
    					robot.keyPress(KeyEvent.VK_C);
    					break;
    				case 'd':
    					robot.keyPress(KeyEvent.VK_D);
    					break;
    				case 'e':
    					robot.keyPress(KeyEvent.VK_E);
    					break;
    				case 'f':
    					robot.keyPress(KeyEvent.VK_F);
    					break;
    				case 'g':
    					robot.keyPress(KeyEvent.VK_G);
    					break;
    				case 'h':
    					robot.keyPress(KeyEvent.VK_H);
    					break;
    				case 'i':
    					robot.keyPress(KeyEvent.VK_I);
    					break;
    				case 'j':
    					robot.keyPress(KeyEvent.VK_J);
    					break;
    				case 'k':
    					robot.keyPress(KeyEvent.VK_K);
    					break;
    				case 'l':
    					robot.keyPress(KeyEvent.VK_L);
    					break;
    				case 'm':
    					robot.keyPress(KeyEvent.VK_M);
    					break;
    				case 'n':
    					robot.keyPress(KeyEvent.VK_N);
    					break;
    				case 'o':
    					robot.keyPress(KeyEvent.VK_O);
    					break;
    				case 'p':
    					robot.keyPress(KeyEvent.VK_P);
    					break;
    				case 'q':
    					robot.keyPress(KeyEvent.VK_Q);
    					break;
    				case 'r':
    					robot.keyPress(KeyEvent.VK_R);
    					break;
    				case 's':
    					robot.keyPress(KeyEvent.VK_S);
    					break;
    				case 't':
    					robot.keyPress(KeyEvent.VK_T);
    					break;
    				case 'u':
    					robot.keyPress(KeyEvent.VK_U);
    					break;
    				case 'v':
    					robot.keyPress(KeyEvent.VK_V);
    					break;
    				case 'w':
    					robot.keyPress(KeyEvent.VK_W);
    					break;
    				case 'x':
    					robot.keyPress(KeyEvent.VK_X);
    					break;
    				case 'y':
    					robot.keyPress(KeyEvent.VK_Y);
    					break;
    				case 'z':
    					robot.keyPress(KeyEvent.VK_Z);
    					break;
    			}

    		}
    		robot.keyPress(KeyEvent.VK_ENTER);
    }
	 
	public Main()
	{
		
	}
	
	public boolean validBall(Obj b)
	{
		int x = b.x;
		int y = b.y;
		for(int i =0; i<balls.size();i++)
		{
			int range = 32;
			if(Math.abs(balls.get(i).x - x) < range && Math.abs(balls.get(i).y - y) < range )
			{
				//System.out.println("b.x (" + Math.abs(balls.get(i).x) +")");
				return false; //normally false
			}
		}
		return true;
	}
	
	public void processImage(BufferedImage img)
	{
		for(int i=0; i<img.getWidth()-res-1; i++)
		{
			for(int j=0; j<img.getHeight()-res-1; j++)
			{
				int r=0,g=0,b=0;
				if(i%res==0 && j%res==0)
				{
					int count = 0;
					for(int k = i; k<i+res; k++)
					{
						for(int m = j; m<j+res; m++)
						{
							count++;
							r+=(new Color(img.getRGB(k,m)).getRed());
							g+=(new Color(img.getRGB(k,m)).getGreen());
							b+=(new Color(img.getRGB(k,m)).getBlue());
						}
					}
					r = r / (res*res);
					g = g / (res*res);
					b = b / (res*res);
					
					int range=20;
					//if(Math.abs(149-r)<range && Math.abs(140-g)<range && Math.abs(240-b)<range)
					if(b>252 && g>50 && g<200 && r>50 && r<200)
					{
						if(Math.abs(360-i)<40 && Math.abs(300-j)<40)
						{
							Obj b1 = new Obj(i, j, 3); //this is main guy
							if(validBall(b1))
							{
								//System.out.println("blue coord = " + i +", " + j);
								balls.add(b1);
								mainBall = b1;
								
							}
						}
						else
						{
							Obj b1 = new Obj(i, j, 0);
							{
								balls.add(b1);
							}
						}
					}
					
					if(r>40&&r<50 && g>40&&g<50 && b>40&&b<50 && Math.abs(360-i)<250 && Math.abs(300-j)<250
							|| Math.abs(78-r)<range && Math.abs(220-g)<range && Math.abs(16-b)<range)
						//f(r==83 && g==83&& b==83)
						{
							Obj b1 = new Obj(i, j, 5);	//spikes
							if(validBall(b1))balls.add(b1);
							//System.out.println("gray = " + count);

							
						}
					
					
					
					
					range = 16;
					int test =110;
					if((Math.abs(test-r)<range && Math.abs(test-g)<range && Math.abs(test-b)<range
					)
					)
					//f(r==83 && g==83&& b==83)
					{
						Obj b1 = new Obj(i, j, 2);
						//if(mainBall == null)
						{
							
							if(validBall(b1) && (Math.abs(410-i)<36 && Math.abs(265-j)<10) == false)
							{
								
								balls.add(b1);
								
								
							}
						}
						/*
						else if((b1.type==2 && b1.x>mainBall.x && b1.x<mainBall.x-50 
								&& b1.y > mainBall.y && b1.y < mainBall.y-50) == false)
						{
							
							if(validBall(b1))
							{
								
									balls.add(b1);
								
								
							}
						}
						else 
						{
							System.out.println("Deleted = coords for b1 = [" + b1.x + ","+ b1.y+"] and mainBall is [" + mainBall.x + "," + mainBall.y + "]    " + count);

						}
						*/
						//

						
					}
					
					
					
					//if(Math.abs(231-r)<range && Math.abs(107-g)<range && Math.abs(113-b)<range)
					if(r>240 && g<100 && b<100)
					{
						if(Math.abs(360-i)<40 && Math.abs(300-j)<40)
						{
							Obj b1 = new Obj(i, j, 4); //this is main guy
							if(validBall(b1))
							{
								//System.out.println("red / = " + i +", " + j);
								balls.add(b1);
								mainBall = b1;

							}
						}
						else
						{
							Obj b1 = new Obj(i, j, 1);
							{
								balls.add(b1);
							}
						}

						
					}
					
					//System.out.println("color for [" + i/res + ","+j/res+"], colorInt = " + img.getRGB(i, j));
					//now find average, get rgb for each pixel r,g,b find average (divide by res*res) make that the array value
					//colors[i/res][j/res] = new Color(img.getRGB(i,j));
				
					//if(r!=0)
					{
						//System.out.println("r = " + r + " g = " + g + " b = " + b);
						colors[i/res][j/res] = new Color(255,255,255);
						colors[i/res][j/res] = new Color(r,g,b);
						//colors[i/res][j/res] = new Color(img.getRGB(i,j));
						//System.out.println(colors[i][j] + " here, but i = " + i + "  j = " + j);
					}
				

				}
				//
				
			}
		}
		
	}

}

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
import java.util.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas implements Runnable
{
	Random r;
	int x,y,i,bx,by,count;
	Thread t;
	Image img,boss;
	Image m[][]=new Image[4][3];
	public MainCanvas(){
		try{
			 r=new Random();
			x=100;
			y=120;
			i=1;
			bx=0;
			by=0;
			for(int a=0;a<4;a++){
			   for(int b=0;b<3;b++){
			   m[a][b]=Image.createImage("/sayo"+a+b+".png");

			   }
			}
			img=m[0][1];
			boss=Image.createImage("/boss.png");

			t=new Thread(this);
	
			t.start();
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}


	public void run(){
		  while(true){
			  try{
			   t.sleep(100);
			  }catch(InterruptedException e){};
			
			 
			  if(r.nextInt(3)==0){
			  if(bx>x){
		
		  
			   bx--;
			  }else if(bx<x){
		  
			   bx++;
			  }

			  if(by>y){
		 
			   by--;
			  }else if(by<y){
				//try{
			   //t.sleep(1000);
			  //}catch(InterruptedException e){};
		
		   
			  by++;
			    };
			  }
			
		  repaint();
		  }  
	}




	
	public void move(int c){
	 if(i==1){
	  img=m[c][0];
	  i++;
	 }else if(i==2){
	   img=m[c][2];
	   i--;
	 }
	}
	public void paint(Graphics g){
		g.setColor(250,200,180);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(img,x,y,0);
		g.drawImage(boss,bx,by,0);
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		if(action==LEFT){
			move(0);
		x-=3;
		}
		if(action==UP){
			move(1);
		y-=3;
		}
		if(action==RIGHT){
			move(2);
		x+=3;
		}
		if(action==DOWN){
			move(3);
		y+=3;
		}
		repaint();
	}

}

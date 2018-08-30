
import java.io.*;
import java.net.*;	
import java.util.Scanner;
public class clientfullduplex
{
	public static void main(String args[])
	{
		try
	    {
			Socket s = new Socket("192.168.43.21",1110);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream dis = new DataInputStream(s.getInputStream());
			WxThread wxt = new WxThread(dos);
			wxt.start();
			while (true)
			{
				System.out.println(dis.readUTF());
			}
		 }
		 catch(Exception e) 
		 {
			System.out.println(e);
		 } 
	 }
}

class WxThread extends Thread
{
	DataOutputStream dos;
	Scanner sc=new Scanner(System.in);
	String str="";
	WxThread(DataOutputStream dos)
	{
		this.dos = dos;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				str=sc.nextLine();
				this.dos.writeUTF(str);
				this.dos.flush();
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}
}
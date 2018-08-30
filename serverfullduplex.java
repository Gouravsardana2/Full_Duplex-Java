import java.io.*;
import java.net.*;	
import java.util.Scanner;
public class serverfullduplex
{
	public static void main(String args[])
	{
		try
	    {
			Scanner sc=new Scanner(System.in);
			String str="";
			ServerSocket ss = new ServerSocket(1020);
			Socket s = ss.accept();
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream dis = new DataInputStream(s.getInputStream());
			RxThread rxt = new RxThread(dis);
			rxt.start();
			while (true)
			{
				str=sc.nextLine();
				dos.writeUTF(str);
				dos.flush();
			}
		 }
		 catch(Exception e) 
		 {
			System.out.println(e);
		 } 
	 }
}

class RxThread extends Thread
{
	DataInputStream din;
	RxThread(DataInputStream dis)
	{
		din = dis;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println(din.readUTF());
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}
}
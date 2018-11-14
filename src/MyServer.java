import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class MyServer {
public static void main(String[] args){
	
	String saveUsername=new String("admin");
	String savePassword=new String("admin");
	try{
		ServerSocket server=new ServerSocket(6666);
		while(true){
			System.out.println("服务器回归原点");//调试信息
			Socket s1=server.accept();
			System.out.println("接到客户端socket请求");//调试信息
			InputStream is1=s1.getInputStream();
			DataInputStream dis1=new DataInputStream(is1);
			String str=dis1.readUTF();
			System.out.println(str);//调试信息
			//如果发来注册的信息就做如下操作
			if(str.equals("Register")){
				s1=server.accept();
				is1=s1.getInputStream();				
				DataInputStream dis2=new DataInputStream(is1);
				String []getStr=dis2.readUTF().split(" ");//用空格把账号和密码分开存储
				saveUsername=getStr[0];//取出账号
				savePassword=getStr[1];//取出密码
				System.out.println(saveUsername+" "+savePassword);//调试信息
				dis2.close();
			}
			//如果发来登录账号和密码做如下操作
			else {
				System.out.println("进入登陆判断");//调试信息
				String []getStr=str.split(" ");//用空格把账号和密码分开存储
				//如果账号和密码都对的话则返回允许登录命令
				if(saveUsername.equals(getStr[0])&&savePassword.equals(getStr[1])){
					OutputStream os=s1.getOutputStream();
					DataOutputStream dos=new DataOutputStream(os);
					dos.writeUTF("YES");
					dos.close();
					System.out.println("用户密码正确");//调试信息
				}
				//如果账号和密码不吻合则返回禁止登录命令
				else{
					OutputStream os=s1.getOutputStream();
					DataOutputStream dos=new DataOutputStream(os);
					dos.writeUTF("NO");
					dos.close();
					System.out.println("用户密码错误");//调试信息
				}
				System.out.println(getStr[0]+" "+getStr[1]);//调试信息
			}
			dis1.close();
			s1.close();
			
		}
	}catch(IOException e){
		e.printStackTrace();
	}
	
}
}

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
			System.out.println("�������ع�ԭ��");//������Ϣ
			Socket s1=server.accept();
			System.out.println("�ӵ��ͻ���socket����");//������Ϣ
			InputStream is1=s1.getInputStream();
			DataInputStream dis1=new DataInputStream(is1);
			String str=dis1.readUTF();
			System.out.println(str);//������Ϣ
			//�������ע�����Ϣ�������²���
			if(str.equals("Register")){
				s1=server.accept();
				is1=s1.getInputStream();				
				DataInputStream dis2=new DataInputStream(is1);
				String []getStr=dis2.readUTF().split(" ");//�ÿո���˺ź�����ֿ��洢
				saveUsername=getStr[0];//ȡ���˺�
				savePassword=getStr[1];//ȡ������
				System.out.println(saveUsername+" "+savePassword);//������Ϣ
				dis2.close();
			}
			//���������¼�˺ź����������²���
			else {
				System.out.println("�����½�ж�");//������Ϣ
				String []getStr=str.split(" ");//�ÿո���˺ź�����ֿ��洢
				//����˺ź����붼�ԵĻ��򷵻������¼����
				if(saveUsername.equals(getStr[0])&&savePassword.equals(getStr[1])){
					OutputStream os=s1.getOutputStream();
					DataOutputStream dos=new DataOutputStream(os);
					dos.writeUTF("YES");
					dos.close();
					System.out.println("�û�������ȷ");//������Ϣ
				}
				//����˺ź����벻�Ǻ��򷵻ؽ�ֹ��¼����
				else{
					OutputStream os=s1.getOutputStream();
					DataOutputStream dos=new DataOutputStream(os);
					dos.writeUTF("NO");
					dos.close();
					System.out.println("�û��������");//������Ϣ
				}
				System.out.println(getStr[0]+" "+getStr[1]);//������Ϣ
			}
			dis1.close();
			s1.close();
			
		}
	}catch(IOException e){
		e.printStackTrace();
	}
	
}
}

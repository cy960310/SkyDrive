package SkyDriveClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
*ʵ���ļ�����
*@author lichaung
*@date  2015/8/29
*
*/

public class DownLoad {
	
	private FileOutputStream fileOutputStream = null;
	private InputStream inputStream = null;
	private Communication con = null;
	private File path = null;
	private File file = null;
	private Socket socket = null;
	private byte[] data = new byte[1024];
	
	public DownLoad(String path, String file, Socket socket) {
		this.path = new File(path);
		this.file = new File(path+file);
		this.socket = socket;
		try {
			fileOutputStream = new FileOutputStream(this.file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			close();
		}
		
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}
	
	public void start() {
		con.printLocal("�ļ���ʼ���أ�" + path.getName() + file.getName() 
				+ " �� " + socket.getInetAddress() +":" + socket.getPort());
		try {
			while(inputStream.read(data) != -1) {
				fileOutputStream.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
		con.printLocal("�ļ�������ϡ�");
		close();
	}
	
	private void close() {
		try {
			if(fileOutputStream != null)
				fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(inputStream != null)
				inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

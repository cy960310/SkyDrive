package SkyDriveClient;


/**
*ʵ�ֳ�ʼ�����ܣ���ȡĬ�ϵ�����
*@author lichaung
*@date  2015/8/29
*
*/

public class SkyDriveClient {
	
    private static final String[] options = {"  0    �г�ѡ��","  1    �������̷�������ַ",
                "  2    �������̷������˿�","  3    �ļ��ϴ�","  4    �ļ�����","  5    �˳�"};
    private static final String[] fileOpts = {"0 �����ϲ�","1 �����ļ�·��","2 ���ô洢·��","3 ��ʼ����"};
    private static final String[] tip1 = {"��ѡ������ (0-5):"};
    private static final String[] tip2 = {"��ѡ������ (0-3):"};
	private static  Communication con = Communication.getInstance();
	private static String command = "";
	
	public static void main(String[] args) {
		showOpts(options);
		while(true) {
			showOpts(tip1);
			command = con.readLocal();
			if(command.matches("5")) {
				con.printLocal("�������˳�");
				break;
			}
			switch (command) {
				case "0":
					showOpts(options);
					break;
				case "1":
					con.printLocal("���������̷�������ַ: ");
					command = con.readLocal();
					if(!Configure.setServerAddress(command)) {
						con.printLocal("��Ч��IP��ַ");
					}
					break;
				case "2":
					con.printLocal("���������̷������˿ں�(0-65545):");
					command = con.readLocal();
					if(!Configure.setServerPort(Integer.parseInt(command))) {
						con.printLocal("��Ч�Ķ˿ں�");
					}
					break;
				case "3":
					showOpts(fileOpts);
					fileHandle(true);
					break;
				case "4":
					showOpts(fileOpts);
					fileHandle(false);
					break;
				case "5":
					
					break;
	
				default:
					break;
				}
		}
	}
	
	private static void showOpts(String[] opts) {
		for(int i = 0; i < opts.length; i++) {
			con.printLocal(opts[i]);
		}
	}
	
	public static void fileHandle(boolean flag) {
		while(true) {
            showOpts(tip2);
			command = con.readLocal();
			if(command.matches("0")) {
				showOpts(options);
				break;
			}
			switch (command) {
				case "0":
					break;
				case "1":
					if(flag == true){
						con.printLocal("�������ϴ��ļ�·��:");
						command = con.readLocal();
						if(!Configure.setLocalFilePath(command)) {
							con.printLocal("�ļ�·��������");
						}
					}else {
						con.printLocal("�����������ļ�·��:");
						command = con.readLocal();
						if(!Configure.setRemoteFilePath(command)) {
							con.printLocal("�����ļ�·��������");
						}
					}
					break;
				case "2":
					if(flag == true){
						con.printLocal("�����������ļ�·��:");
						command = con.readLocal();
						if(!Configure.setRemoteFilePath(command)) {
							con.printLocal("�����ļ�·��������");
						}
					}else {
						con.printLocal("������洢�ļ�·��:");
						command = con.readLocal();
						if(!Configure.setLocalFilePath(command)) {
							con.printLocal("�ļ�·��������");
						}
					}
					break;
				case "3":
					if(flag == true) {
						con.upLoadFile(Configure.getlocalFilePath());
					}else {
						con.downLoadFile(Configure.getStorePath(), Configure.getRemoteFilePath());
					}
					break;
				default:
					break;
			}
		}
		
	}

}


import javax.sound.midi.MidiEvent;
import javax.swing.*;
import javax.swing.event.*;
import javax.xml.transform.Source;

import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream.GetField;

import java.util.*;
import java.io.*;

class data_e{ ///////////////////////////////////////////È¸¿øÁ¤º¸///////////////////
	private String ID;
	private String PW;
	private String Name;
	private String E_mail;
	private int Private_ID_1, Private_ID_2;
	private int age,sex;
	private Vector<String> Message = new Vector<String>();
	private Vector<String> Msg_name = new Vector<String>();
	
	Calendar cale = Calendar.getInstance();
	
	public void Setdata_e(String ID, String PW, String Name, int Private_ID_1, 
		int Private_ID_2,String E_mail){
		this.ID = ID;
		this.PW = PW;
		this.Name = Name;
		this.Private_ID_1 = Private_ID_1;
		this.Private_ID_2 = Private_ID_2;
		this.E_mail = E_mail;
		age = cale.get(Calendar.YEAR) - (1900+Private_ID_1/10000);
		sex = Private_ID_2 / 1000000;
	}
	public String getID(){return ID;}
	public String getPW(){return PW;}
	public String getName(){return Name;}
	public int getPrivate_ID_1(){return Private_ID_1;}
	public int getPrivate_ID_2(){return Private_ID_2;}
	public int getAge(){return age;}
	public String getEmail(){return E_mail;}
	public String getMessage(int index){return Message.get(index);}
	public String getMsg_name(int index){return Msg_name.get(index);}
	public String getSex()
	{
		String man = "³²ÀÚ", wom = "¿©ÀÚ";
		return sex==1?man:wom;
	}
	public void setMesage(String msg,String name){
		Message.add(msg);
		Msg_name.add(name);
	}
	public void delMessage(int index){
		Message.remove(index);
		Msg_name.remove(index);
	}
	public int getMessageSize(){
		return Message.size();
	}
}
class data_v{ ////////////////////////////////È¸¿ø Á¤º¸ º¤ÅÍ ½ºÅ¸Æ½////////////////////////
	static Vector<data_e> vector_data = new Vector<data_e>();
	static int Vector_index;
	static int Find_index;
	static int Msg_cnt;
}
//----------------------------Main Frame---------------------------------------------------//
class Main_Frame extends JFrame implements ActionListener{
	JLabel Id_lb, Pw_lb;
	JTextField Id_tf, Pw_tf;
	JButton Log_bt, New_bt, Del_bt, Exit_bt;
	data_v data_vec = new data_v();
	data_e scan_data = new data_e();

	Main_Pan a = new Main_Pan();
	
	public Main_Frame() {
		setTitle("¡ÙÆ÷½ºÆ®ÀÕ ·Œ¡Ù");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(a);
		a.setSize(295,240);
		a.setLocation(0,0);
		a.setLayout(null);
		a.setBackground(Color.PINK);
		a.add(Id_lb = new JLabel(("ID    : ")));
		Id_lb.setForeground(Color.BLUE);
		Id_lb.setSize(50,25);
		Id_lb.setLocation(55, 55);
		a.add(Id_tf = new JTextField());
		Id_tf.setSize(150,25);
		Id_tf.setLocation(100, 55);
		a.add(Pw_lb = new JLabel("PW : "));
		Pw_lb.setForeground(Color.BLUE);
		Pw_lb.setSize(50,25);
		Pw_lb.setLocation(55, 85);
		a.add(Pw_tf = new JTextField(20));
		Pw_tf.setSize(150,25);
		Pw_tf.setLocation(100, 85);
		
		a.add(Log_bt = new JButton("·Î±×ÀÎ"));
		Log_bt.setSize(80, 70);
		Log_bt.setLocation(55, 120);
		a.add(New_bt = new JButton("È¸¿ø°¡ÀÔ"));
		New_bt.setSize(100, 30);
		New_bt.setLocation(150, 120);
		a.add(Del_bt = new JButton("Å»Åð"));
		Del_bt.setSize(100, 30);
		Del_bt.setLocation(150, 160);
		a.add(Exit_bt = new JButton("Á¾·á"));
		Exit_bt.setSize(70, 30);
		Exit_bt.setLocation(210, 200);
		Exit_bt.setBackground(Color.red);
		
		setSize(300,270);
		setResizable(false);
		setVisible(true);
		
		Log_bt.addActionListener(this);
		New_bt.addActionListener(this);
		Del_bt.addActionListener(this);
		Exit_bt.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		int Id_err_cnt;
		
		if(a == "·Î±×ÀÎ"){ //--------------------------------------------·Î±×ÀÎFrame ¶ç¿ì±â
			Iterator<data_e> it = data_vec.vector_data.iterator();
			data_vec.Vector_index = 0;
			Id_err_cnt = 0;
			while(it.hasNext()){
				data_e mem = it.next();
				if(mem.getID().equals(Id_tf.getText())){
					if(mem.getPW().equals(Pw_tf.getText())){
						Log_member Log_memf = new Log_member();
						setVisible(false);
						break;
					}
					else{
						JOptionPane.showMessageDialog(null, "ºñ¹Ð¹øÈ£ ºÒÀÏÄ¡!!", 
								"¿À·ù", JOptionPane.ERROR_MESSAGE);
						Pw_tf.setText("");
						break;
					}
				}
				data_vec.Vector_index++;
				Id_err_cnt++;
			}
			if(data_vec.vector_data.size() == Id_err_cnt)
			{
				Id_tf.setText("");
				JOptionPane.showMessageDialog(null, "ÇØ´ç ID°¡ ¾ø½À´Ï´Ù!!", "¿À·ù", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(a == "È¸¿ø°¡ÀÔ"){ //------------------------------------È¸¿ø°¡ÀÔFrame ¶ç¿ì±â
			New_Member New_memf = new New_Member();
			setVisible(false);
		}
		else if(a == "Å»Åð"){//---------------------------------------Å»ÅðFrame ¶ç¿ì±â
			Del_Member Del_memf = new Del_Member();
			setVisible(false);
		}
		else if(a == "Á¾·á"){
			System.exit(0);
		}
	}
}//--------------------------------------¸ÞÀÎ ÆÐ³Î-------------------------------------
class Main_Pan extends JPanel{
	ImageIcon icon = new ImageIcon("images/Æ÷½ºÆ®ÀÕ.jpg");
	Image img = icon.getImage();
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
//--------------------------------------È¸¿ø°¡ÀÔ Frame---------------------------------------
class New_Member extends JFrame implements ActionListener{
	data_e data_ele = new data_e();
	data_v data_vec = new data_v();
	boolean con;
	
	JLabel New_infor,Id_lb,Pw_lb,Name_lb,Pr_Id_lb,E_lb;
	JTextField Id_tf,Pw_tf,Name_tf,Pr_1_tf,Pr_2_tf,E_tf;
	JButton Comp_bt,Pr_mem_bt,Cancle_bt;
	New_Pan a = new New_Pan();

	public New_Member(){
		setTitle("È¸¿ø°¡ÀÔÃ¢");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		add(a);
		a.setSize(374, 301);
		a.setLocation(0, 0);
		a.setLayout(null);
		a.add(New_infor = new JLabel("È¸¿øÁ¤º¸¸¦ ºóÄ­¾øÀÌ Á¤È®È÷ ÀÔ·ÂÇØ ÁÖ¼¼¿ä!"));
		New_infor.setSize(300, 25);
		New_infor.setLocation(50,35);
		New_infor.setForeground(Color.CYAN);
		
		a.add(Id_lb = new JLabel("ID        :"));
		Id_lb.setSize(50,25);
		Id_lb.setLocation(60, 80);
		a.add(Id_tf = new JTextField(20));
		Id_tf.setSize(150,25);
		Id_tf.setLocation(150, 80);
		a.add(Pw_lb = new JLabel("PW     :"));
		Pw_lb.setSize(50,25);
		Pw_lb.setLocation(60, 110);
		a.add(Pw_tf = new JTextField(20));
		Pw_tf.setSize(150,25);
		Pw_tf.setLocation(150, 110);
		a.add(Name_lb = new JLabel("ÀÌ¸§   : "));
		Name_lb.setSize(50,25);
		Name_lb.setLocation(60, 140);
		a.add(Name_tf = new JTextField(6));
		Name_tf.setSize(80,25);
		Name_tf.setLocation(220, 140);
		a.add(Pr_Id_lb = new JLabel("ÁÖ¹Îµî·Ï¹øÈ£   : "));
		Pr_Id_lb.setSize(150,25);
		Pr_Id_lb.setLocation(50, 170);
		a.add(Pr_1_tf = new JTextField(6));
		Pr_1_tf.setSize(50,25);
		Pr_1_tf.setLocation(180, 170);
		a.add(Pr_2_tf = new JTextField(7));
		Pr_2_tf.setSize(60,25);
		Pr_2_tf.setLocation(240, 170);
		a.add(E_lb = new JLabel("Email   : "));
		E_lb.setSize(50,25);
		E_lb.setLocation(60, 200);
		a.add(E_tf = new JTextField(10));
		E_tf.setSize(150,25);
		E_tf.setLocation(150, 200);
		
		a.add(Comp_bt = new JButton("ÀÔ·Â¿Ï·á"));
		Comp_bt.setSize(100,35);
		Comp_bt.setLocation(30, 250);
		a.add(Pr_mem_bt = new JButton("È¸¿øÃâ·Â"));
		Pr_mem_bt.setSize(100,35);
		Pr_mem_bt.setLocation(140, 250);
		a.add(Cancle_bt = new JButton("Ãë¼Ò"));
		Cancle_bt.setSize(100,35);
		Cancle_bt.setLocation(250, 250);
		
		Comp_bt.addActionListener(this);
		Pr_mem_bt.addActionListener(this);
		Cancle_bt.addActionListener(this);
		
		setSize(380,330);
		setResizable(false);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		
		if(a == "ÀÔ·Â¿Ï·á"){
			con = Id_tf.getText().equals("")||Pw_tf.getText().equals("")||
					Name_tf.getText().equals("")||Pr_1_tf.getText().equals("")||
					Pr_2_tf.getText().equals("")||E_tf.getText().equals("");
			if(con)
			{
				JOptionPane.showMessageDialog(null, "È¸¿øÁ¤º¸¸¦ Á¤È®È÷ ÀÔ·ÂÇØ ÁÖ¼¼¿ä!!", 
						"¿À·ù", JOptionPane.ERROR_MESSAGE);
			}
			else{
				
				
				
			data_ele.Setdata_e(Id_tf.getText(),Pw_tf.getText(),Name_tf.getText(),
					Integer.parseInt(Pr_1_tf.getText()),Integer.parseInt(Pr_2_tf.getText()),
					E_tf.getText());
			data_vec.vector_data.add(data_ele);
			
			try{
				BufferedWriter fw = new BufferedWriter(new FileWriter("data/ÀÚ·á.txt"));
				for(int i=0;i<data_v.vector_data.size();i++){
					data_e File_Out_ele = new data_e();
					File_Out_ele = data_v.vector_data.get(i);
					fw.write(File_Out_ele.getID());
					fw.newLine();
					fw.write(File_Out_ele.getPW());
					fw.newLine();
					fw.write(File_Out_ele.getName());
					fw.newLine();
					fw.write(Integer.toString(File_Out_ele.getPrivate_ID_1()));
					fw.newLine();
					fw.write(Integer.toString(File_Out_ele.getPrivate_ID_2()));
					fw.newLine();
					fw.write(File_Out_ele.getEmail());
					fw.newLine();
				}
				fw.close();
				
			}catch(FileNotFoundException x){
				x.printStackTrace();
			}catch(IOException x){
				x.printStackTrace();
			}
			try{
				BufferedWriter fw = new BufferedWriter(new FileWriter("data/ÂÊÁö.txt"));
				for(int i=0;i<data_v.vector_data.size();i++){
					data_e File_Out_ele = new data_e();
					File_Out_ele = data_v.vector_data.get(i);
					fw.write(Integer.toString(File_Out_ele.getMessageSize()));
					fw.newLine();
					for(int j=0;j<File_Out_ele.getMessageSize();j++){
						fw.write(File_Out_ele.getMsg_name(j));
						fw.newLine();
						fw.write(File_Out_ele.getMessage(j));
						fw.newLine();
						fw.write("-END-");
						fw.newLine();
					}
				}
				fw.close();
				
			}catch(FileNotFoundException x){
				x.printStackTrace();
			}catch(IOException x){
				x.printStackTrace();
			}
			Main_Frame mf = new Main_Frame();
			setVisible(false);
			}
		}
		else if(a == "È¸¿øÃâ·Â"){
			Iterator<data_e> it = data_vec.vector_data.iterator();
			while(it.hasNext()){
				data_e aa = it.next();
				System.out.println("ID : "+aa.getID());
				System.out.println("PW : "+aa.getPW());
				System.out.println("ÀÌ¸§ : "+aa.getName());
			}
			Print_Member Pm = new Print_Member();
			setVisible(false);
		}
		else if(a == "Ãë¼Ò"){
			Main_Frame mf = new Main_Frame();
			setVisible(false);
		}
	}
}//----------------------------È¸¿ø°¡ÀÔ ÆÐ³Î-------------------------------------
class New_Pan extends JPanel{
	ImageIcon icon = new ImageIcon("images/Æ÷½ºÆ®ÀÕ5.jpg");
	Image img = icon.getImage();
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}//----------------------------È¸¿øÃâ·Â ÇÁ·¹ÀÓ------------------------------------
class Print_Member extends JFrame implements ActionListener{
	data_e data_ele = new data_e();
	data_v data_vec = new data_v();
	
	JLabel User_name_lb;
	JTextArea User_name_ta;
	JScrollPane User_name_sc;
	JButton Ok_bt;
	String names = "";
	
	public Print_Member() {
		setTitle("°¡ÀÔÇÑ È¸¿ø");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Iterator<data_e> it = data_vec.vector_data.iterator();
		while(it.hasNext()){
			data_e aa = it.next();
			names += (aa.getName() + "\n");
		}
		
		add(User_name_lb = new JLabel("**°¡ÀÔÇÑ È¸¿ø ¸í´Ü**"));
		User_name_lb.setSize(150, 25);
		User_name_lb.setLocation(83, 15);
		add(User_name_sc = new JScrollPane(User_name_ta = new JTextArea()));
		User_name_sc.setSize(250, 250);
		User_name_sc.setLocation(20, 50);
		User_name_ta.setText(names);
		add(Ok_bt = new JButton("È®ÀÎ"));
		Ok_bt.setSize(150, 30);
		Ok_bt.setLocation(70, 310);
		
		Ok_bt.addActionListener(this);
		
		setSize(300,380);
		setResizable(false);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		
		if(a == "È®ÀÎ"){
			New_Member mf = new New_Member();
			setVisible(false);
		}
	}
}
//---------------------------------------Å»ÅðFrame---------------------------------------
class Del_Member extends JFrame implements ActionListener{
	data_e data_ele = new data_e();
	data_v data_vec = new data_v();
	String ID,PW;
	int Vector_index;
	
	JLabel Id_lb, Pw_lb;
	JTextField Id_tf,Pw_tf;
	JButton Del_com_bt,Del_ca_bt;
	
	public Del_Member() {
		setTitle("È¸¿øÅ»ÅðÃ¢");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(Id_lb = new JLabel("Å»ÅðÇÏ½Ç ID : "));
		Id_lb.setSize(100, 25);
		Id_lb.setLocation(25, 30);
		add(Id_tf = new JTextField(20));
		Id_tf.setSize(150, 25);
		Id_tf.setLocation(110, 30);
		add(Pw_lb = new JLabel("PW : "));
		Pw_lb.setSize(50, 25);
		Pw_lb.setLocation(70, 65);
		add(Pw_tf = new JTextField(20));
		Pw_tf.setSize(150, 25);
		Pw_tf.setLocation(110, 65);
		add(Del_com_bt = new JButton("»èÁ¦"));
		Del_com_bt.setSize(120, 30);
		Del_com_bt.setLocation(30, 120);
		add(Del_ca_bt = new JButton("Ãë¼Ò"));
		Del_ca_bt.setSize(80, 30);
		Del_ca_bt.setLocation(180, 120);
		
		Del_com_bt.addActionListener(this);
		Del_ca_bt.addActionListener(this);
		
		setSize(300,200);
		setResizable(false);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		
		if(a == "»èÁ¦"){
			Iterator<data_e> it = data_vec.vector_data.iterator();
			Vector_index = 0;
			while(it.hasNext()){
				data_e mem = it.next();
				if(mem.getID().equals(Id_tf.getText()))
				{
					if(mem.getPW().equals(Pw_tf.getText())){
						data_vec.vector_data.remove(Vector_index);
						System.out.print("»èÁ¦¿Ï·á");
						try{
							BufferedWriter fw = new BufferedWriter(new FileWriter("data/ÀÚ·á.txt"));
							for(int i=0;i<data_v.vector_data.size();i++){
								data_e File_Out_ele = new data_e();
								File_Out_ele = data_v.vector_data.get(i);
								fw.write(File_Out_ele.getID());
								fw.newLine();
								fw.write(File_Out_ele.getPW());
								fw.newLine();
								fw.write(File_Out_ele.getName());
								fw.newLine();
								fw.write(Integer.toString(File_Out_ele.getPrivate_ID_1()));
								fw.newLine();
								fw.write(Integer.toString(File_Out_ele.getPrivate_ID_2()));
								fw.newLine();
								fw.write(File_Out_ele.getEmail());
								fw.newLine();
							}
							fw.close();
							
						}catch(FileNotFoundException x){
							x.printStackTrace();
						}catch(IOException x){
							x.printStackTrace();
						}
						try{
							BufferedWriter fw = new BufferedWriter(new FileWriter("data/ÂÊÁö.txt"));
							for(int i=0;i<data_v.vector_data.size();i++){
								data_e File_Out_ele = new data_e();
								File_Out_ele = data_v.vector_data.get(i);
								fw.write(Integer.toString(File_Out_ele.getMessageSize()));
								fw.newLine();
								for(int j=0;j<File_Out_ele.getMessageSize();j++){
									fw.write(File_Out_ele.getMsg_name(j));
									fw.newLine();
									fw.write(File_Out_ele.getMessage(j));
									fw.newLine();
									fw.write("-END-");
									fw.newLine();
								}
							}
							fw.close();
							
						}catch(FileNotFoundException x){
							x.printStackTrace();
						}catch(IOException x){
							x.printStackTrace();
						}
						Main_Frame mf = new Main_Frame();
						setVisible(false);
						break;
					}
					else{
						Id_tf.setText("");
						JOptionPane.showMessageDialog(null, "ÇØ´ç ID°¡ ¾ø½À´Ï´Ù!!", 
								"¿À·ù", JOptionPane.ERROR_MESSAGE);
					}
				}
				Vector_index++;
			}
		}
		else if(a == "Ãë¼Ò"){
			Main_Frame mf = new Main_Frame();
			setVisible(false);
		}
	}
}
//==================================·Î±×ÀÎ Frame===========================================
class Log_member extends JFrame implements ActionListener{
	data_v data_vec = new data_v();
	data_e e = data_vec.vector_data.get(data_vec.Vector_index);
	String name = e.getName();
	
	JLabel name_info;
	JButton Log_out, Find_f, Change_info,Read_msg_bt;
	Log_Pan a = new Log_Pan();
	
	public Log_member(){
		setTitle("·Î±×ÀÎÃ¢");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		add(a);
		a.setSize(335,310);
		a.setLocation(5, 5);
		a.setLayout(null);
		
		a.add(name_info = new JLabel(name + " ´ÔÀÇ ·Î±×ÀÎ È­¸é"));
		name_info.setSize(165,30);
		name_info.setLocation(95, 25);
		a.add(Change_info = new JButton("È¸¿øÁ¤º¸"));
		Change_info.setSize(100, 25);
		Change_info.setLocation(130, 85);
		a.add(Find_f = new JButton("Ä£±¸°Ë»ö"));
		Find_f.setSize(100, 25);
		Find_f.setLocation(130, 143);
		a.add(Read_msg_bt = new JButton("ÂÊÁöÀÐ±â"));
		Read_msg_bt.setSize(100, 25);
		Read_msg_bt.setLocation(130, 202);
		a.add(Log_out = new JButton("·Î±×¾Æ¿ô"));
		Log_out.setSize(100, 25);
		Log_out.setLocation(130, 261);
		
		setSize(350,350);
		setResizable(false);
		setVisible(true);
		
		Change_info.addActionListener(this);
		Find_f.addActionListener(this);
		Log_out.addActionListener(this);
		Read_msg_bt.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		
		if(a == "È¸¿øÁ¤º¸"){
			setVisible(false);
			Print_Info Print_If = new Print_Info();
		}
		else if(a == "Ä£±¸°Ë»ö")
		{
			setVisible(false);
			FindFriends_Frame Find_Fr = new FindFriends_Frame();
		}
		else if(a == "·Î±×¾Æ¿ô"){
			setVisible(false);
			Main_Frame mainf = new Main_Frame();
		}
		else if(a == "ÂÊÁöÀÐ±â"){
			setVisible(false);
			data_vec.Msg_cnt = 0;
			Read_msg read = new Read_msg();
		}
	}
}//-----------------------------------·Î±×ÀÎÈ­¸é ÆÐ³Î---------------------------------------
class Log_Pan extends JPanel{
	ImageIcon icon = new ImageIcon("images/Æ÷½ºÆ®ÀÕ3_·Î±×ÀÎ.jpg");
	Image img = icon.getImage();
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
//------------------------------------Á¤º¸Ãâ·ÂFrame-----------------------------------------
class Print_Info extends JFrame implements ActionListener{
	data_v data_vec = new data_v();
	data_e e = data_vec.vector_data.get(data_vec.Vector_index);
	
	JLabel Print_lb,Id_lb,Name_lb,Pr_1_lb,Pr_2_lb,E_lb,Age_lb,Sex_lb;
	JTextField Id_tf,Name_tf,Pr_1_tf,Pr_2_tf,E_tf,Age_tf,Sex_tf;
	JButton Back_bt;
	
	public Print_Info() {
		setTitle("È¸¿øÁ¤º¸");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		add(Print_lb = new JLabel("È¸¿øÁ¤º¸"));
		Print_lb.setSize(300, 20);
		Print_lb.setLocation(120, 20);
		add(new JTextField());  //JtextField¸¦ Àá±×±â À§ÇØ¼­ ¾²ÀÓ
		
		add(Id_lb = new JLabel("ID       : "));
		Id_lb.setSize(50,20);
		Id_lb.setLocation(40,60);
		add(Id_tf = new JTextField(e.getID()));
		Id_tf.setSize(130,20);
		Id_tf.setLocation(100,60);
		Id_tf.setRequestFocusEnabled(false);
		add(Name_lb = new JLabel("ÀÌ¸§  : "));
		Name_lb.setSize(50,20);
		Name_lb.setLocation(40,90);
		add(Name_tf = new JTextField(e.getName()));
		Name_tf.setSize(130,20);
		Name_tf.setLocation(100,90);
		Name_tf.setRequestFocusEnabled(false);
		add(Age_lb = new JLabel("³ªÀÌ  : "));
		Age_lb.setSize(50,20);
		Age_lb.setLocation(40,120);
		add(Age_tf = new JTextField(""+e.getAge()));
		Age_tf.setSize(30,20);
		Age_tf.setLocation(100,120);
		Age_tf.setRequestFocusEnabled(false);
		add(Sex_lb = new JLabel("¼ºº°  : "));
		Sex_lb.setSize(50,20);
		Sex_lb.setLocation(150,120);
		add(Sex_tf = new JTextField(e.getSex()));
		Sex_tf.setSize(30,20);
		Sex_tf.setLocation(200,120);
		Sex_tf.setRequestFocusEnabled(false);
		add(E_lb = new JLabel("Email: "));
		E_lb.setSize(80,20);
		E_lb.setLocation(40,150);
		add(E_tf = new JTextField(e.getEmail()));
		E_tf.setSize(130,20);
		E_tf.setLocation(100,150);
		E_tf.setRequestFocusEnabled(false);
		add(Back_bt = new JButton("µ¹¾Æ°¡±â"));
		Back_bt.setSize(100,40);
		Back_bt.setLocation(90,200);
		
		setSize(300, 300);
		setResizable(false);
		setVisible(true);
		
		Back_bt.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		
		if(a == "µ¹¾Æ°¡±â")
		{
			setVisible(false);
			Log_member Log_mf = new Log_member();
		}
	}
}
//------------------------------------Ä£±¸Ã£±âFrame---------------------------------------
class FindFriends_Frame extends JFrame implements ActionListener{
	data_v data_vec = new data_v();
	int find_flag, Fr_cnt;
	
	JLabel Fr_Id_lb,Fr_Name_lb;
	JTextField Fr_Id_tf,Fr_Name_tf;
	JButton SendMemo_bt,Back_bt,Find_bt;
	
	public FindFriends_Frame() {
		setTitle("Ä£±¸°Ë»ö");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		add(Fr_Name_lb = new JLabel("°Ë»öÇÏ½Ç Ä£±¸ÀÌ¸§      : "));
		Fr_Name_lb.setSize(150, 20);
		Fr_Name_lb.setLocation(20,30);
		add(Fr_Name_tf = new JTextField());
		Fr_Name_tf.setSize(80, 20);
		Fr_Name_tf.setLocation(170,30);
		add(Fr_Id_lb = new JLabel("Ä£±¸ ID     : "));
		Fr_Id_lb.setSize(100, 20);
		Fr_Id_lb.setLocation(40,80);
		add(Fr_Id_tf = new JTextField());
		Fr_Id_tf.setSize(120, 20);
		Fr_Id_tf.setLocation(130,80);
		Fr_Id_tf.setRequestFocusEnabled(false);
		
		add(Find_bt = new JButton("Ä£±¸°Ë»ö"));
		Find_bt.setSize(90, 25);
		Find_bt.setLocation(90,130);
		add(SendMemo_bt = new JButton("ÂÊÁöº¸³»±â"));
		SendMemo_bt.setSize(100, 25);
		SendMemo_bt.setLocation(20,180);
		add(Back_bt = new JButton("µ¹¾Æ°¡±â"));
		Back_bt.setSize(100, 25);
		Back_bt.setLocation(150,180);
		
		setSize(300,280);
		setResizable(false);
		setVisible(true);
		
		Find_bt.addActionListener(this);
		SendMemo_bt.addActionListener(this);
		Back_bt.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		
		find_flag = 0;
		if(a == "Ä£±¸°Ë»ö")
		{
			Iterator<data_e> it = data_vec.vector_data.iterator();
			Fr_cnt = 0;
			while(it.hasNext()){
				data_e j = it.next();
				if(j.getName().equals(Fr_Name_tf.getText())){
					Fr_Id_tf.setText(j.getID());
					find_flag = 1;
					break;
				}
				Fr_cnt++;
			}
			if(find_flag == 0){
				Fr_Name_tf.setText("");
				JOptionPane.showMessageDialog(null, "ÀÔ·ÂÇÏ½Å ID¿Í ÀÏÄ¡ÇÏ´Â Ä£±¸°¡ ¾ø½À´Ï´Ù!!", 
						"¿À·ù", JOptionPane.ERROR_MESSAGE);
			}
			data_vec.Find_index = Fr_cnt;
		}
		else if(a == "ÂÊÁöº¸³»±â"){
			if(Fr_Id_tf.getText().equals("")){
				Fr_Name_tf.setText(null);
				JOptionPane.showMessageDialog(null, "ÂÊÁö¸¦ º¸³»½Ç Ä£±¸¸¦ °Ë»öÇØ ÁÖ¼¼¿ä!!", 
						"¿À·ù", JOptionPane.ERROR_MESSAGE);
			}
			else{
				Send_msg send_f = new Send_msg();
				setVisible(false);
			}
		}
		else if(a == "µ¹¾Æ°¡±â"){
			setVisible(false);
			Log_member Log_mf = new Log_member();
		}
	}
}
//---------------------------------------ÂÊÁöº¸³»±â Frame--------------------------------------
class Send_msg extends JFrame implements ActionListener{
	data_v data_vec = new data_v();
	data_e ee = data_vec.vector_data.get(data_vec.Find_index);
	data_e sender = data_vec.vector_data.get(data_vec.Vector_index);
	String name = ee.getName();
	
	JTextArea Msg_In;
	JButton Send_bt,Back_bt;
	JScrollPane scl;
	JLabel To_lb;
	Send_Pan a = new Send_Pan();
	
	public Send_msg() {
		setTitle("ÂÊÁöº¸³»±â");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		add(a);
		a.setSize(290, 362);
		a.setLocation(3, 3);
		a.setLayout(null);
		
		a.add(To_lb = new JLabel(name + " ´Ô¿¡°Ô º¸³»½Ç ÂÊÁö"));
		To_lb.setSize(250,25);
		To_lb.setLocation(30,15);
		a.add(scl = new JScrollPane(Msg_In = new JTextArea()));
		scl.setSize(260,200);
		scl.setLocation(15, 50);
		Msg_In.setBackground(Color.green);
		a.add(Send_bt = new JButton("º¸³»±â"));
		Send_bt.setSize(100,30);
		Send_bt.setLocation(30,265);
		a.add(Back_bt = new JButton("µ¹¾Æ°¡±â"));
		Back_bt.setSize(100,30);
		Back_bt.setLocation(160,265);
		
		Send_bt.addActionListener(this);
		Back_bt.addActionListener(this);
		
		
		setSize(300,395);
		setVisible(true);
		setResizable(false);
	}
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		if(a == "µ¹¾Æ°¡±â"){
			FindFriends_Frame s = new FindFriends_Frame();
			setVisible(false);
		}
		else if(a == "º¸³»±â"){
			ee.setMesage(Msg_In.getText(),sender.getName());
			try{
				BufferedWriter fw = new BufferedWriter(new FileWriter("D:/ÂÊÁö.txt"));
				for(int i=0;i<data_v.vector_data.size();i++){
					data_e File_Out_ele = new data_e();
					File_Out_ele = data_v.vector_data.get(i);
					fw.write(Integer.toString(File_Out_ele.getMessageSize()));
					fw.newLine();
					for(int j=0;j<File_Out_ele.getMessageSize();j++){
						fw.write(File_Out_ele.getMsg_name(j));
						fw.newLine();
						fw.write(File_Out_ele.getMessage(j));
						fw.newLine();
						fw.write("-END-");
						fw.newLine();
					}
				}
				fw.close();
				
			}catch(FileNotFoundException x){
				x.printStackTrace();
			}catch(IOException x){
				x.printStackTrace();
			}
			FindFriends_Frame s = new FindFriends_Frame();
			setVisible(false);
		}
	}
}//---------------------------------------ÂÊÁöº¸³»±â ÆÐ³Î--------------------------------------
class Send_Pan extends JPanel{
	ImageIcon icon = new ImageIcon("images/Æ÷½ºÆ®ÀÕ6.jpg");
	Image img = icon.getImage();
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
//---------------------------------ÂÊÁöÀÐ±âFrame--------------------------------------------
class Read_msg extends JFrame implements ActionListener{
	data_v data_vec = new data_v();
	data_e ee = data_vec.vector_data.get(data_vec.Vector_index);
	String name, from, Msg;
	
	JLabel From_lb,Msg_cnt_lb;
	JTextArea Msg_ta;
	JScrollPane Msg_sc;
	JButton Next_bt,Del_bt,Close_bt;
	Read_Pan a = new Read_Pan();
	
	public Read_msg() {
		setTitle("ÂÊÁöÀÐ±â");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		data_vec.Msg_cnt = 0;
		if(ee.getMessageSize() == 0){
			from = "¹ÞÀº ÂÊÁö°¡ ¾ø½À´Ï´Ù";
			Msg = null;
		}
		else{
			name = ee.getMsg_name(data_vec.Msg_cnt);
			from = name + " ´ÔÀ¸·Î ºÎÅÍ ¿Â ÂÊÁö";
			Msg = ee.getMessage(data_vec.Msg_cnt);
		}
		
		add(a);
		a.setSize(292,372);
		a.setLayout(null);
		a.add(Msg_cnt_lb = new JLabel("ÃÑ " + ee.getMessageSize() + "°³ÀÇ ÂÊÁö°¡ ÀÖ½À´Ï´Ù"));
		Msg_cnt_lb.setSize(180, 30);
		Msg_cnt_lb.setLocation(110, 2);
		a.add(From_lb = new JLabel(from+"("+(data_vec.Msg_cnt+1)+
				"/"+ee.getMessageSize()+")"));
		From_lb.setSize(250, 30);
		From_lb.setLocation(15,30);
		a.add(Msg_sc = new JScrollPane(Msg_ta = new JTextArea(Msg)));
		Msg_sc.setSize(250,200);
		Msg_sc.setLocation(15,60);
		Msg_ta.setBackground(Color.yellow);
		Msg_ta.setRequestFocusEnabled(false);
		Msg_sc.setRequestFocusEnabled(false);
		a.add(Next_bt = new JButton("´ÙÀ½"));
		Next_bt.setSize(80,35);
		Next_bt.setLocation(15, 270);
		a.add(Del_bt = new JButton("»èÁ¦"));
		Del_bt.setSize(80,35);
		Del_bt.setLocation(100, 270);
		a.add(Close_bt = new JButton("Á¾·á"));
		Close_bt.setSize(80,35);
		Close_bt.setLocation(185, 270);
		
		Next_bt.addActionListener(this);
		Del_bt.addActionListener(this);
		Close_bt.addActionListener(this);
		
		setResizable(false);
		setSize(300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		String a = e.getActionCommand();
		Iterator<data_e> it = data_vec.vector_data.iterator();
		data_e user_db = it.next();
		if(a == "´ÙÀ½"){
			if(ee.getMessageSize() == 0){		}
			else{
				if(ee.getMessageSize() == data_vec.Msg_cnt+1){
					data_vec.Msg_cnt = 0;
				}
				else{
					data_vec.Msg_cnt++;
				}
				name = ee.getMsg_name(data_vec.Msg_cnt);
				from = name + " ´ÔÀ¸·Î ºÎÅÍ ¿Â ÂÊÁö";
				Msg = ee.getMessage(data_vec.Msg_cnt);
				From_lb.setText(from+"("+(data_vec.Msg_cnt+1)+
						"/"+ee.getMessageSize()+")");
				Msg_ta.setText(Msg);
			}
		}
		else if(a == "»èÁ¦"){
			if(ee.getMessageSize() == 0){
				JOptionPane.showMessageDialog(null, "»èÁ¦½ÃÅ³ ÂÊÁö°¡ ¾ø½À´Ï´Ù!!", 
						"¿À·ù", JOptionPane.ERROR_MESSAGE);
			}
			else{
				ee.delMessage(data_vec.Msg_cnt);
				try{
					BufferedWriter fw = new BufferedWriter(new FileWriter("data/ÂÊÁö.txt"));
					for(int i=0;i<data_v.vector_data.size();i++){
						data_e File_Out_ele = new data_e();
						File_Out_ele = data_v.vector_data.get(i);
						fw.write(Integer.toString(File_Out_ele.getMessageSize()));
						fw.newLine();
						for(int j=0;j<File_Out_ele.getMessageSize();j++){
							fw.write(File_Out_ele.getMsg_name(j));
							fw.newLine();
							fw.write(File_Out_ele.getMessage(j));
							fw.newLine();
							fw.write("-END-");
							fw.newLine();
						}
					}
					fw.close();
				
				}catch(FileNotFoundException x){
					x.printStackTrace();
				}catch(IOException x){
					x.printStackTrace();
				}
				setVisible(false);
				Read_msg remsg = new Read_msg(); 
			}
		}
		else if(a == "Á¾·á"){
			setVisible(false);
			Log_member Log = new Log_member();
		}
	}
}//--------------------------------ÂÊÁöÀÐ±â ÆÐ³Î--------------------------------------------
class Read_Pan extends JPanel{
	ImageIcon icon = new ImageIcon("images/Æ÷½ºÆ®ÀÕ4_ÂÊÁöÀÐ±â.jpg");
	Image img = icon.getImage();
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
//=====================================MAIN===============================================
public class PostitTalk 
{
	public static void main(String[] args)
	{	
		data_v File_IN_vec = new data_v();
		
		String bf;

		String ID = new String();
		String PW = new String();
		String NAME = new String();
		int P1;
		int P2;
		String EM = new String();
		
		try{
			BufferedReader fr = new BufferedReader(new FileReader("data/ÀÚ·á.txt"));
			while((bf = fr.readLine()) != null){
				data_e File_IN_ele = new data_e();
				ID = bf;
				PW = (fr.readLine());
				NAME = (fr.readLine());
				P1 = Integer.parseInt(fr.readLine());
				P2 = Integer.parseInt(fr.readLine());
				EM = (fr.readLine());
				File_IN_ele.Setdata_e(ID, PW, NAME, P1, P2, EM);
				File_IN_vec.vector_data.add(File_IN_ele);
			}
			fr.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			BufferedReader fr = new BufferedReader(new FileReader("data/ÂÊÁö.txt"));
			for(int i=0;i<File_IN_vec.vector_data.size();i++){
				data_e E = new data_e();
				E = File_IN_vec.vector_data.get(i);
				int n = Integer.parseInt(fr.readLine());
				for(int j=0;j<n;j++){
					String msg;
					String msg_sum = "";
					String name = fr.readLine();
					while(true){
						msg = fr.readLine();
						if(msg.equals("-END-")){
							break;
						}
						else{
							msg_sum += msg + "\n";
						}
					}
					E = File_IN_vec.vector_data.get(i);
					E.setMesage(msg_sum, name);
				}
			}
			fr.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

		Main_Frame Mainf = new Main_Frame();		
	}
}
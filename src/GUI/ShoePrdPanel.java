package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import Animation.ButtonAmination;
import Animation.TextFielAmination;
import BUS.LoaiSPBUS;
import BUS.ShoeBUS;
import DAO.ShoeDAO;
import DTO.ImageHelper;
import DTO.LoaiSPDTO;
import DTO.ShoeDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ShoePrdPanel extends JPanel {
	private JTable table_giay;
	ImageIcon icon_logout=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\logout.png").getImage().getScaledInstance(60, 45, 0));
	ImageIcon icon_btn_save=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconSave.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_btn_edit=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconEdit.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_btn_delete=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconDelete.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_btn_reset=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconReset.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_search_name=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconSearch.png").getImage().getScaledInstance(30, 30, 0));
	ImageIcon icon_listPrd=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\listPrd.png").getImage().getScaledInstance(55, 45, 0));
	private byte[] productimg;
	private TextFielAmination tf_tensanpham;
	private TextFielAmination tf_giaban;
	private TextFielAmination tf_hang;
	private TextFielAmination tf_masanpham;
	private TextFielAmination tf_gianhap;
	private TextFielAmination tf_mau;
	private TextFielAmination tf_soluong;
	private TextFielAmination tf_size;
	private JComboBox cbbox_loaisp;
	private JComboBox cbbox_trangthai;
	DefaultTableModel model = new DefaultTableModel();
	ShoeBUS shoebus=new ShoeBUS();
	byte[] anh=null;
	byte [] img_current;
	private JLabel lb_avatar;
	private TextFielAmination tf_loc_tensp;
	private JComboBox ccbox_loc_trangthai;
	private JComboBox ccbox_loc_loaisp;
	private JComboBox cbbox_gia;
	private JComboBox cbbox_khoanggia;
	private ShoeDAO shoeDAO=new ShoeDAO();
	/**
	 * Create the panel.
	 */
	public ShoePrdPanel() {

		
		this.setSize(1300,800);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.white);

		this.setLayout(null);
		 lb_avatar = new JLabel("");
		 lb_avatar.setBorder(new LineBorder(Color.RED, 2));
		lb_avatar.setOpaque(true);
		lb_avatar.setBackground(Color.CYAN);
		lb_avatar.setBounds(50, 10, 222, 192);
		this.add(lb_avatar);
		
		JLabel lblNewLabel_2 = new JLabel("T??n s???n ph???m  :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(307, 0, 126, 46);
		this.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Gi?? b??n           :");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(307, 40, 132, 46);
		this.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Lo\u1EA1i s\u1EA3n ph\u1EA9m :");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(307, 120, 126, 46);
		this.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("H??ng               :");
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(307, 80, 148, 46);
		this.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Tr???ng th??i        :");
		lblNewLabel_2_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_4.setBounds(307, 160, 148, 46);
		this.add(lblNewLabel_2_4);
		
		 tf_tensanpham= new TextFielAmination();
		tf_tensanpham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_tensanpham.setBorderWeight(3);
		tf_tensanpham.setBounds(449, 6, 300, 30);
		this.add(tf_tensanpham);
		tf_tensanpham.setRadius(17);
		tf_tensanpham.setBorderColor(new Color(185,131,255));
		
		 tf_giaban= new TextFielAmination();
		tf_giaban.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_giaban.setBorderWeight(3); //l??m ????? d???y textfield to h??n
		tf_giaban.setBounds(449, 47, 300, 30); //set v??? tr?? thoi
		tf_giaban.setRadius(17); //set ????? cong ????t
		tf_giaban.setBorderColor(new Color(185,131,255)); //set m??u c???a boderD
		this.add(tf_giaban);
		
		 tf_hang= new TextFielAmination();
		tf_hang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_hang.setBorderWeight(3);
		tf_hang.setBounds(449, 90, 300, 30);
		this.add(tf_hang);
		tf_hang.setRadius(17);
		tf_hang.setBorderColor(new Color(185,131,255));
		
		JLabel lblNewLabel_2_5 = new JLabel("M?? s???n ph???m :");
		lblNewLabel_2_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_5.setBounds(780, 0, 126, 46);
		this.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("Gi?? nh???p        :");
		lblNewLabel_2_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_6.setBounds(780, 40, 148, 46);
		this.add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_7 = new JLabel("M??u s???c         :");
		lblNewLabel_2_7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_7.setBounds(780, 80, 126, 46);
		this.add(lblNewLabel_2_7);
		
		JLabel lblNewLabel_2_8 = new JLabel("S??? l?????ng        :");
		lblNewLabel_2_8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_8.setBounds(780, 120, 126, 46);
		this.add(lblNewLabel_2_8);
		
		JLabel lblNewLabel_2_9 = new JLabel("Size                :");
		lblNewLabel_2_9.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_9.setBounds(780, 160, 148, 46);
		this.add(lblNewLabel_2_9);
		
		 tf_masanpham = new TextFielAmination();
		
		tf_masanpham.setRadius(17);
		tf_masanpham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_masanpham.setBorderWeight(3);
		tf_masanpham.setBorderColor(new Color(185, 131, 255));
		tf_masanpham.setBounds(920, 6, 300, 30);
		this.add(tf_masanpham);
		
		 tf_gianhap = new TextFielAmination();
		tf_gianhap.setRadius(17);
		tf_gianhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_gianhap.setBorderWeight(3);
		tf_gianhap.setBorderColor(new Color(185, 131, 255));
		tf_gianhap.setBounds(920, 47, 300, 30);
		this.add(tf_gianhap);
		
		 tf_mau = new TextFielAmination();
		tf_mau.setRadius(17);
		tf_mau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_mau.setBorderWeight(3);
		tf_mau.setBorderColor(new Color(185, 131, 255));
		tf_mau.setBounds(920, 88, 300, 30);
		this.add(tf_mau);
		
		 tf_soluong = new TextFielAmination();
		tf_soluong.setRadius(17);
		tf_soluong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_soluong.setBorderWeight(3);
		tf_soluong.setBorderColor(new Color(185, 131, 255));
		tf_soluong.setBounds(920, 129, 300, 30);
		this.add(tf_soluong);
		
		 tf_size = new TextFielAmination();
		tf_size.setRadius(17);
		tf_size.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_size.setBorderWeight(3);
		tf_size.setBorderColor(new Color(185, 131, 255));
		tf_size.setBounds(920, 170, 300, 30);
		this.add(tf_size);
		
		 cbbox_loaisp =new JComboBox();
	
		cbbox_loaisp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_loaisp.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_loaisp.setBackground(Color.WHITE);
		cbbox_loaisp.setBounds(449, 128, 300, 30);
		LoaiSPBUS loaisp=new LoaiSPBUS();
		ArrayList<LoaiSPDTO> arr_loaisp;
		arr_loaisp=loaisp.getallLoaiSP();
		
		for(int i=0;i<arr_loaisp.size();i++)
		{
			if(arr_loaisp.get(i).getPhanloai().equals("Gi??y"))
				cbbox_loaisp.addItem(arr_loaisp.get(i).getTenloai());
		}
		cbbox_loaisp.setSelectedIndex(-1);
		
		this.add(cbbox_loaisp);
		
		 cbbox_trangthai = new JComboBox();
		cbbox_trangthai.setModel(new DefaultComboBoxModel(new String[] {"Ho???t ?????ng", "Kh??ng Ho???t ?????ng"}));
		cbbox_trangthai.setOpaque(false);
		cbbox_trangthai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_trangthai.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_trangthai.setBackground(Color.WHITE);
		cbbox_trangthai.setBounds(449, 170, 300, 30);
		this.add(cbbox_trangthai);
		
		 tf_loc_tensp = new TextFielAmination();
		tf_loc_tensp.setHorizontalAlignment(SwingConstants.CENTER);
		tf_loc_tensp.setRadius(5);
		tf_loc_tensp.setForeground(Color.BLACK);
		tf_loc_tensp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tf_loc_tensp.setBorderWeight(3);
		tf_loc_tensp.setBorderColor(new Color(185, 131, 255));
		tf_loc_tensp.setBounds(80, 249, 242, 30);
		this.add(tf_loc_tensp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		scrollPane.setBorder(new LineBorder(Color.CYAN, 3));
		scrollPane.setBounds(-1, 290, 1282, 320);
		this.add(scrollPane);
		
		
		
		table_giay = new JTable();
		table_giay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					ShowshSelect();
					try {
						showImg();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		table_giay.setRowHeight(27); //nh?? t??n lu??n nh?? hehe
		table_giay.setSelectionBackground(new Color(185,131,255)); //set m??u khi ch???n v??o 1 row
		table_giay.setGridColor(Color.black); //set m??u c??c thanh ma tr???n b??n d?????i
		table_giay.getTableHeader().setBackground(new Color(0x94DAFF)); //set backround c???a thanh header
		table_giay.setFont(new Font("Times New Roman", Font.PLAIN, 15)); //set font ch??? c??c d??ng chi ti???t b??n d?????i
		table_giay.getTableHeader().setFont(new Font("Time New Roman", Font.BOLD, 16)); //set font ch??? c??c thanh header collum
		table_giay.getTableHeader().setReorderingAllowed(false); //khong cho resize cot	
		
		table_giay.setModel(model);
		model.addColumn("T??n S???n Ph???m");
		model.addColumn("Gi?? B??n");
		model.addColumn("Gi?? Nh???p");
		model.addColumn("M?? SP");
		model.addColumn("H??ng");
		model.addColumn("Lo???i SP");
		model.addColumn("S??? L?????ng");
		model.addColumn("Size");
		model.addColumn("Tr???ng Th??i");
		model.addColumn("M??u S???c");
		updateTb(model);
		table_giay.setDefaultEditor(Object.class, null);//d??ng n??y s??? ko cho edit c??c ?? trong table
		scrollPane.setViewportView(table_giay);
		
		
		ButtonAmination btnSave = new ButtonAmination("L??u");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkTF())
				{
					if(anh==null)
					{
						JOptionPane.showMessageDialog(null,"Vui l??ng ch???n h??nh ???nh", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						
					
					ShoeDTO shoe =new ShoeDTO();
					boolean flag = false;
					do {
						try {
							shoe.setMaSP((chuanHoaMasp(tf_masanpham.getText()) ));
							shoe.setTenSp(chuanHoa(tf_tensanpham.getText()) );
							shoe.setLoaiSp(cbbox_loaisp.getSelectedItem().toString());
							shoe.setSl(Integer.parseInt(tf_soluong.getText()));
							shoe.setGiaban(Integer.parseInt(tf_giaban.getText()));
							shoe.setGianhap(Integer.parseInt(tf_gianhap.getText()));
							shoe.setMau(chuanHoa(tf_mau.getText()) );
							boolean status = false;
							if(cbbox_trangthai.getSelectedItem().toString().equals("Ho???t ?????ng"))
							{
								status=true;
							}
							else if(cbbox_loaisp.getSelectedItem().toString().equals("Kh??ng Ho???t ?????ng"))
							{
								status=false;
							}
							shoe.setTrangthai(status);
							shoe.setBrand(chuanHoa(tf_hang.getText()));
							shoe.setSize(Float.parseFloat(tf_size.getText()));
							shoe.setImage(anh);
							
							JOptionPane.showMessageDialog(null, shoebus.addShoe(shoe), "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
							updateTb(model);
							reset_tf();
							anh=null;
							flag=false;
						} catch (Exception e2) {
							// TODO: handle exception
							//System.out.println(e2);
							JOptionPane.showMessageDialog(null,"Vui l??ng nh???p ch??nh x??c d??? li???u s???n ph???m", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
							
						}
					} while (flag==true);	
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Vui l??ng nh???p ????? th??ng tin ????????", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				}
			
				//
			}
		});
		JPanel pn_find_price = new JPanel();
		pn_find_price.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm theo gi\u00E1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "T\u00ECm theo gi\u00E1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_find_price.setBackground(Color.WHITE);
		pn_find_price.setBounds(351, 233, 398, 55);
		this.add(pn_find_price);
		pn_find_price.setLayout(null);
		
		cbbox_khoanggia = new JComboBox();
		cbbox_khoanggia.setBounds(144, 15, 197, 30);
		pn_find_price.add(cbbox_khoanggia);
		cbbox_khoanggia.setModel(new DefaultComboBoxModel(new String[] {"None", "D?????i 200.000", "200.000 ~ 400.000", "400.000 ~ 600.000", "600.000 ~ 800.000", "800.000 ~ 1.000.000", "Tr??n 1.000.000"}));
		cbbox_khoanggia.setOpaque(false);
		cbbox_khoanggia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_khoanggia.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_khoanggia.setBackground(Color.WHITE);
		
		cbbox_gia = new JComboBox();
		cbbox_gia.setBounds(21, 15, 110, 30);
		pn_find_price.add(cbbox_gia);
		cbbox_gia.setModel(new DefaultComboBoxModel(new String[] {"None", "Gi?? B??n", "Gi?? Nh???p"}));
		cbbox_gia.setOpaque(false);
		cbbox_gia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_gia.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_gia.setBackground(Color.WHITE);
		
		JLabel lb_icon_search_name_1 = new JLabel("");
		lb_icon_search_name_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
		lb_icon_search_name_1.setBounds(351, 11, 47, 44);
		pn_find_price.add(lb_icon_search_name_1);
		lb_icon_search_name_1.setIcon(icon_search_name);
		lb_icon_search_name_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loc_Kgia(model);
				ccbox_loc_loaisp.setSelectedIndex(0);
				tf_loc_tensp.setText("");
				ccbox_loc_trangthai.setSelectedIndex(0);
			}
		});
		
		
		
		btnSave.setIcon(icon_btn_save);
		btnSave.setRadius(30);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave.setBorderColor(new Color(23, 150, 218));
		btnSave.setBackground(new Color(23, 150, 218));
		btnSave.setBounds(10, 623, 120, 50);
		this.add(btnSave);
		
		ButtonAmination btnEdit = new ButtonAmination("S???a");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editshoe();
			}
		});
		btnEdit.setRadius(30);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnEdit.setBorderColor(new Color(255, 157, 105));
		btnEdit.setBackground(new Color(255, 157, 105));
		btnEdit.setBounds(150, 622, 120, 52);
		this.add(btnEdit);
		btnEdit.setIcon(icon_btn_edit);
		
		ButtonAmination btnDelete = new ButtonAmination("X??a");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DelShoe();
			}
		});
		
		btnDelete.setRadius(30);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBorderColor(new Color(251, 83, 124));
		btnDelete.setBackground(new Color(251, 83, 124));
		btnDelete.setBounds(290, 623, 120, 50);
		this.add(btnDelete);
		btnDelete.setIcon(icon_btn_delete);
		
		ButtonAmination btnReset = new ButtonAmination("L??m m???i");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset_tf();
				ArrayList<ShoeDTO> arrShoe=new ArrayList<ShoeDTO>();
				arrShoe= shoeDAO.getAllShoe();
				int temp;
				if(arrShoe.size()==0)
				{
					temp=1;
				}
				else
				{
					 temp=arrShoe.get(arrShoe.size()-1).getMaSP()+1;
				}
				
				tf_masanpham.setText("GI"+temp);
			}
		});
		btnReset.setRadius(30);
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBorderColor(new Color(61, 188, 246));
		btnReset.setBackground(new Color(61, 188, 246));
		btnReset.setBounds(430, 623, 120, 50);
		this.add(btnReset);
		btnReset.setIcon(icon_btn_reset);
		
		 ccbox_loc_loaisp = new JComboBox();
		 
		ccbox_loc_loaisp.setOpaque(false);
		ccbox_loc_loaisp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ccbox_loc_loaisp.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		ccbox_loc_loaisp.setBackground(Color.WHITE);
		ccbox_loc_loaisp.setBounds(800, 249, 223, 30);
		
		ccbox_loc_loaisp.addItem("None");
		for(int i=0;i<arr_loaisp.size();i++)
		{
			if(arr_loaisp.get(i).getPhanloai().equals("Gi??y"))
				ccbox_loc_loaisp.addItem(arr_loaisp.get(i).getTenloai());
		}
		
		this.add(ccbox_loc_loaisp);
		
		 ccbox_loc_trangthai = new JComboBox();

		 ccbox_loc_trangthai.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		loc_sp_trangthai(model);
		 	}
		 });
		ccbox_loc_trangthai.setModel(new DefaultComboBoxModel(new String[] {"None", "??ang Ho???t ?????ng", "Kh??ng Ho???t ?????ng"}));
		ccbox_loc_trangthai.setOpaque(false);
		ccbox_loc_trangthai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ccbox_loc_trangthai.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		ccbox_loc_trangthai.setBackground(Color.WHITE);
		ccbox_loc_trangthai.setBounds(1050, 249, 187, 30);
		this.add(ccbox_loc_trangthai);
		ccbox_loc_loaisp.setSelectedIndex(-1);
		ccbox_loc_loaisp.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		loc_sp_loaisp(model);
		 		cbbox_khoanggia.setSelectedIndex(0);
		 
		 	}
		 });
		ccbox_loc_loaisp.setSelectedIndex(0);
		
		
		
		
		JLabel lb_icon_search_name = new JLabel("");
		lb_icon_search_name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_icon_search_name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loc_sp_ten(model);
			}
		});
		lb_icon_search_name.setBounds(33, 245, 47, 44);
		this.add(lb_icon_search_name);
		lb_icon_search_name.setIcon(icon_search_name);
		
		
		
		ButtonAmination btn_openfile = new ButtonAmination("M??? H??nh");
		btn_openfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser =new JFileChooser();
				int returnValue = chooser.showOpenDialog(null); 
				chooser.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						
						return "Image File (*.jpg) or (*.png)";
					}
					
					@Override
					public boolean accept(File f) {
						if(f.isDirectory())
						{
							return true;
						}
						else
						{
							return f.getName().toLowerCase().endsWith("jpg") || f.getName().toLowerCase().endsWith("png");
						}
					}

					
				});
				if(returnValue==JFileChooser.APPROVE_OPTION) {  //KHI CH???N 1 FILE N??O ???? TH??NH C??NG
					try {
						
						File file =chooser.getSelectedFile();
						ImageIcon icon =new ImageIcon(file.getPath());
						Image img = ImageHelper.resize(icon.getImage(), 220, 190);
						ImageIcon newicon =new ImageIcon(img);
						lb_avatar.setIcon(newicon);
						productimg = ImageHelper.toByteArray(img, "jpg");
						anh=productimg;
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(btn_openfile, "Loi");
					}
				
				}
				else if (chooser.showOpenDialog(getParent()) ==JFileChooser.CANCEL_OPTION); //KHI ???N CANCER THO??T RA BOX CH???N FILE
				{
					return;
				}
			}
		});
		btn_openfile.setRadius(10);
		btn_openfile.setBounds(110, 210, 100, 30);
		this.add(btn_openfile);
		
		
		
		//???n ko cho ch???nh c??c tf
		nonEditTf(false);
		//ko choedti masp
		tf_masanpham.setEditable(false);
	}
	public boolean checkTF()
	{
		if(tf_masanpham.getText().equals("")||tf_tensanpham.getText().equals("") || tf_giaban.getText().equals("") || tf_gianhap.getText().equals("") ||
			tf_hang.getText().equals("") || tf_mau.getText().equals("") ||tf_soluong.getText().equals("") || tf_size.getText().equals("") || 
			cbbox_loaisp.getSelectedIndex()==-1 ||cbbox_trangthai.getSelectedIndex()==-1)
		{
			
			return false;
		}
		return true; //ok c??c tf ?????u ok
		
	}
	public void reset_tf()
	{
		tf_masanpham.setText("");
		tf_tensanpham.setText("");
		tf_giaban.setText("");
		tf_gianhap.setText("");
		tf_hang.setText("");
		tf_mau.setText("");
		tf_soluong.setText("");
		tf_size.setText("");
		cbbox_loaisp.setSelectedIndex(-1);
		cbbox_trangthai.setSelectedIndex(-1);
		lb_avatar.setIcon(null);
		anh=null;
		updateTb(model);
		cbbox_gia.setSelectedIndex(0);
		cbbox_khoanggia.setSelectedIndex(0);
		ccbox_loc_loaisp.setSelectedIndex(0);
		ccbox_loc_trangthai.setSelectedIndex(0);
		nonEditTf(true);
	}
	public void updateTb(DefaultTableModel model)
	{
		model.setRowCount(0);
		ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
		arrShoe =shoebus.getallShoe();
		for(int i=0;i<arrShoe.size();i++)
		{
			ShoeDTO shoe=arrShoe.get(i);
			String trangthai;
			if(shoe.getTrangthai()==true)
			{
				trangthai="Ho???t ?????ng";
			}
			else
			{
				trangthai="Kh??ng Ho???t ?????ng";
			}
			
			model.addRow(new Object[] {shoe.getTenSp(),shoe.getGiaban(),shoe.getGianhap(),shoe.getMaSP(),shoe.getBrand(),shoe.getLoaiSp(),shoe.getSl(),shoe.getSize(),trangthai,shoe.getMau() });
		}
	}
	
	
	public void showImg() throws IOException
	{
		ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
		arrShoe =shoebus.getallShoe();
		 img_current = null;
		for(int i=0;i<arrShoe.size();i++)
		{
			if(chuanHoaMasp(tf_masanpham.getText()) ==arrShoe.get(i).getMaSP())
			{
				img_current =(byte[]) arrShoe.get(i).getImage();
			}
		}
		try {
			Image temp=ImageHelper.createImageFromByteArray(img_current,"jpg");
			ImageIcon icon =new ImageIcon(temp);
			lb_avatar.setIcon(icon);
			anh=img_current;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}		
		}
	
	public void ShowshSelect()
	{
		nonEditTf(true);
		DefaultTableModel model_table =(DefaultTableModel) table_giay.getModel();
		int i=table_giay.getSelectedRow();
		String tensp=(String) model_table.getValueAt(i, 0);
		long giaban =(long) model_table.getValueAt(i, 1);
		long gianhap =(long) model_table.getValueAt(i, 2);
		int masp =(int) model_table.getValueAt(i, 3);
		String hang=(String) model_table.getValueAt(i, 4);
		String loaisp=(String) model_table.getValueAt(i, 5);//no
		int sl =(int) model_table.getValueAt(i, 6);
		float size =(float) model_table.getValueAt(i, 7);
		String trangthai=(String) model_table.getValueAt(i, 8);//no
		String mausac=(String) model_table.getValueAt(i, 9);
		
		
			
		tf_tensanpham.setText(chuanHoa(tensp));
		tf_giaban.setText(giaban+"");
		tf_gianhap.setText(gianhap+"");
		tf_masanpham.setText("GI"+masp+"");
		tf_hang.setText(chuanHoa(hang));
		tf_size.setText(size+"");
		tf_soluong.setText(sl+"");
		tf_mau.setText(chuanHoa(mausac));
		
		
		int trangthai_temp;
		if(trangthai.equals("Ho???t ?????ng"))
		{
			trangthai_temp=0;	
		}
		else {
			trangthai_temp=1;
		}
		cbbox_trangthai.setSelectedIndex(trangthai_temp);
		for(int j=0;j<=cbbox_loaisp.getItemCount();j++)
		{
			if(loaisp.equals(cbbox_loaisp.getItemAt(j)))
			{
				cbbox_loaisp.setSelectedIndex(j);
			}
		}	
	}
	
	public void DelShoe()
	{
		if(checkTF())
		{
			ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
			arrShoe =shoebus.getallShoe();
			int masp =(chuanHoaMasp( tf_masanpham.getText()));
			String tensp= tf_tensanpham.getText();
			String loaisp= cbbox_loaisp.getSelectedItem().toString();
			int sl= Integer.parseInt(tf_soluong.getText());
			int giaban= Integer.parseInt(tf_giaban.getText());
			int gianhap= Integer.parseInt(tf_gianhap.getText());
			String mau =tf_mau.getText();
			String trangthai=cbbox_trangthai.getSelectedItem().toString();
			String hang=tf_hang.getText();
			Float size= Float.parseFloat(tf_size.getText());
		
			
		//	System.out.println(masp+tensp+loaisp+sl+giaban+gianhap+mau+trangthai+hang+size);
			for(int i=0;i<arrShoe.size();i++)
			{System.out.println(arrShoe.get(i).getTrangthai().equals(trangthai));
				if(arrShoe.get(i).getMaSP()==masp&& arrShoe.get(i).getTenSp().equals(tensp)  && arrShoe.get(i).getLoaiSp().equals(loaisp)
						&& arrShoe.get(i).getSl()==sl && arrShoe.get(i).getGiaban()==giaban && arrShoe.get(i).getGianhap()==gianhap 
						&& arrShoe.get(i).getMau().equals(mau)  && arrShoe.get(i).getBrand().equals(hang)
						&& arrShoe.get(i).getSize()==size	)
				{
					
					JOptionPane.showMessageDialog(null, shoebus.DelShoe(masp), "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
					updateTb(model);
					reset_tf();
					return;
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Nh???p ????? th??ng tin ??i anh b???n", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editshoe()
	{
		if(checkTF())
		{
			try {
				ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
				arrShoe =shoebus.getallShoe();
				int masp =chuanHoaMasp(tf_masanpham.getText());
				String tensp= tf_tensanpham.getText();
				String loaisp= cbbox_loaisp.getSelectedItem().toString();
				int sl= Integer.parseInt(tf_soluong.getText());
				int giaban= Integer.parseInt(tf_giaban.getText());
				int gianhap= Integer.parseInt(tf_gianhap.getText());
				String mau =tf_mau.getText();
				String trangthai=cbbox_trangthai.getSelectedItem().toString();
				String hang=tf_hang.getText();
				Float size= Float.parseFloat(tf_size.getText());
				byte[] img=anh;
			
				
				
				boolean Btrangthai=false;
				if(trangthai.equals("Ho???t ?????ng"))
				{
					Btrangthai=true;
				}
				else
				{
					Btrangthai=false;
				}
				
				
				ShoeDTO shoe =new ShoeDTO(masp,chuanHoa(tensp),loaisp,sl,giaban,gianhap,chuanHoa(mau),Btrangthai,chuanHoa(hang),size,img);
				JOptionPane.showMessageDialog(null,shoebus.editShoe(shoe), "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				reset_tf();
				updateTb(model);
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Nh???p ????ng D??? Li???u ??i B???n", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Nh???p ????? th??ng tin ??i anh b???n", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	//ch???c n??ng t??m s???n ph???m theo t??n
	public void loc_sp_ten(DefaultTableModel model)
	{
		
		
		if(tf_loc_tensp.getText().equals("")==false)
		{
			model.setRowCount(0);
			ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
			arrShoe =shoebus.getallShoe();
			String tensp=chuanHoa(tf_loc_tensp.getText()) ;
			for(int i=0;i<arrShoe.size();i++)
			{
				ShoeDTO shoe=arrShoe.get(i);
				String trangthai;
				if(shoe.getTrangthai()==true)
				{
					trangthai="Ho???t ?????ng";
				}
				else
				{
					trangthai="Kh??ng Ho???t ?????ng";
				}
				if(arrShoe.get(i).getTenSp().indexOf(tensp)!=-1)
				{
					
					model.addRow(new Object[] {shoe.getTenSp(),shoe.getGiaban(),shoe.getGianhap(),shoe.getMaSP(),shoe.getBrand(),shoe.getLoaiSp(),shoe.getSl(),shoe.getSize(),trangthai,shoe.getMau() }); 
				}	
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Vui L??ng Nh???p T??n S???n Ph???m T??m Ki???m", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			updateTb(model);
		}
	}
	//ch???c nang loc theo trangthai
	public void loc_sp_trangthai(DefaultTableModel model)
	{
		cbbox_gia.setSelectedIndex(0);
		cbbox_khoanggia.setSelectedIndex(0);
		ccbox_loc_loaisp.setSelectedIndex(0);
		tf_loc_tensp.setText("");
		
	
			int k=ccbox_loc_trangthai.getSelectedIndex();//i=1 l?? ho???t ?????ng, 2 l?? ko hd
			model.setRowCount(0);
			ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
			arrShoe =shoebus.getallShoe();
			Boolean Btrangthai = null;
			if(k==1)
			{
				Btrangthai=true;
			}
			else if(k==2)
			{
				Btrangthai=false;
			}
			if(Btrangthai!=null)
			{
				for(int i=0;i<arrShoe.size();i++)
				{
					ShoeDTO shoe=arrShoe.get(i);
					String trangthai;
					if(shoe.getTrangthai()==true)
					{
						trangthai="Ho???t ?????ng";
					}
					else
					{
						trangthai="Kh??ng Ho???t ?????ng";
					}
					if(arrShoe.get(i).getTrangthai()==Btrangthai)
					{
						
						model.addRow(new Object[] {shoe.getTenSp(),shoe.getGiaban(),shoe.getGianhap(),shoe.getMaSP(),shoe.getBrand(),shoe.getLoaiSp(),shoe.getSl(),shoe.getSize(),trangthai,shoe.getMau() }); 
					}
				}
			}
			else
			{
				updateTb(model);
			}
	}
	//loc theo loai s???n ph???m
	public void loc_sp_loaisp(DefaultTableModel model)
	{
			cbbox_gia.setSelectedIndex(0);
			
			
			String tenlsp=(String) ccbox_loc_loaisp.getSelectedItem();
			
			model.setRowCount(0);
			ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
			arrShoe =shoebus.getallShoe();
			Boolean Btrangthai = null;
			
			if(tenlsp!="None")
			{
				for(int i=0;i<arrShoe.size();i++)
				{
					
					ShoeDTO shoe=arrShoe.get(i);
					String trangthai;
					if(shoe.getTrangthai()==true)
					{
						trangthai="Ho???t ?????ng";
					}
					else
					{
						trangthai="Kh??ng Ho???t ?????ng";
					}
					if(arrShoe.get(i).getLoaiSp().equals(tenlsp))
					{
						
						model.addRow(new Object[] {shoe.getTenSp(),shoe.getGiaban(),shoe.getGianhap(),shoe.getMaSP(),shoe.getBrand(),shoe.getLoaiSp(),shoe.getSl(),shoe.getSize(),trangthai,shoe.getMau() }); 
					}
				}
			}
			else
			{
				updateTb(model);
			}
	}
//	none					0
//	D?????i 200.000			1
//	200.000 ~ 400.000		2
//	400.000 ~ 600.000		3
//	600.000 ~ 800.000		4
//	800.000 ~ 1.000.000		5
//	Tr??n 1.000.000			6
	
	
//	None		0
//	Gi?? B??n		1
//	Gi?? Nh???p	2
	//l???c theo kho???ng gi??
	public void loc_Kgia(DefaultTableModel model)
	{
		
		
		int pos_x=cbbox_gia.getSelectedIndex();
		int pos_y=cbbox_khoanggia.getSelectedIndex();
		model.setRowCount(0);
		if(pos_x==0 || pos_y==0)
		{
			updateTb(model);
			JOptionPane.showMessageDialog(null, "Vui L??ng Ch???n M???c Gi?? L???c", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
			arrShoe =shoebus.getallShoe();
			if(pos_x==1)//l???c theo gi?? b??n
			{
				int min = 0;
				int max=0;
				if(pos_y==1)
				{
					min=0;
					max=200000;
				}
				if(pos_y==2)
				{
					min=200000;
					max=400000;
				}
				if(pos_y==3)
				{
					min=400000;
					max=600000;
				}
				if(pos_y==4)
				{
					min=600000;
					max=800000;
				}
				if(pos_y==5)
				{
					min=800000;
					max=1000000;
				}
				if(pos_y==6)
				{
					min=1000000;
					max=0;
				}
				
					
				for(int i=0;i<arrShoe.size();i++)
				{
					
					ShoeDTO shoe=arrShoe.get(i);
					String trangthai;
					if(shoe.getTrangthai()==true)
					{
						trangthai="Ho???t ?????ng";
					}
					else
					{
						trangthai="Kh??ng Ho???t ?????ng";
					}
					if(arrShoe.get(i).getGiaban()<=max && arrShoe.get(i).getGiaban()>=min)
					{
						
						model.addRow(new Object[] {shoe.getTenSp(),shoe.getGiaban(),shoe.getGianhap(),shoe.getMaSP(),shoe.getBrand(),shoe.getLoaiSp(),shoe.getSl(),shoe.getSize(),trangthai,shoe.getMau() }); 
					}
				}
			}
			else if(pos_x==2) //l???c theo gi?? nh???p
			{
				int min = 0;
				int max=0;
				if(pos_y==1)
				{
					min=0;
					max=200000;
				}
				if(pos_y==2)
				{
					min=200000;
					max=400000;
				}
				if(pos_y==3)
				{
					min=400000;
					max=600000;
				}
				if(pos_y==4)
				{
					min=600000;
					max=800000;
				}
				if(pos_y==5)
				{
					min=800000;
					max=1000000;
				}
				if(pos_y==6)
				{
					min=1000000;
					max=0;
				}
				System.out.println(pos_x+"  "+pos_y+" "+min+" "+max);
					
				for(int i=0;i<arrShoe.size();i++)
				{
					
					ShoeDTO shoe=arrShoe.get(i);
					String trangthai;
					if(shoe.getTrangthai()==true)
					{
						trangthai="Ho???t ?????ng";
					}
					else
					{
						trangthai="Kh??ng Ho???t ?????ng";
					}
					if(arrShoe.get(i).getGianhap()<=max && arrShoe.get(i).getGiaban()>=min)
					{
						
						model.addRow(new Object[] {shoe.getTenSp(),shoe.getGiaban(),shoe.getGianhap(),shoe.getMaSP(),shoe.getBrand(),shoe.getLoaiSp(),shoe.getSl(),shoe.getSize(),trangthai,shoe.getMau() }); 
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "???? X??Y RA L???I", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			}
		}
		
}	
		
	
    public static String chuanHoa(String s) {
        s = s.trim().toLowerCase();// trim l?? x??a kho???ng c??ch d?? ??? ?????u v?? ??u??i, c??n tolower l??m c??? chu???i th??nh ch???
                                   // th?????ng
        s = s.replaceAll("\\s+", " ");
        String temp[] = s.split(" ");// l??c n??y m???i t??? s??? th??nh 1 ph???n t??? trong m???ng temp
        // example : s="em chao co"
        // l??c n??y temp[0]=em temp[1]=chao temp[2]=co

        s = "";// luc n??y minh cho chu???i ban ?????u r???ng
        for (int i = 0; i < temp.length; i++) {
            // l??c n??y ch??n quang l???i c??c
            // ch??? ????? dc chu???n h??a v??o String s ban ?????u
            s += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            // substring(1)??ng ????? l???y to??n b??? c??c ph???n t??? t??? v??? tr?? 1 ?????n cu???i x??u temp[i]
            if (i < temp.length - 1) // n???u tempt[i] kh??ng ph???i t??? cu???i c??ng
                s += " "; // c???ng th??m m???t kho???ng tr???ng
        }
        return s;
    }
    
    public void nonEditTf(boolean sos)
    {
    	
    	tf_tensanpham.setEditable(sos);
		tf_giaban.setEditable(sos);
		tf_gianhap.setEditable(sos);
		tf_hang.setEditable(sos);
		tf_mau.setEditable(sos);
		tf_soluong.setEditable(sos);
		tf_size.setEditable(sos);

    }
    
    public int chuanHoaMasp(String text)
    {
    	String [] splits =text.split("I");
    	return Integer.parseInt(splits[1]);
    }
}
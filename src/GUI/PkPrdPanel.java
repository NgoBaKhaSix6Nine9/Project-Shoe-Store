package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Animation.ButtonAmination;
import Animation.TextFielAmination;
import BUS.AccessoryBUS;
import BUS.LoaiSPBUS;
import DAO.AccessoryDAO;
import DTO.AccessoryDTO;
import DTO.ImageHelper;
import DTO.LoaiSPDTO;
import DTO.ShoeDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.Cursor;

public class PkPrdPanel extends JPanel {
	AccessoryBUS AcBUS =new AccessoryBUS();
	
	ImageIcon icon_logout=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\logout.png").getImage().getScaledInstance(60, 45, 0));
	ImageIcon icon_btn_save=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconSave.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_btn_edit=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconEdit.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_btn_delete=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconDelete.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_btn_reset=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconReset.png").getImage().getScaledInstance(40, 40, 0));
	ImageIcon icon_search_name=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\iconSearch.png").getImage().getScaledInstance(30, 30, 0));
	ImageIcon icon_listPrd=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\icon\\listPrd.png").getImage().getScaledInstance(55, 45, 0));
	private JTable table_pk;
	private JComboBox cbbox_loaisp_pk;
	private TextFielAmination tf_thongso_pk;
	private TextFielAmination tf_giaban_pk;
	private TextFielAmination tf_tensanpham_pk;
	private JComboBox cbbox_trangthai_pk;
	private JComboBox ccbox_loc_loaisp_pk;
	private TextFielAmination tf_ghichu_pk;
	private TextFielAmination tf_soluong_pk;
	private TextFielAmination tf_mau_pk;
	private TextFielAmination tf_gianhap_pk;
	private TextFielAmination tf_masanpham_pk;
	private TextFielAmination tf_loc_tensp_pk;
	private JComboBox cbbox_gia_pk;
	private JComboBox cbbox_khoanggia_pk;
	private byte[] productimg;
	byte[] anh=null;
	byte [] img_current;
	DefaultTableModel model = new DefaultTableModel();
	private JLabel lb_avatar_pk;
	private JComboBox ccbox_loc_trangthai_pk;
	private AccessoryDAO acDAO=new AccessoryDAO();
	
	
	
	/**
	 * Create the panel.
	 */
	public PkPrdPanel() {
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setSize(1300,800);
		 lb_avatar_pk = new JLabel("");
		 lb_avatar_pk.setBorder(new LineBorder(Color.RED, 2));
		lb_avatar_pk.setOpaque(true);
		lb_avatar_pk.setBackground(Color.CYAN);
		lb_avatar_pk.setBounds(49, 10, 222, 192);
		this.add(lb_avatar_pk);
		
		 tf_loc_tensp_pk = new TextFielAmination();
		tf_loc_tensp_pk.setRadius(5);
		tf_loc_tensp_pk.setHorizontalAlignment(SwingConstants.CENTER);
		tf_loc_tensp_pk.setForeground(Color.BLACK);
		tf_loc_tensp_pk.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tf_loc_tensp_pk.setBorderWeight(3);
		tf_loc_tensp_pk.setBorderColor(new Color(185, 131, 255));
		tf_loc_tensp_pk.setBounds(79, 249, 242, 30);
		this.add(tf_loc_tensp_pk);
		
		JLabel lblNewLabel_2_10 = new JLabel("T??n s???n ph???m  :");
		lblNewLabel_2_10.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_10.setBounds(306, 0, 126, 46);
		this.add(lblNewLabel_2_10);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Gi?? b??n           :");
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(306, 40, 132, 46);
		this.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Th??ng S???         :");
		lblNewLabel_2_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_3_1.setBounds(306, 80, 148, 46);
		this.add(lblNewLabel_2_3_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Lo???i s???n ph???m :");
		lblNewLabel_2_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_2_1.setBounds(306, 120, 126, 46);
		this.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Tr???ng th??i        :");
		lblNewLabel_2_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_4_1.setBounds(306, 160, 148, 46);
		this.add(lblNewLabel_2_4_1);
		
		 tf_tensanpham_pk = new TextFielAmination();
		tf_tensanpham_pk.setRadius(17);
		tf_tensanpham_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_tensanpham_pk.setBorderWeight(3);
		tf_tensanpham_pk.setBorderColor(new Color(185, 131, 255));
		tf_tensanpham_pk.setBounds(448, 6, 300, 30);
		this.add(tf_tensanpham_pk);
		
		 tf_giaban_pk = new TextFielAmination();
		tf_giaban_pk.setRadius(17);
		tf_giaban_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_giaban_pk.setBorderWeight(3);
		tf_giaban_pk.setBorderColor(new Color(185, 131, 255));
		tf_giaban_pk.setBounds(448, 47, 300, 30);
		this.add(tf_giaban_pk);
		
		 tf_thongso_pk = new TextFielAmination();
		tf_thongso_pk.setRadius(17);
		tf_thongso_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_thongso_pk.setBorderWeight(3);
		tf_thongso_pk.setBorderColor(new Color(185, 131, 255));
		tf_thongso_pk.setBounds(448, 90, 300, 30);
		this.add(tf_thongso_pk);
		
		 cbbox_loaisp_pk = new JComboBox();
		
		cbbox_loaisp_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_loaisp_pk.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_loaisp_pk.setBackground(Color.WHITE);
		cbbox_loaisp_pk.setBounds(448, 128, 300, 30);
		this.add(cbbox_loaisp_pk);
		
		
		LoaiSPBUS loaisp=new LoaiSPBUS();
		ArrayList<LoaiSPDTO> arr_loaisp;
		arr_loaisp=loaisp.getallLoaiSP();
		for(int i=0;i<arr_loaisp.size();i++)
		{
			if(arr_loaisp.get(i).getPhanloai().equals("Ph??? Ki???n"))
				cbbox_loaisp_pk.addItem(arr_loaisp.get(i).getTenloai());
		}
		cbbox_loaisp_pk.setSelectedIndex(-1);
		
		 cbbox_trangthai_pk = new JComboBox();
		cbbox_trangthai_pk.setModel(new DefaultComboBoxModel(new String[] {"Ho???t ?????ng", "Kh??ng Ho???t ?????ng"}));
		cbbox_trangthai_pk.setOpaque(false);
		cbbox_trangthai_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_trangthai_pk.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_trangthai_pk.setBackground(Color.WHITE);
		cbbox_trangthai_pk.setBounds(448, 170, 300, 30);
		this.add(cbbox_trangthai_pk);
		
		JLabel lblNewLabel_2_5_1 = new JLabel("M?? s???n ph???m :");
		lblNewLabel_2_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_5_1.setBounds(779, 0, 126, 46);
		this.add(lblNewLabel_2_5_1);
		
		JLabel lblNewLabel_2_6_1 = new JLabel("Gi?? nh???p        :");
		lblNewLabel_2_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_6_1.setBounds(779, 40, 148, 46);
		this.add(lblNewLabel_2_6_1);
		
		JLabel lblNewLabel_2_7_1 = new JLabel("M??u s???c         :");
		lblNewLabel_2_7_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_7_1.setBounds(779, 80, 126, 46);
		this.add(lblNewLabel_2_7_1);
		
		JLabel lblNewLabel_2_8_1 = new JLabel("S??? l?????ng        :");
		lblNewLabel_2_8_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_8_1.setBounds(779, 120, 126, 46);
		this.add(lblNewLabel_2_8_1);
		
		JLabel lblNewLabel_2_9_1 = new JLabel("Ghi Ch??          :");
		lblNewLabel_2_9_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_9_1.setBounds(779, 160, 148, 46);
		this.add(lblNewLabel_2_9_1);
		
		 tf_masanpham_pk = new TextFielAmination();
		tf_masanpham_pk.setRadius(17);
		tf_masanpham_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_masanpham_pk.setBorderWeight(3);
		tf_masanpham_pk.setBorderColor(new Color(185, 131, 255));
		tf_masanpham_pk.setBounds(919, 6, 300, 30);
		this.add(tf_masanpham_pk);
		
		 tf_gianhap_pk = new TextFielAmination();
		tf_gianhap_pk.setRadius(17);
		tf_gianhap_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_gianhap_pk.setBorderWeight(3);
		tf_gianhap_pk.setBorderColor(new Color(185, 131, 255));
		tf_gianhap_pk.setBounds(919, 47, 300, 30);
		this.add(tf_gianhap_pk);
		
		 tf_mau_pk = new TextFielAmination();
		tf_mau_pk.setRadius(17);
		tf_mau_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_mau_pk.setBorderWeight(3);
		tf_mau_pk.setBorderColor(new Color(185, 131, 255));
		tf_mau_pk.setBounds(919, 88, 300, 30);
		this.add(tf_mau_pk);
		
		 tf_soluong_pk = new TextFielAmination();
		tf_soluong_pk.setRadius(17);
		tf_soluong_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_soluong_pk.setBorderWeight(3);
		tf_soluong_pk.setBorderColor(new Color(185, 131, 255));
		tf_soluong_pk.setBounds(919, 129, 300, 30);
		this.add(tf_soluong_pk);
		
		 tf_ghichu_pk = new TextFielAmination();
		tf_ghichu_pk.setRadius(17);
		tf_ghichu_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tf_ghichu_pk.setBorderWeight(3);
		tf_ghichu_pk.setBorderColor(new Color(185, 131, 255));
		tf_ghichu_pk.setBounds(919, 170, 300, 30);
		this.add(tf_ghichu_pk);
		
		 ccbox_loc_loaisp_pk = new JComboBox();
		 ccbox_loc_loaisp_pk.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		loc_sp_loaisp(model);
		 	}
		 });
		ccbox_loc_loaisp_pk.setOpaque(false);
		ccbox_loc_loaisp_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ccbox_loc_loaisp_pk.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		ccbox_loc_loaisp_pk.setBackground(Color.WHITE);
		ccbox_loc_loaisp_pk.setBounds(799, 249, 223, 30);
		this.add(ccbox_loc_loaisp_pk);
		
		ccbox_loc_loaisp_pk.addItem("None");
		for(int i=0;i<arr_loaisp.size();i++)
		{
			if(arr_loaisp.get(i).getPhanloai().equals("Ph??? Ki???n"))
				ccbox_loc_loaisp_pk.addItem(arr_loaisp.get(i).getTenloai());
		}
		
		 ccbox_loc_trangthai_pk = new JComboBox();
		 ccbox_loc_trangthai_pk.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		loc_sp_trangthai(model);
		 	}
		 });
		ccbox_loc_trangthai_pk.setModel(new DefaultComboBoxModel(new String[] {"None","??ang Ho???t ?????ng", "Kh??ng Ho???t ?????ng"}));
		ccbox_loc_trangthai_pk.setOpaque(false);
		ccbox_loc_trangthai_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ccbox_loc_trangthai_pk.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		ccbox_loc_trangthai_pk.setBackground(Color.WHITE);
		ccbox_loc_trangthai_pk.setBounds(1049, 249, 187, 30);
		this.add(ccbox_loc_trangthai_pk);
		
		JLabel lb_icon_search_name_pk = new JLabel("");
		lb_icon_search_name_pk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_icon_search_name_pk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loc_sp_ten(model);
			}
		});
		lb_icon_search_name_pk.setBounds(33, 245, 47, 44);
		this.add(lb_icon_search_name_pk);
		lb_icon_search_name_pk.setIcon(icon_search_name);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(Color.CYAN, 3));
		scrollPane_1.setBounds(0, 290, 1282, 320);
		this.add(scrollPane_1);
		
		table_pk = new JTable();
		table_pk.addMouseListener(new MouseAdapter() {
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
		
		table_pk.setModel(model);
		model.addColumn("T??n S???n Ph???m");
		model.addColumn("Gi?? B??n");
		model.addColumn("Gi?? Nh???p");
		model.addColumn("M?? SP");
		model.addColumn("Th??ng S???");
		model.addColumn("Lo???i SP");
		model.addColumn("S??? L?????ng");
		model.addColumn("Ch?? Th??ch");
		model.addColumn("Tr???ng Th??i");
		model.addColumn("M??u S???c");
		scrollPane_1.setViewportView(table_pk);
		table_pk.setSelectionBackground(new Color(185, 131, 255));
		table_pk.setRowHeight(27);
		table_pk.setGridColor(Color.BLACK);
		table_pk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table_pk.getTableHeader().setBackground(new Color(0x94DAFF)); //set backround c???a thanh header
		table_pk.getTableHeader().setReorderingAllowed(false); //khong cho resize cot	
		table_pk.getTableHeader().setFont(new Font("Time New Roman", Font.BOLD, 16)); //set font ch??? c??c thanh header collum
		updateTb(model);
		table_pk.setDefaultEditor(Object.class, null);//d??ng n??y s??? ko cho edit c??c ?? trong table
		
		
		ButtonAmination btnSave_pk = new ButtonAmination("L??u");
		btnSave_pk.addMouseListener(new MouseAdapter() {
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
						
					
					AccessoryDTO pk =new AccessoryDTO();
					boolean flag = false;
					do {
						try {
							pk.setMaSP(chuanHoaMasp(tf_masanpham_pk.getText()));
							pk.setTenSp(chuanHoa(tf_tensanpham_pk.getText()) );
							pk.setLoaiSp(cbbox_loaisp_pk.getSelectedItem().toString());
							pk.setSl(Integer.parseInt(tf_soluong_pk.getText()));
							pk.setGiaban(Integer.parseInt(tf_giaban_pk.getText()));
							pk.setGianhap(Integer.parseInt(tf_gianhap_pk.getText()));
							pk.setMau(chuanHoa(tf_mau_pk.getText()) );
							boolean status = false;
							if(cbbox_trangthai_pk.getSelectedItem().toString().equals("Ho???t ?????ng"))
							{
								status=true;
							}
							else if(cbbox_loaisp_pk.getSelectedItem().toString().equals("Kh??ng Ho???t ?????ng"))
							{
								status=false;
							}
							pk.setTrangthai(status);
							pk.setParameter(chuanHoa(tf_thongso_pk.getText()));
							pk.setNote(tf_ghichu_pk.getText());
							pk.setImage(anh);
							
							JOptionPane.showMessageDialog(null, AcBUS.addAC(pk), "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
							updateTb(model);
							reset_tf();
							anh=null;
							flag=false;
						} catch (Exception e2) {
							// TODO: handle exception
						//	System.out.println(e2);
							JOptionPane.showMessageDialog(null,"Vui l??ng nh???p ch??nh x??c d??? li???u s???n ph???m", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
							
						}
					} while (flag==true);	
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Vui l??ng nh???p ????? th??ng tin ????????", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				}
			}
				
				
				
				
				
				
			
		});
		btnSave_pk.setRadius(30);
		btnSave_pk.setForeground(Color.WHITE);
		btnSave_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave_pk.setBorderColor(new Color(23, 150, 218));
		btnSave_pk.setBackground(new Color(23, 150, 218));
		btnSave_pk.setBounds(9, 623, 120, 50);
		this.add(btnSave_pk);
		btnSave_pk.setIcon(icon_btn_save);
		
		ButtonAmination btnEdit_pk = new ButtonAmination("S???a");
		btnEdit_pk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				editPK();
			}
		});
		btnEdit_pk.setRadius(30);
		btnEdit_pk.setForeground(Color.WHITE);
		btnEdit_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnEdit_pk.setBorderColor(new Color(255, 157, 105));
		btnEdit_pk.setBackground(new Color(255, 157, 105));
		btnEdit_pk.setBounds(149, 622, 120, 52);
		this.add(btnEdit_pk);
		btnEdit_pk.setIcon(icon_btn_edit);
		
		ButtonAmination btnDelete_pk = new ButtonAmination("X??a");
		btnDelete_pk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DelPk();
			}
		});
		btnDelete_pk.setRadius(30);
		btnDelete_pk.setForeground(Color.WHITE);
		btnDelete_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete_pk.setBorderColor(new Color(251, 83, 124));
		btnDelete_pk.setBackground(new Color(251, 83, 124));
		btnDelete_pk.setBounds(289, 623, 120, 50);
		this.add(btnDelete_pk);
		btnDelete_pk.setIcon(icon_btn_delete);
		
		ButtonAmination btnReset_pk = new ButtonAmination("L??m m???i");
		btnReset_pk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset_tf();
				ArrayList<AccessoryDTO> arrAC=new ArrayList<AccessoryDTO>();
				arrAC= acDAO.getAllAS();
				int temp;
				if(arrAC.size()==0)
				{
					temp=1;
				}
				else
				{
					 temp=arrAC.get(arrAC.size()-1).getMaSP()+1;
				}
				tf_masanpham_pk.setText("PK"+temp);
			}
		});
		btnReset_pk.setRadius(30);
		btnReset_pk.setForeground(Color.WHITE);
		btnReset_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset_pk.setBorderColor(new Color(61, 188, 246));
		btnReset_pk.setBackground(new Color(61, 188, 246));
		btnReset_pk.setBounds(429, 623, 120, 50);
		this.add(btnReset_pk);
		btnReset_pk.setIcon(icon_btn_reset);
		
		ButtonAmination btn_openfile = new ButtonAmination("M??? H??nh");
		btn_openfile.setRadius(10);
		btn_openfile.setBounds(110, 210, 100, 30);
		add(btn_openfile);
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
						lb_avatar_pk.setIcon(newicon);
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
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "T\u00ECm theo gi\u00E1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(351, 233, 398, 55);
		add(panel);
		panel.setLayout(null);
		
		 cbbox_gia_pk = new JComboBox();
		cbbox_gia_pk.setBounds(21, 15, 110, 30);
		panel.add(cbbox_gia_pk);
		cbbox_gia_pk.setModel(new DefaultComboBoxModel(new String[] {"None","Gi?? B??n", "Gi?? Nh???p"}));
		cbbox_gia_pk.setOpaque(false);
		cbbox_gia_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_gia_pk.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_gia_pk.setBackground(Color.WHITE);
		
		 cbbox_khoanggia_pk = new JComboBox();
		cbbox_khoanggia_pk.setBounds(144, 15, 197, 30);
		panel.add(cbbox_khoanggia_pk);
		cbbox_khoanggia_pk.setModel(new DefaultComboBoxModel(new String[] {"None","D?????i 200.000", "200.000 ~ 400.000", "400.000 ~ 600.000", "600.000 ~ 800.000", "800.000 ~ 1.000.000", "Tr??n 1.000.000"}));
		cbbox_khoanggia_pk.setOpaque(false);
		cbbox_khoanggia_pk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbbox_khoanggia_pk.setBorder(new LineBorder(new Color(185, 131, 255), 3));
		cbbox_khoanggia_pk.setBackground(Color.WHITE);
		
		JLabel lb_icon_search_name_pk_1 = new JLabel("");
		lb_icon_search_name_pk_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_icon_search_name_pk_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loc_Kgia(model);
			}
		});
		lb_icon_search_name_pk_1.setBounds(351, 11, 47, 44);
		lb_icon_search_name_pk_1.setIcon(icon_search_name);
		panel.add(lb_icon_search_name_pk_1);

		
		
		//???n ko cho ch???nh c??c tf
				nonEditTf(false);
				//ko choedti masp
				tf_masanpham_pk.setEditable(false);
	}
	
	


	public boolean checkTF()
	{
		if(tf_masanpham_pk.getText().equals("")||tf_tensanpham_pk.getText().equals("") || tf_giaban_pk.getText().equals("") || tf_gianhap_pk.getText().equals("") ||
			tf_thongso_pk.getText().equals("") || tf_mau_pk.getText().equals("") ||tf_soluong_pk.getText().equals("") || tf_ghichu_pk.getText().equals("") || 
			cbbox_loaisp_pk.getSelectedIndex()==-1 ||cbbox_trangthai_pk.getSelectedIndex()==-1)
		{
			
			return false;
		}
		return true; //ok c??c tf ?????u ok
		
	}
	public void reset_tf()
	{
		tf_masanpham_pk.setText("");
		tf_tensanpham_pk.setText("");
		tf_giaban_pk.setText("");
		tf_gianhap_pk.setText("");
		tf_thongso_pk.setText("");
		tf_mau_pk.setText("");
		tf_soluong_pk.setText("");
		tf_ghichu_pk.setText("");
		cbbox_loaisp_pk.setSelectedIndex(-1);
		cbbox_trangthai_pk.setSelectedIndex(-1);
		lb_avatar_pk.setIcon(null);
		anh=null;
		updateTb(model);
		nonEditTf(true);
		cbbox_gia_pk.setSelectedIndex(0);
		cbbox_khoanggia_pk.setSelectedIndex(0);
		ccbox_loc_loaisp_pk.setSelectedIndex(0);
		ccbox_loc_trangthai_pk.setSelectedIndex(0);
		
	}
	public void updateTb(DefaultTableModel model)
	{
		model.setRowCount(0);
		ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
		arrAC =AcBUS.getallAS();
		for(int i=0;i<arrAC.size();i++)
		{
			AccessoryDTO pk=arrAC.get(i);
			String trangthai;
			if(pk.getTrangthai()==true)
			{
				trangthai="Ho???t ?????ng";
			}
			else
			{
				trangthai="Kh??ng Ho???t ?????ng";
			}
			
			model.addRow(new Object[] {pk.getTenSp(),pk.getGiaban(),pk.getGianhap(),pk.getMaSP(),pk.getParameter(),pk.getLoaiSp(),pk.getSl(),pk.getNote(),trangthai,pk.getMau() });
		}
	}
	
	public void showImg() throws IOException
	{
		ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
		arrAC =AcBUS.getallAS();
		 img_current = null;
		for(int i=0;i<arrAC.size();i++)
		{
			if(chuanHoaMasp(tf_masanpham_pk.getText()) ==arrAC.get(i).getMaSP())
			{
				img_current =(byte[]) arrAC.get(i).getImage();
			}
		}
		try {
			Image temp=ImageHelper.createImageFromByteArray(img_current,"jpg");
			ImageIcon icon =new ImageIcon(temp);
			lb_avatar_pk.setIcon(icon);
			anh=img_current;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}		
	}
	
	public void ShowshSelect()
	{
		nonEditTf(true);
		DefaultTableModel model_table =(DefaultTableModel) table_pk.getModel();
		int i=table_pk.getSelectedRow();
		String tensp=(String) model_table.getValueAt(i, 0);
		long giaban =(long) model_table.getValueAt(i, 1);
		long gianhap =(long) model_table.getValueAt(i, 2);
		int masp =(int) model_table.getValueAt(i, 3);
		String thongso=(String) model_table.getValueAt(i, 4);
		String loaisp=(String) model_table.getValueAt(i, 5);//no
		int sl =(int) model_table.getValueAt(i, 6);
		String ghichu =(String) model_table.getValueAt(i, 7);
		String trangthai=(String) model_table.getValueAt(i, 8);//no
		String mausac=(String) model_table.getValueAt(i, 9);
		
		
			
		tf_tensanpham_pk.setText(chuanHoa(tensp));
		tf_giaban_pk.setText(giaban+"");
		tf_gianhap_pk.setText(gianhap+"");
		tf_masanpham_pk.setText("PK"+masp+"");
		tf_thongso_pk.setText(chuanHoa(thongso));
		tf_ghichu_pk.setText(ghichu+"");
		tf_soluong_pk.setText(sl+"");
		tf_mau_pk.setText(chuanHoa(mausac));
		
		
		int trangthai_temp;
		if(trangthai.equals("Ho???t ?????ng"))
		{
			trangthai_temp=0;	
		}
		else {
			trangthai_temp=1;
		}
		cbbox_trangthai_pk.setSelectedIndex(trangthai_temp);
		for(int j=0;j<=cbbox_loaisp_pk.getItemCount();j++)
		{
			if(loaisp.equals(cbbox_loaisp_pk.getItemAt(j)))
			{
				cbbox_loaisp_pk.setSelectedIndex(j);
			}
		}	
	}
	
	
	public void DelPk()
	{
		if(checkTF())
		{
			ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
			arrAC =AcBUS.getallAS();
			int masp =Integer.parseInt(tf_masanpham_pk.getText());
			String tensp= tf_tensanpham_pk.getText();
			String loaisp= cbbox_loaisp_pk.getSelectedItem().toString();
			int sl= Integer.parseInt(tf_soluong_pk.getText());
			int giaban= Integer.parseInt(tf_giaban_pk.getText());
			int gianhap= Integer.parseInt(tf_gianhap_pk.getText());
			String mau =tf_mau_pk.getText();
			String trangthai=cbbox_trangthai_pk.getSelectedItem().toString();
			String thongso=tf_thongso_pk.getText();
			String ghichu= tf_ghichu_pk.getText();
		
			
		//	System.out.println(masp+tensp+loaisp+sl+giaban+gianhap+mau+trangthai+hang+size);
			for(int i=0;i<arrAC.size();i++)
			{
			//	System.out.println(arrAC.get(i).getTrangthai().equals(trangthai));
				if(arrAC.get(i).getMaSP()==masp&& arrAC.get(i).getTenSp().equals(tensp)  && arrAC.get(i).getLoaiSp().equals(loaisp)
						&& arrAC.get(i).getSl()==sl && arrAC.get(i).getGiaban()==giaban )
				{
					
					JOptionPane.showMessageDialog(null, AcBUS.DelAC(masp), "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
					updateTb(model);
					return;
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Nh???p ????? th??ng tin ??i anh b???n", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void editPK()
	{
		if(checkTF())
		{
			try {
				ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
				arrAC =AcBUS.getallAS();
				int masp =chuanHoaMasp(tf_masanpham_pk.getText());
				String tensp= tf_tensanpham_pk.getText();
				String loaisp= cbbox_loaisp_pk.getSelectedItem().toString();
				int sl= Integer.parseInt(tf_soluong_pk.getText());
				int giaban= Integer.parseInt(tf_giaban_pk.getText());
				int gianhap= Integer.parseInt(tf_gianhap_pk.getText());
				String mau =tf_mau_pk.getText();
				String trangthai=cbbox_trangthai_pk.getSelectedItem().toString();
				String thongso=tf_thongso_pk.getText();
				String ghichu= tf_ghichu_pk.getText();
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
				
				
				AccessoryDTO pk =new AccessoryDTO(masp,chuanHoa(tensp),loaisp,sl,giaban,gianhap,chuanHoa(mau),Btrangthai,chuanHoa(thongso),chuanHoa(ghichu),img);
				JOptionPane.showMessageDialog(null,AcBUS.editAC(pk), "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				updateTb(model);
				reset_tf();
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
			
			
			if(tf_loc_tensp_pk.getText().equals("")==false)
			{
				model.setRowCount(0);
				ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
				arrAC =AcBUS.getallAS();
				String tensp=chuanHoa(tf_loc_tensp_pk.getText()) ;
				for(int i=0;i<arrAC.size();i++)
				{
					AccessoryDTO pk=arrAC.get(i);
					String trangthai;
					if(pk.getTrangthai()==true)
					{
						trangthai="Ho???t ?????ng";
					}
					else
					{
						trangthai="Kh??ng Ho???t ?????ng";
					}
					if(arrAC.get(i).getTenSp().indexOf(tensp)!=-1)
					{
						
						model.addRow(new Object[] {pk.getTenSp(),pk.getGiaban(),pk.getGianhap(),pk.getMaSP(),pk.getParameter(),pk.getLoaiSp(),pk.getSl(),pk.getNote(),trangthai,pk.getMau() }); 
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

			
		
				int k=ccbox_loc_trangthai_pk.getSelectedIndex();//i=1 l?? ho???t ?????ng, 2 l?? ko hd
				model.setRowCount(0);
				ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
				arrAC =AcBUS.getallAS();
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
					for(int i=0;i<arrAC.size();i++)
					{
						AccessoryDTO pk=arrAC.get(i);
						String trangthai;
						if(pk.getTrangthai()==true)
						{
							trangthai="Ho???t ?????ng";
						}
						else
						{
							trangthai="Kh??ng Ho???t ?????ng";
						}
						if(arrAC.get(i).getTrangthai()==Btrangthai)
						{
							
							model.addRow(new Object[] {pk.getTenSp(),pk.getGiaban(),pk.getGianhap(),pk.getMaSP(),pk.getParameter(),pk.getLoaiSp(),pk.getSl(),pk.getNote(),trangthai,pk.getMau() }); 
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
			
				
				
				String tenlsp=(String) ccbox_loc_loaisp_pk.getSelectedItem();
				
				model.setRowCount(0);
				ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
				arrAC =AcBUS.getallAS();
				Boolean Btrangthai = null;
				
				if(tenlsp!="None")
				{
					for(int i=0;i<arrAC.size();i++)
					{
						
						AccessoryDTO pk=arrAC.get(i);
						String trangthai;
						if(pk.getTrangthai()==true)
						{
							trangthai="Ho???t ?????ng";
						}
						else
						{
							trangthai="Kh??ng Ho???t ?????ng";
						}
						if(arrAC.get(i).getLoaiSp().equals(tenlsp))
						{
							
							model.addRow(new Object[] {pk.getTenSp(),pk.getGiaban(),pk.getGianhap(),pk.getMaSP(),pk.getParameter(),pk.getLoaiSp(),pk.getSl(),pk.getNote(),trangthai,pk.getMau() }); 
						}
					}
				}
				else
				{
					updateTb(model);
				}
		}
	
//		none					0
//		D?????i 200.000			1
//		200.000 ~ 400.000		2
//		400.000 ~ 600.000		3
//		600.000 ~ 800.000		4
//		800.000 ~ 1.000.000		5
//		Tr??n 1.000.000			6
		
		
//		None		0
//		Gi?? B??n		1
//		Gi?? Nh???p	2
		//l???c theo kho???ng gi??
		public void loc_Kgia(DefaultTableModel model)
		{
			
			
			int pos_x=cbbox_gia_pk.getSelectedIndex();
			int pos_y=cbbox_khoanggia_pk.getSelectedIndex();
			model.setRowCount(0);
			if(pos_x==0 || pos_y==0)
			{
				updateTb(model);
				JOptionPane.showMessageDialog(null, "Vui L??ng Ch???n M???c Gi?? L???c", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
				arrAC =AcBUS.getallAS();
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
					
						
					for(int i=0;i<arrAC.size();i++)
					{
						
						AccessoryDTO pk=arrAC.get(i);
						String trangthai;
						if(pk.getTrangthai()==true)
						{
							trangthai="Ho???t ?????ng";
						}
						else
						{
							trangthai="Kh??ng Ho???t ?????ng";
						}
						if(arrAC.get(i).getGiaban()<=max && arrAC.get(i).getGiaban()>=min)
						{
							
							model.addRow(new Object[] {pk.getTenSp(),pk.getGiaban(),pk.getGianhap(),pk.getMaSP(),pk.getParameter(),pk.getLoaiSp(),pk.getSl(),pk.getNote(),trangthai,pk.getMau() }); 
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
						
					for(int i=0;i<arrAC.size();i++)
					{
						
						AccessoryDTO pk=arrAC.get(i);
						String trangthai;
						if(pk.getTrangthai()==true)
						{
							trangthai="Ho???t ?????ng";
						}
						else
						{
							trangthai="Kh??ng Ho???t ?????ng";
						}
						if(arrAC.get(i).getGianhap()<=max && arrAC.get(i).getGiaban()>=min)
						{
							
							model.addRow(new Object[] {pk.getTenSp(),pk.getGiaban(),pk.getGianhap(),pk.getMaSP(),pk.getParameter(),pk.getLoaiSp(),pk.getSl(),pk.getNote(),trangthai,pk.getMau() }); 
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "???? X??Y RA L???I", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				}
			}
			
	}	
	
	
		 public int chuanHoaMasp(String text)
		    {
		    	String [] splits =text.split("K");
		    	return Integer.parseInt(splits[1]);
		    }
	
		 public void nonEditTf(boolean sos)
		    {
		    	
		    	tf_tensanpham_pk.setEditable(sos);
				tf_giaban_pk.setEditable(sos);
				tf_gianhap_pk.setEditable(sos);
				tf_thongso_pk.setEditable(sos);
				tf_mau_pk.setEditable(sos);
				tf_soluong_pk.setEditable(sos);
				tf_ghichu_pk.setEditable(sos);

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
}

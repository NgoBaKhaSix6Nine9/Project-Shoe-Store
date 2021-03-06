package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Animation.ButtonAmination;
import Animation.JPanelAmination;
import BUS.Import_BLL;
import BUS.Supplier_BLL;
import DAO.DetailsAccessory_DAL;
import DTO.DetailsAccessory_DTO;
import DTO.DetailsShoes_DTO;
import DTO.Import_DTO;
import DTO.Supplier;
import BUS.AccessoryBUS;
import DTO.AccessoryDTO;
import BUS.ShoeBUS;
import DTO.ShoeDTO;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportShoesUI extends JFrame {

	private JPanel contentPane;
	ImageIcon iconBack=new ImageIcon(new ImageIcon("..\\ImportShoes\\src\\View\\Images\\back.png").getImage().getScaledInstance(60, 60, 0));
	ImageIcon iconRefresh=new ImageIcon(new ImageIcon("..\\ImportShoes\\src\\View\\Images\\refresh.png").getImage().getScaledInstance(20, 20, 0));
	ImageIcon iconAdd=new ImageIcon(new ImageIcon("..\\ImportShoes\\src\\View\\Images\\Add.png").getImage().getScaledInstance(25, 25, 0));
	ImageIcon iconSearch=new ImageIcon(new ImageIcon("..\\ImportShoes\\src\\View\\Images\\search.png").getImage().getScaledInstance(20, 20, 0));

	private JTable tableProduct;
	private JTable tableBillDetails;
	public DefaultTableModel modelBillDetails = new DefaultTableModel();
	public DefaultTableModel modelProduct = new DefaultTableModel();
	private JTextField tfIdSupp;
	private JTextField tfNameSupp;
	private JTextField tfNameStaff;
	private JTextField tfIdBill;
	private JTextField tfTotalBill;
	private JTextField tfPay;
	ButtonGroup group = new ButtonGroup();
	private JTextField tfSearchName;
	private JTextField tfSearchSize;
	JTextArea tfNote;
	ButtonAmination btnChooseSupp;
	ButtonAmination btnXoaSP;
	ButtonAmination btnThemSL;
	ButtonAmination btnCreateBill;
	ButtonAmination btnmntnLmMi;
	ShoeBUS shoesbus = new ShoeBUS();
	AccessoryBUS AcBUS = new AccessoryBUS();
	Import_BLL importBUS = new Import_BLL();
	Supplier_BLL suppBLL = new Supplier_BLL();
	private static long tongtienpn=0;
	private static String sanphamcc=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ImportShoesUI frame = new ImportShoesUI();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImportShoesUI(FormMenu menu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239,255,253));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(153,254,255));
		panelTitle.setBounds(0, 0, 1288, 70);
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Nh???p h??ng");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(531, 13, 225, 43);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panelTitle.add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(iconBack);
		lblNewLabel.setBounds(1227, 0, 60, 70);
		panelTitle.add(lblNewLabel);
		
		JPanelAmination panel = new JPanelAmination();
		panel.setRadiusBottomLeft(15);
		panel.setRadiusBottomRight(15);
		panel.setRadiusTopLeft(15);
		panel.setRadiusTopRight(15);
		panel.setColor_BG(new Color(185,131,255));
		panel.setBounds(10, 99, 625, 197);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanelAmination panel_2 = new JPanelAmination();
		panel_2.setBounds(4, 4, 617, 189);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setRadiusTopRight(15);
		panel_2.setRadiusTopLeft(15);
		panel_2.setRadiusBottomRight(15);
		panel_2.setRadiusBottomLeft(15);
		panel_2.setColor_BG(Color.WHITE);
		
		JScrollPane scrollPaneBillDetails = new JScrollPane();
		scrollPaneBillDetails.setBounds(5, 5, 607, 180);
		panel_2.add(scrollPaneBillDetails);
		
		tableBillDetails = new JTable();
		modelBillDetails.addColumn("M?? SP");
		modelBillDetails.addColumn("T??n SP");
		modelBillDetails.addColumn("S??? l?????ng");
		modelBillDetails.addColumn("Lo???i");
		modelBillDetails.addColumn("Size");
		modelBillDetails.addColumn("Gi?? nh???p");
		tableBillDetails.setModel(modelBillDetails);
		tableBillDetails.getColumnModel().getColumn(0).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableBillDetails.getColumnModel().getColumn(1).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableBillDetails.getColumnModel().getColumn(2).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableBillDetails.getColumnModel().getColumn(3).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(3).setPreferredWidth(20);
		tableBillDetails.getColumnModel().getColumn(4).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(4).setPreferredWidth(0);
		tableBillDetails.getColumnModel().getColumn(5).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableBillDetails.setFillsViewportHeight(true);
		tableBillDetails.setShowVerticalLines(true);
		tableBillDetails.setSelectionBackground(new Color(185, 131, 255));
		tableBillDetails.setDragEnabled(true);
		tableBillDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tableBillDetails.getTableHeader().setFont(new Font("Time New Roman", Font.PLAIN, 18));
		tableBillDetails.getTableHeader().setBackground(new Color(0x94DAFF));
		tableBillDetails.getTableHeader().setForeground(Color.black);
		tableBillDetails.getTableHeader().setReorderingAllowed(false); //khong cho resize cot
		tableBillDetails.setRowHeight(25);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tableBillDetails.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableBillDetails.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableBillDetails.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableBillDetails.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tableBillDetails.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tableBillDetails.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		scrollPaneBillDetails.setViewportView(tableBillDetails);
		tableBillDetails.setDefaultEditor(Object.class, null);
		
		JPanelAmination panel_1 = new JPanelAmination();
		panel_1.setRadiusBottomLeft(15);
		panel_1.setRadiusBottomRight(15);
		panel_1.setRadiusTopLeft(15);
		panel_1.setRadiusTopRight(15);
		panel_1.setColor_BG(new Color(185,131,255));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 331, 730, 426);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanelAmination panel_2_1 = new JPanelAmination();
		panel_2_1.setLayout(null);
		panel_2_1.setRadiusTopRight(15);
		panel_2_1.setRadiusTopLeft(15);
		panel_2_1.setRadiusBottomRight(15);
		panel_2_1.setRadiusBottomLeft(15);
		panel_2_1.setColor_BG(Color.WHITE);
		panel_2_1.setBounds(4, 4, 722, 418);
		panel_1.add(panel_2_1);
		
		JScrollPane scrollPaneProduct = new JScrollPane();
		scrollPaneProduct.setBounds(5, 62, 711, 351);
		panel_2_1.add(scrollPaneProduct);
		
		tableProduct = new JTable();
		modelProduct.addColumn("M?? SP");
		modelProduct.addColumn("T??n SP");
		modelProduct.addColumn("Lo???i");
		modelProduct.addColumn("Size");
		modelProduct.addColumn("Gi?? nh???p");
		modelProduct.addColumn("S??? l?????ng");
		tableProduct.setModel(modelProduct);
		tableProduct.getColumnModel().getColumn(0).setResizable(false);
		tableProduct.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableProduct.getColumnModel().getColumn(1).setResizable(false);
		tableProduct.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableProduct.getColumnModel().getColumn(2).setResizable(false);
		tableProduct.getColumnModel().getColumn(2).setPreferredWidth(20);
		tableProduct.getColumnModel().getColumn(3).setResizable(false);
		tableProduct.getColumnModel().getColumn(3).setPreferredWidth(0);
		tableProduct.getColumnModel().getColumn(4).setResizable(false);
		tableProduct.getColumnModel().getColumn(4).setPreferredWidth(40);
		tableProduct.getColumnModel().getColumn(5).setResizable(false);
		tableProduct.getColumnModel().getColumn(5).setPreferredWidth(10);
		tableProduct.setFillsViewportHeight(true);
		tableProduct.setShowVerticalLines(true);
		tableProduct.setSelectionBackground(new Color(185, 131, 255));
		tableProduct.setDragEnabled(true);
		tableProduct.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tableProduct.getTableHeader().setFont(new Font("Time New Roman", Font.PLAIN, 18));
		tableProduct.getTableHeader().setBackground(new Color(0x94DAFF));
		tableProduct.getTableHeader().setForeground(Color.black);
		tableProduct.getTableHeader().setReorderingAllowed(false); //khong cho resize cot
		tableProduct.setRowHeight(25);
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
		tableProduct.getColumnModel().getColumn(0).setCellRenderer(centerRenderer1);
		tableProduct.getColumnModel().getColumn(1).setCellRenderer(centerRenderer1);
		tableProduct.getColumnModel().getColumn(2).setCellRenderer(centerRenderer1);
		tableProduct.getColumnModel().getColumn(3).setCellRenderer(centerRenderer1);
		tableProduct.getColumnModel().getColumn(4).setCellRenderer(centerRenderer1);
		tableProduct.getColumnModel().getColumn(5).setCellRenderer(centerRenderer1);
		scrollPaneProduct.setViewportView(tableProduct);
		tableProduct.setDefaultEditor(Object.class, null);
		loadSP();
		
		ButtonAmination btnSearchName = new ButtonAmination("");
		btnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfSearchName.getText().equals("")||tfSearchName.getText().equals("T??n s???n ph???m")) {
					JOptionPane.showMessageDialog(getContentPane(), "C???n nh???p t??n s???n ph???m c???n t??m!");
				} else {
					ArrayList<ShoeDTO> arr = new ArrayList<ShoeDTO>();
					arr = shoesbus.getallShoe();
					ArrayList<AccessoryDTO> arr1 = new ArrayList<AccessoryDTO>();
					arr1 = AcBUS.getallAS();
					Boolean flag = false;
					modelProduct.setRowCount(0);
					for(int i = 0; i<arr.size();i++) {
						if(arr.get(i).getTenSp().indexOf(chuanhoa(tfSearchName.getText()))!=-1) {
							ShoeDTO shoes = arr.get(i);
							String id = "GI"+shoes.getMaSP();
							String name = shoes.getTenSp();
							float size = shoes.getSize();
							long gianhap = shoes.getGianhap();
							int sl = shoes.getSl();
							modelProduct.addRow(new Object[] {id,name,"Gi??y",size,gianhap,sl});
							flag = true;
						}
					}
					for(int i = 0; i<arr1.size();i++) {
						if(arr1.get(i).getTenSp().indexOf(chuanhoa(tfSearchName.getText()))!=-1) {
							AccessoryDTO acc = arr1.get(i);
							String id = "PK"+acc.getMaSP();
							String name = acc.getTenSp();
							String size = acc.getParameter();
							long gianhap = acc.getGianhap();
							int sl = acc.getSl();
							modelProduct.addRow(new Object[] {id,name,"Ph??? ki???n",size,gianhap,sl});
							flag = true;
						}
					}
					if(!flag) {
						JOptionPane.showMessageDialog(contentPane, "Kh??ng t??m th???y nh?? cung c???p!");
					}
				}
			}
		});
		btnSearchName.setRadius(15);
		btnSearchName.setBounds(325, 20, 55, 32);
		panel_2_1.add(btnSearchName);
		btnSearchName.setBorderColor(new Color(185, 131, 255));
		btnSearchName.setIcon(iconSearch);
		btnSearchName.setBackground(new Color(185, 131, 255));
		
		JPanelAmination panel_2_8_1_1 = new JPanelAmination();
		panel_2_8_1_1.setLayout(null);
		panel_2_8_1_1.setRadiusTopRight(15);
		panel_2_8_1_1.setRadiusTopLeft(15);
		panel_2_8_1_1.setRadiusBottomRight(15);
		panel_2_8_1_1.setRadiusBottomLeft(15);
		panel_2_8_1_1.setColor_BG(new Color(185, 131, 255));
		panel_2_8_1_1.setBounds(22, 20, 315, 32);
		panel_2_1.add(panel_2_8_1_1);
		
		JPanelAmination panel_2_8_1_1_1 = new JPanelAmination();
		panel_2_8_1_1_1.setLayout(null);
		panel_2_8_1_1_1.setRadiusTopRight(15);
		panel_2_8_1_1_1.setRadiusTopLeft(15);
		panel_2_8_1_1_1.setRadiusBottomRight(15);
		panel_2_8_1_1_1.setRadiusBottomLeft(15);
		panel_2_8_1_1_1.setColor_BG(Color.WHITE);
		panel_2_8_1_1_1.setBounds(2, 2, 301, 28);
		panel_2_8_1_1.add(panel_2_8_1_1_1);
		
		tfSearchName = new JTextField();
		tfSearchName.setForeground(Color.LIGHT_GRAY);
		tfSearchName.setBorder(null);
		tfSearchName.setText("T??n s???n ph???m");
		tfSearchName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfSearchName.setBounds(5, 1, 289, 27);
		panel_2_8_1_1_1.add(tfSearchName);
		tfSearchName.setColumns(10);
		tfSearchName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfSearchName.getText().equals("T??n s???n ph???m")) {
					tfSearchName.setText("");
				}
				else {
					tfSearchName.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfSearchName.getText().equals("")) {
					tfSearchName.setText("T??n s???n ph???m");
					tfSearchName.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		
		ButtonAmination btnSearchSize = new ButtonAmination("");
		btnSearchSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfSearchSize.getText().equals("")||tfSearchSize.getText().equals("Size")) {
					JOptionPane.showMessageDialog(getContentPane(), "C???n nh???p size c???a s???n ph???m c???n t??m!");
				} else {
					try {
					ArrayList<ShoeDTO> arr = new ArrayList<ShoeDTO>();
					arr = shoesbus.getallShoe();
					ArrayList<AccessoryDTO> arr1 = new ArrayList<AccessoryDTO>();
					arr1 = AcBUS.getallAS();
					Boolean flag = false;
					modelProduct.setRowCount(0);
					for(int i = 0; i<arr.size();i++) {
						if(arr.get(i).getSize()==Float.parseFloat(tfSearchSize.getText())) {
							ShoeDTO shoes = arr.get(i);
							String id = "GI"+shoes.getMaSP();
							String name = shoes.getTenSp();
							float size = shoes.getSize();
							long gianhap = shoes.getGianhap();
							int sl = shoes.getSl();
							modelProduct.addRow(new Object[] {id,name,"Gi??y",size,gianhap,sl});
							flag = true;
						}
					}
					if(!flag) {
						JOptionPane.showMessageDialog(contentPane, "Kh??ng t??m th???y nh?? cung c???p!");
					}
					}catch(NumberFormatException ex1) {
						JOptionPane.showMessageDialog(getContentPane(), "Size nh???p v??o kh??ng h???p l???");
					}
				}
			}
		});
		btnSearchSize.setIcon(iconSearch);
		btnSearchSize.setRadius(15);
		btnSearchSize.setBorderColor(new Color(185, 131, 255));
		btnSearchSize.setBackground(new Color(185, 131, 255));
		btnSearchSize.setBounds(490, 20, 55, 32);
		panel_2_1.add(btnSearchSize);
		
		JPanelAmination panel_2_8_1_1_2 = new JPanelAmination();
		panel_2_8_1_1_2.setLayout(null);
		panel_2_8_1_1_2.setRadiusTopRight(15);
		panel_2_8_1_1_2.setRadiusTopLeft(15);
		panel_2_8_1_1_2.setRadiusBottomRight(15);
		panel_2_8_1_1_2.setRadiusBottomLeft(15);
		panel_2_8_1_1_2.setColor_BG(new Color(185, 131, 255));
		panel_2_8_1_1_2.setBounds(405, 20, 104, 32);
		panel_2_1.add(panel_2_8_1_1_2);
		
		JPanelAmination panel_2_8_1_1_1_1 = new JPanelAmination();
		panel_2_8_1_1_1_1.setLayout(null);
		panel_2_8_1_1_1_1.setRadiusTopRight(15);
		panel_2_8_1_1_1_1.setRadiusTopLeft(15);
		panel_2_8_1_1_1_1.setRadiusBottomRight(15);
		panel_2_8_1_1_1_1.setRadiusBottomLeft(15);
		panel_2_8_1_1_1_1.setColor_BG(Color.WHITE);
		panel_2_8_1_1_1_1.setBounds(2, 2, 83, 28);
		panel_2_8_1_1_2.add(panel_2_8_1_1_1_1);
		
		tfSearchSize = new JTextField();
		tfSearchSize.setText("Size");
		tfSearchSize.setForeground(Color.LIGHT_GRAY);
		tfSearchSize.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfSearchSize.setColumns(10);
		tfSearchSize.setBorder(null);
		tfSearchSize.setBounds(5, 1, 72, 27);
		panel_2_8_1_1_1_1.add(tfSearchSize);
		
		btnThemSL = new ButtonAmination("Ch???n KH");
		btnThemSL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcthd(tableProduct, modelBillDetails, tableBillDetails);
			}
		});
		btnThemSL.setText("Th??m SL");
		btnThemSL.setRadius(15);
		btnThemSL.setForeground(Color.WHITE);
		btnThemSL.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnThemSL.setBackground(new Color(185, 131, 255));
		btnThemSL.setBounds(591, 20, 98, 33);
		btnThemSL.setVisible(false);
		panel_2_1.add(btnThemSL);
		tfSearchSize.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfSearchSize.getText().equals("Size")) {
					tfSearchSize.setText("");
				}
				else {
					tfSearchSize.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfSearchSize.getText().equals("")) {
					tfSearchSize.setText("Size");
					tfSearchSize.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		
		JPanelAmination panel_7 = new JPanelAmination();
		panel_7.setLayout(null);
		panel_7.setRadiusTopRight(15);
		panel_7.setRadiusTopLeft(15);
		panel_7.setRadiusBottomRight(15);
		panel_7.setRadiusBottomLeft(15);
		panel_7.setColor_BG(new Color(185, 131, 255));
		panel_7.setBounds(788, 0, 240, 102);
		panel_1.add(panel_7);
		
		JPanelAmination panel_2_6 = new JPanelAmination();
		panel_2_6.setLayout(null);
		panel_2_6.setRadiusTopRight(15);
		panel_2_6.setRadiusTopLeft(15);
		panel_2_6.setRadiusBottomRight(15);
		panel_2_6.setRadiusBottomLeft(15);
		panel_2_6.setColor_BG(Color.WHITE);
		panel_2_6.setBounds(4, 4, 617, 189);
		panel_7.add(panel_2_6);
		
		JLabel lblTitle1 = new JLabel("Chi ti???t phi???u nh???p");
		lblTitle1.setForeground(new Color(185,131,255));
		lblTitle1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitle1.setBounds(15, 81, 130, 18);
		contentPane.add(lblTitle1);
		
		JLabel lblTitle2 = new JLabel("Th??ng tin s???n ph???m");
		lblTitle2.setForeground(new Color(185, 131, 255));
		lblTitle2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitle2.setBounds(15, 311, 130, 18);
		contentPane.add(lblTitle2);
		
		JPanelAmination panel_3 = new JPanelAmination();
		panel_3.setLayout(null);
		panel_3.setRadiusTopRight(15);
		panel_3.setRadiusTopLeft(15);
		panel_3.setRadiusBottomRight(15);
		panel_3.setRadiusBottomLeft(15);
		panel_3.setColor_BG(new Color(185, 131, 255));
		panel_3.setBounds(749, 99, 529, 658);
		contentPane.add(panel_3);
		
		JPanelAmination panel_2_2 = new JPanelAmination();
		panel_2_2.setLayout(null);
		panel_2_2.setRadiusTopRight(15);
		panel_2_2.setRadiusTopLeft(15);
		panel_2_2.setRadiusBottomRight(15);
		panel_2_2.setRadiusBottomLeft(15);
		panel_2_2.setColor_BG(Color.WHITE);
		panel_2_2.setBounds(4, 4, 521, 650);
		panel_3.add(panel_2_2);
		
		JPanelAmination panel_4 = new JPanelAmination();
		panel_4.setBounds(10, 11, 501, 77);
		panel_2_2.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setRadiusTopRight(15);
		panel_4.setRadiusTopLeft(15);
		panel_4.setRadiusBottomRight(15);
		panel_4.setRadiusBottomLeft(15);
		panel_4.setColor_BG(new Color(185, 131, 255));
		
		JPanelAmination panel_2_3 = new JPanelAmination();
		panel_2_3.setBounds(2, 2, 497, 73);
		panel_4.add(panel_2_3);
		panel_2_3.setLayout(null);
		panel_2_3.setRadiusTopRight(15);
		panel_2_3.setRadiusTopLeft(15);
		panel_2_3.setRadiusBottomRight(15);
		panel_2_3.setRadiusBottomLeft(15);
		panel_2_3.setColor_BG(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("M?? nh?? cung c???p:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(8, 8, 176, 24);
		panel_2_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("T??n nh?? cung c???p:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(8, 42, 176, 24);
		panel_2_3.add(lblNewLabel_1_1);
		
		tfIdSupp = new JTextField();
		tfIdSupp.setDisabledTextColor(Color.BLACK);
		tfIdSupp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfIdSupp.setEditable(false);
		tfIdSupp.setBackground(Color.WHITE);
		tfIdSupp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfIdSupp.setBounds(180, 8, 176, 24);
		panel_2_3.add(tfIdSupp);
		tfIdSupp.setColumns(10);
		
		tfNameSupp = new JTextField();
		tfNameSupp.setDisabledTextColor(Color.BLACK);
		tfNameSupp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfNameSupp.setEditable(false);
		tfNameSupp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfNameSupp.setColumns(10);
		tfNameSupp.setBounds(180, 42, 200, 24);
		tfNameSupp.setBackground(Color.WHITE);
		panel_2_3.add(tfNameSupp);
		
		InfoSupplierUI ifncc =new InfoSupplierUI(this);
		
		btnChooseSupp = new ButtonAmination("Ch???n KH");
		btnChooseSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ifncc.setVisible(true);
				setVisible(false);
			}
		});
		btnChooseSupp.setText("Ch???n NCC");
		btnChooseSupp.setForeground(Color.WHITE);
		btnChooseSupp.setRadius(15);
		btnChooseSupp.setBackground(new Color(185,131,255));
		btnChooseSupp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnChooseSupp.setBounds(390, 19, 98, 33);
		btnChooseSupp.setVisible(false);
		panel_2_3.add(btnChooseSupp);
		
		JPanelAmination panel_5 = new JPanelAmination();
		panel_5.setLayout(null);
		panel_5.setRadiusTopRight(15);
		panel_5.setRadiusTopLeft(15);
		panel_5.setRadiusBottomRight(15);
		panel_5.setRadiusBottomLeft(15);
		panel_5.setColor_BG(new Color(185, 131, 255));
		panel_5.setBounds(10, 99, 501, 40);
		panel_2_2.add(panel_5);
		
		JPanelAmination panel_2_4 = new JPanelAmination();
		panel_2_4.setBounds(2, 2, 497, 36);
		panel_5.add(panel_2_4);
		panel_2_4.setLayout(null);
		panel_2_4.setRadiusTopRight(15);
		panel_2_4.setRadiusTopLeft(15);
		panel_2_4.setRadiusBottomRight(15);
		panel_2_4.setRadiusBottomLeft(15);
		panel_2_4.setColor_BG(Color.WHITE);
		
		JLabel lblNewLabel_1_2 = new JLabel("T??n nh??n vi??n:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(8, 6, 131, 24);
		panel_2_4.add(lblNewLabel_1_2);
		
		tfNameStaff = new JTextField();
		tfNameStaff.setDisabledTextColor(Color.BLACK);
		tfNameStaff.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfNameStaff.setEditable(false);
		tfNameStaff.setBackground(Color.WHITE);
		tfNameStaff.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfNameStaff.setBounds(180, 7, 307, 24);
		panel_2_4.add(tfNameStaff);
		tfNameStaff.setColumns(10);
		//Cho tr?????c t??n
		
		
		JPanelAmination panel_6 = new JPanelAmination();
		panel_6.setLayout(null);
		panel_6.setRadiusTopRight(15);
		panel_6.setRadiusTopLeft(15);
		panel_6.setRadiusBottomRight(15);
		panel_6.setRadiusBottomLeft(15);
		panel_6.setColor_BG(new Color(185, 131, 255));
		panel_6.setBounds(10, 150, 501, 380);
		panel_2_2.add(panel_6);
		
		JPanelAmination panel_2_5 = new JPanelAmination();
		panel_2_5.setLayout(null);
		panel_2_5.setRadiusTopRight(15);
		panel_2_5.setRadiusTopLeft(15);
		panel_2_5.setRadiusBottomRight(15);
		panel_2_5.setRadiusBottomLeft(15);
		panel_2_5.setColor_BG(Color.WHITE);
		panel_2_5.setBounds(2, 2, 497, 376);
		panel_6.add(panel_2_5);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("M?? phi???u nh???p:");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(8, 8, 163, 24);
		panel_2_5.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("T???ng ti???n:");
		lblNewLabel_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1.setBounds(8, 47, 94, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Thanh to??n:");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1_2.setBounds(8, 86, 131, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1_2);
		
		JLabel lblNewLabel_1_2_1_1_6 = new JLabel("Ghi ch??:");
		lblNewLabel_1_2_1_1_6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1_6.setBounds(8, 128, 76, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1_6);
		
		tfIdBill = new JTextField();
		tfIdBill.setForeground(Color.RED);
		tfIdBill.setText("Vui l??ng t???o!");
		tfIdBill.setEditable(false);
		tfIdBill.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfIdBill.setDisabledTextColor(Color.BLACK);
		tfIdBill.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfIdBill.setEditable(false);
		tfIdBill.setBackground(Color.WHITE);
		tfIdBill.setBounds(220, 9, 151, 24);
		panel_2_5.add(tfIdBill);
		tfIdBill.setColumns(10);
		
		tfTotalBill = new JTextField();	
		tfTotalBill.setText("0");
		tfTotalBill.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfTotalBill.setDisabledTextColor(Color.BLACK);
		tfTotalBill.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfTotalBill.setEditable(false);
		tfTotalBill.setBackground(Color.WHITE);
		tfTotalBill.setColumns(10);
		tfTotalBill.setBounds(220, 48, 163, 24);
		panel_2_5.add(tfTotalBill);
		
		tfPay = new JTextField();
		tfPay.setText("0");
		tfPay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfPay.setDisabledTextColor(Color.BLACK);
		tfPay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfPay.setEditable(false);
		tfPay.setBackground(Color.WHITE);
		tfPay.setEditable(false);
		tfPay.setColumns(10);
		tfPay.setBounds(220, 87, 163, 24);
		panel_2_5.add(tfPay);
		
		JLabel lblNewLabel_2 = new JLabel("VN??");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(420, 50, 43, 19);
		panel_2_5.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("VN??");
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(420, 88, 43, 19);
		panel_2_5.add(lblNewLabel_2_1_1);;;
		
		JPanelAmination panel_2_7 = new JPanelAmination();
		panel_2_7.setLayout(null);
		panel_2_7.setRadiusTopRight(15);
		panel_2_7.setRadiusTopLeft(15);
		panel_2_7.setRadiusBottomRight(15);
		panel_2_7.setRadiusBottomLeft(15);
		panel_2_7.setColor_BG(new Color(185,131,255));
		panel_2_7.setBounds(95, 120, 393, 86);
		panel_2_5.add(panel_2_7);
		
		JPanelAmination panel_2_7_1 = new JPanelAmination();
		panel_2_7_1.setLayout(null);
		panel_2_7_1.setRadiusTopRight(15);
		panel_2_7_1.setRadiusTopLeft(15);
		panel_2_7_1.setRadiusBottomRight(15);
		panel_2_7_1.setRadiusBottomLeft(15);
		panel_2_7_1.setColor_BG(Color.WHITE);
		panel_2_7_1.setBounds(2, 2, 389, 82);
		panel_2_7.add(panel_2_7_1);
		
		tfNote = new JTextArea();
		tfNote.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfNote.setBounds(5, 5, 379, 70);
		tfNote.setBorder(null);
		tfNote.setLineWrap(true);// thiet lap tach tu xuong dong
		tfNote.setWrapStyleWord(true);
		panel_2_7_1.add(tfNote);
		
		btnCreateBill = new ButtonAmination();
		btnCreateBill.setBackground(new Color(251,83,124));
		btnCreateBill.setBorderColor(new Color(251,83,124));
		btnCreateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnChooseSupp.setVisible(true);
				btnThemSL.setVisible(true);
				btnXoaSP.setVisible(true);
				tfIdBill.setVisible(true);
				setAutomapn();
			}
		});
		btnCreateBill.setIcon(iconAdd);
		btnCreateBill.setBounds(20, 541, 116, 42);
		panel_2_2.add(btnCreateBill);
		btnCreateBill.setText("T???o");
		btnCreateBill.setRadius(15);
		btnCreateBill.setForeground(Color.WHITE);
		btnCreateBill.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnmntnLmMi = new ButtonAmination();
		btnmntnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rs();
			}
		});
		btnmntnLmMi.setIcon(iconRefresh);
		btnmntnLmMi.setText("L??m m???i");
		btnmntnLmMi.setRadius(15);
		btnmntnLmMi.setForeground(Color.WHITE);
		btnmntnLmMi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnmntnLmMi.setBackground(new Color(61, 188, 246));
		btnmntnLmMi.setBorderColor(new Color(61, 188, 246));
		btnmntnLmMi.setBounds(383, 541, 116, 42);
		panel_2_2.add(btnmntnLmMi);
		
		ButtonAmination btnCreateBill_1 = new ButtonAmination();
		btnCreateBill_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tfIdBill.getText().equals("Vui l??ng t???o!")) {
						JOptionPane.showMessageDialog(getContentPane(),"Vui l??ng t???o ????n!");
					} 
					else if(tfIdSupp.getText().trim().equals("") || tfNameSupp.getText().trim().equals(""))
						JOptionPane.showMessageDialog(getContentPane(),"Vui l??ng ch???n nh?? cung c???p");
					else if(tfTotalBill.getText().equals("0"))
						JOptionPane.showMessageDialog(getContentPane(),"Vui l??ng ch???n s???n ph???m nh???p");
					else {
						Import_DTO imp = new Import_DTO();
						int ImportID = chuanHoaMapn(tfIdBill.getText());
						ArrayList<DetailsShoes_DTO> arrDetailsShoes = new ArrayList<DetailsShoes_DTO>(); 
						for(int i=0;i<tableBillDetails.getModel().getRowCount();i++) {
							if (tableBillDetails.getModel().getValueAt(i,3).toString().equals("Gi??y")) {			                	
								int shoesID = chuanHoaMagiay(modelBillDetails.getValueAt(i, 0).toString());
								int sl = (int) modelBillDetails.getValueAt(i, 2);
								long price = (long) modelBillDetails.getValueAt(i, 5);	
				                DetailsShoes_DTO shoesDetails = new DetailsShoes_DTO(ImportID,shoesID,sl,price);
				                arrDetailsShoes.add(shoesDetails);             
							}
				        }
						ArrayList<DetailsAccessory_DTO> arrDetailsAccessory = new ArrayList<DetailsAccessory_DTO>();
						for(int i=0;i<tableBillDetails.getModel().getRowCount();i++) {
							if (tableBillDetails.getModel().getValueAt(i,3).toString().equals("Ph??? Ki???n")) {			                	
								int accessoryID = chuanHoaMapk(modelBillDetails.getValueAt(i, 0).toString());
								int sl = (int) modelBillDetails.getValueAt(i, 2);
								long price = (long) modelBillDetails.getValueAt(i, 5);	
				                DetailsAccessory_DTO accessoryDetails = new DetailsAccessory_DTO(ImportID,accessoryID,sl,price);
				                arrDetailsAccessory.add(accessoryDetails);
							}
				        }
						imp.setImportID(ImportID);
						imp.setPersonnelID(menu.manv);	//CH?? ?? M?? NH??N VI??N
						imp.setTotal(tongtienpn);
						imp.setNote(tfNote.getText());
						imp.setSupplierID(chuanHoaMancc(tfIdSupp.getText()));
						long millis=System.currentTimeMillis();   
						java.sql.Date date=new java.sql.Date(millis);
						imp.setImportDay(date);
						Boolean flag = true;
						if(importBUS.addImport1(imp)==false)
							JOptionPane.showMessageDialog(getContentPane(), "Thanh to??n th???t b???i");
						if(arrDetailsShoes.size()==0) {
							if(importBUS.addImportAccessory(arrDetailsAccessory)) {
								if(importBUS.updateSLPK(arrDetailsAccessory))
									JOptionPane.showMessageDialog(getContentPane(), "Thanh to??n th??nh c??ng");
								else 
									JOptionPane.showMessageDialog(getContentPane(), "Thanh to??n th???t b???i");
							}
						}
						else if(arrDetailsAccessory.size()==0) {
							if(importBUS.addImportShoes(arrDetailsShoes)) {
								if(importBUS.updateSLGIAY(arrDetailsShoes))
									JOptionPane.showMessageDialog(getContentPane(), "Thanh to??n th??nh c??ng");
								else 
									JOptionPane.showMessageDialog(getContentPane(), "Thanh to??n th???t b???i");
							}
						}
						else {
							if(importBUS.addImportBoth(arrDetailsShoes, arrDetailsAccessory)){
								if(importBUS.updateSLGIAY(arrDetailsShoes)&&importBUS.updateSLPK(arrDetailsAccessory))
									JOptionPane.showMessageDialog(getContentPane(), "Thanh to??n th??nh c??ng");
								else 
									JOptionPane.showMessageDialog(getContentPane(), "Thanh to??n th???t b???i");
							}
						}
						rs();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getContentPane(), "Th??ng tin kh??ng h???p l???");
				}
			}
		});
		btnCreateBill_1.setText("Thanh to??n");
		btnCreateBill_1.setRadius(15);
		btnCreateBill_1.setForeground(Color.WHITE);
		btnCreateBill_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCreateBill_1.setBackground(new Color(185, 131, 255));
		btnCreateBill_1.setBounds(149, 585, 223, 56);
		panel_2_2.add(btnCreateBill_1);
		
		JLabel lblThngTinHa = new JLabel("Th??ng tin nh???p h??ng");
		lblThngTinHa.setForeground(new Color(185, 131, 255));
		lblThngTinHa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThngTinHa.setBounds(754, 80, 142, 18);
		contentPane.add(lblThngTinHa);
		
		btnXoaSP = new ButtonAmination("Ch???n KH");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modelBillDetails.getRowCount()==0)
					JOptionPane.showMessageDialog(contentPane, "C???n ch???n s???n ph???m nh???p");
				else {
					int x=tableBillDetails.getSelectedRow();
					DeleSpCthd(x, tableBillDetails);
				}
			}
		});
		btnXoaSP.setText("X??a SP");
		btnXoaSP.setRadius(15);
		btnXoaSP.setForeground(Color.WHITE);
		btnXoaSP.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnXoaSP.setBackground(new Color(185, 131, 255));
		btnXoaSP.setBounds(640, 186, 98, 33);
		btnXoaSP.setVisible(false);
		contentPane.add(btnXoaSP);
		
		
		
		
		
		
		tfNameStaff.setText(menu.tennv);
	}
	public void setIDnamencc(String mancc, String tenncc, String spcc)
	{
		tfIdSupp.setText(mancc);
		tfNameSupp.setText(tenncc);
		if(spcc.equals("Gi??y")) 
			loadShoes();
		else if(spcc.equals("Ph??? ki???n"))
			loadPK();
		else
			loadSP();
		sanphamcc = spcc;
	}
	
	public void addcthd(JTable TbPd,DefaultTableModel model_cthd,JTable TbDPd)
	{	
		int x=TbPd.getSelectedRow();//-1 l?? s??? ko ch???n g??
		if(x==-1)
		{
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
		else {
			String sl_s=JOptionPane.showInputDialog(null, "Vui l??ng nh???p s??? l?????ng", "TH??NG B??O",JOptionPane.ERROR_MESSAGE) ;
			if(sl_s==null)
			{
				return;
			}
			else
			{
				try {
					int sl=Integer.parseInt(sl_s);
					int tongtien = 0;
					if(sl<=0)
					{
						JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????ng s??? l?????ng", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
						return;
					}
					String id =  (String) TbPd.getValueAt(x, 0);
					int row = model_cthd.getRowCount();
					if(row>0) {
						Boolean bool = true;
						for(int i=0;i<TbDPd.getModel().getRowCount();i++) {
							if(model_cthd.getValueAt(i,0).toString()==id) {
								int sl1 = Integer.parseInt(model_cthd.getValueAt(i,2).toString()) + sl;
								model_cthd.setValueAt(sl1, i, 2);
								tongtienpn+= (Long) TbPd.getValueAt(x, 4)*sl;
								bool = false;
							}
						}
						if(bool) {
							if(TbPd.getValueAt(x, 2).equals("Ph??? Ki???n")) {
								model_cthd.addRow(new Object[] {id,(String) TbPd.getValueAt(x, 1),sl,(String) TbPd.getValueAt(x, 2),(String) TbPd.getValueAt(x, 3),(Long) TbPd.getValueAt(x, 4)});
								tongtienpn+= (Long) TbPd.getValueAt(x, 4)*sl;
							}else {
								model_cthd.addRow(new Object[] {id,(String) TbPd.getValueAt(x, 1),sl,(String) TbPd.getValueAt(x, 2),(Float) TbPd.getValueAt(x, 3),(Long) TbPd.getValueAt(x, 4)});
								tongtienpn+= (Long) TbPd.getValueAt(x, 4)*sl;
							}
						}
					}	
					else {
							if(TbPd.getValueAt(x, 2).equals("Ph??? Ki???n")) {
							model_cthd.addRow(new Object[] {id,(String) TbPd.getValueAt(x, 1),sl,(String) TbPd.getValueAt(x, 2),(String) TbPd.getValueAt(x, 3),(Long) TbPd.getValueAt(x, 4)});
							tongtienpn+= (Long) TbPd.getValueAt(x, 4)*sl;
							}else {
								model_cthd.addRow(new Object[] {id,(String) TbPd.getValueAt(x, 1),sl,(String) TbPd.getValueAt(x, 2),(Float) TbPd.getValueAt(x, 3),(Long) TbPd.getValueAt(x, 4)});
								tongtienpn+= (Long) TbPd.getValueAt(x, 4)*sl;
							}
					}
					Locale localeVN = new Locale("vi", "VN");
					NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
					String str1 = currencyVN.format(tongtienpn);
					tfTotalBill.setText(str1);
					tfPay.setText(str1);
				} catch(NumberFormatException ex1) {
					JOptionPane.showMessageDialog(null, "Vui l??ng nh???p s??? l?????ng", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	public void DeleSpCthd(int x,JTable TbDPd)
	{
		DefaultTableModel model_cthd =(DefaultTableModel) tableBillDetails.getModel();
		if(x==-1)
		{
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m c???n x??a", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			
			tongtienpn=(long)(tongtienpn - (int)TbDPd.getValueAt(x, 2)*Integer.parseInt(TbDPd.getValueAt(x, 5).toString()));
			Locale localeVN = new Locale("vi", "VN");
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
			String str1 = currencyVN.format(tongtienpn);
			tfTotalBill.setText(str1);
			tfPay.setText(str1);
			model_cthd.removeRow(x); 
		}
	}
	public void loadSP() {
		modelProduct.setRowCount(0);
		ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
		arrShoe =shoesbus.getallShoe();
		ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
		arrAC =AcBUS.getallAS();
		for(int i=0;i<arrShoe.size();i++)
		{
			ShoeDTO giay=arrShoe.get(i);
			String trangthai;
			if(giay.getTrangthai()==true)
			{
				trangthai="Ho???t ?????ng";
			}
			else
			{
				trangthai="Kh??ng Ho???t ?????ng";
			}
			if(trangthai=="Ho???t ?????ng")
			{
			modelProduct.addRow(new Object[] {"GI"+giay.getMaSP(),giay.getTenSp(),"Gi??y",giay.getSize(),giay.getGianhap(),giay.getSl()});
			}
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
			if(trangthai=="Ho???t ?????ng")
			{
			modelProduct.addRow(new Object[] {"PK"+pk.getMaSP(),pk.getTenSp(),"Ph??? Ki???n","None",pk.getGianhap(),pk.getSl()});
			}
		}
	}
	public void loadShoes() {
		modelProduct.setRowCount(0);
		ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
		arrShoe =shoesbus.getallShoe();
		for(int i=0;i<arrShoe.size();i++)
		{
			ShoeDTO giay=arrShoe.get(i);
			String trangthai;
			if(giay.getTrangthai()==true)
			{
				trangthai="Ho???t ?????ng";
			}
			else
			{
				trangthai="Kh??ng Ho???t ?????ng";
			}
			if(trangthai=="Ho???t ?????ng")
			{
			modelProduct.addRow(new Object[] {"GI"+giay.getMaSP(),giay.getTenSp(),"Gi??y",giay.getSize(),giay.getGianhap(),giay.getSl()});
			}
		}
	}
	public void loadPK() {
		modelProduct.setRowCount(0);
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
			if(trangthai=="Ho???t ?????ng")
			{
			modelProduct.addRow(new Object[] {"PK"+pk.getMaSP(),pk.getTenSp(),"Ph??? Ki???n","None",pk.getGianhap(),pk.getSl()});
			}
		}
	}
	public void rs() {
			btnChooseSupp.setVisible(false);
			btnXoaSP.setVisible(false);
			btnThemSL.setVisible(false);
			tfIdSupp.setText("");
			tfNameSupp.setText("");
			tfIdBill.setText("Vui l??ng t???o!");
			tfIdBill.setForeground(Color.RED);
			tfTotalBill.setText("0");
			tfPay.setText("0");
			tfNote.setText("");
			modelBillDetails.setRowCount(0);
			loadSP();
	}
	public void setAutomapn()
	{
		ArrayList<Import_DTO> arr_pn =new ArrayList<Import_DTO>();
		arr_pn=	importBUS.getAllImport();
		int temp;
		if(arr_pn.size()==0)
		{
			temp=1;
		}
		else
		{
			temp=arr_pn.get(arr_pn.size()-1).getImportID()+1;
		}
		tfIdBill.setText("PN"+temp+"");
		tfIdBill.setForeground(Color.BLACK);
	}
	public static String chuanhoa(String s) {
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
    public int chuanHoaMancc(String text)
    {
    	String [] splits =text.split("CC");
    	return Integer.parseInt(splits[1]);
    }
    public int chuanHoaMapn(String text)
    {
    	String [] splits =text.split("N");
    	return Integer.parseInt(splits[1]);
    }
    public int chuanHoaMagiay(String text)
    {
    	String [] splits =text.split("I");
    	return Integer.parseInt(splits[1]);
    }
    public int chuanHoaMapk(String text)
    {
    	String [] splits =text.split("K");
    	return Integer.parseInt(splits[1]);
    }
}

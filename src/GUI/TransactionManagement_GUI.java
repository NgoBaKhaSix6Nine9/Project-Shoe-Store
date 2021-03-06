package GUI;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Date;

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
import Animation.JLabelAmination;
import Animation.JPanelAmination;
import Animation.RadioButtonCustom;
import BUS.AccessoryBUS;
import BUS.BillBUS;
import BUS.BillDetailBUS;
import BUS.PromotionBLL;
import BUS.ShoeBUS;
import DAO.BillDAO;
import DTO.AccessoryDTO;
import DTO.BillDTO;
import DTO.BillDetailDTO;
import DTO.PromotionDetails;
import DTO.Promotion_DTO;
import DTO.ShoeDTO;
import DTO.XuatHDDTO;
import Animation.JTextfieldAmination;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransactionManagement_GUI extends JFrame {

	private AccessoryBUS AcBUS = new AccessoryBUS();
	private JPanel contentPane;
	
	ImageIcon iconBack=new ImageIcon(new ImageIcon("..\\TransactionManagement\\Icon\\back.png").getImage().getScaledInstance(60, 60, 0));
	ImageIcon iconRefresh=new ImageIcon(new ImageIcon("..\\TransactionManagement\\Icon\\refresh.png").getImage().getScaledInstance(20, 20, 0));
	ImageIcon iconAdd=new ImageIcon(new ImageIcon("..\\TransactionManagement\\Icon\\Add.png").getImage().getScaledInstance(25, 25, 0));
	ImageIcon iconSearch=new ImageIcon(new ImageIcon("..\\TransactionManagement\\Icon\\search.png").getImage().getScaledInstance(20, 20, 0));
	PromotionBLL kmBUS=new PromotionBLL();
	private JTable tableProduct;
	private JTable tableBillDetails;
	public DefaultTableModel modelBillDetails = new DefaultTableModel();
	public DefaultTableModel modelProduct = new DefaultTableModel();
	private static JTextField tfIdClient;
	private static JTextField tfNameClient;
	private JTextField tfNameStaff;
	private JTextField tfIdBill;
	private JTextField tfTotalBill;
	private JTextField tfDiscount;
	private JTextField tfPay;
	private Animation.JTextfieldAmination tfGive;
	private JTextField textBack;
	ButtonGroup group = new ButtonGroup();
	private JTextField tfSearchName;
	private JTextField tfSearchSize;
	private ShoeBUS shoebus=new ShoeBUS();
	private BillDAO Billdao=new BillDAO();
	private BillBUS Billbus=new BillBUS();
			
	private ButtonAmination btnChooseClient;
	private static long tongtienhd=0;
	private static long tongtienkm=0;
	private RadioButtonCustom rdbtnShip;
	private RadioButtonCustom rdbtnAt;
	private JTextArea tfnote;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//TransactionManagement_GUI frame = new TransactionManagement_GUI();
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
	public TransactionManagement_GUI(FormMenu menu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239,255,253));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(153,254,255));
		panelTitle.setBounds(0, 0, 1288, 70);
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Qu???n l?? giao d???ch");
		lblTitle.setBounds(531, 13, 225, 43);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panelTitle.add(lblTitle);
		setResizable(false);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				menu.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setIcon(iconBack);
		lblNewLabel.setBounds(1227, 0, 60, 70);
		panelTitle.add(lblNewLabel);
		
		JPanelAmination panel = new JPanelAmination();
		panel.setRadiusBottomLeft(15);
		panel.setRadiusBottomRight(15);
		panel.setRadiusTopLeft(15);
		panel.setRadiusTopRight(15);
		panel.setColor_BG(new Color(185,131,255));
		panel.setBounds(10, 99, 641, 197);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanelAmination panel_2 = new JPanelAmination();
		panel_2.setBounds(4, 4, 632, 189);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setRadiusTopRight(15);
		panel_2.setRadiusTopLeft(15);
		panel_2.setRadiusBottomRight(15);
		panel_2.setRadiusBottomLeft(15);
		panel_2.setColor_BG(Color.WHITE);
		
		JScrollPane scrollPaneBillDetails = new JScrollPane();
		scrollPaneBillDetails.setBounds(5, 5, 624, 180);
		panel_2.add(scrollPaneBillDetails);
		
		tableBillDetails = new JTable();
		modelBillDetails.addColumn("T??n SP");
		modelBillDetails.addColumn("SL");
		modelBillDetails.addColumn("Lo???i");
		modelBillDetails.addColumn("Size");
		modelBillDetails.addColumn("Gi??");
		modelBillDetails.addColumn("Khuy???n m??i");
		modelBillDetails.addColumn("M?? SP");
		tableBillDetails.setModel(modelBillDetails);
		tableBillDetails.getColumnModel().getColumn(0).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableBillDetails.getColumnModel().getColumn(1).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableBillDetails.getColumnModel().getColumn(2).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableBillDetails.getColumnModel().getColumn(3).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(3).setPreferredWidth(0);
		tableBillDetails.getColumnModel().getColumn(4).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableBillDetails.getColumnModel().getColumn(5).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(5).setPreferredWidth(60);
		tableBillDetails.getColumnModel().getColumn(6).setResizable(false);
		tableBillDetails.getColumnModel().getColumn(6).setPreferredWidth(30);
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
		scrollPaneBillDetails.setViewportView(tableBillDetails);
		tableBillDetails.setDefaultEditor(Object.class, null);//d??ng n??y s??? ko cho edit c??c ?? trong table
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
		modelProduct.addColumn("????n gi??");
		modelProduct.addColumn("Khuy???n m??i");
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
		tableProduct.getColumnModel().getColumn(4).setPreferredWidth(30);
		tableProduct.getColumnModel().getColumn(5).setResizable(false);
		tableProduct.getColumnModel().getColumn(5).setPreferredWidth(40);
		tableProduct.getColumnModel().getColumn(6).setResizable(false);
		tableProduct.getColumnModel().getColumn(6).setPreferredWidth(10);
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
		tableProduct.setDefaultEditor(Object.class, null);//d??ng n??y s??? ko cho edit c??c ?? trong table
		updateTb(modelProduct);
		
		
		
		
		ButtonAmination btnSearchName = new ButtonAmination("");
		btnSearchName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				locTheoTen(modelProduct);
			}
		});
		btnSearchName.setRadius(15);
		btnSearchName.setBounds(195, 19, 55, 32);
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
		panel_2_8_1_1.setBounds(5, 19, 199, 32);
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
		tfSearchName.setBounds(5, 1, 181, 27);
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
		btnSearchSize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				locTheoSize(modelProduct);
			}
		});
		btnSearchSize.setIcon(iconSearch);
		btnSearchSize.setRadius(15);
		btnSearchSize.setBorderColor(new Color(185, 131, 255));
		btnSearchSize.setBackground(new Color(185, 131, 255));
		btnSearchSize.setBounds(676, 19, 40, 32);
		panel_2_1.add(btnSearchSize);
		
		JPanelAmination panel_2_8_1_1_2 = new JPanelAmination();
		panel_2_8_1_1_2.setLayout(null);
		panel_2_8_1_1_2.setRadiusTopRight(15);
		panel_2_8_1_1_2.setRadiusTopLeft(15);
		panel_2_8_1_1_2.setRadiusBottomRight(15);
		panel_2_8_1_1_2.setRadiusBottomLeft(15);
		panel_2_8_1_1_2.setColor_BG(new Color(185, 131, 255));
		panel_2_8_1_1_2.setBounds(566, 19, 126, 32);
		panel_2_1.add(panel_2_8_1_1_2);
		
		JPanelAmination panel_2_8_1_1_1_1 = new JPanelAmination();
		panel_2_8_1_1_1_1.setLayout(null);
		panel_2_8_1_1_1_1.setRadiusTopRight(15);
		panel_2_8_1_1_1_1.setRadiusTopLeft(15);
		panel_2_8_1_1_1_1.setRadiusBottomRight(15);
		panel_2_8_1_1_1_1.setRadiusBottomLeft(15);
		panel_2_8_1_1_1_1.setColor_BG(Color.WHITE);
		panel_2_8_1_1_1_1.setBounds(2, 2, 233, 28);
		panel_2_8_1_1_2.add(panel_2_8_1_1_1_1);
		
		tfSearchSize = new JTextField();
		tfSearchSize.setText("Size");
		tfSearchSize.setForeground(Color.LIGHT_GRAY);
		tfSearchSize.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfSearchSize.setColumns(10);
		tfSearchSize.setBorder(null);
		tfSearchSize.setBounds(5, 1, 100, 27);
		panel_2_8_1_1_1_1.add(tfSearchSize);
		
		ButtonAmination btnThemsp = new ButtonAmination("Ch???n KH");
		btnThemsp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addcthd(tableProduct,modelBillDetails,tableBillDetails);
			}
		});
		btnThemsp.setText("Th??m S???n Ph???m");
		btnThemsp.setRadius(15);
		btnThemsp.setForeground(Color.WHITE);
		btnThemsp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnThemsp.setBackground(new Color(185, 131, 255));
		btnThemsp.setBounds(318, 17, 160, 37);
		panel_2_1.add(btnThemsp);
		btnThemsp.setIcon(iconAdd);
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
		
		JLabel lblTitle1 = new JLabel("H??a ????n chi ti???t");
		lblTitle1.setForeground(new Color(185,131,255));
		lblTitle1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitle1.setBounds(15, 81, 111, 18);
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
		
		JLabel lblNewLabel_1 = new JLabel("M?? kh??ch h??ng:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(8, 8, 146, 24);
		panel_2_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("T??n kh??ch h??ng:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(8, 42, 150, 24);
		panel_2_3.add(lblNewLabel_1_1);
		
		tfIdClient = new JTextField();
		tfIdClient.setDisabledTextColor(Color.BLACK);
		tfIdClient.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfIdClient.setEditable(false);
		tfIdClient.setBackground(Color.WHITE);
		tfIdClient.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfIdClient.setBounds(160, 9, 142, 24);
		panel_2_3.add(tfIdClient);
		tfIdClient.setColumns(10);
		
		tfNameClient = new JTextField();
		tfNameClient.setDisabledTextColor(Color.BLACK);
		tfNameClient.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfNameClient.setEditable(false);
		tfNameClient.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfNameClient.setColumns(10);
		tfNameClient.setBounds(160, 42, 220, 24);
		tfNameClient.setBackground(Color.WHITE);
		panel_2_3.add(tfNameClient);
		
		 btnChooseClient = new ButtonAmination("Ch???n KH");
		btnChooseClient.setForeground(Color.WHITE);
		btnChooseClient.setRadius(15);
		btnChooseClient.setBackground(new Color(185,131,255));
		btnChooseClient.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnChooseClient.setBounds(390, 19, 98, 33);
		panel_2_3.add(btnChooseClient);
		
		TransactionManagement_GUI gd=this;
		btnChooseClient.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				InfoClient_GUI info =new InfoClient_GUI(gd);
				info.setVisible(true);
				setVisible(false);
			}
		});
		btnChooseClient.setVisible(false);
		
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
		tfNameStaff.setBounds(160, 7, 324, 24);
		panel_2_4.add(tfNameStaff);
		tfNameStaff.setColumns(10);
		
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
		
		JLabel lblNewLabel_1_2_1 = new JLabel("M?? h??a ????n:");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(8, 8, 111, 24);
		panel_2_5.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("T???ng ti???n:");
		lblNewLabel_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1.setBounds(8, 47, 94, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Gi???m gi??:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1_1.setBounds(8, 86, 131, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Thanh to??n:");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1_2.setBounds(8, 125, 131, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1_2);
		
		JLabel lbl = new JLabel("Ti???n kh??ch ????a:");
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl.setBounds(8, 164, 141, 24);
		panel_2_5.add(lbl);
		
		JLabel lblNewLabel_1_2_1_1_4 = new JLabel("Ti???n th???a tr??? kh??ch:");
		lblNewLabel_1_2_1_1_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1_4.setBounds(8, 203, 178, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1_4);
		
		JLabel lblNewLabel_1_2_1_1_5 = new JLabel("H??nh th???c:");
		lblNewLabel_1_2_1_1_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1_5.setBounds(8, 242, 94, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1_5);
		
		JLabel lblNewLabel_1_2_1_1_6 = new JLabel("Ghi ch??:");
		lblNewLabel_1_2_1_1_6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2_1_1_6.setBounds(8, 281, 76, 24);
		panel_2_5.add(lblNewLabel_1_2_1_1_6);
		
		tfIdBill = new JTextField();
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
		
		tfDiscount = new JTextField();
		tfDiscount.setText("0");
		tfDiscount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfDiscount.setDisabledTextColor(Color.BLACK);
		tfDiscount.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfDiscount.setEditable(false);
		tfDiscount.setBackground(Color.WHITE);
		tfDiscount.setEditable(false);
		tfDiscount.setColumns(10);
		tfDiscount.setBounds(220, 87, 163, 24);
		panel_2_5.add(tfDiscount);
		
		tfPay = new JTextField();
		tfPay.setText("0");
		tfPay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfPay.setDisabledTextColor(Color.BLACK);
		tfPay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfPay.setEditable(false);
		tfPay.setBackground(Color.WHITE);
		tfPay.setEditable(false);
		tfPay.setColumns(10);
		tfPay.setBounds(220, 126, 163, 24);
		panel_2_5.add(tfPay);
		
		tfGive = new Animation.JTextfieldAmination();
		tfGive.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(e.getKeyChar()=='\n')
				{
					
					checkTienkh();
				}
			}
		});
		tfGive.setRadius(10);
		tfGive.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfGive.setColumns(10);
		tfGive.setBorderColor(new Color(185, 131, 255));
		tfGive.setBorderWeight(3);
		tfGive.setBounds(220, 165, 163, 24);
		panel_2_5.add(tfGive);
		
		textBack = new JTextField();
		textBack.setText("0");
		textBack.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textBack.setDisabledTextColor(Color.BLACK);
		textBack.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textBack.setEditable(false);
		textBack.setBackground(Color.WHITE);
		textBack.setEditable(false);
		textBack.setColumns(10);
		textBack.setBounds(220, 204, 163, 24);
		panel_2_5.add(textBack);
		
		JLabel lblNewLabel_2 = new JLabel("VN??");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(420, 50, 43, 19);
		panel_2_5.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("VN??");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(420, 88, 43, 19);
		panel_2_5.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("VN??");
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(420, 127, 43, 19);
		panel_2_5.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("VN??");
		lblNewLabel_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(420, 166, 43, 19);
		panel_2_5.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("VN??");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1_1.setBounds(420, 205, 43, 19);
		panel_2_5.add(lblNewLabel_2_1_1_1_1);
		
		 rdbtnShip = new RadioButtonCustom();
		 rdbtnShip.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		checkTienkh();
		 	}
		 });
		rdbtnShip.setText("Giao h??ng");
		rdbtnShip.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbtnShip.setBackground(new Color(185, 131, 255));;
		rdbtnShip.setOpaque(false);
		rdbtnShip.setFocusPainted(false);
		rdbtnShip.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnShip.setBounds(220, 243, 107, 23);
		group.add(rdbtnShip);
		panel_2_5.add(rdbtnShip);
		
		 rdbtnAt = new RadioButtonCustom();
		rdbtnAt.setText("T???i qu???y");
		rdbtnAt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbtnAt.setBackground(new Color(185, 131, 255));;
		rdbtnAt.setOpaque(false);
		rdbtnAt.setFocusPainted(false);
		rdbtnAt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnAt.setBounds(352, 243, 94, 23);
		group.add(rdbtnAt);
		panel_2_5.add(rdbtnAt);
		rdbtnAt.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		checkTienkh();
			 	}
			 });
		
		//set cho 2 rdbn ko chon ,khi nao tao hd th?? m??i cho
		rdbtnAt.setEnabled(false);
		rdbtnShip.setEnabled(false);
		tfGive.setEditable(false);
		
		
		JPanelAmination panel_2_7 = new JPanelAmination();
		panel_2_7.setLayout(null);
		panel_2_7.setRadiusTopRight(15);
		panel_2_7.setRadiusTopLeft(15);
		panel_2_7.setRadiusBottomRight(15);
		panel_2_7.setRadiusBottomLeft(15);
		panel_2_7.setColor_BG(new Color(185,131,255));
		panel_2_7.setBounds(94, 275, 393, 86);
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
		
		 tfnote = new JTextArea();
		tfnote.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfnote.setBounds(5, 5, 379, 71);
		tfnote.setBorder(null);
		tfnote.setLineWrap(true);// thiet lap tach tu xuong dong
		tfnote.setWrapStyleWord(true);
		panel_2_7_1.add(tfnote);
		
		ButtonAmination btnCreateBill = new ButtonAmination("Ch???n KH");
		btnCreateBill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewBill();
			}
		});
		btnCreateBill.setIcon(iconAdd);
		btnCreateBill.setBounds(20, 541, 116, 42);
		panel_2_2.add(btnCreateBill);
		btnCreateBill.setText("T???o");
		btnCreateBill.setRadius(15);
		btnCreateBill.setForeground(Color.WHITE);
		btnCreateBill.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCreateBill.setBackground(new Color(185, 131, 255));
		
		ButtonAmination btnmntnLmMi = new ButtonAmination("Ch???n KH");
		btnmntnLmMi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rs();
			}
		});
		btnmntnLmMi.setIcon(iconRefresh);
		btnmntnLmMi.setText("L??m m???i");
		btnmntnLmMi.setRadius(15);
		btnmntnLmMi.setForeground(Color.WHITE);
		btnmntnLmMi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnmntnLmMi.setBackground(new Color(185, 131, 255));
		btnmntnLmMi.setBounds(383, 541, 116, 42);
		panel_2_2.add(btnmntnLmMi);
		
		ButtonAmination btnCreateBill_2 = new ButtonAmination("Ch???n KH");
		btnCreateBill_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					thanhtoan(menu.manv);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCreateBill_2.setText("Thanh to??n");
		btnCreateBill_2.setRadius(15);
		btnCreateBill_2.setForeground(Color.WHITE);
		btnCreateBill_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCreateBill_2.setBackground(new Color(185, 131, 255));
		btnCreateBill_2.setBounds(149, 585, 223, 56);
		panel_2_2.add(btnCreateBill_2);
		
		JLabel lblThngTinHa = new JLabel("Th??ng tin h??a ????n");
		lblThngTinHa.setForeground(new Color(185, 131, 255));
		lblThngTinHa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThngTinHa.setBounds(754, 80, 120, 18);
		contentPane.add(lblThngTinHa);
		
		ButtonAmination btnmntnXaSp = new ButtonAmination("Ch???n KH");
		btnmntnXaSp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x=tableBillDetails.getSelectedRow();
			DeleSpCthd(x,tableBillDetails);
			}
		});
		btnmntnXaSp.setText("X??a SP");
		btnmntnXaSp.setRadius(15);
		btnmntnXaSp.setForeground(Color.WHITE);
		btnmntnXaSp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnmntnXaSp.setBackground(new Color(185, 131, 255));
		btnmntnXaSp.setBounds(651, 186, 87, 33);
		contentPane.add(btnmntnXaSp);
		
		
		
		//set ten nhan vien trong gd
				tfNameStaff.setText(menu.tennv);
	}
	
	
	public void updateTb(DefaultTableModel model)
	{
		model.setRowCount(0);
		ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
		arrShoe =shoebus.getallShoe();
		
		
		
		Vector<Promotion_DTO> arrkm=new Vector<>();
		arrkm =kmBUS.getAllPromotion();
		for(int i=0;i<arrShoe.size();i++)
		{
			String giakm = "None";
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
			if(trangthai=="Ho???t ?????ng")
			{
				Date currentDate = new Date();
				
				Vector<PromotionDetails> arrctkm=new Vector<>();
				try {
					arrctkm =kmBUS.getAllPromotionDetails();
					
					for(int j=0;j<arrctkm.size();j++)
					{
						if(shoe.getMaSP()==arrctkm.get(j).getShoesID())
						{
							//System.out.println(shoe.getMaSP()+" "+arrctkm.get(j).getShoesID());
							for(int k=0;k<arrkm.size();k++)
							{

								if(currentDate.compareTo(arrkm.get(k).getDateEnd())==-1 &&currentDate.compareTo(arrkm.get(k).getDateStart())==1)
								{
									if(arrctkm.get(j).getPromotionID()==arrkm.get(k).getPromotionID())
									{
										if(arrkm.get(k).getPromotionForm().equals("Gi???m theo s??? ti???n"))
											giakm = arrkm.get(k).getDiscount()+" VND";
										else
											giakm = arrkm.get(k).getDiscount()+" %";
									}
								}
								
								
							}
						}
					}
					
					
				} catch (Exception e) {
					// TODO: handle exception
					giakm="None";
					//System.out.println(e);
				}
				
				model.addRow(new Object[] {"GI"+shoe.getMaSP(),shoe.getTenSp(),"Gi??y",shoe.getSize(),shoe.getGiaban(),giakm,shoe.getSl()});
			}
		}
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
			
			model.addRow(new Object[] {"PK"+pk.getMaSP(),pk.getTenSp(),"Ph??? Ki???n","None",pk.getGiaban(),"None",pk.getSl()});
			}
		}
	}
	

	
	
	public void locTheoTen(DefaultTableModel model)
	{
		
		ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
		arrShoe =shoebus.getallShoe();
		ArrayList<AccessoryDTO> arrAC =new ArrayList<AccessoryDTO>();
		arrAC =AcBUS.getallAS();
		
	
		if(tfSearchName.getText().equals("T??n s???n ph???m")==false  )
		{
			if(tfSearchName.getText().equals(""))
			{
				updateTb(model);
			}
			else
			{
				String tensp=chuanHoa(tfSearchName.getText());
				model.setRowCount(0);
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
					if(trangthai=="Ho???t ?????ng" && shoe.getTenSp().indexOf(tensp)!=-1)
					{
						model.addRow(new Object[] {"GI"+shoe.getMaSP(),shoe.getTenSp(),"Gi??y",shoe.getSize(),shoe.getGiaban(),"None",shoe.getSl()});
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
					if(trangthai=="Ho???t ?????ng" && pk.getTenSp().indexOf(tensp)!=-1)
					{
					model.addRow(new Object[] {"PK"+pk.getMaSP(),pk.getTenSp(),"Ph??? Ki???n","None",pk.getGiaban(),"None",pk.getSl()});
					}
				}
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Vui l??ng nh???p t??n s???n ph???m", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void locTheoSize(DefaultTableModel model)
	{
		ArrayList<ShoeDTO> arrShoe =new ArrayList<ShoeDTO>();
		arrShoe =shoebus.getallShoe();
		if(tfSearchSize.getText().equals("Size")==false )
		{
			
			try {
				float size =Float.parseFloat(tfSearchSize.getText());
				
				model.setRowCount(0);
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
					if(trangthai=="Ho???t ?????ng" && shoe.getSize()==size)
					{
						model.addRow(new Object[] {"GI"+shoe.getMaSP(),shoe.getTenSp(),"Gi??y",shoe.getSize(),shoe.getGiaban(),"None",shoe.getSl()});
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????ng ki???u d??? li???u c???a Size ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else
			
		{
			JOptionPane.showMessageDialog(null, "Vui l??ng nh???p Size ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addcthd(JTable TbPd,DefaultTableModel model_cthd,JTable TbDPd)
	{	
		if(tfIdBill.getText().equals("Vui l??ng t???o!"))
		{
			JOptionPane.showMessageDialog(null, "Vui l??ng t???o h??a ????n ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
		else
		{

			int x=TbPd.getSelectedRow();//-1 l?? s??? ko ch???n g??
			if(x==-1)
			{
				JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				String sl_s=JOptionPane.showInputDialog(null, "Vui l??ng nh???p s??? l?????ng", "TH??NG B??O",JOptionPane.ERROR_MESSAGE) ;
				if(sl_s==null)
				{
					return;
				}
				else
				{
					try {
						
						int sl=Integer.parseInt(sl_s) ;
						if(sl<=0 || sl> Integer.parseInt(TbPd.getValueAt(x, 6)+"") )
						{
							JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????ng s??? l?????ng ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						String masp=((String)TbPd.getValueAt(x, 0));
						int sl_cthd=TbDPd.getRowCount();
						for(int i=0;i<sl_cthd;i++)
						{
							if(masp==((String)TbDPd.getValueAt(i, 6)))
							{
									int tienkm=tienkm((String)TbPd.getValueAt(x,5));
									boolean check=true;
									int temp=(int)TbDPd.getValueAt(i, 1)+sl;
									DeleSpCthd(i,TbDPd);
									if(temp> Integer.parseInt(TbPd.getValueAt(x, 6)+""))
									{
										JOptionPane.showMessageDialog(null, "S??? l?????ng trong kho kh??ng ????? !!", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
										break;
									}
									
									if(tienkm<=100 && tienkm>0)
									{
										long giamtheopt =(long)TbPd.getValueAt(x, 4)-(long)TbPd.getValueAt(x, 4)*tienkm/100;
										tongtienhd=tongtienhd+ temp*((long)TbPd.getValueAt(x, 4));
										tfTotalBill.setText(tongtienhd+"");
										tongtienkm=tongtienkm+giamtheopt*temp;	
										tfDiscount.setText(tongtienkm+"");
										tfPay.setText(tongtienhd-tongtienkm+"");
										
										//masp,sl,loai,size,gia
										model_cthd.addRow( new Object[] { (String)TbPd.getValueAt(x, 1),temp,TbPd.getValueAt(x, 2),TbPd.getValueAt(x, 3)  ,TbPd.getValueAt(x, 4),giamtheopt,TbPd.getValueAt(x, 0)});
									}
									else
									{
										System.out.println(tienkm);
										tongtienhd=tongtienhd+ temp*((long)TbPd.getValueAt(x, 4));
										tongtienkm=tongtienkm+tienkm*temp;
										tfTotalBill.setText(tongtienhd+"");
										tfDiscount.setText(tongtienkm+"");
										tfPay.setText(tongtienhd-tongtienkm+"");
									
										//masp,sl,loai,size,gia,km,masp
										model_cthd.addRow( new Object[] { (String)TbPd.getValueAt(x, 1),temp,TbPd.getValueAt(x, 2),TbPd.getValueAt(x, 3)  ,TbPd.getValueAt(x, 4),tienkm,TbPd.getValueAt(x, 0)});
									}
									return;
							}
						}
						int tienkm=tienkm((String)TbPd.getValueAt(x,5));
						if(tienkm<=100 && tienkm>0)
						{
							long giamtheopt =(long)TbPd.getValueAt(x, 4)-(long)TbPd.getValueAt(x, 4)*tienkm/100;
							tongtienhd=tongtienhd+ sl*((long)TbPd.getValueAt(x, 4));
							tfTotalBill.setText(tongtienhd+"");
							tongtienkm=tongtienkm+giamtheopt*sl;	
							
							tfDiscount.setText(tongtienkm+"");
							tfPay.setText(tongtienhd-tongtienkm+"");
							
							//masp,sl,loai,size,gia
							model_cthd.addRow( new Object[] { TbPd.getValueAt(x, 1),sl,TbPd.getValueAt(x, 2),TbPd.getValueAt(x, 3)  ,TbPd.getValueAt(x, 4),giamtheopt,TbPd.getValueAt(x, 0)});
						}
						else
						{
							tongtienhd=tongtienhd+ sl*((long)TbPd.getValueAt(x, 4));
							tongtienkm=tongtienkm+tienkm*sl;
							
							tfTotalBill.setText(tongtienhd+"");
							tfDiscount.setText(tongtienkm+"");
							tfPay.setText(tongtienhd-tongtienkm+"");
							String tensp=TbPd.getValueAt(x, 1)+"";
							//masp,sl,loai,size,gia
							//System.out.println(tensp);
							model_cthd.addRow( new Object[] {tensp ,sl,TbPd.getValueAt(x, 2),TbPd.getValueAt(x, 3)  ,TbPd.getValueAt(x, 4),tienkm,TbPd.getValueAt(x, 0)});
						}

					} catch (Exception e) {
						// TODO: handle exception
						//System.out.println(e);
						JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????ng s??? l?????ng ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		
		
		
		
	}
	
public int tienkm(String muckm) {
		if(muckm.equals("None"))
		{
			return 0;
		}
		else
		{
			String [] split =muckm.split(" ");
			return Integer.parseInt(split[0]);
		}
		
		
	}

	public void DeleSpCthd(int x,JTable TbDPd)
	{
		DefaultTableModel model_cthd =(DefaultTableModel) tableBillDetails.getModel();
		System.out.println(x);
		if(x==-1)
		{
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m c???n x??a", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			
			tongtienhd=(long)(tongtienhd- (int)TbDPd.getValueAt(x, 1)*Integer.parseInt(TbDPd.getValueAt(x, 4).toString()));
			tongtienkm=(long)(tongtienkm-(int)TbDPd.getValueAt(x, 1) * Integer.parseInt(TbDPd.getValueAt(x, 5).toString()));
			tfTotalBill.setText(tongtienhd+"");
			tfPay.setText(tongtienhd+"");
			tfDiscount.setText(tongtienkm+"");
			model_cthd.removeRow(x); 
		}
	}
	
	
	public void NewBill()
	{
		ArrayList<BillDTO> arrB=new ArrayList<BillDTO>();
		arrB= Billdao.getallBill();
		btnChooseClient.setVisible(true);
		tfGive.setEditable(true);
		rdbtnAt.setEnabled(true);
		rdbtnShip.setEnabled(true);
		int mahd;
		if(arrB.size()==0)
		{
			 mahd=1;
		}
		else
		{
			 mahd=arrB.get(arrB.size()-1).getMahd()+1;
		}
		
		tfIdBill.setText(mahd+"");
	}
	

	public void checkTienkh()
	{
		String tientrakh=tfGive.getText();
		try {
			long tienkh=Integer.parseInt(tientrakh);
			long tonghd=Integer.parseInt(tfPay.getText());
	
			if(tienkh<tonghd)
			{
				JOptionPane.showMessageDialog(null, "Ti???n kh??ch ????a b?? h??n t???ng h??a ????n !!!", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				textBack.setText(tienkh-tonghd+"");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "'Ti???n kh??ch ????a' Sai d??? li???u ", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	public void thanhtoan(int manv) throws IOException
	{
		if(tfIdBill.getText().equals("Vui l??ng t???o!"))
		{
			JOptionPane.showMessageDialog(null, "! ! !Vui l??ng t???o h??a ????n! ! !", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			
			
			
			if(tfIdClient.getText().equals("") || tfNameClient.getText().equals("") 
				|| (rdbtnAt.isSelected()==false && rdbtnShip.isSelected()==false) )
			{
				JOptionPane.showMessageDialog(null, "Nh???p thi???u d??? li???u ! ! !", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				//L??U HOADON
				Date currentDate = new Date();
				String dateToStr = DateFormat.getInstance().format(currentDate);
				String datereal=dateToStr.substring(3,5)+"/"+dateToStr.substring(0,2)+"/"+dateToStr.substring(6,10)+dateToStr.substring(11,17);
				BillDTO newbill=new BillDTO();
				newbill.setMahd(Integer.parseInt(tfIdBill.getText()));
				newbill.setNgaytao(datereal);
				newbill.setMakh(chuanHoaMakh(tfIdClient.getText()) );
				newbill.setManv(manv); //chua doneCHUA DONEW NHE
				newbill.setChuthich(tfnote.getText());
				newbill.setTongtien(tongtienhd);
				newbill.setHinhthuc(rdbtnAt.isSelected()?"T???i qu???y":"Giao h??ng");
				
				
				
				XuatHd xuathd=new XuatHd();
				

				try {
					Billbus.addBill(newbill);//l??u h??a don
					ArrayList<XuatHDDTO> xuat=new ArrayList<>();
					xuat=saveCThd();//luu cthd
					updtsl();
					JOptionPane.showMessageDialog(null,"Thanh To??n th??nh c??ng", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
					int k=JOptionPane.showConfirmDialog(null, "B???n mu???n xu???t h??a ????n kh??ng?","M??y In", JOptionPane.YES_NO_OPTION);
					if(k==0)
					{
						//ma, dc,tenkh,ngay
						xuathd.cc((tfIdBill.getText()), tfNameClient.getText(), datereal,xuat);
						JOptionPane.showMessageDialog(null,"Xu???t Th??nh C??ng hehe", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
					}
					rs();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
					//JOptionPane.showMessageDialog(null,"Thanh To??n kh??ng th??nh c??ng", "TH??NG B??O",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
	}
	public ArrayList<XuatHDDTO> saveCThd()
	{
		BillDetailBUS BdBUS=new BillDetailBUS();
		int row=tableBillDetails.getModel().getRowCount();
		ArrayList<XuatHDDTO> arrxhd=new ArrayList<>();
		for(int i=0;i<row;i++)
		{
			XuatHDDTO hd=new XuatHDDTO();
			hd.setStt(i);
			hd.setTensp(tableBillDetails.getValueAt(i, 0)+"");
			int slcc=Integer.parseInt( tableBillDetails.getValueAt(i, 1)+"");
			hd.setSl(slcc);
			int dongia=Integer.parseInt(tableBillDetails.getValueAt(i, 4)+"")-Integer.parseInt(tableBillDetails.getValueAt(i, 5)+"");
			hd.setDongia(dongia);
			hd.setThanhtien(slcc*dongia);
			arrxhd.add(hd);
			//System.out.println(tableBillDetails.getValueAt(i, 0));
			if(tableBillDetails.getValueAt(i, 2).equals("Gi??y"))
			{
				BillDetailDTO bdgiay=new BillDetailDTO();
				int mahd=Integer.parseInt(tfIdBill.getText());
				
				int masp=chuanHoaMasp((String)tableBillDetails.getValueAt(i, 6));
				int sl=Integer.parseInt(tableBillDetails.getValueAt(i, 1)+"");
				long gia=Integer.parseInt(tableBillDetails.getValueAt(i, 4)+"")-Integer.parseInt(tableBillDetails.getValueAt(i, 5)+"");
				bdgiay.setMahd(mahd);
				bdgiay.setMasp(masp);
				bdgiay.setSl(sl);
				bdgiay.setGia(gia);
				
				BdBUS.addDBill(bdgiay, 0);
			}
			else if(tableBillDetails.getValueAt(i, 2).equals("Ph??? Ki???n"))
				{
				
					BillDetailDTO bdgiay=new BillDetailDTO();
					int mahd=Integer.parseInt(tfIdBill.getText());
					int masp=chuanHoaMasp((String)tableBillDetails.getValueAt(i, 6));
					int sl=Integer.parseInt(tableBillDetails.getValueAt(i, 1)+"");
					long gia=Integer.parseInt(tableBillDetails.getValueAt(i, 4)+"")-Integer.parseInt(tableBillDetails.getValueAt(i, 5)+"");
					bdgiay.setMahd(mahd);
					bdgiay.setMasp(masp);
					bdgiay.setSl(sl);
					bdgiay.setGia(gia);
					
					BdBUS.addDBill(bdgiay, 1);
				}
		}
		return arrxhd;
	}
	
	
	public void updtsl()
	{
		int k=tableBillDetails.getRowCount();//sl d??ng cthd
		
		for(int i=0;i<k;i++)
		{
			
			String tenloai=tableBillDetails.getValueAt(i, 2).toString();

			int slmua=Integer.parseInt( tableBillDetails.getValueAt(i,1)+"");
			int masp=chuanHoaMasp( tableBillDetails.getValueAt(i,6)+"");
			if(tenloai=="Gi??y")
			{
				Billbus.updtSL(masp, "Giay", slmua);
				System.out.println("dachange");
			}
			else
			{
				Billbus.updtSL(masp, "Phukien", slmua);
			}
			
		}
	}
	
	
	public void rs()
	{
		btnChooseClient.setVisible(false);
		tfGive.setEditable(false);
		rdbtnAt.setEnabled(false);
		rdbtnShip.setEnabled(false);
		
		tfIdClient.setText("");
		tfNameClient.setText("");
		tfNameStaff.setText("");
		
		tfIdBill.setText("Vui l??ng t???o!");
		tfTotalBill.setText("0");
		tfDiscount.setText("0");
		tfPay.setText("0");
		tfGive.setText("0");
		textBack.setText("0");
		tfnote.setText("");
		rdbtnAt.setSelected(false);
		rdbtnShip.setSelected(false);
		
		modelBillDetails.setRowCount(0);
		
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
	  public int chuanHoaMasp(String text)
	    {
	    	String [] splits =text.split("I");
	    	if(splits[0].equals("G")==false)
	    	{
	    		splits=text.split("K");
	    		return  Integer.parseInt(splits[1]);
	    	}
	    	return Integer.parseInt(splits[1]);
	    }
	  public int chuanHoaMakh(String text)
	    {
	    	String [] splits =text.split("H");
	    	
	    	return Integer.parseInt(splits[1]);
	    }
	  
	  public static void getTenMakh(String makh,String tenkh)
	  {
		  tfIdClient.setText(makh);
		  tfNameClient.setText(tenkh);
	  }
	  
}

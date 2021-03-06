package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Animation.ButtonAmination;
import Animation.JPanelAmination;
import Animation.RadioButtonCustom;
import Animation.TextFielAmination;
import BUS.Customer_BUS;
import DTO.CustomerList;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DTO.Customer;

import java.awt.event.MouseListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CustomerManagerUI extends JFrame {

	private JPanel contentPane;
	private CustomerList customerMangerView;
	public ButtonAmination btnColor;
	public ButtonGroup btg = new ButtonGroup();
	public JPanelAmination panelStyle;
	private JTextField textField;
	private ButtonAmination btnStyle;
	private ButtonAmination btnSave;
	private ButtonAmination btnEdit;
	private ButtonAmination btnDelete;
	private ButtonAmination btnReset;
	private TextFielAmination textmaKH;
	private TextFielAmination textName;
	private TextFielAmination textPhoneNumber;
	private RadioButtonCustom rbtnMale;
	private RadioButtonCustom rbtnNu;
	public DefaultTableModel model = new DefaultTableModel();
	public DefaultTableModel model2;
	private ButtonAmination btnSearchID, btnSearchName;
	private Customer_BUS khBUS = new Customer_BUS();
	private ArrayList<DTO.Customer> dskh= new ArrayList<DTO.Customer>();
	private Object[] row = new Object[5];
	private Object[] row1 = new Object[5];

	private static boolean isSelectRow = false;
	private JTextArea tfAddress;

	//H??nh ???nh
	ImageIcon iconExit=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\image\\Vector.png").getImage().getScaledInstance(60, 60, 0));
	ImageIcon iconSearch=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\image\\search.png").getImage().getScaledInstance(20, 20, 0));
	ImageIcon iconSave=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\image\\iconSave.png").getImage().getScaledInstance(30, 30, 0));
	ImageIcon iconEdit=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\image\\iconEdit.png").getImage().getScaledInstance(30, 30, 0));
	ImageIcon iconDelete=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\image\\iconDelete.png").getImage().getScaledInstance(30, 30, 0));
	ImageIcon iconReset=new ImageIcon(new ImageIcon("..\\Form Login And Menu\\image\\iconReset.png").getImage().getScaledInstance(30, 30, 0));
	private JTable table;
	private JTextField tfSearchID;
	private JTextField tfSearchName;
	private JTable table_1;
	private JComboBox comboBoxForSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CustomerManagerUI frame = new CustomerManagerUI();
				//	frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerManagerUI(FormMenu menu) {
		this.customerMangerView= new CustomerList();
		Border border = BorderFactory.createLineBorder(new Color(0xB983FF)); //set color for border of text field
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239,255,253));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		JPanel title = new JPanel();
		title.setBackground(new Color(0x99FEFF));
		title.setBounds(0, 0, 1288, 70);
		contentPane.add(title);
		title.setLayout(null);
		
		JLabel lblTitle = new JLabel("Qu???n l?? kh??ch h??ng\r\n");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitle.setBounds(515, 18, 257, 33);
		title.add(lblTitle);
		
		JLabel lb_logout = new JLabel("");
		lb_logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu.setVisible(true);
				dispose();
			}
		});
		lb_logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_logout.setIcon(iconExit);
		lb_logout.setBounds(1227, 0, 60, 70);
		title.add(lb_logout);
		lb_logout.setIcon(iconExit);
		
		JPanel customerInformation = new JPanel();
		customerInformation.setBorder(new LineBorder(new Color(185, 131, 255), 2));
		customerInformation.setBackground(Color.WHITE);
		customerInformation.setBounds(11, 95, 1268, 286);
		customerInformation.setBackground(Color.WHITE);
		contentPane.add(customerInformation);
		customerInformation.setLayout(null);
		
		JLabel lblmaKH = new JLabel("M?? kh??ch h??ng:");
		lblmaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblmaKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblmaKH.setBounds(40, 29, 142, 26);
		customerInformation.add(lblmaKH);
		
		textmaKH = new TextFielAmination();
		
		textmaKH.setText("KH"+khBUS.maxMa());
		
		textmaKH.setEditable(false);
		textmaKH.setBorderWeight(3);
		textmaKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textmaKH.setRadius(10);									// Bo g??c c???a text m???i b??n 10 ????? 
		textmaKH.setBorderColor(new Color(0xB983FF));			// Set m??u vi???n cho text
		textmaKH.setBounds(240, 29, 331, 27);
		customerInformation.add(textmaKH);
		
		JLabel lblName = new JLabel("T??n kh??ch h??ng:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblName.setBounds(40, 96, 155, 26);
		customerInformation.add(lblName);
		
		textName = new TextFielAmination ();
		textName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textName.setBounds(240, 96, 331, 27);
		customerInformation.add(textName);
		textName.setBorderColor(new Color(0xB983FF));
		textName.setRadius(10);
		textName.setBorderWeight(3);
				
		JLabel lblGender = new JLabel("Gi???i t??nh:");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGender.setBounds(40, 163, 81, 26);
		customerInformation.add(lblGender);
		
		JLabel lblPhoneNumber = new JLabel("S??? ??i???n tho???i:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPhoneNumber.setBounds(40, 230, 117, 26);
		customerInformation.add(lblPhoneNumber);
		
		textPhoneNumber = new TextFielAmination ();
		textPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textPhoneNumber.setBounds(240, 230, 331, 27);
		customerInformation.add(textPhoneNumber);
		textPhoneNumber.setRadius(10);
		textPhoneNumber.setBorderColor(new Color(0xB983FF));
		textPhoneNumber.setBorderWeight(3);
		
		rbtnMale = new RadioButtonCustom();    // Custom RadioButton
		rbtnMale.setText("Nam");
		rbtnMale.setOpaque(false);
        rbtnMale.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rbtnMale.setBackground(new Color(0xB983FF));;
		rbtnMale.setHorizontalAlignment(SwingConstants.LEFT);
		rbtnMale.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rbtnMale.setBounds(251, 165, 63, 26);
		rbtnMale.setFocusPainted(false); // L??m cho ch??? ??? radio button kh??ng c?? vi???n
		customerInformation.add(rbtnMale);
		btg.add(rbtnMale);
		
		rbtnNu = new RadioButtonCustom();
		rbtnNu.setText("N???");
		rbtnNu.setOpaque(false);								// m??u n???n c???a nguy??n radiobutton (mu???n test th?? b???t true)
        rbtnNu.setCursor(new Cursor(Cursor.HAND_CURSOR));		// h??nh d???ng c???a chu???t khi tr??? v??o n??t 
        rbtnNu.setBackground(new Color(0xB983FF));;				// m??u c???a n??t
		rbtnNu.setHorizontalAlignment(SwingConstants.LEFT);
		rbtnNu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rbtnNu.setBounds(348, 165, 101, 26);
		rbtnNu.setFocusPainted(false);	// l??m cho ch??? ??? radio button kh??ng c?? vi???n
		customerInformation.add(rbtnNu);
		btg.add(rbtnNu);
		
		JLabel lblAdress = new JLabel("?????a ch???:");
		lblAdress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAdress.setBounds(691, 29, 68, 26);
		customerInformation.add(lblAdress);
		
			
			btnSave = new ButtonAmination("L??u");
			btnSave.setBounds(657, 173, 130, 60);
			customerInformation.add(btnSave);
			btnSave.setForeground(Color.WHITE);
			btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btnSave.setRadius(10);
			btnSave.setBorderColor(new Color(0x1796DA));
			btnSave.setBackground(new Color(0x1796DA));
			btnSave.setIcon(iconSave);
			
	
			
			btnEdit = new ButtonAmination("S???a");
			btnEdit.setBounds(813, 173, 130, 60);
			customerInformation.add(btnEdit);
			btnEdit.setForeground(Color.WHITE);
			btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btnEdit.setRadius(10);
			btnEdit.setBorderColor(new Color(0xFF9D69));
			btnEdit.setBackground(new Color(0xFF9D69));
			btnEdit.setIcon(iconEdit);
			
			
			btnDelete = new ButtonAmination("X??a");
			btnDelete.setBounds(964, 173, 130, 60);
			customerInformation.add(btnDelete);
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btnDelete.setRadius(10);
			btnDelete.setBorderColor(new Color(0xFB537C));
			btnDelete.setBackground(new Color(0xFB537C));
			btnDelete.setIcon(iconDelete);
			
			
			btnReset = new ButtonAmination("L??m m???i");
			btnReset.setBounds(1114, 173, 130, 60);
			customerInformation.add(btnReset);
			btnReset.setForeground(Color.WHITE);
			btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btnReset.setRadius(10);
			btnReset.setBorderColor(new Color(0x3DBCF6));
			btnReset.setBackground(new Color(0x3DBCF6));
			btnReset.setIcon(iconReset);
			
			JPanelAmination panel = new JPanelAmination();
			panel.setBackgroundColor(new Color(185, 131, 255));
			panel.setRadiusTopRight(15);
			panel.setRadiusTopLeft(15);
			panel.setRadiusBottomRight(15);
			panel.setRadiusBottomLeft(15);
			panel.setBackground(Color.WHITE);
			panel.setBounds(847, 29, 365, 97);
			customerInformation.add(panel);
			panel.setLayout(null);
			
			JPanelAmination panel_1 = new JPanelAmination();
			panel_1.setBackgroundColor(Color.WHITE);
			panel_1.setRadiusTopRight(15);
			panel_1.setRadiusTopLeft(15);
			panel_1.setRadiusBottomRight(15);
			panel_1.setRadiusBottomLeft(15);
			panel_1.setBounds(3, 3, 359, 91);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			tfAddress = new JTextArea();
			tfAddress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			tfAddress.setBounds(5, 5, 349, 81);
			panel_1.add(tfAddress);
			tfAddress.setBorder(null);
			tfAddress.setLineWrap(true);// thiet lap tach tu xuong dong
			tfAddress.setWrapStyleWord(true);
			
		JTabbedPane customerList = new JTabbedPane(JTabbedPane.TOP);
		customerList.setBackground(Color.WHITE);
		customerList.setBounds(0, 393, 1286, 370);
		contentPane.add(customerList);
		
		JPanel infoList = new JPanel();
		infoList.setBackground(Color.WHITE);
		customerList.addTab("Th??ng tin c?? nh??n", null, infoList, null);
		infoList.setLayout(null);
		
		String combo[]={"T???t c???","Nam","N???"};
		comboBoxForSearch = new JComboBox(combo);
		comboBoxForSearch.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxForSearch.setBounds(1065, 48, 146, 30);
		comboBoxForSearch.setBackground(Color.white);
		infoList.add(comboBoxForSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 103, 1281, 240);
		infoList.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setSelectionBackground(new Color(185, 131, 255));
		table.setDragEnabled(true);
		model.addColumn("M?? kh??ch h??ng");
		model.addColumn("H??? v?? t??n");
		model.addColumn("Gi???i t??nh");
		model.addColumn("S??? ??i???n tho???i");
		model.addColumn("?????a ch???");
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(320);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.getTableHeader().setBackground(new Color(0x94DAFF));
		table.getTableHeader().setForeground(Color.black);
		table.getTableHeader().setReorderingAllowed(false); //khong cho resize cot	
		table.setRowHeight(25);
		//can giua table
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer1);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer1);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer1);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer1);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer1);
		
		JLabel lblSearchForGender = new JLabel("Gi???i t??nh:");
		lblSearchForGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchForGender.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSearchForGender.setBounds(1065, 17, 81, 26);
		lblSearchForGender.setForeground(Color.BLACK);
		infoList.add(lblSearchForGender);
		
		JLabel lblSearch = new JLabel("T??m ki???m:");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setForeground(Color.BLACK);
		lblSearch.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSearch.setBounds(55, 17, 81, 29);
		infoList.add(lblSearch);
		
		btnSearchID = new ButtonAmination("");
		btnSearchID.setBounds(246, 48, 49, 32);
		btnSearchID.setBorderColor(new Color(185, 131, 255));
		btnSearchID.setRadius(15);
		infoList.add(btnSearchID);
		btnSearchID.setBackground(new Color(185, 131, 255));
		btnSearchID.setIcon(iconSearch);
		
		JPanelAmination panelSearchID = new JPanelAmination();
		panelSearchID.setLayout(null);
		panelSearchID.setRadiusTopRight(15);
		panelSearchID.setRadiusTopLeft(15);
		panelSearchID.setRadiusBottomRight(15);
		panelSearchID.setRadiusBottomLeft(15);
		panelSearchID.setBackgroundColor(new Color(185, 131, 255));
		panelSearchID.setBounds(55, 48, 202, 32);
		infoList.add(panelSearchID);
		
		JPanelAmination panelSearchID_1 = new JPanelAmination();
		panelSearchID_1.setLayout(null);
		panelSearchID_1.setRadiusTopRight(15);
		panelSearchID_1.setRadiusTopLeft(15);
		panelSearchID_1.setRadiusBottomRight(15);
		panelSearchID_1.setRadiusBottomLeft(15);
		panelSearchID_1.setBackgroundColor(Color.WHITE);
		panelSearchID_1.setBounds(3, 3, 189, 25);
		panelSearchID.add(panelSearchID_1);
		
		tfSearchID = new JTextField();
		tfSearchID.setForeground(Color.GRAY);
		tfSearchID.setText("M?? kh??ch h??ng");
		tfSearchID.setBounds(5, 2, 174, 20);
		panelSearchID_1.add(tfSearchID);
		tfSearchID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfSearchID.setBorder(null);
		tfSearchID.setColumns(10);
		tfSearchID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfSearchID.getText().equals("M?? kh??ch h??ng")) {
					tfSearchID.setText("");
				}
				else {
					tfSearchID.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfSearchID.getText().equals("")) {
					tfSearchID.setText("M?? kh??ch h??ng");
					tfSearchID.setForeground(Color.GRAY);
				}
			}
		});
		
		btnSearchName = new ButtonAmination("");
		btnSearchName.setRadius(15);
		btnSearchName.setBorderColor(new Color(185, 131, 255));
		btnSearchName.setBackground(new Color(185, 131, 255));
		btnSearchName.setBounds(657, 48, 49, 32);
		btnSearchName.setIcon(iconSearch);
		infoList.add(btnSearchName);
		
		JPanelAmination panelSearchName = new JPanelAmination();
		panelSearchName.setLayout(null);
		panelSearchName.setRadiusTopRight(15);
		panelSearchName.setRadiusTopLeft(15);
		panelSearchName.setRadiusBottomRight(15);
		panelSearchName.setRadiusBottomLeft(15);
		panelSearchName.setBackgroundColor(new Color(185, 131, 255));
		panelSearchName.setBounds(316, 48, 355, 32);
		infoList.add(panelSearchName);
		
		JPanelAmination panelSearchName_1 = new JPanelAmination();
		panelSearchName_1.setLayout(null);
		panelSearchName_1.setRadiusTopRight(15);
		panelSearchName_1.setRadiusTopLeft(15);
		panelSearchName_1.setRadiusBottomRight(15);
		panelSearchName_1.setRadiusBottomLeft(15);
		panelSearchName_1.setBackgroundColor(Color.WHITE);
		panelSearchName_1.setBounds(3, 3, 339, 25);
		panelSearchName.add(panelSearchName_1);
		
		tfSearchName = new JTextField();
		tfSearchName.setText("T??n kh??ch h??ng");
		tfSearchName.setForeground(Color.GRAY);
		tfSearchName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tfSearchName.setColumns(10);
		tfSearchName.setBorder(null);
		tfSearchName.setBounds(5, 2, 329, 20);
		panelSearchName_1.add(tfSearchName);
		tfSearchName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfSearchName.getText().equals("T??n kh??ch h??ng")) {
					tfSearchName.setText("");
				}
				else {
					tfSearchName.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfSearchName.getText().equals("")) {
					tfSearchName.setText("T??n kh??ch h??ng");
					tfSearchName.setForeground(Color.GRAY);
				}
			}
		});
		
		
		JPanel thongKe = new JPanel();
		thongKe.setBackground(Color.WHITE);
		customerList.addTab("Th???ng k?? c?? nh??n", null, thongKe, null);
		thongKe.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 10, 1281, 333);
		thongKe.add(scrollPane_1);
		
		model2 = new DefaultTableModel() {
			@Override
		         public Class getColumnClass(int columnIndex) {
				    switch (columnIndex) {
				    case 0:
				    	return String.class;
				    case 1:
				    	return String.class;
				    case 2:
				    	return String.class;
				    case 3:
				    	return String.class;
				    case 4:
				    	return Boolean.class;
				    default:
				    	return String.class;
				    }
				   }
				  };
		
		table_1 = new JTable();
		model2.addColumn("H??? v?? t??n");
		model2.addColumn("S??? ??i???n tho???i");
		model2.addColumn("T???ng h??a ????n");
		model2.addColumn("T???ng ti???n");
		model2.addColumn("Kh??ch h??ng th??n thi???t");
		
		table_1.setModel(model2);
		
		table_1.setFillsViewportHeight(true);
		table_1.setShowVerticalLines(true);
		table_1.setSelectionBackground(new Color(185, 131, 255));
		table_1.setDragEnabled(true);
		
		scrollPane_1.setViewportView(table_1);
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(20);
		table_1.getColumnModel().getColumn(3).setResizable(false);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(80);
		table_1.getColumnModel().getColumn(4).setResizable(false);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(50);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table_1.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table_1.getTableHeader().setBackground(new Color(0x94DAFF));
		table_1.getTableHeader().setForeground(Color.black);
		table_1.getTableHeader().setReorderingAllowed(false); //khong cho resize cot	
		table_1.setRowHeight(25);
		//can giua table
		DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
		centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
		table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer2);
		table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer2);
		table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer2);
		table_1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer2);
		
		JLabel lblTitle1 = new JLabel("Thi???t l???p th??ng tin kh??ch h??ng");
		lblTitle1.setForeground(new Color(185, 131, 255));
		lblTitle1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitle1.setBounds(15, 72, 201, 29);
		contentPane.add(lblTitle1);
		
		showList();
		addController();
		
		table.setDefaultEditor(Object.class, null);
		table_1.setDefaultEditor(Object.class, null);

		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
	}
	
	public void showList() {
		ArrayList<DTO.Customer> dskh= new ArrayList<DTO.Customer>();
		khBUS.docDskh();
		dskh = khBUS.getDskh();
		model.setRowCount(0);
		
		int x = 0;
		try {
			x = dskh.size();
		} catch (Exception ex) {
			x = 0;
		}
		
		for (int i = 0; i < x; i++) {
			row[0] = dskh.get(i).getMaKH();
			row[1] = dskh.get(i).getName();
			if (dskh.get(i).getGender())
				row[2] = "Nam";
			else
				row[2] = "N???";

			row[3] = dskh.get(i).getPhoneNumber();
			row[4] = dskh.get(i).getAddress();
			model.addRow(row);
		}
	}
	
	public void showList_1(ArrayList<DTO.Customer> dskh) {
		model.setRowCount(0);
		int x = 0;
		try {
			x = dskh.size();
		} catch (Exception ex) {
			x = 0;
		}
		for (int i = 0; i < x; i++) {
			row[0] = dskh.get(i).getMaKH();
			row[1] = dskh.get(i).getName();
			if (dskh.get(i).getGender())
				row[2] = "Nam";
			else
				row[2] = "N???";

			row[3] = dskh.get(i).getPhoneNumber();
			row[4] = dskh.get(i).getAddress();
			model.addRow(row);
		}
	}

	private void addController() {
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				isSelectRow = true;
				
				if (i >= 0) {
					btnEdit.setEnabled(true);
					btnSave.setEnabled(false);
					btnDelete.setEnabled(true);
					try {
						tfAddress.setText(model.getValueAt(i, 4).toString());
					}catch (Exception e1) {
						tfAddress.setText("");
					}
					
					try {
						textmaKH.setText(model.getValueAt(i, 0).toString());
					}catch (Exception e1) {
						textmaKH.setText("");
					}
					
					try {
						textName.setText(model.getValueAt(i, 1).toString());
					}catch (Exception e2) {
						textName.setText("");

					}
					
					try {
						textPhoneNumber.setText(model.getValueAt(i, 3).toString());
					}catch (Exception e3) {
						textPhoneNumber.setText("");
					}
					
					try {
						if (model.getValueAt(i, 2).toString().equals("Nam"))
							rbtnMale.setSelected(true);
						else 
							rbtnNu.setSelected(true);
					}catch (Exception e1) {
						rbtnMale.setSelected(true);
					}
					
					Customer a = new Customer();
					a.setMaKH(textmaKH.getText());
					a.setName(textName.getText());
					a.setPhoneNumber(textPhoneNumber.getText());
					tkCaNhan(a);
					
				}
				
			}
		});
		
		table_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					if ((boolean) model2.getValueAt(0, 4))
						khBUS.themKHTT(textmaKH.getText(), true);
					else
						khBUS.themKHTT(textmaKH.getText(), false);
				}catch (Exception e1) {
					rbtnMale.setSelected(true);
				}
				
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				emptyForm();
				model2.setRowCount(0);
				textmaKH.setText("KH"+khBUS.maxMa());
				showList();
				isSelectRow = false;
				btnSave.setEnabled(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		});
		
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Customer kh = new Customer();
				kh.setAddress(tfAddress.getText());
				
				boolean flag = true;
				
				if (rbtnMale.isSelected()) {
					kh.setGender(true);
				} else if (rbtnNu.isSelected()) {
					kh.setGender(false);
				} else {
					flag = false;
					JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
			                "B???n ch??a ch???n gi???i t??nh!",
			                "Th??ng b??o!",
			                JOptionPane.ERROR_MESSAGE);
				}
				try {
					kh.setPhoneNumber(Long.parseLong(textPhoneNumber.getText())+"");
				} catch (Exception e2) {
					// TODO: handle exception
					flag=false;
					JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
			                "S??? ??i???n tho???i kh??ng ????ng",
			                "Th??ng b??o!",
			                JOptionPane.INFORMATION_MESSAGE);
				}
				kh.setName(textName.getText());
				
				if (kh.getName().equals("")) {
					flag = false;
					JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
			                "B???n ch??a nh???p t??n kh??ch h??ng!",
			                "Th??ng b??o!",
			                JOptionPane.ERROR_MESSAGE);
				}
				
				if (flag) {
					if (khBUS.themkh(kh)) {
						JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
				                "Th??m kh??ch h??ng th??nh c??ng!",
				                "Th??ng b??o!",
				                JOptionPane.INFORMATION_MESSAGE);
						showList();
						emptyForm();//themdong nay
						textmaKH.setText("KH"+khBUS.maxMa());//them dong nay
					} else {
						JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
				                "Th??m kh??ch h??ng th???t b???i!",
				                "Th??ng b??o!",
				                JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelectRow) {
					Customer kh = new Customer();
					String maKH = textmaKH.getText();
					if (maKH.length() <= 2)
						maKH = "KH";
					kh.setMaKH(maKH);
					kh.setAddress(tfAddress.getText());
					if (rbtnMale.isSelected()) {
						kh.setGender(true);
					} else {
						kh.setGender(false);

					}
					kh.setPhoneNumber(textPhoneNumber.getText());
					kh.setName(textName.getText());
					
					if (khBUS.suakh(kh)) {
						JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
				                "S???a kh??ch h??ng th??nh c??ng!",
				                "Th??ng b??o!",
				                JOptionPane.INFORMATION_MESSAGE);
						showList();
						emptyForm();
						textmaKH.setText("KH"+khBUS.maxMa());//them dong nay
						btnEdit.setEnabled(false);
						btnSave.setEnabled(true);

					} else {
						JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
				                "S???a kh??ch h??ng th???t b???i!",
				                "Th??ng b??o!",
				                JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (khBUS.xoaKH(textmaKH.getText())) {
					JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
			                "X??a kh??ch h??ng th??nh c??ng!",
			                "Th??ng b??o!",
			                JOptionPane.INFORMATION_MESSAGE);
					showList();
					textmaKH.setText("KH"+khBUS.maxMa());//them dong nay
					btnDelete.setEnabled(true);
					btnDelete.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(CustomerManagerUI.getFrames()[0],
			                "X??a kh??ch h??ng th???t b???i!",
			                "Th??ng b??o!",
			                JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSearchID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				ArrayList<DTO.Customer> dskh= new ArrayList<DTO.Customer>();
				Customer kh = new Customer();
				kh =  khBUS.timKiemMa(tfSearchID.getText());
				if (kh != null)
					dskh.add(kh);
				if (dskh != null)
					showList_1(dskh);
			}
		});
		
		btnSearchName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				showList_1(khBUS.timKiemTen(tfSearchName.getText()));
			}
		});
		
		comboBoxForSearch.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxForSearch.getSelectedIndex() == 1) 
					showList_1(khBUS.timKiemGT("Nam"));
				else if (comboBoxForSearch.getSelectedIndex() == 2)
					showList_1(khBUS.timKiemGT("N???"));
				else 
					showList();
			}
		});
		
	}
	
	public void  tkCaNhan(Customer dskh) {
		model2.setRowCount(0);
		row1[0] = dskh.getName();
		row1[1] = dskh.getPhoneNumber();
		
		int soluong = khBUS.soLuong(dskh.getMaKH());
		
		row1[2] = soluong;

		row1[3] = khBUS.tongHD(dskh.getMaKH());
		if (khBUS.khtt(dskh.getMaKH())) {
			row1[4] = true;
		}
		else
			row1[4] = false;
		model2.addRow(row1);
	}
	
	public void emptyForm() {
		tfAddress.setText("");
		textmaKH.setText("");
		textName.setText("");
		textPhoneNumber.setText("");
		btg.clearSelection();
		
	}
}

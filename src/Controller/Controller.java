package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.Vector;

import javax.swing.JOptionPane;

import BUS.PersonnelManagement_BLL;
import DTO.PersonnelManagement_DTO;
import GUI.FormLogin;
import GUI.FormMenu;
import GUI.LoandingGUI;



public class Controller implements ActionListener,MouseListener{
	
	
	
	FormLogin login;
	LoandingGUI loading;
	boolean val_login=false;
	public Controller(FormLogin login)
	{
		this.login=login;
	}

	PersonnelManagement_BLL nvBUS=new PersonnelManagement_BLL();
	Vector<PersonnelManagement_DTO> arrnv=new Vector<>();
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==login.bt_login)
		{
			check_login();
		}
	}






	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==login.lb_X)
		{
			System.exit(0);
		}
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	void check_login()
	{
		boolean checknv=false;
		int manv = 0;
		String tk=new String(chuanHoa(login.tf_tk.getText()) );
		String mk=new String(login.tf_mk.getPassword());
		arrnv=nvBUS.getAllPersonnel();
		
		for(int i=0 ; i<arrnv.size();i++)
		{
			
			String tknv=chuanHoa(arrnv.get(i).getAccount());
			String mknv= chuanHoa(arrnv.get(i).getPassword());
		
			if(tk.equals(tknv) && mk.equals(mknv))
			{
				checknv=true;
				manv=arrnv.get(i).getPersonnelID();
				break;
			}
		}
		
		if(checknv==true)
		{
			
			login.dispose();
			loading=new LoandingGUI(manv);
			loading.setVisible(true);
			
		}
		else if(tk.equals("") || mk.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Vui l??ng nh???p ?????y ????? th??ng tin");
		}else
		{
			JOptionPane.showMessageDialog(null,"Tai kho???n ho???c m???t kh???u kh??ng ch??nh x??c");
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

}

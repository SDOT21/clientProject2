import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserInterface extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton submit;
	private JPanel center;
	private JPanel upper;
	private JButton getSpreadsheet;
	private JPanel dataPanel;
	private JPanel sortingInfo;
	private JComboBox<String> pdMenu;
	private JComboBox<String> teachMenu;
	private JComboBox<String>testMenu;
	private JButton submitIndexes;
	public UserInterface () {
		super("AP Exam Notifier App");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		upper= new JPanel();
		center=new JPanel();
		JLabel welcome= new JLabel("Welcome to the AP Exam Notifier");
		JLabel userLabel=new JLabel("Enter your username");
		JLabel passwordLabel=new JLabel("Enter your password");
		JTextField userText=new JTextField(20);
		JTextField passwordText=new JTextField(20);
		submit = new JButton("Submit");
		upper.add(welcome);
		center.add(userLabel);
		center.add(userText);
		center.add(passwordLabel);
		center.add(passwordText);
		center.add(submit);
		submit.addActionListener(this);
		this.add(upper,BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		
	}


	@SuppressWarnings("hiding")
	public void actionPerformed(ActionEvent e) {
		Object src= e.getSource();
		if(src==submit) {
			center.setVisible(false);
			//make sure username and password are legit
			dataPanel = new JPanel();
			JLabel directions=new JLabel("Please input your spreadsheet");
			getSpreadsheet= new JButton("Choose the Excel file");
			getSpreadsheet.addActionListener(this);
			dataPanel.add(directions);
			dataPanel.add(getSpreadsheet);
			this.add(dataPanel,BorderLayout.CENTER);
			dataPanel.setVisible(true);
		}
		else if(src==getSpreadsheet) {
			
			JFileChooser fileChooser= new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.showOpenDialog(null);
			File file =fileChooser.getSelectedFile();
			FileInputStream excelFile=null;
			Workbook workbook=null;
			try{
				excelFile = new FileInputStream(file);
				workbook = new XSSFWorkbook(excelFile);
				DataMatrix data= new DataMatrix(file,workbook);
				dataPanel.setVisible(false);
				System.out.println(data);
				sortingInfo= new JPanel();
				JLabel dir=new JLabel("Please verify that columns headings correspond with the labels.");
				dir.setSize(500,100);
				JLabel pdLabel=new JLabel("Period");
				data.makeFirst(2);
				pdMenu= new JComboBox<String>(data.getHeader());
				JLabel teachLabel=new JLabel("Teacher");
				data.makeFirst(3);
				teachMenu= new JComboBox<String>(data.getHeader());
				JLabel testLabel=new JLabel("Test Name");
				data.makeFirst(4);
				testMenu= new JComboBox<String>(data.getHeader());
				submitIndexes=new JButton("Submit");
				/*JPanel east=new JPanel();
				east.setSize(100, 300);
				JPanel west=new JPanel();
				west.setMinimumSize(100,300);
				this.add(east, BorderLayout.EAST);
				this.add(west, BorderLayout.WEST);*/
				//upper.add(dir);
				//upper.setVisible(true);
				sortingInfo.add(dir, BorderLayout.WEST);
				sortingInfo.add(pdLabel,BorderLayout.EAST);
				sortingInfo.add(pdMenu,BorderLayout.EAST);
				sortingInfo.add(teachLabel,BorderLayout.EAST);
				sortingInfo.add(teachMenu,BorderLayout.EAST);
				sortingInfo.add(testLabel,BorderLayout.EAST);
				sortingInfo.add(testMenu,BorderLayout.EAST);
				sortingInfo.add(submitIndexes);
				this.add(sortingInfo, BorderLayout.CENTER);
				sortingInfo.setVisible(true);
				
				
			} catch(NullPointerException e1) {
				JOptionPane.showMessageDialog(this,"A file was not selected");
			}catch (FileNotFoundException e2) {
				JOptionPane.showMessageDialog(this,"The file was not found");
			}catch (IOException e3) {
				JOptionPane.showMessageDialog(this, "Something when wrong. Try again.");//what causes IOException
			}catch(POIXMLException e0) {
				JOptionPane.showMessageDialog(this, "You did not select an Excel (.xlsx) file");
			}catch(NotOfficeXmlFileException e4) {
				JOptionPane.showMessageDialog(this, "You did not select an Excel (.xlsx) file");
			}
		}
		else if (src==submitIndexes) {
			int pdIndex= pdMenu.getSelectedIndex();
			int teachIndex=teachMenu.getSelectedIndex();
			
			
		}
		
		

	}

	public static void main(String[] args) {
		UserInterface gui=new UserInterface();
		gui.setVisible(true);
	}
}

package Proyecto_KWIC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class View {

	private JFrame frmKwic;
	private JTextField textField;
	private JTextArea textArea;
	private JTextArea textArea1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frmKwic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKwic = new JFrame();
		frmKwic.setTitle("KWIC");
		frmKwic.setBounds(100, 100, 922, 708);
		frmKwic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKwic.getContentPane().setLayout(null);

		JButton btnSubirArchivo = new JButton("Subir Archivo");
		btnSubirArchivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File archivo = fc.getSelectedFile();
				try {
					FileReader fr = new FileReader(archivo);
					BufferedReader br = new BufferedReader(fr);
					String texto="";
					String linea="";
					while(((linea=br.readLine())!=null)) {
						texto+=linea+"\n";
					}
					textArea.setText(texto);
					//JOptionPane.showMessageDialog(null, "Archivo Leido Correctamente");
				}catch(Exception e1) {
					
				}
			}
		});
		btnSubirArchivo.setBounds(102, 382, 138, 35);
		frmKwic.getContentPane().add(btnSubirArchivo);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(250, 382, 421, 35);
		frmKwic.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel titulo = new JLabel("Key Word In Context");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBounds(279, 41, 369, 35);
		frmKwic.getContentPane().add(titulo);
		

		
		textArea = new JTextArea();
		textArea.setEnabled(true);
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(102, 111, 698, 231);
		frmKwic.getContentPane().add(scrollPane);
		
		textArea1 = new JTextArea();
		textArea1.setEnabled(true);
		textArea1.setEditable(false);
		scrollPane1 = new JScrollPane(textArea1);
		scrollPane1.setBounds(102, 446, 698, 191);
		frmKwic.getContentPane().add(scrollPane1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBounds(681, 382, 119, 35);
		frmKwic.getContentPane().add(btnBuscar);
	}
}
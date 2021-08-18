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
				menu("hola mundo");
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBounds(681, 382, 119, 35);
		frmKwic.getContentPane().add(btnBuscar);
	}

	public static void menu(String buscar){
		String str= "titulos.txt";
		keywords cab=null;
		cab=enviar(str,cab);
		listado(cab);


	}

	public static keywords enviar(String str, keywords cab){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File (str);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null) {

				cab=adicionar(cab,linea,palabras_clave(linea),Circular(linea,palabras_clave(linea)));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if( null != fr ){
					fr.close();
				}
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}
		return cab;
	}

	public static String[] Circular(String str,String[] palabra){
		String pal[] = str.split(" ");
		String an="",des="",sum="";
		String cir[] = new String[palabra.length];
		Boolean x=false;
		for(int i=0;i<palabra.length;i++){
			sum="";
			an="";
			des="";
			x=false;
			for(int j=0;j<pal.length;j++){
				if(palabra[i].equalsIgnoreCase(pal[j])){
					x=true;
				}
				if(x){
					an=an+pal[j]+" ";
				}else{
					des=des+pal[j]+" ";
				}
			}
			sum=an+des;
			cir[i]=sum;
		}
		return cir;
	}

	public static String[] palabras_clave(String str){
		String v[]={"2ª","las","los","con","como","que","no","lo","en","de","la","y","al","el","o","a","por","tu","si","-","1","2","3","4","5","6","7","8","9","0","del","Los","El","La","Una","Un","Uso","En","Las","Los","los","Del"};
		String pal[] = str.split(" ");
		String cont="";
		Boolean centi=true,centi2=true;
		for(int i=0;i<pal.length;i++){
			for(int j=0;j<v.length;j++){
				if(pal[i].equalsIgnoreCase(v[j])){
					centi=true;

					break;
				}else{
					centi=false;
				}
			}
			if(centi!=true){
				if(i!=v.length-1){

					cont=cont+pal[i]+" ";
				}else{
					cont=cont+pal[i];
				}

			}

		}
		String palabras[] = cont.split(" ");


		return palabras;
	}

	public static keywords adicionar(keywords a,String pal,String[] palabras,String[] cir) {
		keywords c=null;
		keywords b=new keywords();
		b.titulos=pal;
		b.Palabras=palabras;
		b.iteraciones=cir;
		b.sgte=null;
		if(a==null) {
			a=b;
		}else {
			c=a;
			while(c.sgte!=null) {
				c=c.sgte;
			}
			c.sgte=b;
		}
		return a;
	}
	public static void listado(keywords c) {
		String pal[];
		while(c!=null) {
			System.out.print("Titulo : "+c.titulos);
			System.out.print("\nPalabras clave : ");
			for(int i=0;i< ((String[])c.Palabras).length ;i++){
				System.out.print("/"+((String[])c.Palabras)[i]);
			}
			System.out.print("\n");
			for(int i=0;i< ((String[])c.iteraciones).length ;i++){
				System.out.println("-->"+((String[])c.iteraciones)[i]);
			}
			System.out.print("\n");
			c=c.sgte;
		}

	}
}
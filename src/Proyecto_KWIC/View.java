package Proyecto_KWIC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class View extends JFrame implements ActionListener{

	private JTextField textField;
	private JTextArea textArea;
	private JTextArea textArea1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private JButton btnBuscar;
	private JButton btnSubirArchivo;
	private keywords cab;

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
		setTitle("KWIC");
		setBounds(100, 100, 922, 708);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel titulo = new JLabel("Key Word In Context");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBounds(279, 41, 369, 35);
		getContentPane().add(titulo);

		btnSubirArchivo = new JButton("Subir Archivo");
		btnSubirArchivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubirArchivo.addActionListener(this);
		btnSubirArchivo.setBounds(102, 382, 138, 35);
		getContentPane().add(btnSubirArchivo);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(250, 382, 421, 35);
		getContentPane().add(textField);
		textField.setColumns(10);


		textArea = new JTextArea();
		textArea.setEnabled(true);
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(102, 111, 698, 231);
		getContentPane().add(scrollPane);
		
		textArea1 = new JTextArea();
		textArea1.setEnabled(true);
		textArea1.setEditable(false);
		scrollPane1 = new JScrollPane(textArea1);
		scrollPane1.setBounds(102, 446, 698, 191);
		getContentPane().add(scrollPane1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBounds(681, 382, 119, 35);
		getContentPane().add(btnBuscar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSubirArchivo){
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			File archivo = fc.getSelectedFile();
			try {
				FileReader fr = new FileReader(archivo);
				BufferedReader br = new BufferedReader(fr);
				String texto="";
				String linea="";
				while(((linea=br.readLine())!=null)) {
					cab=adicionar(cab,linea,palabras_clave(linea),Circular(linea,palabras_clave(linea)));
					texto+=linea+"\n";
				}
				textArea.setText(texto);

			}catch(Exception e1) {

			}
		}else {
			if(e.getSource()==btnBuscar){
				textArea1.setText(buscar(cab,textField.getText()));
			}
		}
	}

	public static String buscar(keywords c,String bus){
		String titulo="";
		String busq[]=bus.split(" ");
		Boolean centi=false;
		int cc=0;
		while(c!=null) {
			// System.out.print("Titulo : "+c.titulos);
			for(int i=0;i< ((String[])c.iteraciones).length ;i++){
				String pal[]=((String[])c.iteraciones)[i].split(" ");
				for(int k=0;k<busq.length;k++){
					//System.out.println(" palabra busqueda : " + busq[k]);
					for(int u=0;u<pal.length;u++){

						// System.out.println(" palabras titulo : " + pal[u]);
						if(centi){

							if(busq[k].equalsIgnoreCase(pal[u])){
								u--;
								cc++;
								break;
							}
						}else{
							if(busq[k].equalsIgnoreCase(pal[0])){

								u--;
								cc++;
								centi=true;
								break;
							}
						}

					}
					if(k+1==busq.length){
						if(cc==busq.length){
							titulo+=c.titulos+"\n";
							System.out.println("Titulo : "+c.titulos);
							System.out.println("ENCONTRADO : =>"+((String[])c.iteraciones)[i]);

						}
						centi=false;
						cc=0;
					}
				}
			}


			c=c.sgte;
		}
		return titulo;
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
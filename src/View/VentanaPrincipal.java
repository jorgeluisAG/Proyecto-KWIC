package View;

import java.awt.EventQueue;

        import javax.swing.JFrame;
        import javax.swing.JTextField;
        import javax.swing.JButton;
        import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;
        import java.awt.Font;
        import javax.swing.JList;
        import javax.swing.JTextArea;
        import javax.swing.JScrollBar;
        import javax.swing.JComboBox;
        import javax.swing.JScrollPane;
        import javax.swing.JLabel;
        import javax.swing.JPanel;

public class VentanaPrincipal {

    private JFrame frmKwic;
    private JTextField textField;

    public VentanaPrincipal() {
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

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnBuscar.setBounds(102, 333, 119, 35);
        frmKwic.getContentPane().add(btnBuscar);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField.setBounds(266, 335, 534, 35);
        frmKwic.getContentPane().add(textField);
        textField.setColumns(10);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(449, 111, 352, 191);
        frmKwic.getContentPane().add(scrollPane1);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(449, 397, 351, 234);
        frmKwic.getContentPane().add(scrollPane2);

        JLabel titulo = new JLabel("Key Word In Context");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        titulo.setBounds(279, 41, 369, 35);
        frmKwic.getContentPane().add(titulo);

        JPanel panel = new JPanel();
        panel.setBounds(102, 111, 337, 191);
        frmKwic.getContentPane().add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(102, 397, 337, 234);
        frmKwic.getContentPane().add(panel_1);
    }

    public void setVible(boolean b) {
    }
}
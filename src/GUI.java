
import de.re.easymodbus.modbusclient.ModbusClient;
import de.re.easymodbus.modbusclient.ReceiveDataChangedListener;
import de.re.easymodbus.modbusclient.SendDataChangedListener;


import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Desktop.Action;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

public class GUI extends JFrame implements ReceiveDataChangedListener, SendDataChangedListener {
    JComboBox comboBox;
    private ModbusClient modbusClient;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JList<String> jList1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea1;
    private JTextField jTextFieldIPAddress;
    private JTextField jTextFieldNumberOfValues;
    private JTextField jTextFieldPort;
    private JTextField jTextFieldStartingAddress;
    private JPanel jpModbusTCP;
    private JPanel jpModbusRTU;
    private JLabel lblComport;
    private JTextField txtCom;
    private JTextField textField_1;
    private JLabel lblSlaveid;

    public GUI() {
        this.initComponents();
        this.modbusClient = new ModbusClient();
        this.modbusClient.setUnitIdentifier((byte) 0);
        this.modbusClient.addReveiveDataChangedListener(this);
        this.modbusClient.addSendDataChangedListener(this);
    }

    public void ReceiveDataChanged() {
        this.jTextArea1.append("Rx:");

        for(int i = 0; i < this.modbusClient.receiveData.length; ++i) {
            this.jTextArea1.append(" ");
            if (this.modbusClient.receiveData[i] < 16) {
                this.jTextArea1.append("0");
            }

            this.jTextArea1.append(Integer.toHexString(this.modbusClient.receiveData[i]));
        }

        this.jTextArea1.append("\n");
    }

    public void SendDataChanged() {
        this.jTextArea1.append("Tx:");

        for(int i = 0; i < this.modbusClient.sendData.length; ++i) {
            this.jTextArea1.append(" ");
            if (this.modbusClient.sendData[i] < 16) {
                this.jTextArea1.append("0");
            }

            this.jTextArea1.append(Integer.toHexString(this.modbusClient.sendData[i]));
        }

        this.jTextArea1.append("\n");
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jPanel1 = new JPanel();
        this.jLabel5 = new JLabel();
        this.jTextFieldStartingAddress = new JTextField();
        this.jLabel6 = new JLabel();
        this.jTextFieldNumberOfValues = new JTextField();
        this.jScrollPane1 = new JScrollPane();
        this.jList1 = new JList();
        this.jLabel2 = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.setDefaultCloseOperation(3);
        this.jLabel1.setToolTipText("");
        this.jLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                GUI.this.jLabel1MouseClicked(evt);
            }
        });
        this.jButton1.setText("Read Coils - FC1");
        this.jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                GUI.this.jButton1MouseClicked(evt);
            }
        });
        this.jButton2.setText("Read Discrete Inputs - FC2");
        this.jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                GUI.this.jButton2MouseClicked(evt);
            }
        });
        this.jButton3.setText("Read Holding Registers - FC3");
        this.jButton3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                GUI.this.jButton3MouseClicked(evt);
            }
        });
        this.jButton4.setText("Read Input Registers - FC4");
        this.jButton4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                GUI.this.jButton4MouseClicked(evt);
            }
        });
        this.jLabel5.setText("Starting Address");
        this.jTextFieldStartingAddress.setText("1");
        this.jLabel6.setText("Number of Values");
        this.jTextFieldNumberOfValues.setText("1");
        this.jScrollPane1.setViewportView(this.jList1);
        this.jLabel2.setForeground(new Color(0, 0, 204));
        this.jLabel2.setText("http://www.EasyModbusTCP.net");
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.jTextFieldStartingAddress, -2, 48, -2).addComponent(this.jTextFieldNumberOfValues, -2, 48, -2)).addGap(10).addComponent(this.jScrollPane1, -2, 0, 32767)).addComponent(this.jLabel2)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(21).addComponent(this.jLabel2).addGap(46).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel5).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTextFieldStartingAddress, -2, -1, -2).addGap(18).addComponent(this.jLabel6).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTextFieldNumberOfValues, -2, -1, -2)).addComponent(this.jScrollPane1, -2, 155, -2)).addContainerGap(-1, 32767)));
        this.jPanel1.setLayout(jPanel1Layout);
        this.comboBox = new JComboBox();
        this.comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                if ((String)arg0.getItem() == "Modbus TCP") {
                    GUI.this.modbusClient = new ModbusClient();
                    GUI.this.modbusClient.setUnitIdentifier((byte) 0);
                    GUI.this.jpModbusTCP.setVisible(true);
                    GUI.this.jpModbusRTU.setVisible(false);
                } else {
                    GUI.this.modbusClient = new ModbusClient();
                    GUI.this.modbusClient.setUnitIdentifier((byte) 0);
                    GUI.this.jpModbusRTU.setVisible(true);
                    GUI.this.jpModbusTCP.setVisible(false);
                }

            }
        });
        this.comboBox.setModel(new DefaultComboBoxModel(new String[]{"Modbus TCP", "Modbus RTU"}));
        this.jpModbusTCP = new JPanel();
        this.jpModbusRTU = new JPanel();
        this.jpModbusRTU.setVisible(false);
        this.lblComport = new JLabel();
        this.lblComport.setText("COM-Port");
        this.txtCom = new JTextField();
        this.txtCom.setToolTipText("");
        this.txtCom.setText("COM1");
        this.textField_1 = new JTextField();
        this.textField_1.setText("1");
        this.lblSlaveid = new JLabel();
        this.lblSlaveid.setText("Slave-ID");
        GroupLayout gl_jpModbusRTU = new GroupLayout(this.jpModbusRTU);
        gl_jpModbusRTU.setHorizontalGroup(gl_jpModbusRTU.createParallelGroup(Alignment.LEADING).addGap(0, 174, 32767).addGroup(Alignment.TRAILING, gl_jpModbusRTU.createSequentialGroup().addContainerGap().addGroup(gl_jpModbusRTU.createParallelGroup(Alignment.LEADING).addComponent(this.lblComport).addComponent(this.txtCom, -2, 108, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_jpModbusRTU.createParallelGroup(Alignment.TRAILING).addGroup(gl_jpModbusRTU.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblSlaveid, -1, 46, 32767)).addComponent(this.textField_1, -2, 46, -2)).addContainerGap()));
        gl_jpModbusRTU.setVerticalGroup(gl_jpModbusRTU.createParallelGroup(Alignment.LEADING).addGap(0, 51, 32767).addGroup(gl_jpModbusRTU.createSequentialGroup().addGroup(gl_jpModbusRTU.createParallelGroup(Alignment.BASELINE).addComponent(this.lblComport).addComponent(this.lblSlaveid)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_jpModbusRTU.createParallelGroup(Alignment.BASELINE).addComponent(this.textField_1, -2, -1, -2).addComponent(this.txtCom, -2, -1, -2)).addContainerGap()));
        this.jpModbusRTU.setLayout(gl_jpModbusRTU);
        this.jTextArea1 = new JTextArea();
        this.jTextArea1.setEditable(false);
        this.jTextArea1.setBackground(new Color(204, 204, 204));
        this.jTextArea1.setColumns(20);
        this.jTextArea1.setRows(5);
        GroupLayout groupLayout = new GroupLayout(this.getContentPane());
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel1).addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(this.comboBox, -2, 174, -2)).addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(10).addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton1, -1, 174, 32767)).addComponent(this.jButton2, -1, 174, 32767).addComponent(this.jButton3, -1, -1, 32767).addComponent(this.jButton4, -1, -1, 32767))).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(this.jpModbusTCP, -2, 174, -2)).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(this.jpModbusRTU, -2, 174, -2))).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2)).addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(this.jScrollPane2, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTextArea1))).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(this.jLabel1).addGap(11).addComponent(this.comboBox, -2, -1, -2).addGap(7).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jPanel1, Alignment.LEADING, -1, -1, 32767).addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addComponent(this.jpModbusTCP, -2, -1, -2).addGap(2).addComponent(this.jpModbusRTU, -2, 51, -2).addGap(18).addComponent(this.jButton1).addGap(11).addComponent(this.jButton2).addGap(11).addComponent(this.jButton3).addGap(11).addComponent(this.jButton4))).addGap(6).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.jScrollPane2, -2, 103, -2).addComponent(this.jTextArea1, -2, 101, -2))));
        this.jLabel3 = new JLabel();
        this.jLabel3.setText("IP-Address");
        this.jLabel4 = new JLabel();
        this.jLabel4.setText("Port");
        this.jTextFieldIPAddress = new JTextField();
        this.jTextFieldIPAddress.setText("192.168.200.1");
        this.jTextFieldIPAddress.setToolTipText("");
        this.jTextFieldPort = new JTextField();
        this.jTextFieldPort.setText("6607");
        GroupLayout gl_jpModbusTCP = new GroupLayout(this.jpModbusTCP);
        gl_jpModbusTCP.setHorizontalGroup(gl_jpModbusTCP.createParallelGroup(Alignment.LEADING).addGroup(gl_jpModbusTCP.createSequentialGroup().addContainerGap().addGroup(gl_jpModbusTCP.createParallelGroup(Alignment.TRAILING).addGroup(gl_jpModbusTCP.createSequentialGroup().addComponent(this.jLabel3).addGap(75)).addGroup(gl_jpModbusTCP.createSequentialGroup().addComponent(this.jTextFieldIPAddress, -2, 122, -2).addPreferredGap(ComponentPlacement.RELATED))).addGroup(gl_jpModbusTCP.createParallelGroup(Alignment.LEADING).addComponent(this.jTextFieldPort, -2, 46, -2).addComponent(this.jLabel4)).addContainerGap()));
        gl_jpModbusTCP.setVerticalGroup(gl_jpModbusTCP.createParallelGroup(Alignment.LEADING).addGroup(gl_jpModbusTCP.createSequentialGroup().addGroup(gl_jpModbusTCP.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel4)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_jpModbusTCP.createParallelGroup(Alignment.BASELINE).addComponent(this.jTextFieldPort, -2, -1, -2).addComponent(this.jTextFieldIPAddress, -2, -1, -2)).addContainerGap()));
        this.jpModbusTCP.setLayout(gl_jpModbusTCP);
        this.getContentPane().setLayout(groupLayout);
        this.pack();
    }

    private void jLabel1MouseClicked(MouseEvent evt) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Action.BROWSE)) {
                try {
                    desktop.browse(new URI("www.easymodbustcp.net"));
                } catch (IOException var4) {
                    var4.printStackTrace();
                } catch (URISyntaxException var5) {
                    var5.printStackTrace();
                }
            }
        }

    }

    private void jButton1MouseClicked(MouseEvent evt) {
        if (!this.modbusClient.isConnected()) {
            if (this.comboBox.getSelectedItem() == "Modbus TCP") {
                this.modbusClient.setipAddress(this.jTextFieldIPAddress.getText());
                this.modbusClient.setPort(Integer.valueOf(this.jTextFieldPort.getText()));

                try {
                    this.modbusClient.Connect();
                } catch (Exception var8) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            } else {
                try {
                    this.modbusClient.setUnitIdentifier((byte)0);
                    this.modbusClient.Connect(this.txtCom.getText());
                } catch (Exception var7) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            }
        }

        DefaultListModel listModel = new DefaultListModel();
        int startingAddress = Integer.valueOf(this.jTextFieldStartingAddress.getText()) - 1;
        int numberOfValues = Integer.valueOf(this.jTextFieldNumberOfValues.getText());

        try {
            boolean[] serverResponse = this.modbusClient.ReadCoils(startingAddress, numberOfValues);

            for(int i = 0; i < serverResponse.length; ++i) {
                listModel.addElement(serverResponse[i]);
            }
        } catch (Exception var9) {
            JOptionPane.showMessageDialog((Component)null, "Server response error", "Connection failed", 2);
        }

        this.jList1.setModel(listModel);
    }

    private void jButton2MouseClicked(MouseEvent evt) {
        if (!this.modbusClient.isConnected()) {
            if (this.comboBox.getSelectedItem() == "Modbus TCP") {
                this.modbusClient.setipAddress(this.jTextFieldIPAddress.getText());
                this.modbusClient.setPort(Integer.valueOf(this.jTextFieldPort.getText()));

                try {
                    this.modbusClient.setUnitIdentifier((byte)0);
                    this.modbusClient.Connect();
                } catch (Exception var8) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            } else {
                try {
                    this.modbusClient.Connect(this.txtCom.getText());
                } catch (Exception var7) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            }
        }

        DefaultListModel listModel = new DefaultListModel();
        int startingAddress = Integer.valueOf(this.jTextFieldStartingAddress.getText()) - 1;
        int numberOfValues = Integer.valueOf(this.jTextFieldNumberOfValues.getText());

        try {
            boolean[] serverResponse = this.modbusClient.ReadDiscreteInputs(startingAddress, numberOfValues);

            for(int i = 0; i < serverResponse.length; ++i) {
                listModel.addElement(serverResponse[i]);
            }
        } catch (Exception var9) {
            JOptionPane.showMessageDialog((Component)null, "Server response error ", "Connection failed", 2);
        }

        this.jList1.setModel(listModel);
    }

    private void jButton3MouseClicked(MouseEvent evt) {
        if (!this.modbusClient.isConnected()) {
            if (this.comboBox.getSelectedItem() == "Modbus TCP") {
                this.modbusClient.setipAddress(this.jTextFieldIPAddress.getText());
                this.modbusClient.setPort(Integer.valueOf(this.jTextFieldPort.getText()));
                try {
                    this.modbusClient.Connect();
                } catch (Exception var8) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            } else {
                try {
                    this.modbusClient.Connect(this.txtCom.getText());
                    System.out.println(this.txtCom.getText());
                } catch (Exception var7) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            }
        }

        DefaultListModel listModel = new DefaultListModel();
        int startingAddress = Integer.valueOf(this.jTextFieldStartingAddress.getText()) - 1;
        int numberOfValues = Integer.valueOf(this.jTextFieldNumberOfValues.getText());

        try {
            int[] serverResponse = this.modbusClient.ReadHoldingRegisters(startingAddress, numberOfValues);

            for(int i = 0; i < serverResponse.length; ++i) {
                listModel.addElement(serverResponse[i]);
            }
        } catch (Exception var9) {
            JOptionPane.showMessageDialog((Component)null, "Server response error", "Connection failed", 2);
        }

        this.jList1.setModel(listModel);
    }

    private void jButton4MouseClicked(MouseEvent evt) {
        if (!this.modbusClient.isConnected()) {
            if (this.comboBox.getSelectedItem() == "Modbus TCP") {
                this.modbusClient.setipAddress(this.jTextFieldIPAddress.getText());
                this.modbusClient.setPort(Integer.valueOf(this.jTextFieldPort.getText()));

                try {
                    this.modbusClient.Connect();
                } catch (Exception var8) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            } else {
                try {
                    this.modbusClient.Connect(this.txtCom.getText());
                } catch (Exception var7) {
                    JOptionPane.showMessageDialog((Component)null, "Connection failed", "Connection failed", 2);
                }
            }
        }

        DefaultListModel listModel = new DefaultListModel();
        int startingAddress = Integer.valueOf(this.jTextFieldStartingAddress.getText()) - 1;
        int numberOfValues = Integer.valueOf(this.jTextFieldNumberOfValues.getText());

        try {
            int[] serverResponse = this.modbusClient.ReadInputRegisters(startingAddress, numberOfValues);

            for(int i = 0; i < serverResponse.length; ++i) {
                listModel.addElement(serverResponse[i]);
            }
        } catch (Exception var9) {
            JOptionPane.showMessageDialog((Component)null, "Server response error", "Connection failed", 2);
        }

        this.jList1.setModel(listModel);
    }

    public static void main(String[] args) {
        try {
            LookAndFeelInfo[] var4;
            int var3 = (var4 = UIManager.getInstalledLookAndFeels()).length;

            for(int var2 = 0; var2 < var3; ++var2) {
                LookAndFeelInfo info = var4[var2];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new GUI()).setVisible(true);
            }
        });
    }
}

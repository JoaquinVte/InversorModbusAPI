import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import de.re.easymodbus.modbusclient.ReceiveDataChangedListener;
import de.re.easymodbus.modbusclient.SendDataChangedListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Test implements Runnable, ReceiveDataChangedListener, SendDataChangedListener {

    static ModbusClient modbusClient;
    int startingAddress;
    int numberOfValues;

    static {
        modbusClient = new ModbusClient("192.168.200.1", 6607);
        modbusClient.setUnitIdentifier((byte) 0);
        modbusClient.setConnectionTimeout(3000);
    }

    public Test() {

//        modbusClient.addReveiveDataChangedListener(this);
//        modbusClient.addSendDataChangedListener(this);

        conectar();
    }

    private void conectar() {
        try {
            modbusClient.Connect();
            // Se debe esperar al menos 1 segundo despues de conectar.
            Thread.sleep(1000);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection failed", "Connection failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void desconectar(){
        try {
            modbusClient.Disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Test());
        thread.start();
    }

    public void ReceiveDataChanged() {
        StringBuilder output = new StringBuilder("Rx:");

        for (int i = 0; i < modbusClient.receiveData.length; i++) {
            output.append(" ");
            if (modbusClient.receiveData[i] < 16) {
                output.append("0");
            }

            output.append(Integer.toHexString(modbusClient.receiveData[i]));
        }

        System.out.println(output);
    }

    public void SendDataChanged() {
        StringBuilder output = new StringBuilder("Tx:");

        for (int i = 0; i < modbusClient.sendData.length; i++) {
            output.append(" ");
            if (modbusClient.sendData[i] < 16) {
                output.append("0");
            }

            output.append(Integer.toHexString(modbusClient.sendData[i]));
        }

        System.out.println(output);
    }

    @Override
    public void run() {

        int period = 5000;

        while (true) {

            conectar();

            leer();

            desconectar();

            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void leer() {


        try {
            float acumulated_energy_yield = modbusClient.ReadHoldingRegisters(32106, 2)[1] / 100;
            float daily_energy_yield = modbusClient.ReadHoldingRegisters(32114, 2)[1] / 100;
            float pv1_voltage = modbusClient.ReadHoldingRegisters(32016, 1)[0] / 10;
            float pv1_current = modbusClient.ReadHoldingRegisters(32017, 1)[0] / 100;
            float pv2_voltage = modbusClient.ReadHoldingRegisters(32018, 1)[0] / 10;
            float pv2_current = modbusClient.ReadHoldingRegisters(32019, 1)[0] / 100;
            float inversor_temp = modbusClient.ReadHoldingRegisters(32087, 1)[0] / 10;
            float input_power = modbusClient.ReadHoldingRegisters(32080, 2)[1] ;
            float peak_active_power = modbusClient.ReadHoldingRegisters(32078, 2)[1] / 1000;
            int number_of_strings = modbusClient.ReadHoldingRegisters(30071, 1)[0];
            float exported1 = modbusClient.ReadHoldingRegisters(37113, 2)[0];
            float exported2 = modbusClient.ReadHoldingRegisters(37113, 2)[1];
            float exported = exported2 - exported1;

            System.out.println("Acumulated energy yield: " + acumulated_energy_yield + "kwh");
            System.out.println("Daily energy yield: " + daily_energy_yield + "kwh");
            System.out.println("Peak active power: " + peak_active_power + "kw");
            System.out.println("Number of strings: " + number_of_strings);
            System.out.println("Internal temperature: " + inversor_temp + "ºC");
            System.out.println("Input power: " + input_power + "w");
            System.out.println("Feeding: " + exported2 + "w");
            //System.out.println("Exported1 " + exported1);
            //System.out.println("Exported2 " + exported2);
            System.out.println("Total " + ((exported > 0) ? "feeding: " : "obtain: ") + exported2 + "w");
            System.out.println(((exported > 0) ? "Feeding: " : "Obtain: ") + exported + "w");
            System.out.println("PV1 Voltage: " + pv1_voltage + "V");
            System.out.println("PV1 Current: " + pv1_current + "A");
            System.out.println("PV2 Voltage: " + pv2_voltage + "V");
            System.out.println("PV2 Current: " + pv2_current + "A");


        } catch (Exception var9) {
            JOptionPane.showMessageDialog(null, "Server response error", "Connection failed", 2);
        }
    }

}

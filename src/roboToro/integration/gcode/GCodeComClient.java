package roboToro.integration.gcode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;
import com.serialpundit.core.SerialComException;
import com.serialpundit.serial.SerialComManager;

import com.serialpundit.serial.SerialComManager.BAUDRATE;
import com.serialpundit.serial.SerialComManager.DATABITS;
import com.serialpundit.serial.SerialComManager.FLOWCONTROL;
import com.serialpundit.serial.SerialComManager.PARITY;
import com.serialpundit.serial.SerialComManager.STOPBITS;

public class GCodeComClient {

	SerialComManager scm;// = new SerialComManager();
	long handle;

	public GCodeComClient() throws IOException {
		// TODO Auto-generated constructor stub
		comPort = SerialPort.getCommPorts()[0];
		// comPort.
		comPort.openPort();
		comPort.setBaudRate(115200);
		sendCommand("IsDelta");
		sendCommand("IsDelta");
		sendCommand("G28");
		sendCommand("X45 Y98 Z-240.5");
	}

	public void close() {
		try {
			scm.closeComPort(handle);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	static SerialPort comPort;

	public static void main(String[] args) {
		try {
			// SerialPort comPort
			comPort = SerialPort.getCommPorts()[0];
			// comPort.
			comPort.openPort();
			comPort.setBaudRate(115200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validateConnectionTest() {
		byte[] writeBuffer = "IsDelta\n\r".getBytes();

		return true;
	}

	public boolean sendCommand(String s) {

		byte[] writeBuffer = (s + "\n\r").getBytes();
		int numRead;
		byte[] readBuffer;
		//readBuffer = new byte[comPort.bytesAvailable()];
		
		try {
			comPort.writeBytes(writeBuffer, (long) writeBuffer.length);
			while (comPort.bytesAvailable() == 0)// || comPort.bytesAvailable() == -1)
				Thread.sleep(20);

			readBuffer = new byte[comPort.bytesAvailable()];
			numRead = comPort.readBytes(readBuffer, readBuffer.length);
			System.out.println("Read " + numRead + " bytes." + new String(readBuffer, StandardCharsets.US_ASCII));
			// writeBuffer = "G28\n\r".getBytes();
			//numRead = comPort.readBytes(readBuffer, readBuffer.length);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public boolean sendCommand(ArrayList<String> s) {
		
		sendCommand("IsDelta");
		sendCommand("IsDelta");
		sendCommand("G28");
		sendCommand("X45 Y98 Z-240.5");
		return true;
			// G01 X42 Y24 Z-365.5 W0
			// writeBuffer = "G01 X42 Y24 Z-365.5 W0\r\n".getBytes();

			
	}

}

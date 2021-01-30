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
		scm = new SerialComManager();
		// "/dev/ttyACM0"
		// USB\VID_1A86&PID_7523

		handle = scm.openComPort("USB\\VID_1A86&PID_7523", true, true, false);
		scm.configureComPortData(handle, DATABITS.DB_8, STOPBITS.SB_1, PARITY.P_NONE, BAUDRATE.B115200, 0);
		scm.configureComPortControl(handle, FLOWCONTROL.NONE, 'x', 'x', false, false);
		// scm.writeString(handle, "testing hello", 0) == true);
		scm.writeString(handle, "IsDelta", 0);
		scm.writeString(handle, "IsDelta", 0);
		String data = scm.readString(handle);
		System.out.println("data read is :" + data);
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
			//SerialPort comPort 
			comPort = SerialPort.getCommPorts()[0];
			// comPort.
			comPort.openPort();
			comPort.setBaudRate(115200);
			// byte[] readBuffer = new byte[comPort.bytesAvailable()];
			/*
			 * G01 X42 Y24 Z-352.5 W0 G01 X42 Y24 Z-365.5 W0 G01 X45 Y98 Z-365.5 W0 G01 X45
			 * Y98 Z-340.5 W0
			 */

			byte[] writeBuffer = "IsDelta\n\r".getBytes();
			int numRead;
			byte[] readBuffer;//
			readBuffer = new byte[comPort.bytesAvailable()];
			numRead = comPort.readBytes(readBuffer, readBuffer.length);
			try {
				comPort.writeBytes(writeBuffer, (long) writeBuffer.length);

				// G01 X42 Y24 Z-365.5 W0
				// writeBuffer = "G01 X42 Y24 Z-365.5 W0\r\n".getBytes();

				while (true) {

					writeBuffer = "IsDelta\r\n".getBytes();
					comPort.writeBytes(writeBuffer, (long) writeBuffer.length);
					while (comPort.bytesAvailable() == 0)// || comPort.bytesAvailable() == -1)
						Thread.sleep(20);
					writeBuffer = "IsDelta\r\n".getBytes();
					comPort.writeBytes(writeBuffer, (long) writeBuffer.length);
					while (comPort.bytesAvailable() == 0)// || comPort.bytesAvailable() == -1)
						Thread.sleep(20);
					readBuffer = new byte[comPort.bytesAvailable()];
					numRead = comPort.readBytes(readBuffer, readBuffer.length);
					System.out
							.println("Read " + numRead + " bytes." + new String(readBuffer, StandardCharsets.US_ASCII));
					// writeBuffer = "G28\n\r".getBytes();
					// comPort.writeBytes(writeBuffer, (long)writeBuffer.length);
					writeBuffer = "X45 Y98 Z-240.5\r\n".getBytes();
					comPort.writeBytes(writeBuffer, (long) writeBuffer.length);
					// writeBuffer = "G28\n\r".getBytes();
					// comPort.writeBytes(writeBuffer, (long)writeBuffer.length);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// comPort.closePort();
			/*
			 * SerialComManager scm = new SerialComManager(); // "/dev/ttyACM0" long handle
			 * = scm.openComPort("\\USB\\VID_1A86&PID_7523", true, true, true);
			 * scm.configureComPortData(handle, DATABITS.DB_8, STOPBITS.SB_1, PARITY.P_NONE,
			 * BAUDRATE.B115200, 0); scm.configureComPortControl(handle, FLOWCONTROL.NONE,
			 * 'x', 'x', false, false); // scm.writeString(handle, "testing hello", 0) ==
			 * true); scm.writeString(handle, "IsDelta", 0); scm.writeString(handle,
			 * "IsDelta", 0); String data = scm.readString(handle);
			 * System.out.println("data read is :" + data); scm.closeComPort(handle);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean sendCommand(ArrayList<String> s) {
		
		byte[] writeBuffer = "IsDelta\n\r".getBytes();
		int numRead;
		//byte[] writeBuffer = "IsDelta\n\r".getBytes();
		//int numRead;
		byte[] readBuffer;//
		readBuffer = new byte[comPort.bytesAvailable()];
		numRead = comPort.readBytes(readBuffer, readBuffer.length);
		
		try {
			comPort.writeBytes(writeBuffer, (long) writeBuffer.length);

			// G01 X42 Y24 Z-365.5 W0
			// writeBuffer = "G01 X42 Y24 Z-365.5 W0\r\n".getBytes();

			while (true) {


				while (comPort.bytesAvailable() == 0)// || comPort.bytesAvailable() == -1)
					Thread.sleep(20);
				
				readBuffer = new byte[comPort.bytesAvailable()];
				numRead = comPort.readBytes(readBuffer, readBuffer.length);
				System.out
						.println("Read " + numRead + " bytes." + new String(readBuffer, StandardCharsets.US_ASCII));
				// writeBuffer = "G28\n\r".getBytes();
				// comPort.writeBytes(writeBuffer, (long)writeBuffer.length);
				writeBuffer = "X45 Y98 Z-240.5\r\n".getBytes();
				comPort.writeBytes(writeBuffer, (long) writeBuffer.length);
				// writeBuffer = "G28\n\r".getBytes();
				// comPort.writeBytes(writeBuffer, (long)writeBuffer.length);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

}

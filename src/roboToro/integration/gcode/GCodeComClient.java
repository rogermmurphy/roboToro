package roboToro.integration.gcode;

import java.io.IOException;

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
		handle = scm.openComPort("/dev/ttyUSB1", true, true, false);
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
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		try {
			SerialComManager scm = new SerialComManager();
			// "/dev/ttyACM0"
			long handle = scm.openComPort("/dev/ttyUSB1", true, true, false);
			scm.configureComPortData(handle, DATABITS.DB_8, STOPBITS.SB_1, PARITY.P_NONE, BAUDRATE.B115200, 0);
			scm.configureComPortControl(handle, FLOWCONTROL.NONE, 'x', 'x', false, false);
			// scm.writeString(handle, "testing hello", 0) == true);
			scm.writeString(handle, "IsDelta", 0);
			scm.writeString(handle, "IsDelta", 0);
			String data = scm.readString(handle);
			System.out.println("data read is :" + data);
			scm.closeComPort(handle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

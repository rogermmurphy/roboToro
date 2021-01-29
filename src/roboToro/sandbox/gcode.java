package roboToro.sandbox;
//maven dependency com.github.igor-suhorukov:camel-gcode:0.1

import roboToro.integration.gcode.GCodeClient;


public class gcode {

  public static void main(String[] args) throws Exception {

      String hostname = "beaglebone.local";

      int port = 5007;
      try (GCodeClient gcodeclient = new GCodeClient(hostname, port)) {
          System.out.println(gcodeclient.login("emc", "jvm", "1.0"));
          gcodeclient.sendCommand("set mode manual");

          gcodeclient.sendCommand("set estop off");

          gcodeclient.sendCommand("set machine on");

          for (int axis = 0; axis < 4; axis++)

              gcodeclient.sendCommand("set home " + axis);

          System.out.println(gcodeclient.sendCommand("get abs_act_pos"));

          gcodeclient.sendCommand("set mode mdi");

          gcodeclient.sendCommand("set mdi g0 x3 y4");

          gcodeclient.sendCommand("set mdi g0 x5 y2");

      }

  }

}
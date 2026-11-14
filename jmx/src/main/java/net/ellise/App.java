package net.ellise;

import javax.management.ObjectName;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class App {
    private static void log(Exception e) {
        System.err.println("[ERROR] " + e.getMessage());
        e.printStackTrace();
    }

    /*
    -Dcom.sun.management.jmxremote.port=9010
    -Dcom.sun.management.jmxremote.authenticate=false
    -Dcom.sun.management.jmxremote.ssl=false
     */
    public static void main(String[] args) {
        boolean alive = true;

        RequestCounter requestCounter = new RequestCounter();
        try {
            ManagementFactory.getPlatformMBeanServer().
                    registerMBean(requestCounter, new ObjectName("net.ellise:type=RequestCounter"));
        } catch (Exception e) {
            log(e);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (alive) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException ioe) {
                log(ioe);
                alive = false;
            }

            if (line != null && line.equalsIgnoreCase("exit")) {
                alive = false;
            } else {
                requestCounter.increment();
                System.out.println("Processed: " + line);
            }
        }
    }
}

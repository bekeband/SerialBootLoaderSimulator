
import com.fazecast.jSerialComm.*;

import java.util.ArrayList;

public class Simulator {


    public Simulator() {

    }

    public void Run() {

        SerialInOut serialInOut = new SerialInOut();
        Controller controller = new Controller();
        while (true) {
            byte[] readBuffer = serialInOut.readNextPacket();
            ProcessFrame processFrame = new ProcessFrame();


            try {

                ArrayList<Byte> result = processFrame.TranslatePacket(readBuffer);

                ProcessCommands processCommands = new ProcessCommands(serialInOut, controller);
                processCommands.Process(result);

            } catch (PacketException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "Simulator{}";
    }

}

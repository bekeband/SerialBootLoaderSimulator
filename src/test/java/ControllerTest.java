import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    byte[] TestBuffer = {3, 2, 0, 0, 4, 0, 0, -6, 2, 0, 0, 4, 31, -64, 27, 4, -1, -64, 0,
            -1, -1, -1, -121, -71, 2, 0, 0, 4, 0, 0, -6, 2, 0, 0, 4, 31, -64,
            27, 4, -1, -60, 0, 26, -79, -7, 127, -10, 2, 0, 0, 4, 0, 0, -6,
            2, 0, 0, 4, 31, -64, 27, 4, -1, -56, 0, -7, -4, 116, 3, -55, 2,
            0, 0, 4, 0, 0, -6, 2, 0, 0, 4, 31, -64, 27};

    byte[] LongTestBuffer = {
            (byte)0x03,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x1D,(byte)0x00,(byte)0xDD,
            (byte)0x10,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x75,(byte)0x2B,(byte)0x42,(byte)0x0B,(byte)0x00,
            (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x1B,(byte)0xE4,(byte)0x41,(byte)0x0B,(byte)0x00,(byte)0x00,
            (byte)0x00,(byte)0x00,(byte)0xB6,
            (byte)0x10,(byte)0x02,(byte)0x10,(byte)0x00,(byte)0x0B,(byte)0xE5,(byte)0x41,(byte)0x0B,(byte)0x00,
            (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x93,(byte)0xE4,(byte)0x41,(byte)0x0B,
            (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xDF,
            (byte)0x10,(byte)0x02,(byte)0x20,(byte)0x00,(byte)0x00,(byte)0x00,
            (byte)0x02,(byte)0x3C,(byte)0x00,(byte)0x00,(byte)0x42,(byte)0x24,(byte)0x05,(byte)0x00,(byte)0x40,
            (byte)0x10,(byte)0x00,
            (byte)0x00,(byte)0x02,(byte)0x3C,(byte)0x97,(byte)0x10,(byte)0x02,(byte)0x30,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x42,
            (byte)0x24,(byte)0x03,(byte)0x00,(byte)0x40,(byte)0x10,(byte)0x0A,(byte)0x9D,(byte)0x02,(byte)0x3C,(byte)0x3F,(byte)0x00,
            (byte)0x00,(byte)0x70,(byte)0x71,(byte)0x10,(byte)0x02,(byte)0x40,(byte)0x00,(byte)0x0A,(byte)0x9D,(byte)0x02,(byte)0x3C,
            (byte)0xB8,(byte)0xFE,(byte)0x42,(byte)0x24,(byte)0xFD,(byte)0xFF,(byte)0x40,
            (byte)0x10,(byte)0x00,(byte)0x00,(byte)0x00,
            (byte)0x00,(byte)0x61,(byte)0x08,(byte)0x02,(byte)0x50,(byte)0x00,(byte)0x09,(byte)0xF8,(byte)0x40,(byte)0x00,(byte)0x00,
            (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x65,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0xFA,
            (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x1F,(byte)0xC0,(byte)0x1B,
            (byte)0x10,(byte)0x06,(byte)0x34,(byte)0x00,
            (byte)0x00,(byte)0x00,(byte)0x00,(byte)0xD0,(byte)0x1F,(byte)0x00,(byte)0xC0,(byte)0x00,(byte)0x1F,(byte)0x00,(byte)0xC4,
            (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xD2,(byte)0x52,
            (byte)0x10,(byte)0x06,(byte)0x44,(byte)0x00,(byte)0x1F,
            (byte)0x00,(byte)0xC8,(byte)0x00,(byte)0x1F,(byte)0x00,(byte)0xCC,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xF0,
            (byte)0x17,(byte)0x00,(byte)0xC0,(byte)0x00,(byte)0x0D};

        byte[] RealTestBuffer = {
     (byte)0x03,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0xFA,
                (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x1F,(byte)0xC0,(byte)0x1B,
                (byte)0x04,(byte)0x2F,(byte)0xF4,(byte)0x00,(byte)0xD9,(byte)0xFF,(byte)0xF8,(byte)0xFF,(byte)0x0A,
                (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0xFA,
                (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x1F,(byte)0xC0,(byte)0x1B,
                (byte)0x04,(byte)0x2F,(byte)0xF8,(byte)0x00,(byte)0xFB,(byte)0x1D,(byte)0x7F,(byte)0xFF,(byte)0x3F,
                (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0xFA,
                (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x1F,(byte)0xC0,(byte)0x1B,
                (byte)0x04,(byte)0x2F,(byte)0xFC,(byte)0x00,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0x7F,(byte)0x55,
                (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0xFA,
                (byte)0x02,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x1F,(byte)0xC0,(byte)0x1B

/*                (byte)0x03,(byte)0x10,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x60,(byte)0x1A,(byte)0x40,(byte)0xC0,(byte)0x04,(byte)0x5A,(byte)0x7F,(byte)0x05,(byte)0x00,(byte)0x40,
                (byte)0x13,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x41,(byte)0x10,(byte)0x00,(byte)0x10,(byte)0x00,(byte)0x08,(byte)0x9D,(byte)0x1A,(byte)0x3C,(byte)0xD4,(byte)0xF1,
                (byte)0x5A,(byte)0x27,(byte)0x08,(byte)0x00,(byte)0x40,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x54,(byte)0x10,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x00,
                (byte)0xA0,(byte)0x1D,(byte)0x3C,(byte)0xF8,(byte)0x7F,(byte)0xBD,(byte)0x27,(byte)0x01,(byte)0xA0,(byte)0x1C,(byte)0x3C,(byte)0x20,(byte)0x80,(byte)0x9C,(byte)0x27,(byte)0x20,
                (byte)0x10,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x02,(byte)0x60,(byte)0x09,(byte)0x40,(byte)0x20,(byte)0x58,(byte)0x20,(byte)0x01,(byte)0x80,(byte)0x1E,(byte)0x2A,(byte)0x7D,
                (byte)0x84,(byte)0x49,(byte)0x49,(byte)0x7D,(byte)0xA4,(byte)0x10,(byte)0x00,(byte)0x40,(byte)0x00,(byte)0x02,(byte)0x60,(byte)0x89,(byte)0x40,(byte)0xC0,(byte)0x00,(byte)0x00,
                (byte)0x00,(byte)0x00,(byte)0xE0,(byte)0xDC,(byte)0x41,(byte)0x02,(byte)0x60,(byte)0x8B,(byte)0x40,(byte)0x9B,(byte)0x10,(byte)0x00,(byte)0x50,(byte)0x00,(byte)0xC0,(byte)0x00,
                (byte)0x00,(byte)0x00,(byte)0x08,(byte)0x9D,(byte)0x08,(byte)0x3C,(byte)0x24,(byte)0xF2,(byte)0x08,(byte)0x25,(byte)0x09,(byte)0xF8,(byte)0x00,(byte)0x01,(byte)0xB2,(byte)0x10,
                (byte)0x00,(byte)0x60,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xA0,(byte)0x08,(byte)0x3C,(byte)0x98,(byte)0x00,(byte)0x08,(byte)0x25,(byte)0x00,
                (byte)0xA0,(byte)0x09,(byte)0x3C,(byte)0x02,(byte)0x10,(byte)0x00,(byte)0x70,(byte)0x00,(byte)0xC8,(byte)0x02,(byte)0x29,(byte)0x25,(byte)0x06,(byte)0x00,(byte)0x00,(byte)0x10,
                (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xAD,(byte)0xA5,(byte)0x10,(byte)0x00,(byte)0x80,(byte)0x00,(byte)0x04,(byte)0x00,(byte)0x00,
                (byte)0xAD,(byte)0x08,(byte)0x00,(byte)0x00,(byte)0xAD,(byte)0x0C,(byte)0x00,(byte)0x00,(byte)0xAD,(byte)0x10,(byte)0x00,(byte)0x08,(byte)0x25,(byte)0x14,(byte)0x10,(byte)0x00,
                (byte)0x90,(byte)0x00,(byte)0x2B,(byte)0x08,(byte)0x09,(byte)0x01,(byte)0xF9,(byte)0xFF,(byte)0x20,(byte)0x14,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x07,(byte)0x9D,
                (byte)0x08,(byte)0x3C,(byte)0x0F,(byte)0x10,(byte)0x00,(byte)0xA0,(byte)0x00,(byte)0xA4,(byte)0x54,(byte)0x08,(byte)0x25,(byte)0x00,(byte)0x00,(byte)0x09,(byte)0x8D,(byte)0x18,
                (byte)0x00,(byte)0x20,(byte)0x11,(byte)0x04,(byte)0x00,(byte)0x08,(byte)0x25,(byte)0x1B,*/


        };

@Test
    void interpreter() {
        ArrayList<Byte> TestList = new MakeByteArray().MakeArray(RealTestBuffer);
        Controller controller = new Controller();
        SimulatorUI simulatorUI = new SimulatorUI(controller);
        try {
            controller.Interpreter(TestList);
            simulatorUI.printHexList(controller.appMemoryArea, 0x1D000004, 0x1D000045);
/*            System.out.println("--------------- Config memory list ---------------------------");
            System.out.println(controller.printConfigMem());
            System.out.println("--------------- Boot Memory List -----------------------------");
            System.out.println(controller.printBootMem());
            System.out.println("--------------- App Memory List  -----------------------------");
            System.out.println(controller.printAppMem());*/
        } catch (ControllerException e) {
            System.out.println(e.getMessage());
        }
    }
}
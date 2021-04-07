import java.util.List;

public class SimulatorUI {
    Controller controller;

    public SimulatorUI(Controller controller) {
        this.controller = controller;
    }

    public void printHexList(List<Byte> memoryList, int startAddress, int endAddress) {

    }

    public String getHexString(List<Byte> memoryList, int startAddress, int endAddress) {
        String result = "";
        for (int i = 0; i < memoryList.size() / 16; i++) {
            result += String.format("%04X : ", i * 16);
            for (int j = 0; j < 16; j++) {
                result += String.format("%02X,", memoryList.get((i * 16) + j));
            }
            result += "\n\r";
        }
        return result;

    }

 /*   public String printAppMem(int startAddress, int endAddress) {
        return getHexString(appMemory, startAddress, endAddress);
    }

    public String printBootMem(int startAddress, int endAddress) {
        return getHexString(bootMemory, startAddress, endAddress);
    }

    public String printConfigMem(int startAddress, int endAddress) {
        return getHexString(configMemory, startAddress, endAddress);
    }*/
}

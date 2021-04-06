public class MemoryTypes {

    private int startAddress;
    private int endAddress;

    MemoryTypes(int startAddress, int endAddress) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }

    public boolean getInBounds(int address) {
        return ((address >= getStartAddress()) & (address <= getEndAddress()));
    }

    public int getMemorySize() {
        return (getEndAddress() - getStartAddress() + 1);
    }

    public int getArrayShift(int address) {
        return (address - getStartAddress());
    }

}

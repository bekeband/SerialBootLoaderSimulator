import java.util.ArrayList;

public class Controller {

    MemoryArea bootMemoryArea = new MemoryArea(0x1FC00000, 0x1FC02FEF);
    MemoryArea appMemoryArea = new MemoryArea(0x1D000000, 0x1D07FFFF);
    MemoryArea configMemoryArea = new MemoryArea(0x1FC02FF0, 0x1FC02FFF);

    int extendedLinearSegmentAddress = 0;
    int extendedSegmentAddress = 0;
    final int shiftAddress = 0;

    int ksegValue = 0;

       /*
    :BBAAAATTHHHH……………………………………………………………………HHCC
    : Record Start Character
    BB two digit byte count specifying the number of data bytes in this record.
    AAAA Four digit starting address of this data record
    TT Two digit record type
        00 = data record
        01 = End of File record
        02 = Segment Address Record
        04 = Extended Linear Address record
    HH Data Bytes
    CC Two digit checksum calculated as 2’s complement of all preceding bytes in data record except the colon.
     */

    public final int DATA_RECORD_TYPE = 0x00;
    public final int EOF_RECORD_TYPE = 0x01;
    public final int SEGM_ADDR_RECORD_TYPE = 0x02;

    /**
     * Extended Linear Address Records (HEX386)
     * Extended linear address records are also known as 32-bit address records and HEX386 records. These records
     * contain the upper 16 bits (bits 16-31) of the data address. The extended linear address record always has two
     * data bytes and appears as follows:
     * :02000004FFFFFC
     * where:
     *
     * 02 is the number of data bytes in the record.
     * 0000 is the address field. For the extended linear address record, this field is always 0000.
     * 04 is the record type 04 (an extended linear address record).
     * FFFF is the upper 16 bits of the address.
     * FC is the checksum of the record and is calculated as
     * 01h + NOT(02h + 00h + 00h + 04h + FFh + FFh).
     */
    public final int EXT_LINEAR_RECORD_TYPE = 0x04;

    public int getPhysicalMemoryAddress(int virtualAddress) {
        int memoryShift = 0;
        switch (ksegValue) {
            case 0: memoryShift = 0x80000000; break;
            case 1: memoryShift = 0xA0000000; break;
        }
        return virtualAddress - memoryShift;
    }

    public int getVirtualMemoryAddress(int physicalAddress) {
        int memoryShift = 0;
        switch (ksegValue) {
            case 0: memoryShift = 0x80000000; break;
            case 1: memoryShift = 0xA0000000; break;
        }
        return physicalAddress + memoryShift;
    }

    boolean isEndOfFileRecord = false;

    public Controller() {

    }

    public byte getMemoryByte(int physicalAddress) throws ControllerException {
        if (!appMemoryArea.setByte(physicalAddress, data)) {
            if (!bootMemoryArea.setByte(physicalAddress, data)) {
                if (!configMemoryArea.setByte(physicalAddress, data)) {
                    throw new ControllerException("Unknown memory type.");
                }
            }
        }
    }

    public void setMemoryByte(int physicalAddress, byte data) {
        try {
            appMemoryArea.setByte(physicalAddress, data);
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }

    private int getAddressWithLinearSegment(int address) {
        return ((((extendedLinearSegmentAddress << 16) & 0XFFFF0000) | address) + shiftAddress);
    }

    private int getAddressWithSegment(int address) {
        return ((extendedSegmentAddress << 4) | address);
    }

    private int makeAddress(byte aByte, byte bByte) {

        return (aByte << 8) & 0xFF00 | (bByte & 0x0FF);
    }

    public void ProgramMemory(ArrayList<Byte> datas) throws ControllerException {

        int numbytes = datas.get(0);
        int address = makeAddress(datas.get(1), datas.get(2));
        int record_type = datas.get(3);
        int counter = 0;

        switch (record_type) {
            /* DATA RECORD */
            case DATA_RECORD_TYPE:
                for (int i = 0; i < numbytes; i++) {
                    setMemoryByte(getAddressWithLinearSegment(address), datas.get(i));
                    address++;
                }
                break;
            /* END OF FILE RECORD. */
            case EOF_RECORD_TYPE:
                isEndOfFileRecord = true;
                break;
            /* SEGMENT ADDRESS RECORD. */
            case SEGM_ADDR_RECORD_TYPE:
                if (numbytes == 2) {
                    extendedSegmentAddress = makeAddress(datas.get(4), datas.get(5));
                } else {
                    throw new ControllerException("The address size have to 2 bytes!");
                }
                break;
            /* EXTENDED LINEAR ADDRESS RECORD. */
            case EXT_LINEAR_RECORD_TYPE:
                if (numbytes == 2) {
                    extendedLinearSegmentAddress = makeAddress(datas.get(4), datas.get(5));
                } else {
                    throw new ControllerException("The address size have to 2 bytes!");
                }
                break;
        }

    }

    public void Interpreter(ArrayList<Byte> datas) throws ControllerException {
        ArrayList<Byte> oneLine = new ArrayList<>();
        int i = 1;
        int j = 0;

        while (i < datas.size()) {

            oneLine.clear();
            oneLine.add(datas.get(i++));

            for (j = 0; j < 3; j++) {
                oneLine.add(datas.get(i++));
            }

            for (j = 0; j < oneLine.get(0); j++) {
                oneLine.add(datas.get(i++));
            }

            CRCCalc crcCalc = new CRCCalc();
            byte CRCVal = (byte) ((byte) 0 - (byte) crcCalc.getCheksumBytes(oneLine));
            if (CRCVal != datas.get(i++)) {
                throw new ControllerException("Invalid hex record CRC.");
            } else {
                ProgramMemory(oneLine);
            }

        }

    }

}

package RAM.obj;

import progress.obj.PCB;

/**
 * 内存空间分配对象
 */
public class SpaceAssignment {
    private int address;
    private int size;
    private PCB pcb;

    public SpaceAssignment() {
        address = -1;
        size = -1;
        pcb = null;
    }

    public SpaceAssignment(int address, int size, PCB pcb) {
        this.address = address;
        this.size = size;
        this.pcb = pcb;
    }

    public PCB getPcb() {
        return pcb;
    }

    public void setPcb(PCB pcb) {
        this.pcb = pcb;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

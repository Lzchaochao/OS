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

    SpaceAssignment(int address, int size, PCB pcb) {
        this.address = address;
        this.size = size;
        this.pcb = pcb;
    }

    PCB getPcb() {
        return pcb;
    }

    void setPcb(PCB pcb) {
        this.pcb = pcb;
    }

    int getAddress() {
        return address;
    }

    void setAddress(int address) {
        this.address = address;
    }

    int getSize() {
        return size;
    }

    void setSize(int size) {
        this.size = size;
    }
}

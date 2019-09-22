package RAM.obj;

import progress.obj.PCB;

import java.util.LinkedList;
import java.util.List;

public class Memory {
    private List<PCB> pcbs;
    private SpaceAssignmentTable memoryTable;
    private byte[] userMemory;

    public Memory() {
        pcbs = new LinkedList<PCB>();
        memoryTable = new SpaceAssignmentTable();
        userMemory = new byte[512];

        for (int i = 0; i < 512; i++) {
            userMemory[i] = 0;
        }
    }

    public boolean applyMemory(int size, PCB process) {
        boolean assignment = true;
        SpaceAssignment apply = new SpaceAssignment(-1, size, process);
        apply = memoryTable.apply(apply);
        if (apply == null) {
            assignment = false;
        }
        if (assignment) {
            pcbs.add(process);
            System.out.println(apply.getSize() + "    " + apply.getAddress());
            for (int i = apply.getAddress(); i < apply.getAddress() + apply.getSize(); i++) {
                userMemory[i] = (byte) (apply.getPcb().getId());
            }
        }
        return assignment;
    }

    public void freeMemory(int id) {
        for (PCB pcb : pcbs) {
            if (pcb.getId() == id) {
                freeMemory(pcb);
                break;
            }
        }
    }

    public void freeMemory(PCB progress) {
        SpaceAssignment space = memoryTable.free(progress);
        pcbs.remove(progress);
        for (int i = space.getAddress(); i < space.getAddress() + space.getSize(); i++) {
            userMemory[i] = (byte) 0;
        }
    }

    public byte[] getMemoryTable() {
        return userMemory;
    }

    //压缩外碎片
    public void compaction() {
        memoryTable.reviseTable();
        Node node = memoryTable.getTableHead();
        while (node != null) {
            byte id = (byte) (node.getPcb() == null ? 0 : node.getPcb().getId());
            for (int i = node.getAddress(); i < node.getAddress() + node.getSize(); i++) {
                userMemory[i] = id;
            }
            node = node.getNext();
        }
    }
}

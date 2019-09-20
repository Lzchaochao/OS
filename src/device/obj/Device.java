package device.obj;

import progress.obj.PCB;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Device {
    public final int A_DEVICE = 0;
    public final int B_DEVICE = 1;
    public final int C_DEVICE = 2;
    private final int A_SIZE = 2;
    private final int B_SIZE = 3;
    private final int C_SIZE = 3;

    private int[] deviceSize = new int[]{A_SIZE, B_SIZE, C_SIZE};
    private ArrayList[] deviceUseTable = new ArrayList[]{new ArrayList<DeviceAssignment>(A_SIZE),
            new ArrayList<DeviceAssignment>(B_SIZE),
            new ArrayList<DeviceAssignment>(C_SIZE)};
    private Queue[] deviceQueue = new Queue[]{new LinkedList<PCB>(), new LinkedList<PCB>(), new LinkedList<PCB>()};

    public boolean applyDevice(int type, int time, PCB pcb) {
        boolean success = false;
        if (deviceUseTable[type].size() < deviceSize[type]) {
            //已分配的设备数小于设备最大数，则可以进行分配
            DeviceAssignment assignment = new DeviceAssignment(pcb, time, deviceUseTable[type]);
            assignment.start();
            success = true;
        } else {
            deviceQueue[type].offer(pcb);
            pcb.block();
        }
        return success;
    }


    public PCB[][] getDeviceUseTable() {
        PCB[][] table = new PCB[][]{new PCB[2], new PCB[3], new PCB[3]};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < deviceUseTable[i].size(); j++) {
                table[i][j] = ((DeviceAssignment) (deviceUseTable[i].get(j))).getPcb();
            }
        }
        return table;
    }

    public PCB[][] getDeviceWaitQueue() {
        PCB[][] table = new PCB[3][];
        for (int i = 0; i < 3; i++) {
            table[i] = (PCB[]) (deviceQueue[i].toArray());
        }
        return table;
    }
}

package device.obj;

import progress.obj.PCB;

import java.util.*;

public class Device {
    public static final int A_DEVICE = 0;
    public static final int B_DEVICE = 1;
    public static final int C_DEVICE = 2;
    private static final int A_SIZE = 2;
    private static final int B_SIZE = 3;
    private static final int C_SIZE = 3;

    private static int[] deviceSize = new int[]{A_SIZE, B_SIZE, C_SIZE};
    private static ArrayList<Vector<DeviceAssignment>> deviceUseTable = new ArrayList<Vector<DeviceAssignment>>();
    private static ArrayList<Queue<DeviceAssignment>> deviceQueue = new ArrayList<Queue<DeviceAssignment>>();

    static {
        deviceQueue.add(new LinkedList<DeviceAssignment>());
        deviceQueue.add(new LinkedList<DeviceAssignment>());
        deviceQueue.add(new LinkedList<DeviceAssignment>());
        deviceUseTable.add(new Vector<DeviceAssignment>(A_SIZE));
        deviceUseTable.add(new Vector<DeviceAssignment>(B_SIZE));
        deviceUseTable.add(new Vector<DeviceAssignment>(C_SIZE));
    }

    public static void applyDevice(int type, int time, PCB pcb) {
        //每次申请设备时，都将进程放入设备等待队列中
        DeviceAssignment assignment = new DeviceAssignment(pcb, time, deviceUseTable.get(type), type);
        deviceQueue.get(type).offer(assignment);
        checkDeviceUseStatu(type);
    }

    public static synchronized void checkDeviceUseStatu(int type) {
        Vector<DeviceAssignment> list = deviceUseTable.get(type);
        Queue<DeviceAssignment> queue = deviceQueue.get(type);
        if (list.size() < deviceSize[type] && !queue.isEmpty()) {
            DeviceAssignment device = queue.poll();
            device.start();
            deviceUseTable.get(type).add(device);
        }
    }

    public static PCB[][] getDeviceUseTable() {
        PCB[][] table = new PCB[][]{new PCB[2], new PCB[3], new PCB[3]};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < deviceUseTable.get(i).size(); j++) {
                table[i][j] = ((DeviceAssignment) (deviceUseTable.get(i).get(j))).getPcb();
            }
        }
        return table;
    }

    public static PCB[][] getDeviceWaitQueue() {
        PCB[][] table = new PCB[3][];
        for (int i = 0; i < 3; i++) {
            table[i] = (PCB[]) (deviceQueue.get(i).toArray());
        }
        return table;
    }
}

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
    private static final int DEVICE_AMOUNT = 3;

    private static int[] deviceSize = new int[]{A_SIZE, B_SIZE, C_SIZE};
    private static ArrayList<Vector<DeviceAssignment>> deviceUseTable = new ArrayList<>();
    private static ArrayList<Queue<DeviceAssignment>> deviceQueue = new ArrayList<>();

    static {
        deviceUseTable.add(new Vector<>(A_SIZE));
        deviceUseTable.add(new Vector<>(B_SIZE));
        deviceUseTable.add(new Vector<>(C_SIZE));
        deviceQueue.add(new LinkedList<>());
        deviceQueue.add(new LinkedList<>());
        deviceQueue.add(new LinkedList<>());
    }

    public static void applyDevice(int type, int time, PCB pcb) {
        //每次申请设备时，都将进程放入设备等待队列中
        DeviceAssignment assignment = new DeviceAssignment(pcb, time, deviceUseTable.get(type), type);
        pcb.block();
        deviceQueue.get(type).offer(assignment);
        checkDeviceUseStatus(type);
    }

    static synchronized void checkDeviceUseStatus(int type) {
        Vector<DeviceAssignment> list = deviceUseTable.get(type);
        Queue<DeviceAssignment> queue = deviceQueue.get(type);
        if (list.size() < deviceSize[type] && !queue.isEmpty()) {
            DeviceAssignment device = queue.poll();
            device.start();
            deviceUseTable.get(type).add(device);
        }
    }

    public static PCB[][] getDeviceUseTable() {
        PCB[][] table = new PCB[][]{new PCB[A_SIZE], new PCB[B_SIZE], new PCB[C_SIZE]};
        for (int i = 0; i < DEVICE_AMOUNT; i++) {
            for (int j = 0; j < deviceUseTable.get(i).size(); j++) {
                table[i][j] = deviceUseTable.get(i).get(j) == null ? null : deviceUseTable.get(i).get(j).getPcb();
            }
        }
        return table;
    }

    public static PCB[][] getDeviceWaitQueue() {
        PCB[][] table = new PCB[DEVICE_AMOUNT][];
        for (int i = 0; i < DEVICE_AMOUNT; i++) {
            table[i] = deviceQueue.get(i).isEmpty() ? new PCB[0] : (PCB[]) (deviceQueue.get(i).toArray());
        }
        return table;
    }
}

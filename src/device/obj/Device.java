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
    private static DeviceAssignment[][] deviceUseTable = new DeviceAssignment[3][];
    private static ArrayList<Queue<DeviceAssignment>> deviceQueue = new ArrayList<>();

    static {
        deviceUseTable[0] = new DeviceAssignment[A_SIZE];
        deviceUseTable[1] = new DeviceAssignment[B_SIZE];
        deviceUseTable[2] = new DeviceAssignment[C_SIZE];
        deviceQueue.add(new LinkedList<>());
        deviceQueue.add(new LinkedList<>());
        deviceQueue.add(new LinkedList<>());
    }

    public static void applyDevice(int type, int time, PCB pcb) {
        //每次申请设备时，都将进程放入设备等待队列中
        DeviceAssignment assignment = new DeviceAssignment(pcb, time, deviceUseTable[type], type);
        pcb.block();
        deviceQueue.get(type).offer(assignment);
        checkDeviceUseStatus(type);
    }

    static synchronized void checkDeviceUseStatus(int type) {
        DeviceAssignment[] list = deviceUseTable[type];
        Queue<DeviceAssignment> queue = deviceQueue.get(type);
        if (!isArrayFull(list) && !queue.isEmpty()) {
            DeviceAssignment device = queue.poll();
            device.start();
            for (int i = 0; i < deviceUseTable[type].length; i++) {
                if (deviceUseTable[type][i] == null) {
                    deviceUseTable[type][i] = device;
                    break;
                }
            }
        }
    }

    public static PCB[][] getDeviceUseTable() {
        PCB[][] table = new PCB[][]{new PCB[A_SIZE], new PCB[B_SIZE], new PCB[C_SIZE]};
        for (int i = 0; i < DEVICE_AMOUNT; i++) {
            for (int j = 0; j < deviceUseTable[i].length; j++) {
                table[i][j] = deviceUseTable[i][j] == null ? null : deviceUseTable[i][j].getPcb();
            }
        }
        return table;
    }

    public static PCB[][] getDeviceWaitQueue() {
        PCB[][] table = new PCB[DEVICE_AMOUNT][];
        for (int i = 0; i < DEVICE_AMOUNT; i++) {
            PCB[] devices = new PCB[deviceQueue.get(i).size()];
            Iterator<DeviceAssignment> ite = deviceQueue.get(i).iterator();
            int j = 0;
            while (ite.hasNext()) {
                devices[j] = ite.next().getPcb();
                j++;
            }
            table[i] = devices;
        }
        return table;
    }

    private static boolean isArrayFull(DeviceAssignment[] array) {
        boolean judge = true;
        for (DeviceAssignment device : array) {
            if (device == null) {
                judge = false;
                break;
            }
        }
        return judge;
    }
}

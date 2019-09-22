package device.obj;

import progress.obj.PCB;

import java.util.Vector;

public class DeviceAssignment extends Thread {
    private PCB pcb;
    private int time;
    private DeviceAssignment[] list;
    private int type;

    DeviceAssignment(PCB pcb, int useTime, DeviceAssignment[] list, int type) {
        this.pcb = pcb;
        this.time = useTime;
        this.list = list;
        this.type = type;
    }

    @Override
    public void run() {
        pcb.block();
        //开始io，阻塞进程
        while (time > 0) {
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //io结束
        pcb.aWake();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].getPcb() == pcb) {
                list[i] = null;
                break;
            }
        }
        Device.checkDeviceUseStatus(type);
    }

    PCB getPcb() {
        return pcb;
    }
}

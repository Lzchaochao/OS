package device.obj;

import progress.obj.PCB;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Vector;

public class DeviceAssignment extends Thread {
    private PCB pcb;
    private int time;
    private Vector<DeviceAssignment> list;
    private int type;

    public DeviceAssignment(PCB pcb, int useTime, Vector<DeviceAssignment> list, int type) {
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
        list.remove(this);
        pcb.aWake();
        Device.checkDeviceUseStatu(type);
    }

    public PCB getPcb(){
        return pcb;
    }
}

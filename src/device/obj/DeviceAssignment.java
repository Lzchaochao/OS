package device.obj;

import progress.obj.PCB;

import java.util.ArrayList;

public class DeviceAssignment extends Thread {
    private PCB pcb;
    private int time;
    private ArrayList list;

    public DeviceAssignment(PCB pcb, int useTime, ArrayList list) {
        this.pcb = pcb;
        this.time = useTime;
        this.list = list;
    }

    @Override
    public void run() {
        while (time > 0) {
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pcb.aWake();//io结束，重新唤醒进程
        list.remove(this);
    }

    public PCB getPcb() {
        return pcb;
    }
}

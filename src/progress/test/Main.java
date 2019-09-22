package progress.test;

import java.util.Scanner;

import device.obj.Device;
import progress.obj.OutPutThread;
import progress.obj.PCB;

public class Main {
    public static void main(String[] args) {
        OutPutThread out = new OutPutThread();
        out.start();
        while (true) {
            System.out.println("1.add progress  2.change out type 3.random");
            Scanner input = new Scanner(System.in);
            int in = input.nextInt();
            if (in == 1) {
                System.out.println("input progress id and device type(0 to A, 1 to B or 2 to C)");
                int id = input.nextInt();
                int type = input.nextInt();
                int time = (int) (Math.random() * 2) + 3;
                PCB pcb = new PCB(id);
                Device.applyDevice(type, time, pcb);
            } else if (in == 2) {
                System.out.println("0 to wait queue, 1 to use list");
                out.changeOutType();
            } else if (in == 3) {
                int length = 20;
                while (length-- > 0) {
                    int id = (int)(Math.random()*1000);
                    int time = (int) (Math.random() * 2) + 3;
                    int type = (int)(Math.random()*3);
                    Device.applyDevice(type, time, new PCB(id));
                }
            }
        }
    }
}

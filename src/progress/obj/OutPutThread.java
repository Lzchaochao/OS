package progress.obj;

import device.obj.Device;

public class OutPutThread extends Thread {
    private final int WAIT_QUEUE = 0;
    private final int USE_LIST = 1;

    private int outType = 1;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (outType == WAIT_QUEUE) {
//                PCB[][] pcbs = Device.getDeviceWaitQueue();
//                System.out.println();
//                System.out.println();
//                for (int i = 0; i < pcbs.length; i++) {
//                    for (int j = 0; j < pcbs[i].length; j++) {
//                        System.out.printf("%4s   ", pcbs[i][j]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//                System.out.println();
//
//            } else if (outType == USE_LIST) {
//                PCB[][] pcbs = Device.getDeviceUseTable();
//                System.out.println();
//                System.out.println();
//                for (int i = 0; i < pcbs.length; i++) {
//                    for (int j = 0; j < pcbs[i].length; j++) {
//                        System.out.printf("%4s   ", pcbs[i][j]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//                System.out.println();
//            }
            System.out.println();
            System.out.println();
            PCB[][] waitQueue = Device.getDeviceWaitQueue();
            PCB[][] useList = Device.getDeviceUseTable();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < useList[i].length; j++) {
                    System.out.printf("%4s   ", useList[i][j]);
                }
                if(i==0){
                    System.out.print("       ");
                }
                System.out.print("       ");
                for (int j = 0; j < waitQueue[i].length; j++) {
                    System.out.printf("%4s   ", waitQueue[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }

    public void setOutType(int type) {
        outType = type;
    }

    public void changeOutType() {
        outType = 1 - outType;
    }
}

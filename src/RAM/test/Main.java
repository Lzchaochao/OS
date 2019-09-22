package RAM.test;

import RAM.obj.Memory;
import progress.obj.PCB;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("输入操作，1表新建进程，2表释放进程，3查看内存，4压缩外碎片，5预填");
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            if (i == 1) {
                System.out.println("input size id");
                int size = input.nextInt();
                int id = input.nextInt();
                Memory.applyMemory(size, new PCB(id));
            } else if (i == 2) {
                System.out.println("input id");
                int id = input.nextInt();
                Memory.freeMemory(id);
            } else if (i == 3) {
                byte[] table = Memory.getMemoryTable();
                for (int k = 0; k < 512; k++) {
                    System.out.printf("%4d", (int) table[k]);
                    if (k % 16 == 15) {
                        System.out.println();
                    }
                }
            } else if (i == 4) {
                Memory.compaction();
            } else if (i == 5) {
//                memory.applyMemory(1, new PCB(1));
//                memory.applyMemory(2, new PCB(2));
//                memory.applyMemory(3, new PCB(3));
//                memory.applyMemory(4, new PCB(4));
//                memory.applyMemory(5, new PCB(5));
//                memory.applyMemory(6, new PCB(6));
//                memory.applyMemory(7, new PCB(7));
//                memory.applyMemory(8, new PCB(8));
//                memory.applyMemory(9, new PCB(9));
//                memory.applyMemory(11, new PCB(11));
                System.out.println("input progress size");
                int size = input.nextInt();
                for(int j=0;j<size;j++){
                    Memory.applyMemory((int)(Math.random()*50)+1, new PCB((int)(Math.random()*50)+1));
                }
            }
        }
    }
}

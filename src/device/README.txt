设备管理模块
本模块提供三个常量和三个接口
Device.A_DEVICE 代表A设备
Device.B_DEVICE 代表B设备
Device.C_DEVICE 代表C设备

1.Device.applyDevice(int type, int time, PCB pcb):void
申请设备函数，调用此函数时，需传入三个蚕食
int type 为设备的类型，为上述三个常量之一，代表ABC设备其中一个
int time 为设备的io时间
PCB pcb  为申请设备的PCB对象

Device类会将申请设备的PCB自动调用PCB.block()函数让其阻塞，得到io结束后再调用PCB.block()让他唤醒


2.Device.getDeviceUseTable():PCB[][]
调取设备使用状况，界面调用
返回一个PCB二维数组，其大小如下
PCB[0].size() = 2 代表A设备的数量
PCB[1].size() = 3 代表B设备的数量
PCB[2].size() = 3 代表C设备的数量
当PCB[x][y]不为null时代表有进程占用设备

3.Device.getDeviceWaitQueue():PCB[][]
调取设备等待队列，界面调用
返回一个PCB二维数组
PCB[0] 代表设备A的等待队列，size<=0
PCB[1] 代表设备B的等待队列，size<=0
PCB[2] 代表设备C的等待队列，size<=0
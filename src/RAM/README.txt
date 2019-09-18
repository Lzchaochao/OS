本文档为RAM包使用文档
RAM包包含了内存申请等相关接口
只需要查看RAM.obj.Memory下API即可使用

applyMemory(int size, PCB process)
传入需要申请的空间大小以及PCB对象
返回值boolean true表示申请成功，false表示申请失败


freeMemory(PCB progress)
传入即将要销毁的PCB对象
无返回值

getMemoryTable()
返回内存使用表

compaction()
压缩外碎片
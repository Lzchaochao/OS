package RAM.obj;

import progress.obj.PCB;

/**
 * 内存空间分配表
 */
public class SpaceAssignmentTable {
    private Node head;
    private final int MIN_MEMORY_LEFT = 5;
    private final int MAX_MEMORY = 512;

    public SpaceAssignmentTable() {
        head = new Node(new SpaceAssignment(0, MAX_MEMORY, null));
    }

    public Node getTableHead() {
        return head;
    }

    /**
     * 传入需要分配的内存空间大小，采用首次适配，设立最大剩余空间为5，如果分配后剩余的空间小于5则将剩余的空间也分配给该进程
     */
    public SpaceAssignment apply(SpaceAssignment apply) {
        SpaceAssignment spaceAssignment = null;
        Node node = head;
        while (node != null) {
            if (node.getPCB() != null) {
                //如果该空间已被分配到某个PCB，则不为空
                node = node.getNext();
                continue;
            }
            if (node.getSize() < apply.getSize()) {
                //如果空间小于申请的长度，则不分配
                node = node.getNext();
                continue;
            }
            if (node.getSize() - MIN_MEMORY_LEFT <= apply.getSize()) {
                //如果空间大于申请长度同时分配后的剩余空间小于指定长度MIN，则将空间全部分配
                spaceAssignment = new SpaceAssignment(node.getAddress(), node.getSize(), apply.getPcb());
                node.setSpace(spaceAssignment);
                break;
            } else {
                //如果空间很大，将分配的空间和剩余的空间分配为两个内存分配对象，并变成节点连接起来
                spaceAssignment = new SpaceAssignment(node.getAddress(), apply.getSize(), apply.getPcb());
                SpaceAssignment newSpace = new SpaceAssignment(node.getAddress() + apply.getSize(), node.getSize() - apply.getSize(), null);
                node.setSpace(spaceAssignment);
                Node newNode = new Node(newSpace);
                newNode.setNext(node.getNext());
                node.setNext(newNode);
                break;
            }
        }
        return spaceAssignment;
    }

    public SpaceAssignment free(PCB pcb) {
        SpaceAssignment findSpace = null;
        Node node = head;
        Node preNode = head;
        while (node != null) {
            if (node.getPCB() != pcb) {
                preNode = node;
                node = node.getNext();
                continue;
            }
            if (preNode != node && preNode.getPCB() == null) {
                //如果节点有前节点且前节点为空，则连接两个空间
                SpaceAssignment space = preNode.getSpace();
                space.setSize(space.getSize() + node.getSize());
                preNode.setNext(node.getNext());
                preNode.setSpace(space);
                findSpace = space;
                node = preNode;
            }
            if (node.getNext() != null && node.getNext().getPCB() == null) {
                //如果节点有后节点且后节点为空，则连接两个空间
                Node nextNode = node.getNext();
                SpaceAssignment space = node.getSpace();
                space.setSize(space.getSize() + nextNode.getSize());
                node.setSpace(space);
                findSpace = space;
                node.setNext(nextNode.getNext());
            } else {
                node.getSpace().setPcb(null);
                findSpace = node.getSpace();
            }
            break;
        }
        return findSpace;
    }

    public void reviseTable() {
        Node node = head;
        head = new Node(new SpaceAssignment(0, MAX_MEMORY, null));

        int spaceAccount = 0;
        while (node != null) {
            if(node.getPCB()!=null){
                apply(new SpaceAssignment(-1, node.getSize(), node.getPCB()));
            }
            node = node.getNext();
        }
    }
}

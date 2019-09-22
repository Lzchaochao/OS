package RAM.obj;

import progress.obj.PCB;

class Node {
    private SpaceAssignment space;
    private Node next;

    Node(SpaceAssignment space){
        this.space = space;
        next = null;
    }

    SpaceAssignment getSpace() {
        return space;
    }

    void setSpace(SpaceAssignment space) {
        this.space = space;
    }

    Node getNext() {
        return next;
    }

    void setNext(Node next) {
        this.next = next;
    }

    PCB getPcb(){
        return space.getPcb();
    }

    int getAddress(){
        return space.getAddress();
    }

    int getSize(){
        return space.getSize();
    }
}

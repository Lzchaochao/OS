package RAM.obj;

import progress.obj.PCB;

public class Node {
    private SpaceAssignment space;
    private Node next;



    public Node(SpaceAssignment space){
        this.space = space;
        next = null;
    }

    public SpaceAssignment getSpace() {
        return space;
    }

    public void setSpace(SpaceAssignment space) {
        this.space = space;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public PCB getPCB(){
        return space.getPcb();
    }

    public int getAddress(){
        return space.getAddress();
    }

    public int getSize(){
        return space.getSize();
    }
}

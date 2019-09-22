package progress.obj;

/**
 * PCB对象
 */
public class PCB {
    private int id;

    public PCB(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void aWake() {
    }

    public void block() {
    }

    @Override
    public String toString() {
        return "" + id;
    }
}

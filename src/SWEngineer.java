public class SWEngineer extends Employee {
    TITLE title;
    public enum TITLE {
        Junior, Senior, Staff
    }

    public SWEngineer(String name, String surname, int id) {
        super(name, surname, id);
        this.title = TITLE.Junior;
    }

    @Override
    public void doWork() {

    }
}

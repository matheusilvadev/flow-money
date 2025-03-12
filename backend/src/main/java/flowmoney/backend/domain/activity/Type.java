package flowmoney.backend.domain.activity;

public enum Type {
    REVENUE("revenue"),
    EXPENSE("expense");

    private String value;

    Type(final String aValue){
        this.value = aValue;
    }

    public String getValue(){
        return this.value;
    }
}

package jms;


public class Command {

    private String operator;
    private int value;

    public Command() {
    }

    public Command(String operator, int value) {
        setOperator(operator);
        this.value = value;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        if (!isValidOperator(operator)) {
            throw new IllegalArgumentException("Operator must be one of '+', '-', '*'");
        }
        this.operator = operator;
    }

    private boolean isValidOperator(String operator) {
        return "+".equals(operator) || "-".equals(operator) || "*".equals(operator);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Command{" + "operator='" + operator + '\'' + ", value=" + value + '}';
    }
}

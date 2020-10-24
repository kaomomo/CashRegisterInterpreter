package language;

// <primitive command> ::= reg | del | pay
public class PrimitiveCommandNode extends Node {
    private String name;
    private int number;
    private Executor executor;
    public void parse(Context context) throws ParseException {
        name = context.currentToken();
        context.skipToken(name);
        number = context.currentNumber();
        executor = context.createExecutor(name, number);
        context.nextToken();
    }
    public void execute() throws ExecuteException {
        if (executor == null) {
            throw new ExecuteException(name + ": is not defined");
        } else {
            executor.execute();
        }
    }
    public String toString() {
        return name;
    }
}

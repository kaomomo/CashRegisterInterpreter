package register;

import language.ExecuteException;
import language.Executor;
import language.ExecutorFactory;

public class Register implements ExecutorFactory {
    private long total;
    private Executor executor;
    public Register() {
        initialize();
    }
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
    void reg(long price) {
        total += price;
        System.out.println("éÊçûÇ›ÅF" + price + "â~ìoò^ÇµÇ‹ÇµÇΩÅB");
        this.chk();
    }
    void del(long price) {
        if((total - price) < 0){
        	System.out.println("è§ïiÇìoò^ÇµÇƒÇ≠ÇæÇ≥Ç¢ÅB");
        	return;
        }
        total -= price;
        System.out.println("éÊè¡ÇµÅF" + price + "â~éÊÇËè¡ÇµÇ‹ÇµÇΩÅB");
        this.chk();
    }
    void pay(long price) {
    	long change = price - total;
    	if(change < 0){
    		long shortage = change * -1;
    		throw new IllegalArgumentException(shortage + "â~ë´ÇËÇ‹ÇπÇÒÅB");
    	}else{
    		System.out.println("óaÇËã‡ÅF" + price + "â~óaÇ©ÇËÇ‹ÇµÇΩÅB");
    		if(change == 0){
    			System.out.println("ÇøÇÂÇ§Ç«Ç®óaÇ©ÇËÇ¢ÇΩÇµÇ‹ÇµÇΩÅB");
    		}else{
    			System.out.println("Ç®íﬁÇËÅF" + change + "â~ÇÃÇ®íﬁÇËÇ≈Ç∑ÅB");
    		}
    		initialize();
    	}
    }
    private void chk(){
    	System.out.println("---çáåväzÅF" + total + "â~Ç≈Ç∑ÅB---");
    }
    public Executor createExecutor(String name, long price) {
        if (name.equals("reg")) {
            return new RegExecutor(this, price);
        } else if (name.equals("del")) {
            return new DelExecutor(this, price);
        } else if (name.equals("pay")) {
            return new PayExecutor(this, price);
        } else {
            return null;
        }
    }
    public void initialize() {
        this.total = 0;
    }
    public void payment() {
        initialize();
        if (executor != null) {
            try {
                executor.execute();
            } catch (ExecuteException e) {
            }
        }
    }
}

abstract class RegisterExecutor implements Executor {
    protected Register register;
    protected long price;
    public RegisterExecutor(Register register, long price) {
        this.register = register;
        this.price = price;
    }
    public abstract void execute();
}

class RegExecutor extends RegisterExecutor {
    public RegExecutor(Register register, long price) {
        super(register, price);
    }
    public void execute() {
        register.reg(this.price);
    }
}

class DelExecutor extends RegisterExecutor {
    public DelExecutor(Register register, long price) {
        super(register, price);
    }
    public void execute() {
        register.del(this.price);
    }
}

class PayExecutor extends RegisterExecutor {
    public PayExecutor(Register register, long price) {
        super(register, price);
    }
    public void execute() {
        register.pay(this.price);
    }
}
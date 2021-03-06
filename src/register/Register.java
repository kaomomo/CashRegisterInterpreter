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
        System.out.println("取込み：" + price + "円登録しました。");
        this.chk();
    }
    void del(long price) {
        if((total - price) < 0){
        	System.out.println("商品を登録してください。");
        	return;
        }
        total -= price;
        System.out.println("取消し：" + price + "円取り消しました。");
        this.chk();
    }
    void pay(long price) {
    	long change = price - total;
    	if(change < 0){
    		long shortage = change * -1;
    		throw new IllegalArgumentException(shortage + "円足りません。");
    	}else{
    		System.out.println("預り金：" + price + "円預かりました。");
    		if(change == 0){
    			System.out.println("ちょうどお預かりいたしました。");
    		}else{
    			System.out.println("お釣り：" + change + "円のお釣りです。");
    		}
    		initialize();
    	}
    }
    private void chk(){
    	System.out.println("---合計額：" + total + "円です。---");
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
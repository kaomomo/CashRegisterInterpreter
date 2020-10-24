import java.io.BufferedReader;
import java.io.FileReader;

import language.InterpreterFacade;
import register.Register;

public class Main{
    private Register register = new Register();
    private InterpreterFacade facade = new InterpreterFacade(register);
    private String path = "src\\program.txt";

    // コンストラクタ
    public Main(String title) {

    	System.out.println("*****" + title + "*****");
        register.setExecutor(facade);
        getTextField();

    }

    private void getTextField(){
    	String text;
    	try (BufferedReader br = new BufferedReader(new FileReader(path))) {
    		while ((text = br.readLine()) != null){
    			if(text.startsWith("//") || text.equals("")){
                    // コマンドの先頭が"//"の行はスキップ
    				// コマンドなしの行もスキップ
    				continue;
    			}
    			parseAndExecute(text);
    		}
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseAndExecute(String programText) {
        System.out.println("programText = " + programText);
        facade.parse(programText);
        register.payment();
    }

    public static void main(String[] args){
        new Main("Interpreter Pattern Sample");
    }
}

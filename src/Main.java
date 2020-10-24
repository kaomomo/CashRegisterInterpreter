import java.io.BufferedReader;
import java.io.FileReader;

import language.InterpreterFacade;
import register.Register;

public class Main{
    private Register register = new Register();
    private InterpreterFacade facade = new InterpreterFacade(register);
    private String path = "src\\program.txt";

    // �R���X�g���N�^
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
                    // �R�}���h�̐擪��"//"�̍s�̓X�L�b�v
    				// �R�}���h�Ȃ��̍s���X�L�b�v
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

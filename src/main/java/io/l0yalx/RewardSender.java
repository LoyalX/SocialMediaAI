package io.l0yalx;
import javax.script.*;

public class RewardSender {
	
    public static void send(long uuid) throws Exception{
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("JavaScript");

	    // JavaScript code in a String
	    String script = (String)"function sendAward(uuid) {print ('[AWARD] ' + uuid);}";
	    // evaluate script
	    engine.eval(script);

	    Invocable inv = (Invocable)engine;

	    inv.invokeFunction("sendAward", uuid );
	}
}

package lsv.core;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lsv.grammar.Formula;

import lsv.grammar.FormulaParser;
import lsv.grammar.FormulaLexer;

import lsv.model.Model;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 * Builder contains static methods for building models and formulas from files and other
 * chunks of json.  Shouldn't need to deal with json or file access anywhere else.
 * */
public class Builder {

    public static Model buildModel(String filePath) {
	Gson gson = new Gson();
	try {
	    BufferedReader br = new BufferedReader(new FileReader(filePath));
	    Model model = gson.fromJson(br, Model.class);
	    return model;
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static Formula buildFormula(String filePath) {

	Gson gson = new Gson();
	String formulaString = "";
	JsonParser parser = new JsonParser();
	try {
	    JsonElement jsonElement = parser.parse(new FileReader(filePath));
	    JsonObject jsonObject = jsonElement.getAsJsonObject();
	            
	    formulaString = jsonObject.get("formula").getAsString();

	    Map<Character, String[]> actionMap = new HashMap<>();

	    for(int i = 97; i < 123; i++) {
		String [] str = gson.fromJson(jsonObject.get((char) i+""), String[].class);
		if(str != null) {
		    actionMap.put((char) i, str);
		}
	    }   
	
	    try {
		Formula formula = parseCTL(formulaString);
		formula.addActions(actionMap);
		return formula;
	    } catch (RecognitionException e) {
		System.out.println("Unable to generate formula:");
		e.printStackTrace();
	    }
	    return null;

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    return null;
	}

    }

    public static Formula parseCTL(String input) throws RecognitionException {
	ANTLRStringStream in = new ANTLRStringStream(input);
        FormulaLexer lexer = new FormulaLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormulaParser parser = new FormulaParser(tokens);
        Formula form = parser.query();
        return form;
    }
	
}

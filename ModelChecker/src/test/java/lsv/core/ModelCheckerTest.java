package lsv.core;

import static org.junit.Assert.*;

import org.junit.Test;

import lsv.model.Model;

import lsv.grammar.Formula;


public class ModelCheckerTest {

    /*
     * An example of how to set up and call the model building methods and make a call to the
     * model checker itself.  The contents of model.json, constraint1.json and ctl.json are
     * just examples, you need to add new models and formulas for the mutual exclusion task.
     * */
    @Test
    public void buildAndCheckModel() {

	Model model = Builder.buildModel("src/test/resources/model.json");

	Formula fairnessConstraint = Builder.buildFormula("src/test/resources/constraint1.json");

	Formula query = Builder.buildFormula("src/test/resources/ctl.json");
	
	ModelChecker mc = new SimpleModelChecker();

	// TO IMPLEMENT
	//assertTrue(mc.check(model, fairnessConstraint, query));
    }

}

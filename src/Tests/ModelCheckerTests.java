package Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lsv.core.Builder;
import lsv.core.ExecutionGraph;
import lsv.core.SimpleModelChecker;
import lsv.grammar.Formula;
import lsv.model.Model;

public class ModelCheckerTests {
	
	SimpleModelChecker checker = new SimpleModelChecker();
	Model model;
	Formula formula;
	Formula constraints;
	ExecutionGraph graph;
	
	// Parses the model, formula and constraints, and generate the execution graph
	public void parseArguments(String modelPath, String formulaPath, String constraintPath) {
		model = Builder.buildModel(modelPath);
		formula = Builder.buildFormula(formulaPath);
		if (constraintPath != null) {
			constraints = Builder.buildFormula(constraintPath);
		}

		checker.setExecutionGraph(model);
	}
	
	@Test
	public void testTautologyTrue() {
		parseArguments("test/resources/ourTests/apTestModel.json", "test/resources/ourTests/tautologyTrueFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testTautologyFalse() {
		parseArguments("test/resources/ourTests/apTestModel.json", "test/resources/ourTests/tautologyFalseFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}

	@Test
	public void testAP() {
		parseArguments("test/resources/ourTests/apTestModel.json", "test/resources/ourTests/apTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testAPNegationFails() {
		parseArguments("test/resources/ourTests/apTestModel.json", "test/resources/ourTests/apNegationTestFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testAPNegationHolds() {
		parseArguments("test/resources/ourTests/implicationTestModel1.json", "test/resources/ourTests/apNegationTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testConjunctionHolds() {
		parseArguments("test/resources/ourTests/conjunctionTestModel.json", "test/resources/ourTests/conjunctionTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testConjunctionFails() {
		parseArguments("test/resources/ourTests/apTestModel.json", "test/resources/ourTests/conjunctionTestFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}

	@Test
	public void testDisjunctionHoldsWithBoth() {
		parseArguments("test/resources/ourTests/conjunctionTestModel.json", "test/resources/ourTests/disjunctionTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testDisjunctionHoldsWithOne() {
		parseArguments("test/resources/ourTests/apTestModel.json", "test/resources/ourTests/disjunctionTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testDisjunctionFails() {
		parseArguments("test/resources/ourTests/apTestModel.json", "test/resources/ourTests/disjunctionFailsTestFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testImplicationHoldsPremiseNeg() {
		parseArguments("test/resources/ourTests/implicationTestModel1.json", "test/resources/ourTests/implicationTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testImplicationHoldsConsequentPos() {
		parseArguments("test/resources/ourTests/implicationTestModel2.json", "test/resources/ourTests/implicationTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testImplicationFails() {
		parseArguments("test/resources/ourTests/implicationTestModel3.json", "test/resources/ourTests/implicationTestFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testEqualityBothHold() {
		parseArguments("test/resources/ourTests/conjunctionTestModel.json", "test/resources/ourTests/equalityTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testEqualityBothFalse() {
		parseArguments("test/resources/ourTests/conjunctionTestModel.json", "test/resources/ourTests/equalityTestFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testEqualityFails() {
		parseArguments("test/resources/ourTests/equalityTestModel.json", "test/resources/ourTests/equalityTestFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testUntilNoAct() {
		parseArguments("test/resources/ourTests/untilModel1.json", "test/resources/ourTests/untilNoActSpecFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testUntilWithAct() {
		parseArguments("test/resources/ourTests/untilModel1.json", "test/resources/ourTests/untilWithActFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testUntilFails() {
		parseArguments("test/resources/ourTests/untilModel2.json", "test/resources/ourTests/untilWithActFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testNextHolds() {
		parseArguments("test/resources/ourTests/nextModel1.json", "test/resources/ourTests/nextFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testNextFails() {
		parseArguments("test/resources/ourTests/nextModel2.json", "test/resources/ourTests/nextFormula.json", null);
		assertFalse(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testEventuallyNoActHolds() {
		parseArguments("test/resources/ourTests/eventuallyModel.json", "test/resources/ourTests/eventuallyFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
	
	@Test
	public void testEventuallyWithActHolds() {
		parseArguments("test/resources/ourTests/eventuallyModel.json", "test/resources/ourTests/eventuallyWithActFormula.json", null);
		assertTrue(checker.check(model, constraints, formula));
	}
}

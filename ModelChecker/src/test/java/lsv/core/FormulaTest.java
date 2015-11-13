package lsv.core;

import static org.junit.Assert.*;
import org.junit.Test;

import lsv.grammar.Formula;

import org.antlr.runtime.RecognitionException;

public class FormulaTest {


    @Test
    public void parseConstraint1() {

	Formula formula = Builder.buildFormula("src/test/resources/constraint1.json");

	assertEquals("p", formula.getAp()[0]);
	assertEquals("q", formula.getAp()[1]);
	assertEquals("act1", formula.getActions()[0][0]);
	assertEquals("act2", formula.getActions()[0][1]);
	assertEquals("act3", formula.getActions()[1][0]);
	assertEquals("act4", formula.getActions()[1][1]);

	assertEquals("&&", formula.getOperator());

	assertEquals("AF", formula.getQuantifier());

    }

    @Test
    public void parseCtl2() {
	Formula formula = Builder.buildFormula("src/test/resources/ctl2.json");
	

	assertEquals("E", formula.getQuantifier());
	assertEquals("q", formula.getAp()[0]);
	assertEquals("p", formula.getAp()[1]);

	assertEquals("U", formula.getOperator());

	assertEquals(2, formula.getActions().length);

	assertEquals(1, formula.getActions()[0].length);
	assertEquals(1, formula.getActions()[1].length);

	assertEquals("act1", formula.getActions()[0][0]);
	assertEquals("act2", formula.getActions()[1][0]);

    }


    
    @Test
    public void parseCtl() {
	Formula formula = Builder.buildFormula("src/test/resources/ctl.json");
	

	assertEquals("AF", formula.getQuantifier());
	assertEquals("g", formula.getAp()[0]);

	assertEquals("&&", formula.getOperator());
	
	assertNull(formula.getAp()[1]);

	assertEquals(2, formula.getNestedCTL().length);
	assertNull(formula.getNestedCTL()[0]);
	
	Formula nested1 = formula.getNestedCTL()[1];

	assertEquals(nested1.getQuantifier(),"AG");
	assertEquals(2, nested1.getNestedCTL().length);
	
	Formula nested2 = nested1.getNestedCTL()[0];
	assertEquals("A", nested2.getQuantifier());
	assertEquals("U", nested2.getOperator());

	assertEquals(2, nested2.getActions()[0].length);
	assertEquals(2, nested2.getActions()[1].length);
	assertEquals("act2", nested2.getActions()[0][1]);
	
	assertNull(nested1.getNestedCTL()[1]);

	assertEquals("True", nested2.getTautology()[0]);
	
	Formula nested3 = nested2.getNestedCTL()[1];
	assertEquals("EF", nested3.getQuantifier());

	assertEquals("p", nested3.getAp()[0]);
	assertEquals("q", nested3.getAp()[1]);
	assertEquals("||", nested3.getOperator());


	assertNull(nested3.getActions()[0]);
	assertNull(nested3.getActions()[1]);
	
    }


    @Test
    public void parseCTLFromString() throws RecognitionException {
       Formula formula =  Builder.parseCTL("A(a U AF(b))");
       assertNull(formula.getActions());
       assertEquals(formula.getAp()[0], "a");
       assertFalse(formula.getApNeg()[0]);
       assertNotNull(formula.getNestedCTL()[1]);
       assertEquals(formula.getOperator(), "U");
       assertEquals(formula.getQuantifier(),"A");
       assertNull(formula.getNestedCTL()[0]);
       assertEquals(formula.getNestedCTL()[1].getQuantifier(),"AF");
    }


}

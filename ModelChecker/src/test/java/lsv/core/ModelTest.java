package lsv.core;

import static org.junit.Assert.*;
import org.junit.Test;

import lsv.model.State;
import lsv.model.Transition;
import lsv.model.Model;


public class ModelTest {

    @Test
    public void parseModel() {

	Model model = Builder.buildModel("src/test/resources/model.json");

	assertEquals(3, model.getStates().length);


	

    }


}

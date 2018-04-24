package de.franzmue.nameencoder;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import de.franzmue.nameencoder.RegexRule;
import de.franzmue.nameencoder.ReplacementRule;
import de.franzmue.nameencoder.Rule;
import de.franzmue.nameencoder.Rules;

public class RulesTest {

	@Test
	public void testGetInitialRules() {
		Rules rules = getRules();

		assertEquals(0, rules.getRules().size());
	}

	@Test
	public void testAddEmptyReplacementRule() {
		Rules rules = getRules();
		rules.addReplacementRule("", "");

		assertEquals(1, rules.getRules().size());
	}

	@Test
	public void testAddOneReplacementRule() {
		Rules rules = getRules();
		rules.addReplacementRule("b", "p");

		assertEquals(1, rules.getRules().size());
	}

	@Test
	public void testAddTwoReplacementRules() {
		Rules rules = getRules();
		rules.addReplacementRule("b", "p");
		rules.addReplacementRule("ph", "f");

		assertEquals(2, rules.getRules().size());
	}

	@Test
	public void testAddEmptyRegexRule() {
		Rules rules = getRules();
		rules.addRegexRule("", "");

		assertEquals(1, rules.getRules().size());
	}

	@Test
	public void testAddOneRegexRule() {
		Rules rules = getRules();
		rules.addRegexRule("^b", "p");

		assertEquals(1, rules.getRules().size());
	}

	@Test
	public void testAddTwoRegexRules() {
		Rules rules = getRules();
		rules.addRegexRule("^b", "p");
		rules.addRegexRule("b$", "p");

		assertEquals(2, rules.getRules().size());
	}

	@Test
	public void testAddRegexAndReplacementRule() {
		Rules rules = getRules();
		rules.addRegexRule("^b", "p");
		rules.addReplacementRule("b", "p");

		assertEquals(2L, rules.getRules().size());
	}

	@Test
	public void testEmptyIterator() {
		Rules rules = getRules();
		Iterator<Rule> it = rules.iterator();

		assertFalse(it.hasNext());
	}

	@Test
	public void testIterator() {
		final String regex = "^b";
		final String source = "b";

		Rules rules = getRules();
		rules.addRegexRule(regex, "p");
		rules.addReplacementRule(source, "p");
		
		for (Rule rule: rules) {
			if (rule instanceof RegexRule)
			  assertEquals(regex, rule.getRegex());
			else if (rule instanceof ReplacementRule)
				assertEquals(source, rule.getSource());
			else
				fail("Wrong rule type\n");
		}
	}

	private Rules getRules() {
		return new Rules();
	}

}

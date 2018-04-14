package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.RegexRule;

import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RegexRuleTest {
	
	private final static String EXAMPLE_REGEX_RULE = "^c";
	private final static String EXAMPLE_DESTINATION_RULE = "s";

	@Test
	public void testNullRegexArgument() {
		Rule r = new RegexRule(null, "");

		assertNotNull(r.getRegex());
	}

	@Test
	public void testEmptyRegexArgument() {
		Rule r = new RegexRule("", "");

		assertEquals("", r.getRegex());
	}

	@Test
	public void testRegexArgument() {
		Rule r = new RegexRule(EXAMPLE_REGEX_RULE, "");

		assertEquals(EXAMPLE_REGEX_RULE, r.getRegex());
	}

	@Test
	public void testNullDestinationArgument() {
		Rule r = new RegexRule("", null);

		assertNotNull("", r.getDestination());
	}

	@Test
	public void testEmptyDestinationArgument() {
		Rule r = new RegexRule("", "");

		assertEquals("", r.getDestination());
	}

	@Test
	public void testDestinationArgument() {
		Rule r = new RegexRule("", EXAMPLE_DESTINATION_RULE);

		assertEquals(EXAMPLE_DESTINATION_RULE, r.getDestination());
	}

	@Test
	public void testNullPatternArgument() {
		Rule r = new RegexRule(null, "");

		assertNotNull(r.getPattern());
	}

	@Test
	public void testEmptyPatternArgument() {
		Rule r = new RegexRule("", "");

		comparePatterns(r);
	}

	@Test
	public void testPatternArgument() {
		Rule r = new RegexRule(EXAMPLE_REGEX_RULE, "");

		comparePatterns(r);
	}
	
	private void comparePatterns(Rule r) {
		String expected = r.getPattern().matcher("c").replaceAll(r.getDestination());
		String actual = Pattern.compile(r.getRegex()).matcher("c").replaceAll(r.getDestination());
		assertEquals(expected, actual);
	}

	@Test( expected = UnsupportedOperationException.class )
	public void testSourceArgument() {
		Rule r = new RegexRule("", "");
		r.getSource();
	}

}

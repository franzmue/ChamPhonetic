/**
 * 
 */
package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.ReplacementRule;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author linux
 *
 */
public class ReplacementRuleTest {
	
	private final static String EXAMPLE_REPLACEMENT_RULE = "ph";
	private final static String EXAMPLE_DESTINATION_RULE = "f";

	@Test
	public void testNullSourceArgument() {
		Rule r = new ReplacementRule(null, "");

		assertNotNull(r.getSource());
	}

	@Test
	public void testEmptySourceArgument() {
		Rule r = new ReplacementRule("", "");

		assertEquals("", r.getSource());
	}

	@Test
	public void testSourceArgument() {
		Rule r = new ReplacementRule(EXAMPLE_REPLACEMENT_RULE, "");

		assertEquals(EXAMPLE_REPLACEMENT_RULE, r.getSource());
	}

	@Test
	public void testNullDestinationArgument() {
		Rule r = new ReplacementRule("", null);

		assertNotNull("", r.getDestination());
	}

	@Test
	public void testEmptyDestinationArgument() {
		Rule r = new ReplacementRule("", "");

		assertEquals("", r.getDestination());
	}

	@Test
	public void testDestinationArgument() {
		Rule r = new ReplacementRule("", EXAMPLE_DESTINATION_RULE);

		assertEquals(EXAMPLE_DESTINATION_RULE, r.getDestination());
	}

	@Test( expected = UnsupportedOperationException.class )
	public void testRegexArgument() {
		Rule r = new ReplacementRule("", "");
		r.getRegex();
	}

	@Test( expected = UnsupportedOperationException.class )
	public void testPatternArgument() {
		Rule r = new ReplacementRule("", "");
		r.getPattern();
	}

}

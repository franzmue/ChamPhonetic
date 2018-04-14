package de.franzmue.NameEncoder;

import java.util.function.Supplier;

import de.franzmue.NameEncoder.NameEncoder;

/**
 * Add rules the the NameEncoder class in order to be able to find similar German family names.
 * 
 * @see de.franzmue.NameEncoder.NameEncoder
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public class GermanNameEncoder extends NameEncoder {
	
	protected static Supplier<NameEncoder> encoderSupplier = () -> new GermanNameEncoder();

	public static void main(String[] args) {
		createCodes(args, encoderSupplier);
	}

	public GermanNameEncoder() {
		addReplacementRuleLayer(new String[][] { // Note: toLowerCase(Locale.GERMAN) is already done at the beginning of the encoding
			{ "\u00e4", "e" }, // German umlaut "ä"
			{ "\u00f6", "o" }, // German umlaut "ö"
			{ "\u00fc", "i" }, // German umlaut "ü"
			{ "\u00df", "s" }, // German umlaut "ß"
		});

		addReplacementRuleLayer(new String[][] {
			{ "j", "i" },
			{ "y", "i" },
			{ "z", "s" },
			{ "v", "f" },
			{ "ph", "f" },
			{ "p", "b" },
			{ "d", "t" },
			{ "x", "chs" },
		});

		addRegexRuleLayer(new String[][] {
			{ "([^s])ca", "$1ka" },
			{ "([^s])ch", "$1k" },
			{ "([^s])ck", "$1k" },
			{ "([^s])cl", "$1kl" },
			{ "([^s])co", "$1ko" },
			{ "([^s])cq", "$1k" },
			{ "([^s])cr", "$1kr" },
			{ "([^s])cu", "$1ku" },
		});

		addRegexRuleLayer(new String[][] {
			{ "^c", "s" },
		});

		addReplacementRuleLayer(new String[][] {
			{ "sc", "s" },
		});

		addReplacementRuleLayer(new String[][] {
			{ "c", "s" },
		});

		addReplacementRuleLayer(new String[][] {
			{ "h", "" },
		});

		addReplacementRuleLayer(new String[][] {
			{ "ae", "e" },
			{ "oe", "o" },
			{ "ie", "i" },
		});

		addRegexRuleLayer(new String[][] {
			{ "e$", "" },
			{ "er$", "r" },
			{ "([bfklmnrstw])er", "$1r" },
			{ "el$", "l" },
			{ "([bfklmnrstw])el", "$1l" },
			{ "ar$", "r" },
			{ "([bfklmnrstw])a$", "$1r" },
		});

		addReplacementRuleLayer(new String[][] {
			{ "ai", "ei" },
		});

		addReplacementRuleLayer(new String[][] {
			{ "gk", "k" },
			{ "g", "k" },
			{ "qu", "k" },
			{ "q", "k" },
			{ "ts", "s" },
			{ "tk", "s" },
		});

	}

}

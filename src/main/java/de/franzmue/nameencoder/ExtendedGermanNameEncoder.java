package de.franzmue.nameencoder;

import java.util.function.Supplier;

import de.franzmue.nameencoder.GermanNameEncoder;
import de.franzmue.nameencoder.NameEncoder;

/**
 * Add rules the the GermanNameEncoder class in order to be able to find similar German family names
 * in regional historic spelling variations.
 * <p>
 * Please note: The rules here are only meant to serve as an example for a specific case.
 * </p>
 * 
 * @see de.franzmue.nameencoder.GermanNameEncoder
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public class ExtendedGermanNameEncoder extends GermanNameEncoder {
	
	protected static Supplier<NameEncoder> encoderSupplier = () -> new ExtendedGermanNameEncoder();

	public static void main(String[] args) {
		createCodes(args, encoderSupplier);
	}

	public ExtendedGermanNameEncoder() {
		super();
		
		addRegexRuleLayer(new String[][] {
			{ "(e?r)(t|w)", "$1b" },
			{ "(se)m", "$1n" },
			{ "tke$", "t" },
			{ "(in)t(el|er)", "$1s$2" },
			{ "(il|ele?r|le?r)$", "l" },
			{ "(irm)(il|en|e)", "eren" },
			{ "(erm)(il|en|e?l)", "$1" },
			{ "kne?r", "kr" },
			{ "tle?r", "tel" },
			{ "tne?r", "tr" },
			{ "sne?r$", "s" },
			{ "kt$", "kr" },
			{ "([bfklmnrstw])in$", "$1" },
			{ "efe?r", "esr" },
			{ "(in)(o|us|in)", "$1" },
			{ "([aeiou]k)t", "$1" },
			{ "(lt)i$", "$1" },
			{ "l(e|i|o)t$", "lt" },
			{ "(ma)(r|us)", "$1n" },
			{ "nstl$", "nsl" },
			{ "nft$", "nf" },
			{ "^(se)r", "$1" },
			{ "rlk$", "lk" },
			{ "drik", "s" },
			{ "er(ink)", "$1" },
			{ "al(i|t|u)(s.+)$", "al${2}" },
			{ "s(en|e?r|in)", "s" },
			{ "mb", "m" },
			{ "et", "es" },
			{ "alt", "als" },
			{ "tim", "sim" },
			{ "tw", "sw" },
			{ "ult", "olt" },
			{ "rik", "rk" },
			{ "ont", "on" },
			{ "alk", "lik" },
			{ "link", "lik" },
			{ "elk", "ilk" },
			{ "bok", "buk" },
		});

		addRegexRuleLayer(new String[][] {
			{ "(f|w)([aeiou])", "b$2" },
			{ "([aeiou])(f|w)", "$1b" },
		});
	}

}

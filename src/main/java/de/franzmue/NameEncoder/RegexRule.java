package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.Rule;

import java.util.regex.Pattern;

/**
 * A rule describing the matching of a string against a regular expression and replacing it.
 * 
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public final class RegexRule extends Rule {
	
	private Pattern pattern;

	public RegexRule(String regex, String replacement) {
		super(regex, replacement);

		pattern =  Pattern.compile(source);
	}
	
	@Override
	public String getRegex() {
		return source;
	}
	
	@Override
	public Pattern getPattern() {
		return pattern;
	}

}

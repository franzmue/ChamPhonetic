package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.Rule;

/**
 * A rule describing the replacement of a substring by another string.
 * 
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public final class ReplacementRule extends Rule {

	public ReplacementRule(String source, String destination) {
		super(source, destination);
	}
	
	@Override
	public String getSource() {
		return source;
	}

}

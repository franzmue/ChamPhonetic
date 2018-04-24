package de.franzmue.nameencoder;

import java.util.Iterator;
import java.util.Collections;
import java.util.List;

import de.franzmue.nameencoder.RegexRule;
import de.franzmue.nameencoder.ReplacementRule;
import de.franzmue.nameencoder.Rule;

import java.util.ArrayList;

/**
 * Sorted group of rules in a rule layer to be applied to the temporary code during the process of encoding a name.
 * 
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public class Rules implements Iterable<Rule> {
	protected List<Rule> rules = new ArrayList<>();

	public List<Rule> getRules() {
		return Collections.unmodifiableList(rules);
	}
	
	public Rules addReplacementRule(String source, String destination) {
		ReplacementRule rule = new ReplacementRule(source, destination);
		rules.add(rule);

		return this;
	}
	
	public Rules addRegexRule(String regex, String replacement) {
		RegexRule rule = new RegexRule(regex, replacement);
		rules.add(rule);
		
		return this;
	}

	@Override
	public Iterator<Rule> iterator() {
		return new RulesIterator();
	}
	
	private class RulesIterator implements Iterator<Rule> {
		
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < rules.size() && rules.get(currentIndex) != null;
		}

		@Override
		public Rule next() {
			return rules.get(currentIndex++);
		}
		
		@Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
		
	}
}

package net.michael_bailey

import net.michael_bailey.matcher.DuckDuckGoSeraphMatcher

class BritishInformationTechnologiesSeraphProvider : ExternalSeraphProvider() {
	override fun getMatchers(): List<SeraphMatcher> {
		return listOf(
			DuckDuckGoSeraphMatcher
		)
	}
}

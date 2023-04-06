package io.github.tropheusj.clean_slate.config;

import com.google.gson.JsonParseException;

import java.util.List;

public enum FilterType {
	BLACKLIST,
	WHITELIST;

	public boolean permits(String namespace, List<String> namespaces) {
		return switch (this) {
			case BLACKLIST -> !namespaces.contains(namespace);
			case WHITELIST -> namespaces.contains(namespace);
		};
	}

	public static FilterType of(String name) {
	    return switch (name) {
			case "blacklist" -> BLACKLIST;
			case "whitelist" -> WHITELIST;
			default -> throw new JsonParseException("Unknown filter type: " + name);
		};
	}
}

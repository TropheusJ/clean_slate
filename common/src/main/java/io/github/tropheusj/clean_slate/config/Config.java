package io.github.tropheusj.clean_slate.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import io.github.tropheusj.clean_slate.CleanSlate;
import net.minecraft.util.GsonHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
	public static final Map<String, ListenerConfiguration> BY_NAME = new HashMap<>();

	public static void load(Path configFile) {
		CleanSlate.LOGGER.info("Loading Clean Slate config...");
		if (!Files.exists(configFile)) {
			CleanSlate.LOGGER.info("No config to load!");
			return;
		}
		try {
			String content = Files.readString(configFile);
			JsonObject json = GsonHelper.convertToJsonObject(JsonParser.parseString(content), "root");
			JsonObject filters = GsonHelper.getAsJsonObject(json, "filters");
			filters.entrySet().forEach(entry -> {
				String name = entry.getKey();
				JsonObject configuration = GsonHelper.convertToJsonObject(entry.getValue(), "filter for " + name);

				JsonArray namespacesJson = GsonHelper.getAsJsonArray(configuration, "namespaces");
				List<String> namespaces = new ArrayList<>();
				namespacesJson.forEach(
						namespace -> namespaces.add(GsonHelper.convertToString(namespace, "namespace"))
				);

				FilterType type = FilterType.of(GsonHelper.getAsString(configuration, "type"));

				ListenerConfiguration config = new ListenerConfiguration(name, namespaces, type);
				if (BY_NAME.put(name, config) != null)
					throw new JsonParseException("Duplicate configuration for " + name);
			});
		} catch (IOException | JsonParseException e) {
			throw new RuntimeException("Failed to read config", e);
		}
		CleanSlate.LOGGER.info("Config loaded successfully!");
		int configured = BY_NAME.size();
		switch (configured) {
			case 0 -> CleanSlate.LOGGER.info("No configuration loaded.");
			case 1 -> CleanSlate.LOGGER.info("Loaded configuration for {} reloader: {}", configured, BY_NAME.keySet());
			default -> CleanSlate.LOGGER.info("Loaded configuration for {} reloaders: {}", configured, BY_NAME.keySet());
		}
	}
}

package io.github.tropheusj.clean_slate;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.tropheusj.clean_slate.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class CleanSlate {
	public static final Logger LOGGER = LoggerFactory.getLogger("Clean Slate");

	public static void init() {
		Path config = configDir().resolve("clean_slate.json");
		Config.load(config);
	}

	@ExpectPlatform
	public static Path configDir() {
	    throw new AssertionError();
	}
}

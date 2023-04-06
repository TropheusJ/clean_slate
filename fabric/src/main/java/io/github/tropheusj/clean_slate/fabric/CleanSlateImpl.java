package io.github.tropheusj.clean_slate.fabric;

import io.github.tropheusj.clean_slate.CleanSlate;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class CleanSlateImpl implements ModInitializer {
	@Override
	public void onInitialize() {
		CleanSlate.init();
	}

	public static Path configDir() {
		return FabricLoader.getInstance().getConfigDir();
	}
}

package io.github.tropheusj.clean_slate.forge;

import io.github.tropheusj.clean_slate.CleanSlate;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

@Mod("clean_slate")
public class CleanSlateImpl {
	public CleanSlateImpl() {
		CleanSlate.init();
	}

	public static Path configDir() {
		return FMLPaths.CONFIGDIR.get();
	}
}

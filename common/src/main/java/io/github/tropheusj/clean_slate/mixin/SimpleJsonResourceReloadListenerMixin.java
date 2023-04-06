package io.github.tropheusj.clean_slate.mixin;

import com.google.common.collect.Iterators;
import io.github.tropheusj.clean_slate.config.Config;
import io.github.tropheusj.clean_slate.config.ListenerConfiguration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Iterator;

@Mixin(SimpleJsonResourceReloadListener.class)
public class SimpleJsonResourceReloadListenerMixin {

	@Shadow
	@Final
	private String directory;

	@ModifyVariable(
			method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Ljava/util/Map;",
			at = @At(
					value = "INVOKE_ASSIGN",
					target = "Ljava/util/Collection;iterator()Ljava/util/Iterator;"
			)
	)
	private Iterator<ResourceLocation> clean_slate$filterIterator(Iterator<ResourceLocation> iterator) {
		ListenerConfiguration config = Config.BY_NAME.get(directory);
		return config == null ? iterator : Iterators.filter(iterator, config::permits);
	}
}

package io.github.tropheusj.clean_slate.config;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record ListenerConfiguration(String listenerName, List<String> namespaces, FilterType type) {
	public boolean permits(ResourceLocation id) {
		return type.permits(id.getNamespace(), namespaces);
	}
}

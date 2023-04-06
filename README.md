# Clean Slate
This mod allows for filtering data from datapacks by namespace and reloader.

### Configuration
The config file should be at `.minecraft/config/clean_slate.json`.
Here's a template that will prevent all recipes and advancements in Minecraft's namespace from loading.
```json
{
  "filters": {
    "recipes": {
      "type": "blacklist",
      "namespaces": [ "minecraft" ]
    },
    "advancements": {
      "type": "blacklist",
      "namespaces": [ "minecraft" ]
    }
  }
}
```
This supports any JSON reloader. In vanilla this includes:
- `advancements`
- `recipes`
- `item_modifiers`
- `loot_tables`
- `predicates`

and on Forge, `loot_modifiers` as well.
Mods also often add their own reloaders.

# GriefPrevention Squaremap Addon

Shows GriefPrevention claims on your [squaremap](https://github.com/jpenilla/squaremap) web map. Click a claim to see its owner and everyone trusted in it.
Based on the GriefPrevention addon from [jpenilla/squaremap-addons](https://github.com/jpenilla/squaremap-addons).

## Requirements
| Software | Version |
| --- | --- |
| Paper | 1.20+ |
| Java | 21 |
| GriefPrevention | 17.0.0 or newer |
| squaremap | 1.2.2 or newer |

## Installation

1. Drop the jar into your `plugins` folder.
2. Restart the server.
3. Edit `plugins/GriefPrevention-Squaremap-Addon/config.yml` if you want to change colors or tooltips, then restart again.

Claims appear only in worlds where GriefPrevention has claims enabled. Subdivisions are not drawn, only top level claims.

## Configuration

| Option | Default | Description |
| --- | --- | --- |
| `update-interval` | `300` | Seconds between map refreshes. Minimum is `20`. |
| `control.label` | `GriefPrevention` | Layer name in the map's layer list. |
| `control.show` | `true` | Show the layer toggle on the map. |
| `control.hide-by-default` | `false` | Hide the layer until a visitor turns it on. |
| `control.z-index` | `99` | Drawing order of the layer. |
| `control.layer-priority` | `99` | Position of the layer in the layer list. |
| `style.stroke.color` | `#00AA00` | Claim border color. |
| `style.stroke.weight` | `1` | Claim border width. |
| `style.stroke.opacity` | `1.0` | Claim border opacity, from `0.0` to `1.0`. |
| `style.fill.color` | `#00AA00` | Claim fill color. |
| `style.fill.opacity` | `0.2` | Claim fill opacity, from `0.0` to `1.0`. |
| `strings.public` | `Public` | Owner name shown for admin claims or claims without an owner. |
| `region.tooltip.regular-claim` | see config | HTML tooltip for player claims. |
| `region.tooltip.admin-claim` | see config | HTML tooltip for admin claims. |

Admin claims are always drawn in blue.

### Tooltip placeholders

| Placeholder | Value |
| --- | --- |
| `{owner}` | Claim owner |
| `{managers}` | Players with permission trust |
| `{builders}` | Players with build trust |
| `{containers}` | Players with container trust |
| `{accessors}` | Players with access trust |
| `{world}` | World name |
| `{id}` | Claim ID |
| `{area}` | Claim area in blocks |
| `{width}` | Claim width |
| `{height}` | Claim length |

## Building

```
./gradlew build
```

The jar lands in `build/libs`.

## Credits

Original addon by BillyGalbreath, part of [squaremap-addons](https://github.com/jpenilla/squaremap-addons).
dsfsdf
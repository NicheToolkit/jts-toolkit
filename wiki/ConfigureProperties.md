# jackson configuration

* prefix

>
> nichetoolkit.jts.jackson
>

* values

|   value   |   type    | defaultValue |                  description                   |
|:---------:|:---------:|:------------:|:----------------------------------------------:|
| `enabled` | `Boolean` |   `false`    | the switch of auto configure with jts jackson. |

* properties

```properties
nichetoolkit.jts.jackson.enabled=true
```

# shape configuration

* prefix

>
> nichetoolkit.jts.shape
>

* values

|       value        |   type    | defaultValue |                 description                 |
|:------------------:|:---------:|:------------:|:-------------------------------------------:|
|     `enabled`      | `Boolean` |   `false`    | the switch of auto configure with jts shap. |
| `space.root-path`  | `String`  |              |   the root file path of jts shape space.    |
| `space.cache-path` | `String`  |              |   the cache file path of jts shape space.   |
| `space.shape-path` | `String`  |              |   the shape file path of jts shape space.   |
|  `space.zip-path`  | `String`  |              |    the zip file path of jts shape space.    |

* properties

```properties
nichetoolkit.jts.shape.enabled=true
nichetoolkit.jts.shape.space.root-path=G:\\data\\server
nichetoolkit.jts.shape.space.cache-path=\\cache
nichetoolkit.jts.shape.space.shape-path=\\shape
nichetoolkit.jts.shape.space.zip-path=\\zip
```

# Flow Property Manipulation #

$[] - is property expansion notation in Flow. It is a preprocessor action, done before running a script, etc. The default content is an XPATH expression such as:
```xml
$[/path/anotherPath/[A path with spaces]/propertynameOrIntrinsicName]
```
There are other content types:
```
/increment - this will increment the following XPATH referenced property by 1
/javascript
- This gives access to properties through dot notation (and ["paths with spaces"])
- It's useful for doing comparisons and importing JSON objects
- It also gives full access to the API using, api.APINAME({JSON})
```

## Basic usage ##

To reference a property called 'ResultVal' in current component, you can use:

Javascript:
```javascript
$[/javascript myComponent.ResultVal]

$[/javascript getProperty("/myComponent/ResultVal")]

$[/javascript getProperty("$[/myComponent]/ResultVal")]
```

Elsewhere:

```
$[/myComponent/ResultVal]
```

Instead of relative addresses, can use absolute eg:

If run in the context of a pipeline called 'protected-command-credentials', then the 2 following are equivalent:

```
$[/myPipeline/matcherTest]

$[/projects/Custom012/pipelines/protected-command-credentials/matcherTest]
```

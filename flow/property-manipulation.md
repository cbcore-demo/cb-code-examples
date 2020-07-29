# Flow Property Manipulation #

$[] - is property expansion notation in Flow. It is a preprocessor action, done before running a script, etc. The default content is an XPATH expression such as:
```xml
/path/anotherPath[A path with spaces]/propertynameOrIntrinsicName
```
There are other content types:
```
/increment - this will increment the following XPATH referenced property by 1
/javascript
- This gives access to properties thought dot notation (and ["paths with spaces"])
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

If run in the context of the pipeline, then the 2 following are equivalent:

```
/myPipeline/matcherTest

/projects/Custom012/pipelines/protected-command-credentials/matcherTest
```

## Reference result of JIRA Get Issues (multiple property sheets) ##
```javascript
$[/javascript myStageRuntime.getIssuesResult.issues["$[jiraticket]"].status]
```
```
$[/myStageRuntime/getIssuesResult/issues[$[jiraticket]]/status]
```

## Get an element from a JSON result that is stored in an outputParameter from a different step in a stage ##
```javascript
$[/javascript JSON.parse(myPipelineStagetasks["The task Name"].job.outputParameters)["JSON field Path"]]
```

## Access outputParameter called restResult from a different step in the current Process ##
```
$[/myJob/steps/Get Route Details/steps/Get a Route in a namespace/outputParameters/restResult]
```

## Access outputParameter called ChangeRequestSysID from a different step, called Create CR, in the same pipeline stage ##
```
$[/myStageRuntime/tasks/Create CR/job/outputParameters/ChangeRequestSysID]
```

## Access outputParameter of a procedure step from another step in that procedure ##
$[/myJob/jobSteps[Call Subproc]/outputParameters/myoutput]

## Use parameter (called SourceFileName) defined in Procedure within a procedure step ##
```
$[SourceFileName]
```

### Get PipelineRuntime name ###
```
$[/myPipelineRuntime/name]
```

## Extract single value from JSON ##
### JSON sample ###
```json
"{
	"project":{
		"projectId":"11a6a613-d878-11e9-8df7-000c297e1ec0",
		"projectName":"My Project",
		"createTime":"2019-09-16T11:49:41.506Z",
		"lastModifiedBy":"admin",
		"modifyTime":"2019-09-16T11:49:41.506Z",
		"owner":"admin",
		"processCount":"0",
		"propertySheetId":"11a6cd25-d878-11e9-8df7-000c297e1ec0",
		"resourceName":"local",
		"stageCount":"0",
		"tracked":"1",
		"workspaceName":"default"
	}
}"
```
### Example - retrieve project.workspaceName ###
```javascript
$[/javascript var obj=JSON.parse('$[/myComponent/ResultVal]'); obj.project.workspaceName]
```

Groovy example:
```groovy
import com.electriccloud.client.groovy.ElectricFlow
import groovy.json.JsonSlurper
ElectricFlow ef = new ElectricFlow()

def jsonSlurper = new JsonSlurper()
def obj = ef.getProperty propertyName: "/myComponent/ResultVal"

def objval = jsonSlurper.parseText(obj.property.value)

println objval

println objval.project.workspaceName
```


##  Get an element from a JSON result that is stored in an outputParameter of a different step, run it in the context of a previously run step ##
```groovy
expandString jobStepId : "72411ebf-a73f-11ea-afaa-42010a840017", value : '''\
$[/javascript
	JSON.parse(
		myJob.steps["Get Route Details"].steps["Get a Route in a namespace"].outputParameters.restResult
	).spec.to.kind
]
'''
```
## Similar to above but from a job in a stage ##
```groovy
expandString jobId : "job_168573_20200622125324", value : '''\
$[/javascript
	myStageRuntime.tasks["Create CR"].job.outputParameters.ChangeRequestSysID
]
'''
```

## expandString to a workflow run (called Workflow), the definition is called WorkflowDefinition ##
```groovy
expandString workflowName : "workflow_14_20200608104314", projectName: "pollServiceNow", value : '''\
$[/javascript
	myWorkflow.recordID
]
'''
```

## Other ##

### Reference the task in a particular stage entry gate (eg for setting correlation id for ServiceNow Create CR ###)
```
/flowRuntime/$[/myPipelineRuntime/id]/stage/Production/gate/PRE/taskName/ServiceNow Approval
```

### Generate link to a pipeline run ###
```
https://flow.cloudbees.guru/flow/?s=Flow+Tools&ss=Flow#pipeline-run/$[/myPipeline/id]/$[/myPipelineRuntime/id]'
```

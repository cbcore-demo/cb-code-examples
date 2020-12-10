# Flow Jenkins Plugin #

## Call REST API Example - trigger Release and pass in Parameter ##

Configuration: MyFlowAtHome

URL Path: /releases

HTTP Method: POST

Body:
{
"projectName": "My Project",
"releaseName": "REST release",
"pipelineParameter": [{"pipelineParameterName": "JenkinsParam", "value": "PhilsTest!"}]
}

### Notes ###
- Param 'JenkinsParam' must be defined at the pipeline level

def ProjectName = "Custom007"
def JiraRESTConfig = "JiraREST"
def JiraConfig = "pm_demo"
def JiraProject = "BAR"
​
project ProjectName,{
	pipeline 'Jira FixVersion', {
		stage 'Dev', {
			task 'Create Fix Version', {
				actualParameter = [
					connection_config: JiraRESTConfig,
					contentType: 'application/json',
					cookieHeaderValue: '',
					filePath: '',
					formContent: """\
						{"description": "An excellent version","name": "1.\$[/increment /myPipeline/FixVersion]","userReleaseDate": "5/Jul/2020","project": "${JiraProject}","archived": false,"released": false}
					""".stripIndent(),
					headers: '',
					ignoreAuth: '0',
					pathUrl: 'rest/api/latest/version',
					postScriptContent: '''\
						import groovy.json.* 
						import com.electriccloud.client.groovy.ElectricFlow
						ElectricFlow ef = new ElectricFlow()
						File file = new File(args[0])
						String fileContent = file.text
						def jsonSlurper = new JsonSlurper()
						def RestResponse = jsonSlurper.parseText(fileContent)
						def JobId = ef.getProperty(propertyName: "/myJob/jobId").property.value
						ef.setProperty(propertyName: "/myStageRuntime/ec_summary/Fix Version",
							value: """\
								<html><a href="${RestResponse.self}" target="_blank">${RestResponse.name}</a></html>
							""".stripIndent(),
							jobId: JobId)
					'''.stripIndent(),
					postScriptOutput: '',
					postScriptShell: 'ec-groovy',
					requestType: 'POST',
					response_outpp: '/myJob/RestResponse',
				]
				subpluginKey = 'EC-Rest'
				subprocedure = 'runRest'
				taskType = 'PLUGIN'
			}
		}
	}
}
Collapse




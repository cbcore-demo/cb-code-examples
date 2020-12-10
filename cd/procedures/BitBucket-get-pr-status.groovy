
procedure 'Get PR Status', {
  projectName = 'Custom008'
  timeLimitUnits = 'minutes'

  formalParameter 'config_name', defaultValue: '', {
    expansionDeferred = '0'
    required = '1'
    type = 'entry'
  }

  formalParameter 'outputProperty', defaultValue: '', {
    description = 'Property in which to store the PR status'
    expansionDeferred = '0'
    required = '1'
    type = 'entry'
  }

  formalParameter 'prId', defaultValue: '', {
    description = 'The id of the PR to be queried'
    expansionDeferred = '0'
    required = '1'
    type = 'entry'
  }

  step 'Call REST API', {
    alwaysRun = '0'
    broadcast = '0'
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    parallel = '0'
    projectName = 'Custom008'
    releaseMode = 'none'
    subprocedure = 'runRest'
    subproject = '/plugins/EC-Rest/project'
    timeLimitUnits = 'minutes'
    actualParameter 'connection_config', '$[config_name]'
    actualParameter 'contentType', ''
    actualParameter 'cookieHeaderValue', ''
    actualParameter 'filePath', ''
    actualParameter 'formContent', ''
    actualParameter 'headers', 'Accept application/json'
    actualParameter 'ignoreAuth', '0'
    actualParameter 'pathUrl', 'rest/api/1.0/projects/~dev1/repos/barclays-tc15-demo/pull-requests/$[prId]'
    actualParameter 'postScriptContent', '''import groovy.json.* 
import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()
File file = new File(args[0])
String fileContent = file.text
def jsonSlurper = new JsonSlurper()
def RestResponse = jsonSlurper.parseText(fileContent)
ef.setProperty(propertyName: \"$[outputProperty]\",
\tvalue: RestResponse.state)
println \"State of PR is \" + RestResponse.state'''
    actualParameter 'postScriptOutput', ''
    actualParameter 'postScriptShell', 'ec-groovy'
    actualParameter 'requestType', 'GET'
    actualParameter 'response_outpp', '/myJob/RestResponse'
  }

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties

    property 'parameters', {

      // Custom properties

      property 'Configuration', {

        // Custom properties
        formType = 'standard'
      }

      property 'config_name', {

        // Custom properties
        formType = 'standard'
      }

      property 'outputProperty', {

        // Custom properties
        formType = 'standard'
      }

      property 'prId', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
  ec_parameterForm = '''<editor>
  <formElement>
    <property>config_name</property>
    <label>Configuration</label>
    <documentation>Select which configuration to use from the EC-Rest plugin</documentation>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

 <formElement>
    <property>prId</property>
    <label>Pull Request ID</label>
    <documentation>The bitbucket Pull Request ID to be queried.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>outputProperty</property>
    <label>Output Property</label>
    <documentation>The property in which to store the PR state.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}

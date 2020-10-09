
procedure 'Get Branch from Commit Hash', {
  projectName = 'Custom008'
  timeLimitUnits = 'minutes'

  formalParameter 'commit_hash', defaultValue: '', {
    expansionDeferred = '0'
    required = '1'
    type = 'entry'
  }

  formalParameter 'config_name', defaultValue: '', {
    expansionDeferred = '0'
    required = '1'
    type = 'entry'
  }

  formalParameter 'output_property', defaultValue: '', {
    expansionDeferred = '0'
    required = '1'
    type = 'entry'
  }

  step 'Get Branch', {
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
    actualParameter 'pathUrl', 'rest/branch-utils/1.0/projects/~dev1/repos/barclays-tc15-demo/branches/info/$[commit_hash]'
    actualParameter 'postScriptContent', '''import groovy.json.* 
import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()
File file = new File(args[0])
String fileContent = file.text
def jsonSlurper = new JsonSlurper()
def RestResponse = jsonSlurper.parseText(fileContent)
ef.setProperty(propertyName: \"$[output_property]\",
\tvalue: RestResponse.values[0].displayId)
println \"Branch for this commit hash is \" + RestResponse.values[0].displayId'''
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

      property 'commit_hash', {

        // Custom properties
        formType = 'standard'
      }

      property 'config_name', {

        // Custom properties
        formType = 'standard'
      }

      property 'output_property', {

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
    <property>commit_hash</property>
    <label>Commit Hash</label>
    <documentation>The bitbucket commit hash to be queried.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>output_property</property>
    <label>Output Property</label>
    <documentation>The property in which to store the branch name.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}

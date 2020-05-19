
procedure 'OpenShift Patch Route', {
  description = 'Uses the Openshift REST API to create or modify a route'
  jobNameTemplate = 'Openshift_CreateModify_Route_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'Custom005'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'Config', defaultValue: '', {
    description = 'The REST plugin configuration to use'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Namespace', defaultValue: '', {
    description = 'The Openshift namespace in which the route is defined'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Output', defaultValue: '', {
    description = 'The property to store the output from the API call'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Patch', defaultValue: '''{

}''', {
    description = 'The yaml specifying the patch to be applied'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'textarea'
  }

  formalParameter 'Route', defaultValue: '', {
    description = 'The name of the Route to patch'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Token', defaultValue: '', {
    description = 'The Bearer Token for the Service Account'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'Patch Route', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = null
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = null
    parallel = '0'
    postProcessor = null
    precondition = ''
    projectName = 'Custom005'
    releaseMode = 'none'
    resourceName = ''
    shell = null
    subprocedure = 'runRest'
    subproject = '/plugins/EC-Rest/project'
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = null
    workspaceName = ''
    actualParameter 'connection_config', '$[Config]'
    actualParameter 'contentType', 'application/json-patch+json'
    actualParameter 'filePath', ''
    actualParameter 'formContent', '$[Patch]'
    actualParameter 'headers', 'Authorization: Bearer $[Token]'
    actualParameter 'pathUrl', '/oapi/v1/namespaces/$[Namespace]/routes/$[Name]'
    actualParameter 'requestType', 'PATCH'
    actualParameter 'response_outpp', '$[Output]'
  }

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties

    property 'parameters', {

      // Custom properties

      property 'Config', {

        // Custom properties
        formType = 'standard'
      }

      property 'Configuration', {

        // Custom properties
        formType = 'standard'
      }

      property 'Namespace', {

        // Custom properties
        formType = 'standard'
      }

      property 'Output', {

        // Custom properties
        formType = 'standard'
      }

      property 'Patch', {

        // Custom properties
        formType = 'standard'
      }

      property 'Route', {

        // Custom properties
        formType = 'standard'
      }

      property 'Token', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
  ec_parameterForm = '''<editor>
  <formElement>
    <property>Config</property>
    <documentation>The REST plugin configuration to use</documentation>
    <label>Configuration</label>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

    <formElement>
    <property>Token</property>
    <documentation>The Bearer Token for the Service Account</documentation>
    <label>Bearer Token</label>
    <type>entry</type>
    <required>1</required>
  </formElement>
  
  <formElement>
    <property>Namespace</property>
    <documentation>\tThe Openshift namespace in which the route is defined</documentation>
    <label>Namespace</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

    <formElement>
    <property>Route</property>
    <documentation>The name of the Route to patch</documentation>
    <label>Route</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

    <formElement>
    <property>Patch</property>
    <documentation>The yaml specifying the patch to be applied</documentation>
    <label>Patch</label>
    <type>textarea</type>
    <required>1</required>
    <value>{

}</value>
  </formElement>

    <formElement>
    <property>Output</property>
    <documentation>The property to store the output from the API call</documentation>
    <label>Output Property</label>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}

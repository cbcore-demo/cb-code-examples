
procedure 'Run Pipeline Stage', {
  description = ''
  jobNameTemplate = 'RunPipelineStage_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'My Tests'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'Configuration', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Pipeline', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Project', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'project'
  }

  formalParameter 'Stage', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'RunPipelineStage', {
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
    projectName = 'My Tests'
    releaseMode = 'none'
    resourceName = ''
    shell = null
    subprocedure = 'runRest'
    subproject = '/plugins/EC-Rest/project'
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = null
    workspaceName = ''
    actualParameter 'connection_config', '$[Configuration]'
    actualParameter 'contentType', 'application/json'
    actualParameter 'filePath', ''
    actualParameter 'formContent', '''{
    \"projectName\" : \"$[Project]\",
    \"pipelineName\" : \"$[Pipeline]\",
    \"stagesToRun\" : \"$[Stage]\"
}'''
    actualParameter 'headers', ''
    actualParameter 'pathUrl', 'rest/v1.0/pipelines'
    actualParameter 'requestType', 'POST'
    actualParameter 'response_outpp', ''
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

      property 'Pipeline', {

        // Custom properties
        formType = 'standard'
      }

      property 'Project', {

        // Custom properties
        formType = 'standard'
      }

      property 'Stage', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
  ec_ParameterForm = '''<editor>
  <formElement>
    <property>Configuration</property>
    <label>Configuration</label>
    <documentation>Select which configuration to use from the EC-Rest plugin</documentation>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>Project</property>
    <label>Project</label>
    <documentation>In which project is the pipeline to run?</documentation>
    <type>project</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>Pipeline</property>
    <label>Pipeline</label>
    <documentation>Which pipeline to run?</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

    <formElement>
    <property>Stage</property>
    <label>Stage</label>
    <documentation>Which stage to run?</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}

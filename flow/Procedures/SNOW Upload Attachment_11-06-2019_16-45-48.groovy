
procedure 'SNOW Upload Attachment', {
  description = 'Upload attachment to ServiceNow'
  jobNameTemplate = 'SNOW_Upload_Attachment_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'Default'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'Config', defaultValue: '', {
    description = 'EC-Rest configuration that defines the endpoint'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'ContentType', defaultValue: '', {
    description = 'Content Type of the attachment.  e.g. image/jpeg'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'FileName', defaultValue: '', {
    description = 'Filename to give the attachment.  This will show in the ServiceNow GUI.'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'SourceFileLocation', defaultValue: '', {
    description = 'Location of source file'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'TableName', defaultValue: '', {
    description = 'Name of table to upload attachment to'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'TableSysId', defaultValue: '', {
    description = 'Table Sys id of the record to upload the attachment to'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'Upload attachment', {
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
    projectName = 'Default'
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
    actualParameter 'contentType', '$[ContentType]'
    actualParameter 'filePath', '@$[SourceFileLocation]'
    actualParameter 'formContent', ''
    actualParameter 'headers', ''
    actualParameter 'pathUrl', 'api/now/attachment/file?table_name=$[TableName]&table_sys_id=$[TableSysId]&file_name=$[FileName]'
    actualParameter 'requestType', 'POST'
    actualParameter 'response_outpp', ''
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

      property 'ContentType', {

        // Custom properties
        formType = 'standard'
      }

      property 'FileName', {

        // Custom properties
        formType = 'standard'
      }

      property 'Source file location', {

        // Custom properties
        formType = 'standard'
      }

      property 'SourceFileLocation', {

        // Custom properties
        formType = 'standard'
      }

      property 'TableName', {

        // Custom properties
        formType = 'standard'
      }

      property 'TableSysId', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
  ec_parameterForm = '''<editor>
  <formElement>
    <property>Config</property>
    <label>Configuration</label>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
  </formElement>

  <formElement>
    <property>ContentType</property>
    <label>ContentType</label>
  </formElement>

  <formElement>
    <property>FileName</property>
    <label>FileName</label>
  </formElement>

  <formElement>
    <property>SourceFileLocation</property>
    <label>SourceFileLocation</label>
  </formElement>

  <formElement>
    <property>TableName</property>
    <label>TableName</label>
  </formElement>

  <formElement>
    <property>TableSysId</property>
    <label>TableSysId</label>
  </formElement>
</editor>'''
}

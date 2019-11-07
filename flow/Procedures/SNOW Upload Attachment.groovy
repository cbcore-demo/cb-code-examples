
procedure 'SNOW Upload Attachment', {
  description = 'Upload attachment to ServiceNow'
  jobNameTemplate = 'SNOW_Upload_Attachment_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'Default'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'Configuration', defaultValue: '', {
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
    description = 'The Table Sys Id of the record to attach the file to.'
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
    actualParameter 'filePath', '$[SourceFileLocation]'
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

      property 'Configuration', {

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
  <help>https://developer.servicenow.com/app.do#!/rest_api_doc?v=newyork&amp;id=r_AttachmentAPI-POST</help>
  <formElement>
    <property>Configuration</property>
    <label>Configuration</label>
    <documentation>Select which configuration to use from the EC-Rest plugin</documentation>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

 <formElement>
    <property>TableName</property>
    <label>TableName</label>
    <documentation>The name of the table you want to attach the file to. This parameter is required to post an attachment.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TableSysId</property>
    <label>TableSysId</label>
    <documentation>The sys_id of the record on the specified table that you want to attach the file to. This parameter is required to post an attachment.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>FileName</property>
    <label>FileName</label>
    <documentation>The name to give the attachment. This parameter is required to post an attachment.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>SourceFileLocation</property>
    <label>SourceFileLocation</label>
    <documentation>The location of the file on the filesystem.  File must be on the filesystem where the agent running this step is.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>ContentType</property>
    <label>ContentType</label>
    <documentation>The content type of the file you want to attach. This header is mandatory to post file attachments.</documentation>
    <value>application/json</value>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}


procedure 'Upload attachment curl', {
  description = 'Upload binary attachment to ServiceNow using curl'
  jobNameTemplate = 'SNOW_Upload_Attachment_curl_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'Default'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'FileName', defaultValue: '', {
    description = 'Location and name of file to upload.  Location is relative to the agent where this step will run., e.g. /tmp/WordDoc1.docx'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Host', defaultValue: '', {
    description = 'Service now host, e.g. ven01735.service-now.com'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Password', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'TableName', defaultValue: '', {
    description = 'Table Name to upload attachment to, e.g. change_request'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'TableSysId', defaultValue: '', {
    description = 'Table Sys Id to upload attachment to, e.g. 12037612087361287351823'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Username', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'curl', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''curl \"https://$[Host]/api/now/attachment/upload\" \\\n--request POST \\\n--header \"Accept:application/json\" \\\n--user \'$[Username]\':\'$[Password]\' \\\n--header \"Content-Type:multipart/form-data\" \\\n-F \'table_name=$[TableName]\' -F \'table_sys_id=$[TableSysId]\' -F \'uploadFile=@$[FileName]\''''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    projectName = 'Default'
    releaseMode = 'none'
    resourceName = ''
    shell = ''
    subprocedure = null
    subproject = null
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = ''
    workspaceName = ''
  }

  // Custom properties
  ec_parameterForm = '''<editor>
  <formElement>
    <property>Host</property>
    <label>Host</label>
    <documentation>Service now host, e.g. ven01735.service-now.com</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TableName</property>
    <label>TableName</label>
    <documentation>Table Name to upload attachment to, e.g. change_request</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TableSysId</property>
    <label>TableSysId</label>
    <documentation>Table Sys Id to upload attachment to, e.g. 12037612087361287351823</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>Username</property>
    <label>Username</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>Password</property>
    <label>Password</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

    <formElement>
    <property>FileName</property>
    <label>FileName</label>
    <documentation>Location and name of file to upload.  Location is relative to the agent where this step will run., e.g. /tmp/WordDoc1.docx</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}

/*
This procedure demonstrates retrieving a large piece of text (e.g. a file content0
From a credential password field and using it.  In thie exmaple the content is simple
echo'd to the log, but in reality could be written to disk
The file content places in the password field should use the '\n' encoding of the
carriage returns
*/
procedure 'Get-secret-file', {
  description = ''
  jobNameTemplate = ''
  projectName = 'pcherry'
  resourceName = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'cred', {
    description = 'Credential Object containing the secret file'
    expansionDeferred = '0'
    label = 'Credential Object'
    orderIndex = '1'
    required = '1'
    type = 'credential'
  }

  step 'Use-secret', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''password=$(ectool getFullCredential "cred" --value password)

echo "Starting"

echo -e "$password"

echo "Finishing"'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    releaseMode = 'none'
    resourceName = ''
    shell = 'bash'
    subprocedure = ''
    subproject = ''
    timeLimitUnits = 'seconds'
    workingDirectory = ''
    workspaceName = ''

    attachParameter {
      formalParameterName = '/projects/pcherry/procedures/Get-secret-file/formalParameters/cred'
    }
  }
}
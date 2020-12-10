
procedure 'oc login DOS', {
  description = ''
  jobNameTemplate = ''
  projectName = 'Custom012'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'credentials', defaultValue: '', {
    description = 'Openshift credentials'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'credential'
  }

  formalParameter 'host', defaultValue: '', {
    description = 'Openshift host'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'oc login', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''for /f %%i in (\'ectool getFullCredential \"credentials\" --value userName\') do set userName=%%i
for /f %%i in (\'ectool getFullCredential \"credentials\" --value password\') do set password=%%i

echo \"Starting\"

echo %userName%
echo %password%

oc login myhost.com --username %userName% --password %password%

echo \"Finishing\"
'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    projectName = 'Custom012'
    releaseMode = 'none'
    resourceName = 'windows-flow-test'
    shell = ''
    subprocedure = null
    subproject = null
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = ''
    workspaceName = ''

    attachParameter {
      formalParameterName = '/projects/Custom012/procedures/oc login DOS/formalParameters/credentials'
    }
  }

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties

    property 'parameters', {

      // Custom properties

      property 'credentials', {

        // Custom properties
        formType = 'standard'
      }

      property 'host', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
}

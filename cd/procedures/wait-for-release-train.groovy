
procedure 'WaitForReleaseTrain', {
  description = ''
  jobNameTemplate = ''
  projectName = 'Custom016'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'controlFlag', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'RTProject', defaultValue: '', {
    description = 'Which Project is the Release Train in?'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'WaitForFlowRuntime', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'WaitForReleaseTrain', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()
def Iteration = 1
def RTStatus = \"\"

def GetRTStatus = {
    RTStatus=ef.getProperty(
        propertyName: \'/projects/$[RTProject]/flowRuntimes/$[WaitForFlowRuntime]/$[controlFlag]\').property.value

    println \"Step outcome is: \" + RTStatus
    return RTStatus
}

while (GetRTStatus() != \'True\' && Iteration < 1000) {
    sleep 5000
    Iteration=Iteration+1
    println \'Check #\' + Iteration
}

if (RTStatus != \'True\') {
    println \"Release Train has not been released in a reasonable time.\"
    System.ext(-1)
}

println \"Release Train is allowing this pipeline to continue.\"'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    projectName = 'Custom016'
    releaseMode = 'none'
    resourceName = ''
    shell = 'ec-groovy'
    subprocedure = null
    subproject = null
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = ''
    workspaceName = ''
  }

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties

    property 'parameters', {

      // Custom properties

      property 'RTProject', {

        // Custom properties
        formType = 'standard'
      }

      property 'WaitForFlowRuntime', {

        // Custom properties
        formType = 'standard'
      }

      property 'controlFlag', {

        // Custom properties
        formType = 'standard'
      }

    }
  }
}

project "Default",{
	release 'Run from REST', {
		plannedEndDate = '2020-06-23'
		plannedStartDate = '2020-06-09'

		pipeline 'pipeline_Run from REST', {

			formalParameter 'BuildID'

			stage 'Stage 1', {
				task 'Get Build Information', {
					actualParameter = [
						'commandToRun': 'echo $[BuildID]',
					]
					subpluginKey = 'EC-Core'
					subprocedure = 'RunCommand'
					taskType = 'COMMAND'
				}
			}
		}
	}
}

// How to execute the above release and pass in params:
//
// ectool runPipeline Default "pipeline_Run from REST" --releaseName "Run from REST" --actualParameter BuildID=11111

//
// or
//
// curl -X POST "https://flow/rest/v1.0/pipelines?pipelineName=pipeline_Run%20from%20REST&projectName=Default&releaseName=Run%20from%20REST" -H "accept: application/json" -d "{\"actualParameter\":[{\"actualParameterName\":\"BuildID\",\"value\":\"1234\"}]}"
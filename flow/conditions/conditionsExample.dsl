project 'conditionsProj', {
	release 'conditionsR2', {
	  plannedEndDate = '2019-12-02'
	  plannedStartDate = '2019-11-18'

	  pipeline 'pipeline_conditionsR2', {

		formalParameter 'ec_stagesToRun', {
		  expansionDeferred = '1'
		}

		stage 'Stage 1', {

		  gate 'PRE'

		  gate 'POST'

		  task 'manual', {
			notificationEnabled = '1'
			notificationTemplate = 'ec_default_pipeline_manual_task_notification_template'
			taskType = 'MANUAL'
			approver = [
			  'admin',
			]
		  }
		}

		// Custom properties

		property 'ec_counters', {

		  // Custom properties
		  pipelineCounter = '1'
		}
	  }
	}
	
	
	release 'conditionsR1', {
	  plannedEndDate = '2019-12-02'
	  plannedStartDate = '2019-11-18'

	  pipeline 'pipeline_conditionsR1', {

		formalParameter 'ec_stagesToRun', {
		  expansionDeferred = '1'
		}

		stage 'stage1', {
		  colorCode = '#00adee'

		  gate 'PRE'

		  gate 'POST'

		  task 't1 - wait for precondition', {
			actualParameter = [
			  'commandToRun': 'echo test',
			]
			precondition = '$[/javascript myRelease.testProperty==\'100\']'
			subpluginKey = 'EC-Core'
			subprocedure = 'RunCommand'
			taskType = 'COMMAND'
		  }

		  task 't2 - run release', {
			subErrorHandling = 'continueOnError'
			subproject = 'conditionsProj'
			subrelease = 'conditionsR2'
			taskType = 'RELEASE'
			triggerType = 'async'
		  }

		  task 't3 - wait for release task completion', {
			actualParameter = [
			  'commandToRun': 'echo t3',
			]
			subpluginKey = 'EC-Core'
			subprocedure = 'RunCommand'
			taskType = 'COMMAND'

			waitDependency 'a1c5301a-09fa-11ea-b65e-00155d6915ff', {
			  dependentProjectName = 'conditionsProj'
			  dependentReleaseName = 'conditionsR2'
			  dependentStageName = 'Stage 1'
			  dependentTaskName = 'manual'
			}
		  }

		  task 't4 - wait for planning start date', {
			actualParameter = [
			  'commandToRun': 'echo t4',
			]
			duration = '5'
			plannedEndDate = '2019-11-18T12:10'
			plannedStartDate = '2019-11-18T12:05'
			subpluginKey = 'EC-Core'
			subprocedure = 'RunCommand'
			taskType = 'COMMAND'
			waitForPlannedStartDate = '1'
		  }
		}

		// Custom properties

		property 'ec_counters', {

		  // Custom properties
		  pipelineCounter = '2'
		}
	  }

	  subrelease {
		subreleaseName = 'conditionsR2'
		subreleaseProject = 'conditionsProj'
	  }

	  // Custom properties
	  testProperty = '10'
	}

}

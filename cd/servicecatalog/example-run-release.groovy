catalogItem 'Example Run Release', {
  description = '''<xml>
  <title>
    Release: Triggered by Service Catalog
Project: Custom020
  </title>

  <htmlData>
    <![CDATA[
      
    ]]>
  </htmlData>
</xml>'''
  allowScheduling = '0'
  buttonLabel = 'Execute'
  catalogName = 'Phil Test'
  dslParamForm = ''
  dslString = '''def result = runPipeline( pipelineName: \'pipeline_Triggered by Service Catalog\',
			              releaseName:  \'Triggered by Service Catalog\',
            			  projectName:  \'Custom020\'
			            )

// Store the flow runtime id in a property that will be used by endTarget JSON. 
// Make sure that the property path is unique so that multiple users do not overwrite each others values. 

setProperty propertyName: \'/myUser/run_pipeline_flowRuntimeId\', value: result.flowRuntimeId
'''
  endTargetJson = '''{
  "source": "property",
  "object": "flowRuntime",
  "objectId": "$[/myUser/run_pipeline_flowRuntimeId]"
}'''
  iconUrl = 'logo-cloudbees.svg'
  projectName = 'Default'
  useFormalParameter = '1'
}
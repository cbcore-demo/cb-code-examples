var args = {
	flowRuntimeId: "cbbb267f-ca26-11eb-83c4-42010a84005b"
}

var res = api.getPipelineRuntimeDetails(args).flowRuntime[0].stages.stage;
res

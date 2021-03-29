$[/javascript
    var args = {
        projectName: myPipelineRuntime.projectName,
        ciBuildDetailName: Object.keys(myPipelineRuntime.ciBuildDetails)[0]
    }
    var num=api.getCIBuildDetail(args).ciBuildDetailInfo.buildNumber
    api.getCIBuildDetail(args).ciBuildDetailInfo.displayName.replace(' » ','/job/').replace(' #'+num,'')
    ]
$[/javascript
    var args = {
        projectName: myPipelineRuntime.projectName,
        ciBuildDetailName: Object.keys(myPipelineRuntime.ciBuildDetails)[0]
    };
    api.getCIBuildDetail(args).ciBuildDetailInfo.buildNumber;
    ]
# Flow ectool #

## Login ##
cd /opt/electriccloud/electriccommander/bin
./ectool --server 192.168.50.105 login "admin" "changeme"

## Releases ##
### Start Release ###
./ectool startRelease --projectName "My Project" --releaseName "REST release"

## Pipelines ##
### Run Pipeline ###
./ectool runPipeline --projectName "My Project" --releaseName "REST release" --pipelineName "pipeline_REST release"

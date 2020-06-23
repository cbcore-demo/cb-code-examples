# Flow ectool #

## Login ##
cd /opt/electriccloud/electriccommander/bin
./ectool --server 192.168.50.105 login "user" "pwd"

## Releases ##
### Start Release ###
./ectool startRelease --projectName "My Project" --releaseName "REST release"

## Pipelines ##
### Run Pipeline ###
./ectool runPipeline --projectName "My Project" --releaseName "REST release" --pipelineName "pipeline_REST release"
ectool runPipeline Default "pipeline_Run from REST" --releaseName "Run from REST" --actualParameter BuildID=11111

## DSL ##
### evalDsl ###
./ectool evalDsl --dslFile /tmp/newenvdsl.groovy

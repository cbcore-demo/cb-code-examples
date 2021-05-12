# Flow ectool #

## Login ##

```bash
cd /opt/electriccloud/electriccommander/bin
./ectool --server 192.168.50.105 login "user" "pwd"
```
## Releases ##

```bash
# Start Release
./ectool startRelease --projectName "My Project" --releaseName "REST release"
```
## Pipelines ##

```bash
# Run Pipeline
./ectool runPipeline --projectName "My Project" --releaseName "REST release" --pipelineName "pipeline_REST release"
ectool runPipeline Default "pipeline_Run from REST" --releaseName "Run from REST" --actualParameter BuildID=11111
```

## DSL ##

```bash
# evalDsl
./ectool evalDsl --dslFile /tmp/newenvdsl.groovy

# Create Plugin Configuration
# Linux example:
printf 'apassword\n' | ectool runProcedure /plugins/ECSCM-git/project --procedureName CreateConfiguration --actualParameter config=myectoolcreds --actualParameter credentialType=password --actualParameter credential=myectoolcreds --credential myectoolcreds=auser

# Export DSL
ectool generateDsl "/projects/Default" --suppressNulls true --suppressDefaults true  > c:\temp\flow-dsl-exports/2020-07-15-Default-Project.dsl'
```

## Service Catalog Item ##

```bash
# Run Service Catalog Item
ectool runCatalogItem "Electric Cloud" "DSL" "Import DSL" --actualParameter directory="/opt/electriccloud/imports" rsrcName="local"
```
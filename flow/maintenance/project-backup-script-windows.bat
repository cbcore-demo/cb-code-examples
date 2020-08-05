@echo off
REM This script will export the DSL of 1 or more projects
REM
REM You should login to your Flow server before running this script
REM eg ectool --server flowserver.com login "user" "passwrd"
REM
REM Adjust the following array as needed
set projectlist[0]=Custom005
set projectlist[1]=Custom006
set projectlist[2]=Custom007
set projectlist[3]=Custom008
set projectlist[4]=Custom009
set projectlist[5]=Custom010
set projectlist[6]=Custom011
set projectlist[7]=Custom012
set projectlist[8]=Autosys

REM allow for spaces in project names
setlocal enabledelayedexpansion

REM Get web server host name and at the same time check ectool is logged in to a Flow server
echo Checking Flow Server connection
(for /f %%i in ('ectool --timeout 5 getProperty "/server/settings/webServerHost"') do set host=%%i) && (
    echo Success
    (call )
) || (
  echo Not logged in to a Flow server
  exit /B 1
)

REM Echo summary of the export operation
echo Exporting the following projects from server %host%
echo:
set x=0
:SymLoop 

if defined projectlist[%x%] (
   call echo %%projectlist[%x%]%%
   set /a "x+=1"
   GOTO :SymLoop
)

echo:
echo A total of %x% projects
echo:

REM Loop through the array
set /a "x-=1"
for /l %%i in (0,1, %x%)do (
  REM   Echo the project name we are going to export
  echo|set /p= Exporting project !projectlist[%%i]! 
  REM   Export the DSL
  ectool generateDsl "/projects/!projectlist[%%i]!" --suppressNulls true --suppressDefaults true  > !projectlist[%%i]!.dsl
  REM   Show the size and name of the created file
  for %%? in (!projectlist[%%i]!.dsl) do (
      echo generated file %%~n?.dsl with size %%~z?
))
REM Finished
echo:
echo Finished
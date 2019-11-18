# EC-Rest #

## Flow REST API ##
### Get a project ###
#### Configuration ####
Name: LocalFlowServer (admin)
URL Base: https://192.168.50.105/rest/v1.0
Port:
Login as:
- User name: <user>
- Password: <password>

#### Task Parameters ####
Configuration: LocalFlowServer (admin)
Path URL: projects/My%20Project
Content Type: Blank
Headers: Accept: application/json
Content: Blank
Source file: Blank
Request Type: GET
Response: /myTaskRuntime/ResultVal

#### Notes ####

## ServiceNow REST API ##
### Configuration ###
Name: SA ServiceNow
URL Base: https://ven01735.service-now.com
Port:
Login as:
- User name: <user>
- Password:

### Get a CR ###
#### Task Parameters ####
Configuration: SA ServiceNow
Path URL: api/now/table/change_request?sysparm_limit=1
Content Type: Blank
Headers: Blank
Content: Blank
Source file: Blank
Request Type: GET
Response: Blank

#### Notes ####
- If you specify accept of application/json you get a 406 error

### Upload an attachment ###
#### Task Parameters ####
Configuration: SA ServiceNow
Path URL: api/now/attachment/file?table_name=change_request&table_sys_id=51581bfddb0080508156e5b74b961984&file_name=WordDoc1
OR api/now/attachment/file?table_name=change_request&table_sys_id=$[/myStage/CR-table-sys-id]&file_name=$[/myStage/file-name]
Content Type: application/vnd.openxmlformats-officedocument.wordprocessingml.document
Headers: Blank
Content: Blank
Source file: /tmp/WordDoc1.docx OR $[/myStage/file-location]
Request Type: POST
Response: Blank

#### Notes ####

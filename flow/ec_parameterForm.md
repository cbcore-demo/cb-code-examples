<editor>
  <formElement>
    <property>Config</property>
    <label>Configuration</label>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
  </formElement>

  <formElement>
    <property>ContentType</property>
    <label>ContentType</label>
  </formElement>

  <formElement>
    <property>FileName</property>
    <label>FileName</label>
  </formElement>

  <formElement>
    <property>SourceFileLocation</property>
    <label>SourceFileLocation</label>
  </formElement>

  <formElement>
    <property>TableName</property>
    <label>TableName</label>
  </formElement>

  <formElement>
    <property>TableSysId</property>
    <label>TableSysId</label>
  </formElement>
</editor>

Notes:
- set type to entry and use propertyReference to create a drop down that pulls values from somewhere else
- /plugin/EC-Rest/project/rest_cfgs pulls the list of created Configurations from the current EC-Rest plugin

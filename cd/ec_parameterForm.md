```xml
<editor>
  <help>/commander/pages/EC-Jenkins-1.14.1.270/EC-Jenkins_help?s=Administration&amp;ss=Plugins#ShowBuildProcess</help>
  <formElement>
    <property>Config</property>
    <label>Configuration</label>
    <documentation>Select which configuration to use from the EC-Rest plugin</documentation>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

 <formElement>
    <property>TableName</property>
    <label>TableName</label>
    <documentation><p><span style="font-family: Arial;">The name of the <b>table</b> you want to attach the file to. <i>This parameter is required to post an attachment.</i>  See ServiceNow documentation for more detail: <a href="https://developer.servicenow.com/app.do#!/rest_api_doc?v=newyork&amp;id=r_AttachmentAPI-POST" target="_blank">Here</a>.</span></p></documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TableSysId</property>
    <label>TableSysId</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>FileName</property>
    <label>FileName</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>SourceFileLocation</property>
    <label>SourceFileLocation</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>ContentType</property>
    <label>ContentType</label>
    <value>application/json</value>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>

Notes:
- set type to entry and use propertyReference to create a drop down that pulls values from somewhere else
- /plugin/EC-Rest/project/rest_cfgs pulls the list of created Configurations from the current EC-Rest plugin
- For HTML encoding see: http://rabbit.eng.miami.edu/info/htmlchars.html
- Include target="_blank" on href's as some URL's don't seem to open inside a frame within a Flow page

More examples (from https://docs.cloudbees.com/docs/cloudbees-cd/latest/automation-platform/custom-editors):

<editor>
    <formElement>
        <label>One:</label>
        <property>one</property>
        <documentation>The first parameter.</documentation>
        <type>entry</type>
        <value>Test value</value>
    </formElement>
    <formElement>
        <label>Two:</label>
        <property>two</property>
        <documentation>The second parameter.</documentation>
        <type>textarea</type>
        <required>1</required>
    </formElement>
    <formElement>
        <label>Three:</label>
        <property>three</property>
        <documentation>The third parameter.</documentation>
        <type>select</type>
        <option>
            <name>ABC</name>
            <value>abc</value>
        </option>
        <option>
            <name>XYZ</name>
            <value>xyz</value>
        </option>
        <value>xyz</value>
    </formElement>
    <formElement>
        <label>Four:</label>
        <property>four</property>
        <documentation>The fourth parameter.</documentation>
        <type>radio</type>
        <option>
            <name>First Option</name>
            <value>123</value>
        </option>
        <option>
            <name>Second Option</name>
            <value>456</value>
        </option>
        <value>123</value>
    </formElement>
    <formElement>
        <label>Five:</label>
        <property>five</property>
        <documentation>The fifth parameter.</documentation>
        <type>checkbox</type>
        <checkedValue>true</checkedValue>
        <uncheckedValue>false</uncheckedValue>
        <initiallyChecked>1</initiallyChecked>
        <value>true</value>
    </formElement>
    <formElement>
        <label>Six:</label>
        <property>six</property>
        <documentation>The sixth parameter.</documentation>
        <type>project</type>
        <value>myProject</value>
    </formElement>
    <formElement>
        <label>Seven:</label>
        <property>seven</property>
        <documentation>The seventh parameter.</documentation>
        <type>savedSearch</type>
        <value>/projects/myProject/ec_savedSearches/mySavedSearch</value>
    </formElement>
    <formElement>
        <label>Eight:</label>
        <property>eight</property>
        <documentation>The eighth parameter.</documentation>
        <type>credential</type>
    </formElement>
</editor>
```
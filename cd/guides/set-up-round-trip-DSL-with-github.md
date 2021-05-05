# Set Up Round Trip DSL with GitHub #

## Create PAT in GitHub ##

- In GitHub, click on the user menu (top right) and select Settings.
- Go to Settings->Developer Settings-> Personal access tokens.
- Create a Personal Access Token (with scopes such as repo:all, admin:repo_hook, admin:org_hook) and make a note of the token.

## Create plugin config ##

- In CloudBees CD, go to DevOps Essentials->Platform Home Page->Administration->Source Control and click Create Configuration.
- Choose SCM Type 'Git'.
- Enter a Configuration Name (e.g. My GItHub)
- Set User Name to be your username for connecting to GitHub
- Set the Password to be the Personal Access Token generated above.

## Create service account in CD ##

- In CloudBees CD, go to Administration->Configurations->Service Account and create a service account (just needs a name).

## Create pipeline with Event Trigger ##

- In CloudBees CD, go to Release Orchestration->Pipelines (or Releases).
- Create a pipeline as needed.
- From the Run menu (green box with white arrow on the right hand side) select Triggers, then Add to create a new one.
- Give the trigger a name (e.g. My GitHub Trigger)
- Select Plugin EC-Github.
- Select Trigger Type of Webhook.
- Enter a secret in the Secret field.  This can be any string and is used to ensure the webhook source is one you approve.  e.g. (mytriggersecret)
- In Repositories list the org/repo you want to trigger the WebHook (e.g. cloudbees-guru/cd-examples-library).
- Change any remaining settings as needed.
- Click Next.
- Select the Service Account to use (created earlier).
- Click Next, and Next again (unless you need to specify stages or input parameters to the pipeline).
- Copy the webhook URL you are presented with.

## Create WebHook in GitHub ##

- In GitHub, go to the repo you want to trigger the pipeline.  Then go to Settings->Webhooks.
- Add a new webhook.
- For the Payload URL paste in the webhook URL presented in previous section.
- Set Content type to application/json.
- Set Secret to be the secret you entered earlier (e.g. mytriggersecret).
- Click Add Webhook and check the webhook is successfully sent and that your pipeline/release runs.
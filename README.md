# Jenkins Build Task

This plugin exposes a new custom Task type for triggering builds in a jenkins project and awaiting completion of the build. This utilizes the service api token and username to kick off a build along with the job name. Once the build is completed, the contents of the `/api/json` endpoint is pushed into the results chain. This can include information such as the artefact url.

## Installing

First check to make sure the version of Morpheus installed is above or equal to the minimum required version of this plugin and then download the plugin file above.
Once the file is downloaded, browse to the Administration -> Integrations -> Plugins section of the Morpheus appliance. Click the Upload File button to select your plugin and upload it.
The plugin should now be loaded into the environment for use.

## Configuring

Once the plugin is loaded into the environment, a new task type is made available in the Library -> Automation section. This task type is called Jenkins and allows one to enter the relevant parameters to trigger a build. Build parameters are also accepted in form url encoded format and is passed to `buildWithParameters` as is. Some projects don't need these but if necessary they can be filled out.

## Things to be done

It is not yet possible to use morpheus context variables in the build parameters payload. This would be a nice future enhancement for this task type.

package com.morpheusdata.jenkins

import com.morpheusdata.core.Plugin

class JenkinsPlugin extends Plugin {
	@Override
	String getCode() {
		return 'morpheus-jenkins-plugin'
	}

	@Override
	void initialize() {
		 JenkinsTaskProvider jenkinsTaskProvider = new JenkinsTaskProvider(this, morpheus)
		 this.pluginProviders.put("jenkins", jenkinsTaskProvider)
		 this.setName("Jenkins")
	}

	/**
	 * Called when a plugin is being removed from the plugin manager (aka Uninstalled)
	 */
	@Override
	void onDestroy() {
		morpheus.task.disableTask('jenkins').blockingGet()
	}
}
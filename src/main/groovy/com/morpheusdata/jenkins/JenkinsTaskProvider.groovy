package com.morpheusdata.jenkins

import com.morpheusdata.core.AbstractTaskService
import com.morpheusdata.core.ExecutableTaskInterface
import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.core.TaskProvider
import com.morpheusdata.model.Icon
import com.morpheusdata.model.OptionType
import com.morpheusdata.model.Task
import com.morpheusdata.model.TaskResult
import com.morpheusdata.model.TaskType
import com.morpheusdata.model.Workload

class JenkinsTaskProvider implements TaskProvider {
    MorpheusContext morpheusContext
    Plugin plugin
    AbstractTaskService service

    JenkinsTaskProvider(Plugin plugin, MorpheusContext morpheusContext) {
        this.plugin = plugin
        this.morpheusContext = morpheusContext
        this.service = new JenkinsTaskService(morpheus)
    }

    @Override
    TaskType.TaskScope getScope() {
        return TaskType.TaskScope.app
    }

    @Override
    String getDescription() {
        return "Allows the triggering of a Jenkins Pipeline Build and waits for task to complete"
    }

    /**
     * A flag indicating if this task can be configured to execute on a remote context
     * @return boolean
     */
    @Override
    Boolean isAllowExecuteLocal() {
        return true
    }

    /**
     * A flag indicating if this task can be configured to execute on a remote context
     * @return boolean
     */
    @Override
    Boolean isAllowExecuteRemote() {
        return false
    }

    /**
     * A flag indicating if this task can be configured to execute on a resource
     * @return boolean
     */
    @Override
    Boolean isAllowExecuteResource() {
        return false
    }

    /**
     * A flag indicating if this task can be configured to execute a script from a git repository
     * @return boolean
     */
    @Override
    Boolean isAllowLocalRepo() {
        return false
    }

    /**
     * A flag indicating if this task can be configured with ssh keys
     * @return boolean
     */
    @Override
    Boolean isAllowRemoteKeyAuth() {
        return false
    }

    /**
     * A flag indicating if the TaskType presents results that can be chained into other tasks
     * @return
     */
    @Override
    Boolean hasResults() {
        return true
    }

    /**
     * Additional task configuration
     * {@link OptionType}
     * @return a List of OptionType
     */
    @Override
    List<OptionType> getOptionTypes() {
        return [
                new OptionType(code: 'jenkins.serviceUrl', name: 'Service URL', inputType: OptionType.InputType.TEXT, fieldName: 'serviceUrl', fieldLabel: 'API Url', displayOrder: 0),
                new OptionType(code: 'jenkins.serviceUser', name: 'Service Username', inputType: OptionType.InputType.TEXT, fieldName: 'serviceUsername', fieldLabel: 'Username', displayOrder: 2),
                new OptionType(code: 'jenkins.serviceToken', name: 'Service Token', inputType: OptionType.InputType.PASSWORD, fieldName: 'servicePassword', fieldLabel: 'Token', displayOrder: 3),
                new OptionType(code: 'jenkins.jobName', name: 'Job Name', inputType: OptionType.InputType.TEXT, fieldName: 'jobName', fieldLabel: 'Job Name', displayOrder: 4),
                new OptionType(code: 'jenkins.buildParameters', name: 'Build Parameters', inputType: OptionType.InputType.CODE_EDITOR, fieldName: 'buildParameters', fieldLabel: 'Build Parameters', displayOrder: 5),

        ]
    }

    /**
     * Returns the Morpheus Context for interacting with data stored in the Main Morpheus Application
     *
     * @return an implementation of the MorpheusContext for running Future based rxJava queries
     */
    @Override
    MorpheusContext getMorpheus() {
        return morpheusContext
    }

    /**
     * Returns the instance of the Plugin class that this provider is loaded from
     * @return Plugin class contains references to other providers
     */
    @Override
    Plugin getPlugin() {
        return plugin
    }

    /**
     * A unique shortcode used for referencing the provided provider. Make sure this is going to be unique as any data
     * that is seeded or generated related to this provider will reference it by this code.
     * @return short code string that should be unique across all other plugin implementations.
     */
    @Override
    String getCode() {
        return 'jenkins'
    }

    /**
     * Provides the provider name for reference when adding to the Morpheus Orchestrator
     * NOTE: This may be useful to set as an i18n key for UI reference and localization support.
     *
     * @return either an English name of a Provider or an i18n based key that can be scanned for in a properties file.
     */
    @Override
    String getName() {
        return 'Jenkins Trigger Build'
    }

    /**
     * Returns the Task Type Icon for display when a user is browsing tasks
     * @since 0.12.7
     * @return Icon representation of assets stored in the src/assets of the project.
     */
    @Override
    Icon getIcon() {
        return new Icon(path:"jenkins-black.svg", darkPath: "jenkins-white.svg")
    }


	/**
	 * empty implementation until fix in core to provide default implementation
	 */
	TaskResult executeContainerTask(Workload workload, Task task, Map opts) {
		return null;
	}
}

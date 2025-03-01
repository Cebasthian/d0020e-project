package org.example.edc.extension;

import org.eclipse.edc.connector.controlplane.contract.spi.policy.ContractNegotiationPolicyContext;
import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.policy.engine.spi.RuleBindingRegistry;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

import org.eclipse.edc.spi.monitor.Monitor;

public class PolicyExtension implements ServiceExtension{

    @Inject
    private RuleBindingRegistry rulebindingregistry;

    @Inject
    private PolicyEngine policyengine;

    @Inject
    private Monitor monitor;


    public void initialize(ServiceExtensionContext context){

        String bindingkey = "data-transfer process";
        String ruleID = "negotiation-scope";

        rulebindingregistry.bind(bindingkey, ruleID);

        policyengine.registerFunction(ContractNegotiationPolicyContext.class, Permission.class, bindingkey, new AllowedIDConstraint(monitor));
    }
}
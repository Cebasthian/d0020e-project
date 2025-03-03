package org.example.edc.extension;

import org.eclipse.edc.connector.controlplane.catalog.spi.policy.CatalogPolicyContext;
import org.eclipse.edc.connector.controlplane.contract.spi.policy.ContractNegotiationPolicyContext;
import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.policy.engine.spi.RuleBindingRegistry;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

import static org.eclipse.edc.connector.controlplane.catalog.spi.policy.CatalogPolicyContext.CATALOG_SCOPE;
import static org.eclipse.edc.jsonld.spi.PropertyAndTypeNames.ODRL_USE_ACTION_ATTRIBUTE;
import static org.eclipse.edc.policy.engine.spi.PolicyEngine.ALL_SCOPES;
import static org.eclipse.edc.spi.constants.CoreConstants.EDC_NAMESPACE;

public class PolicyExtension implements ServiceExtension{

    private static final String IDENTITY_CONSTRAINT_KEY = EDC_NAMESPACE + "identity";

    @Inject
    private RuleBindingRegistry rulebindingregistry;

    @Inject
    private PolicyEngine policyengine;


    @Override
    public void initialize(ServiceExtensionContext context){
        rulebindingregistry.bind(ODRL_USE_ACTION_ATTRIBUTE, ALL_SCOPES);
        rulebindingregistry.bind(IDENTITY_CONSTRAINT_KEY, CATALOG_SCOPE);
        policyengine.registerFunction(CatalogPolicyContext.class, Permission.class, IDENTITY_CONSTRAINT_KEY, new AllowedIDConstraint(context.getMonitor()));
    }
}
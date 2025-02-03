package com.example.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;

@Component
public class PolicyEvaluator {
    private final Map<String, PolicyRule> policyMap = new HashMap<>();

    public PolicyEvaluator() throws IOException {
        loadPolicies();
    }

    private void loadPolicies() throws IOException {                   //loads resource, roles and conditions into policyMap
        ObjectMapper objectMapper = new ObjectMapper();                //used to convert java objects -> JSON
        JsonNode root = objectMapper.readTree(new                      // parses JSON file into JsonNode
                ClassPathResource("policies.json").getInputStream());  //loads & reads policies from classpath


        for (JsonNode policyNode : root.get("policies")) {             //loops each element in policies array
            String resource = policyNode.get("resource").asText();     //save resource-field from policyNode in string
            List<String> roles = Arrays.asList(objectMapper.           //extracts JSON array, converts to string and
                    convertValue(policyNode.get("allowed_roles"), String[].class));     //transforms -> List<string>
            JsonNode conditions = policyNode.get("conditions");

            policyMap.put(resource, new PolicyRule(roles, conditions));//Stores a PolicyRule object in policyMap, using resource as the key
        }
    }

    public boolean isAccessAllowed(String resource, String userRole, Map<String, String> requestContext) {
        PolicyRule policy = policyMap.get(resource);                   //extract policyMap from resource
        if (policy == null) return false;                              //if no policy

        // Role-based check
        if (!policy.allowedRoles.contains(userRole)) return false;     //if role is not allowed

        // Condition checks
        return policy.checkConditions(requestContext);
    }
}
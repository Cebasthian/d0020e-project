package org.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DppService {

    @Autowired
    private EdcConnector edcConnector;
    @Autowired
    private InstructionService instructionService;

    public void getDppData(String dppId) {
        // get data from edcconnector

        // get recycle instructions from database depending on the materials from dpp data.

        // merge and return the data and instructions
    }
}

package com.meli.cms.service;

import com.meli.cms.carrier.BranchAddCarrier;
import com.meli.cms.entity.Branch;
import com.meli.cms.repository.BranchRepository;

import java.util.UUID;


public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch addBranch(BranchAddCarrier branchAddCarrier) {
        return branchRepository.save(new Branch(branchAddCarrier.getLocation()));

    }

    public Branch getBranch(UUID branchId) {
        return branchRepository.findByBranchId(branchId);
    }
}
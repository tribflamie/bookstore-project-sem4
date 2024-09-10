package com.backend.bookstore.mappers.impls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bookstore.mappers.DashboardMapper;
import com.backend.bookstore.services.DashboardService;

@RestController
public class DashboardMapperImpl implements DashboardMapper{

    @Autowired
    DashboardService dashboardService;

    @Override
    public ResponseEntity<Map<String, Object>> getDetails() {
        return dashboardService.getDetails();
    }
    
}

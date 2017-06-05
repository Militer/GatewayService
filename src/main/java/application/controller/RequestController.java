package application.controller;

import application.filter.PerformancePostFilter;
import application.model.RequestData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: militer
 * Date: 03.06.2017.
 */
@RestController
@RequestMapping("/request-monitor")
public class RequestController {
    @GetMapping
    public List<RequestData> getRequestsData(){
        return PerformancePostFilter.getRequests();
    }
}

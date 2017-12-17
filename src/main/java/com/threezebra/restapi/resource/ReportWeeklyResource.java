package com.threezebra.restapi.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.threezebra.domain.report.ReportWeek;
import com.threezebra.restapi.controller.ReportWeekRestController;
import com.threezebra.restapi.resource.report.ReportWeekResource;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


/**
 * @author vikas.sharma
 *
 */
public class ReportWeeklyResource extends ResourceSupport {

    private Collection<ReportWeekResource> reportWeeks;

    public ReportWeeklyResource(Collection<ReportWeek> reportWeeks, String userName) {

        this.reportWeeks = reportWeeks.stream()
                .map(
                        reportWeek -> new ReportWeekResource(reportWeek, userName)
                ).collect(Collectors.toList());

        add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(ReportWeekRestController.class, userName).getWeeklyReport(userName)
        ).withSelfRel());
    }

    public Collection<ReportWeekResource> getReportWeeks() {
        return reportWeeks;
    }
}

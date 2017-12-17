package com.threezebra.repository.interceptor;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.threezebra.common.DateUtils;
import com.threezebra.domain.TimeEntry;


/**
 * @author vikas.sharma
 *
 */
@Component
public class TimeEntryBeforeConvertListener extends AbstractMongoEventListener<TimeEntry> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<TimeEntry> event) {

        TimeEntry timeEntry = event.getSource();

        timeEntry.setWeek(
                DateUtils.getWeek(timeEntry.getDate())
        );

    }





}
